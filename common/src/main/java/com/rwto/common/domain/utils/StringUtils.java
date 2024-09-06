package com.rwto.common.domain.utils;

/**
 * @author renmw
 * @create 2024/9/6 11:43
 **/
public class StringUtils {

    public static boolean  isEmpty(String str) {
        return str == null || "".equals(str);
    }


    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}