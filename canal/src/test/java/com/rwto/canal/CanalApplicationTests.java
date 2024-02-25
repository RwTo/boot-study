package com.rwto.canal;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.InetSocketAddress;
import java.util.List;

@SpringBootTest
class CanalApplicationTests {
    @Value("${canal.server.address}")
    private String hostName;

    @Test
    void CanalClient() throws InvalidProtocolBufferException {
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



    @Test
    void SimpleCanalClientExample() {
        // 创建链接
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(hostName,
                11111), "example", "", "");
        int batchSize = 1000;
        int emptyCount = 0;
        try {
            connector.connect();
            connector.subscribe(".*\\..*");
            connector.rollback();
            int totalEmptyCount = 120;
            while (emptyCount < totalEmptyCount) {
                Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
                long batchId = message.getId();
                int size = message.getEntries().size();
                if (batchId == -1 || size == 0) {
                    emptyCount++;
                    System.out.println("empty count : " + emptyCount);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                } else {
                    emptyCount = 0;
                    // System.out.printf("message[batchId=%s,size=%s] \n", batchId, size);
                    printEntry(message.getEntries());
                }

                connector.ack(batchId); // 提交确认
                // connector.rollback(batchId); // 处理失败, 回滚数据
            }

            System.out.println("empty too many times, exit");
        } finally {
            connector.disconnect();
        }
    }

    private static void printEntry(List<CanalEntry.Entry> entrys) {
        for (CanalEntry.Entry entry : entrys) {
            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN || entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
                continue;
            }

            CanalEntry.RowChange rowChage = null;
            try {
                rowChage = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                        e);
            }

            CanalEntry.EventType eventType = rowChage.getEventType();
            System.out.println(String.format("================&gt; binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                    eventType));

            for (CanalEntry.RowData rowData : rowChage.getRowDatasList()) {
                if (eventType == CanalEntry.EventType.DELETE) {
                    printColumn(rowData.getBeforeColumnsList());
                } else if (eventType == CanalEntry.EventType.INSERT) {
                    printColumn(rowData.getAfterColumnsList());
                } else {
                    System.out.println("-------&gt; before");
                    printColumn(rowData.getBeforeColumnsList());
                    System.out.println("-------&gt; after");
                    printColumn(rowData.getAfterColumnsList());
                }
            }
        }
    }

    private static void printColumn(List<CanalEntry.Column> columns) {
        for (CanalEntry.Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
        }
    }

}
