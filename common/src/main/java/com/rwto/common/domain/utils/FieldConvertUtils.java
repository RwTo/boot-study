package com.rwto.common.domain.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author renmw
 * @create 2024/9/6 11:43
 **/
public class FieldConvertUtils {
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /*下划线转驼峰*/
    public static String snake2Camel(String field){
        //field = field.toLowerCase();
        Matcher matcher = linePattern.matcher(field);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String camel2Snake(String field){
        Matcher matcher = humpPattern.matcher(field);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
