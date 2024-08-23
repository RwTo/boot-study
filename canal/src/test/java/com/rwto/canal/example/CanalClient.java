package com.rwto.canal.example;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.beans.factory.annotation.Value;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author: Eternity.麒麟
 * @description: canal简单使用
 * @date: 2023/2/3 17:32
 * @version: 1.0
 */
public class CanalClient {

    @Value("${canal.server.address}")
    private static String hostName;

    public static void main(String[] args) throws InvalidProtocolBufferException {
        // 连接canal服务器
        CanalConnector connector = CanalConnectors.newSingleConnector(
                new InetSocketAddress(hostName, 11111),
                "example",
                "",
                "");
        System.out.println("开始监听......");
        while (true) {
            // 连接
            connector.connect();
            // 订阅的库和表
            connector.subscribe("canal.*");
            // 一次拉取的数据量
            Message message = connector.get(10);
            List<CanalEntry.Entry> entries = message.getEntries();
            if (!entries.isEmpty()) {
                for (CanalEntry.Entry entry : entries) {
                    // 表名
                    String tableName = entry.getHeader().getTableName();
                    // 类型
                    CanalEntry.EntryType type = entry.getEntryType();
                    switch (type) {
                        // 数据变更
                        case ROWDATA :{
                            // 获取数据
                            ByteString storeValue = entry.getStoreValue();
                            // 解析数据
                            CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(storeValue);
                            // 事件类型
                            CanalEntry.EventType eventType = rowChange.getEventType();
                            // 获取行数据
                            List<CanalEntry.RowData> datasList = rowChange.getRowDatasList();
                            for (CanalEntry.RowData rowData : datasList) {
                                JSONObject beforeData = new JSONObject();
                                // 变更之前数据
                                rowData.getBeforeColumnsList().forEach(item -> beforeData.put(item.getName(), item.getValue()));
                                JSONObject afterData = new JSONObject();
                                // 变更之后数据
                                rowData.getAfterColumnsList().forEach(item -> afterData.put(item.getName(), item.getValue()));
                                System.out.println("表名: " + tableName + ", 事件类型: " + eventType + ", 变更之前: " + beforeData + ", 变更之后: " + afterData);
                            }
                        }
                        default : System.out.println("当前操作类型为：" + type);
                    }
                }
            }
        }
    }
}
