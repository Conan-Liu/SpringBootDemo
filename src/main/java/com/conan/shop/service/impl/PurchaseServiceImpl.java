package com.conan.shop.service.impl;

import com.conan.shop.dao.ProductMapper;
import com.conan.shop.dao.PurchaseRecordMapper;
import com.conan.shop.pojo.Product;
import com.conan.shop.pojo.PurchaseRecord;
import com.conan.shop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private PurchaseRecordMapper purchaseRecordMapper;

    @Override
    // 事务隔离机制的串行化Isolation.SERIALIZABLE，大大减弱吞吐量，还可能造成数据库的死锁，尽量不用，可以考虑sql语句的悲观锁串行化
    // 配合乐观锁CAS的version机制，需要将隔离机制设置为读写提交Isolation.READ_COMMITTED
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean purchase(Integer userId, Integer productId, Integer quantity) {
        // 获取要购买的产品
        Product product = productMapper.getProduct(productId);
        // 比较该产品库存和购买数量
        if (product.getStock() < quantity) {
            // 库存不足
            return false;
        }

        /**
         * 乐观锁的版本version处理机制
         */
        int version = product.getVersion();

        // 扣减库存，带上version发送给后台比较
        int result = productMapper.decreaseProduct(productId, quantity, version);

        // 更新数据失败，说明数据被其它线程修改，导致失败返回，这种情况也属于抢购失败
        // 运气真不好，没想到速度跟上，但是和别人冲突了，反而没抢到
        if (result == 0) {
            return false;
        }

        // 初始化购买记录
        PurchaseRecord record = this.initPurchaseRecord(userId, product, quantity);
        purchaseRecordMapper.insertPurchaseRecord(record);
        return true;
    }

    @Override
    // 因为高并发环境下，乐观锁可能因为版本version机制，造成大部分都是失败的请求
    // 可以考虑可重入机制，利用循环实现CAS，反复比对，然后更新数据库，实现抢购
    // 重入机制，兼顾了数据准确性和效率
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean purchaseReentrant(Integer userId, Integer productId, Integer quantity) {
        // 不能无限次的重入，这样徒增数据库的压力，可重入个3次，增加抢购成功几率即可
        for (int i = 0; i < 3; i++) {
            // 获取要购买的产品
            Product product = productMapper.getProduct(productId);
            // 比较该产品库存和购买数量
            if (product.getStock() < quantity) {
                // 库存不足
                return false;
            }

            /**
             * 乐观锁的版本version处理机制
             */
            int version = product.getVersion();

            // 扣减库存，带上version发送给后台比较
            int result = productMapper.decreaseProduct(productId, quantity, version);

            // 运气不好，更新数据失败，重试一次
            if (result == 0) {
                continue;
            }

            // 初始化购买记录
            PurchaseRecord record = this.initPurchaseRecord(userId, product, quantity);
            purchaseRecordMapper.insertPurchaseRecord(record);
            return true;
        }
        // 重入次数达到3次，还是没有更新，则算作抢购失败
        return false;
    }

    private PurchaseRecord initPurchaseRecord(Integer userId, Product product, int quantity) {
        PurchaseRecord record = new PurchaseRecord();
        record.setNote("购买日志，时间: " + System.currentTimeMillis());
        record.setPrice(product.getPrice());
        record.setProductId(product.getId());
        record.setQuantity(quantity);
        double sum = product.getPrice() * quantity;
        record.setSum(sum);
        record.setUserId(userId);
        return record;
    }
}
