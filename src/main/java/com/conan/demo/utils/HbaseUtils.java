package com.conan.demo.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Conan on 2019/5/3.
 * HBase 操作工具类
 *
 * TODO... 待改善的的是， 改成连接池， 会快点
 */
public class HbaseUtils {

    private static Admin admin = null;
    private static Configuration conf = null;
    private static HbaseUtils hbaseUtils = null;
    private static Connection connection = null;

    /**
     * 单例类， 私有构造方法
     */
    private HbaseUtils() {
        conf = HBaseConfiguration.create();
        ;
        conf.set("hbase.zookeeper.quorum", "CentOS");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.rootdir", "hdfs://CentOS:8020/hbase");
    }

    public static Connection getConnection() throws IOException {
        if (connection == null) {
            connection = ConnectionFactory.createConnection(conf);
        }
        return connection;
    }

    public static Admin getHbaseAdmin() throws IOException {
        if (admin == null) {
            admin = getConnection().getAdmin();
        }
        return admin;
    }

    public static synchronized HbaseUtils getInstance() {
        if (hbaseUtils == null) {
            hbaseUtils = new HbaseUtils();
        }
        return hbaseUtils;
    }

    /**
     * 获取hbase的表名
     *
     * @param tableName
     * @return
     */
    public HTable getTable(String tableName) {
        HTable table = null;
        try {
            table = (HTable) getConnection().getTable(TableName.valueOf(tableName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return table;
    }

    /**
     * 添加记录到Hbase
     *
     * @param tableName
     * @param rowKey
     * @param familyName
     * @param columnName
     * @param value
     */
    public void put(String tableName, String rowKey, String familyName, String columnName, String value) {
        HTable table = getTable(tableName);
        Put put = new Put(Bytes.toBytes(rowKey));
        put.add(Bytes.toBytes(familyName), Bytes.toBytes(columnName), Bytes.toBytes(value));
        try {
            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Long> query(String tableName, String condition) throws IOException {
        Map<String, Long> map = new HashMap<>();

        HTable table = getTable(tableName);
        String familyName = "info";
        String qualifierName = "click_count";

        Scan scan = new Scan();
        Filter filter = new PrefixFilter(Bytes.toBytes(condition));
        scan.setFilter(filter);

        ResultScanner rs = table.getScanner(scan);
        for (Result result : rs) {
            String row = Bytes.toString(result.getRow());
            long clickCouunt = Bytes.toLong(result.getValue(Bytes.toBytes(familyName), Bytes.toBytes(qualifierName)));
            map.put(row, clickCouunt);
        }
        return map;
    }

    public static void main(String[] args) throws IOException {
        Map<String, Long> map = HbaseUtils.getInstance().query("course_clickcount", "20190503");

        for (Map.Entry<String, Long> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}