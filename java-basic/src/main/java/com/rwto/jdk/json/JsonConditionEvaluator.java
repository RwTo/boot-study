package com.rwto.jdk.json;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONPath;

public class JsonConditionEvaluator {


    public static boolean evaluate(String condition, String jsonStr) {
        JSONObject json = JSONObject.parseObject(jsonStr);
        return evaluate(condition, json);
    }

    public static boolean evaluate(String condition, JSONObject json) {
        condition = condition.trim();
        if (condition.isEmpty()) {
            return true;
        }

        // 处理括号包围的表达式
        if (condition.startsWith("(") && condition.endsWith(")")) {
            return evaluate(condition.substring(1, condition.length() - 1), json);
        }

        // 检查顶层的逻辑或运算符 ||
        int orIndex = findTopLevelOperator(condition, "||");
        if (orIndex != -1) {
            String left = condition.substring(0, orIndex).trim();
            String right = condition.substring(orIndex + 2).trim();
            return evaluate(left, json) || evaluate(right, json);
        }

        // 检查顶层的逻辑与运算符 &
        int andIndex = findTopLevelOperator(condition, "&");
        if (andIndex != -1) {
            String left = condition.substring(0, andIndex).trim();
            String right = condition.substring(andIndex + 1).trim();
            return evaluate(left, json) && evaluate(right, json);
        }

        // 处理原子条件
        return evaluateAtomicCondition(condition, json);
    }

    private static int findTopLevelOperator(String expression, String operator) {
        int bracketCount = 0;
        int opLength = operator.length();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(') {
                bracketCount++;
            } else if (c == ')') {
                bracketCount--;
            }

            // 检查当前是否在顶层且匹配运算符
            if (bracketCount == 0 && i + opLength <= expression.length()) {
                String sub = expression.substring(i, i + opLength);
                if (sub.equals(operator)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private static boolean evaluateAtomicCondition(String condition, JSONObject json) {
        String[] parts = condition.split("=", 2);
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid atomic condition: " + condition);
        }

        String path = parts[0].trim();
        String expectedValue = parts[1].trim();

        Object actualValue = JSONPath.eval(json, path);
        if (actualValue == null) {
            return false;
        }

        return expectedValue.equals(actualValue.toString());
    }
}