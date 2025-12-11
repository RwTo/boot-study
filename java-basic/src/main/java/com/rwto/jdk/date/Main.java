package com.rwto.jdk.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;

/**
 * @author renmw
 * @create 2025/2/22 16:19
 **/
public class Main {

//    public static void main(String[] args) {
//        String dateTimeStr = "Sun Dec 01 00:00:00 CST 2024";
//        String sourcePattern = "EEE MMM dd HH:mm:ss z yyyy";
//        String targetPattern = "yyyy-MM-dd HH:mm:ss";
//
//        // 假设CST代表Asia/Shanghai时区
//        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
//
//        // 创建一个DateTimeFormatter来解析原始字符串
//        DateTimeFormatter sourceFormatter = DateTimeFormatter.ofPattern(sourcePattern, Locale.US);
//                ;
//
//        try {
//            // 解析原始字符串为ZonedDateTime对象
//            ZonedDateTime parsedDateTime = ZonedDateTime.parse(dateTimeStr, sourceFormatter);
//
//            // 创建一个DateTimeFormatter来格式化日期时间为所需的格式
//            DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern(targetPattern);
//
//            // 格式化ZonedDateTime对象为字符串
//            String formattedDateTimeStr = parsedDateTime.format(targetFormatter);
//
//            // 输出结果
//            System.out.println("Formatted date and time: " + formattedDateTimeStr);
//        } catch (DateTimeParseException e) {
//            System.err.println("Error parsing date-time string: " + e.getMessage());
//        }
//    }

//    public static void main(String[] args) throws ParseException {
//        String dateString = "Sun Dec 01 00:00:00 CST 2024";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy").withZone(ZoneId.systemDefault());
////        ZonedDateTime parse = ZonedDateTime.parse(dateString, formatter);
////        System.out.println(parse);
//        System.out.println(ZonedDateTime.now(ZoneId.of("Asia/Shanghai")).format(formatter));
//        System.out.println(ZonedDateTime.now(ZoneId.systemDefault()).format(formatter));
//
//    }

    private final static DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss",Locale.US).withZone(ZoneId.systemDefault());

    public static void main(String[] args) throws ParseException {
        String arg0 = "2023-12-04";
        String arg1 = "yyyy-MM-dd";
        String arg2 = "yyyy-MM-dd HH:mm:ss";
        String dateTime = String.valueOf(arg0);
        DateTimeFormatter sourceFormat = DateTimeFormatter.ofPattern(String.valueOf(arg1), Locale.US).withZone(ZoneId.systemDefault());
        LocalDateTime source = LocalDateTime.parse(dateTime, sourceFormat);
        DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern(String.valueOf(arg2),Locale.US).withZone(ZoneId.systemDefault());;
        System.out.println(source.format(targetFormat));
    }
}
