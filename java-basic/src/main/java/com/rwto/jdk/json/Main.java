package com.rwto.jdk.json;

import com.alibaba.fastjson2.JSONObject;

/**
 * @author renmw
 * @since 2025/2/26 17:24
 **/
public class Main {

    public static void main(String[] args) {
        String jsonStr = "{\"code\":\"000200\",\"content\":{\"returnCode\":\"BFSUCCESS001\"}}";


        String condition = "code=000200 & (content.returnCode=BFSUCCESS001 || content.returnCode=BFFAIL002)";
        boolean result = JsonConditionEvaluator.evaluate(condition, jsonStr);
        System.out.println(result); // 输出 true
    }
}
