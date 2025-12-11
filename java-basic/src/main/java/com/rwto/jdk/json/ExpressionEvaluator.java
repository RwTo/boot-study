package com.rwto.jdk.json;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionEvaluator {

    /**
     * 计算 JSON 和 表达式的匹配结果
     *
     * @param jsonStr   JSON 字符串
     * @param expression 表达式
     * @return 计算结果
     */
    public static boolean evaluate(String jsonStr, String expression) {
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        String processedExpression = processExpression(expression, jsonObject);
        return evaluateBooleanExpression(processedExpression);
    }

    /**
     * 解析表达式，将变量替换为实际值
     */
    private static String processExpression(String expression, JSONObject jsonObject) {
        Pattern pattern = Pattern.compile("([a-zA-Z0-9_.]+)=([a-zA-Z0-9_]+)");
        Matcher matcher = pattern.matcher(expression);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String key = matcher.group(1);
            String expectedValue = matcher.group(2);
            Object actualValue = getJsonValue(jsonObject, key);

            // 替换成布尔值
            boolean result = expectedValue.equals(actualValue);
            matcher.appendReplacement(sb, result ? "true" : "false");
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 根据 JSON 路径获取对应的值
     */
    private static Object getJsonValue(JSONObject jsonObject, String key) {
        String[] keys = key.split("\\.");
        Object value = jsonObject;
        for (String k : keys) {
            if (value instanceof JSONObject) {
                value = ((JSONObject) value).get(k);
            } else {
                return null;
            }
        }
        return value;
    }

    /**
     * 计算布尔表达式的值
     */
    private static boolean evaluateBooleanExpression(String expression) {
        Stack<Boolean> values = new Stack<>();
        Stack<Character> operators = new Stack<>();
        char[] tokens = expression.toCharArray();
        int n = tokens.length;

        for (int i = 0; i < n; i++) {
            char c = tokens[i];

            if (c == ' ') {
                continue;
            } else if (c == 't' || c == 'f') { // 解析 true / false
                boolean val = (c == 't');
                values.push(val);
                i += val ? 3 : 4; // 跳过 "true" 或 "false"
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    applyOperator(values, operators.pop());
                }
                operators.pop(); // 弹出 '('
            } else if (c == '&' || c == '|') {
                if (c == '|' && i + 1 < n && tokens[i + 1] == '|') {
                    i++;
                } else if (c == '&' && i + 1 < n && tokens[i + 1] == '&') {
                    i++;
                }
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(c)) {
                    applyOperator(values, operators.pop());
                }
                operators.push(c);
            }
        }

        while (!operators.isEmpty()) {
            applyOperator(values, operators.pop());
        }

        return values.pop();
    }

    /**
     * 应用运算符
     */
    private static void applyOperator(Stack<Boolean> values, char op) {
        boolean b = values.pop();
        boolean a = values.pop();
        if (op == '&') {
            values.push(a && b);
        } else if (op == '|') {
            values.push(a || b);
        }
    }

    /**
     * 运算符优先级
     */
    private static int precedence(char op) {
        return (op == '&') ? 2 : (op == '|') ? 1 : 0;
    }

    public static void main(String[] args) {
        String json = "{\"code\":\"000200\",\"content\":{\"returnCode\":\"BFFAIL002\"}}";
        String expression = "code=000200&content.returnCode=BFFAIL002";
        System.out.println(evaluate(json, expression)); // 输出：true
    }
}
