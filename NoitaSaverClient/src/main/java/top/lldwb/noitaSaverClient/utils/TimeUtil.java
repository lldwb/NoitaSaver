package top.lldwb.noitaSaverClient.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    public static String currentTime(){
        // 自定义时间格式
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        // 返回根据自定义时间格式生成的字符串
        return dateTimeFormatter.format(LocalDateTime.now());
    }
}
