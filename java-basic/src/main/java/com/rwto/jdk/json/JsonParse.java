package com.rwto.jdk.json;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.jsonpath.JsonPath;


import java.util.List;
import java.util.Map;

/**
 * @author renmw
 * @create 2025/2/12 16:48
 **/
public class JsonParse {

    public static void main(String[] args) {
        String s = updateJson("{\"dataSource\": \"\",\"content\":{\"name\":\"1\"}}", "content1.start", "1");
        System.out.println(s);

        String json = "{\n" +
                "    \"code\": \"000200\",\n" +
                "    \"message\": \"业务通讯成功\",\n" +
                "    \"dataSource\": \"S004\",\n" +
                "    \"content\": {\n" +
                "        \"returnCode\": \"业务通讯成功\",\n" +
                "        \"returnMessage\": \"\",\n" +
                "        \"details\": [\n" +
                "            {\n" +
                "                \"branchBankCode\": \"102100000312\",\n" +
                "                \"bankCode\": \"02\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t {\n" +
                "                \"branchBankCode\": \"123\",\n" +
                "                \"bankCode\": \"03\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t {\n" +
                "                \"branchBankCode\": \"2333\",\n" +
                "                \"bankCode\": \"04\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t\t]\n" +
                "\t\t\t\t}\n" +
                "\t\t\t\t}";

        String path = "$.content.details";
        List<Map<String, Object>> maps = extractJsonArray(json, path);
        System.out.println(maps);
    }

    public static List<Map<String, Object>> extractJsonArray(String json, String path) {
        try { 
            Object result = JsonPath.read(json, path);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.convertValue(result, new TypeReference<List<Map<String, Object>>>() {});
        } catch (Exception e) {
            System.err.println("解析 JSON 失败: " + e.getMessage());
            return null;
        }
    }

    public static String updateJson(String json, String key, String value) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);

            String[] keyParts = key.split("\\.");
            JsonNode currentNode = rootNode;
            ObjectNode parentNode = (ObjectNode) rootNode;

            for (int i = 0; i < keyParts.length - 1; i++) {
                JsonNode nextNode = currentNode.path(keyParts[i]);
                if (nextNode.isMissingNode() || !nextNode.isObject()) {
                    nextNode = objectMapper.createObjectNode();
                    ((ObjectNode) currentNode).set(keyParts[i], nextNode);
                }
                parentNode = (ObjectNode) nextNode;
                currentNode = nextNode;
            }

            parentNode.put(keyParts[keyParts.length - 1], value);

            return objectMapper.writeValueAsString(rootNode);
        } catch (Exception e) {
            return "Error processing JSON: " + e.getMessage();
        }
    }

}
