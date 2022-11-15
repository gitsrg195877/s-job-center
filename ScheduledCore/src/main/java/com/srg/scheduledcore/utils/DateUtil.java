package com.srg.scheduledcore.utils;

import java.text.DateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author: EQ-SRG
 * @create: 2022/10/2
 * @description: Date 工具类
 **/
public class DateUtil {

    public static Date getNowTime(){
        Date date = new Date();
        return date;
    }


    public static String dateFormat(ZonedDateTime zonedDateTime){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(zonedDateTime);
    }
}
