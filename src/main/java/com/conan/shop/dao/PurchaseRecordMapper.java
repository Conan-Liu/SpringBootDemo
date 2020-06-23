package com.conan.shop.dao;

import com.conan.shop.pojo.PurchaseRecord;

public interface PurchaseRecordMapper {

    // 传递参数直接是Pojo对象，xml中直接使用变量名称
    int insertPurchaseRecord(PurchaseRecord record);
}
