package com.rwto.jdk.json;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONPath;
import com.alibaba.fastjson2.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author renmw
 * @create 2025/2/12 13:47
 **/
@Slf4j
public class JsonUtil {

    public static void main(String[] args) {
        String json = "{}";

        JSONObject jsonObject = JSON.parseObject(json);
        Map<String, String> map = new HashMap<>();
        map.put("name.nam", "zhangsan");

        System.out.println(updateJson(json, map));
    }

    /**
     * 根据给定的表达式判断JSON数据
     * @param json JSON字符串
     * @param expression 表达式，格式为 "path=value"
     * @return 如果表达式成立返回 true，否则返回 false
     */
    public static boolean executeJudgeExpression(String json, String expression) {
        String[] expressions = expression.split("&");
        boolean ans = true;
        for (String expr : expressions) {
            String[] split = expr.split("=");
            if (split.length != 2) {
                log.error("expression 不合法！ json: {} expression: {}", json, expr);
                throw new RuntimeException();
            }

            try {
                JSONObject rootNode = JSON.parseObject(json);

                String path = split[0].trim();
                String expectedValue = split[1].trim();

                Object result = JSONPath.eval(rootNode, "$." + path);
                String resultStr = (result != null) ? result.toString() : null;
                ans &= expectedValue.equals(resultStr);
            } catch (Exception e) {
                log.error("executeJudgeExpression error！ json: {} expression: {}", json, expr, e);
                throw new RuntimeException("解析 judgeExpression 失败！");
            }
        }

        return ans;
    }


    /**
     * 更新json内容
     * @param json
     * @param map path->value
     * @return
     * @throws Exception
     */
    public static String updateJson(String json, Map<String, String> map){
        if(CollectionUtils.isEmpty(map)){
            return json;
        }
        JSONObject rootNode = JSON.parseObject(json);

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            JSONPath.set(rootNode, "$." + key, value);
        }

        return rootNode.toString();
    }


    /**
     * 获取json路径的值
     * @param json
     * @param path
     * @param typeReference
     * @return
     * @param <T>
     */
    public static <T> T jsonPathValue(String json, String path, TypeReference<T> typeReference) {
        try {
            JSONObject jsonObject = JSON.parseObject(json);
            Object result = JSONPath.eval(jsonObject, "$." + path);
            return JSON.parseObject(JSON.toJSONString(result), typeReference);
        } catch (Exception e) {
            return null;
        }
    }

}
