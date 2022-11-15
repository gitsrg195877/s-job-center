package com.srg.scheduledcore.utils;

import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Optional;

import static com.cronutils.model.CronType.QUARTZ;

/**
 * @author: EQ-SRG
 * @create: 2022/10/2
 * @description: cron 工具类
 **/
public class CronUtil {

    public static CronParser parser;

    static {
        parser = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(QUARTZ));
    }


    /**
     * 解析cron表达式，针对当前的时间，得到距离下次执行还需多少时间
     **/
    public static Long getTimeToNextExecution(String cron) {
        ExecutionTime executionTime = ExecutionTime.forCron(parser.parse(cron));
        Optional<Duration> duration = executionTime.timeToNextExecution(ZonedDateTime.now());
        return duration.get().toMillis();
    }


    /**
     * 解析cron表达式，得到cron下次执行的时间戳
     **/
    public static String getNextExecution(String cron) {
        ExecutionTime executionTime = ExecutionTime.forCron(parser.parse(cron));
        Optional<ZonedDateTime> zonedDateTime = executionTime.nextExecution(ZonedDateTime.now());
        return DateUtil.dateFormat(zonedDateTime.get());
    }

    public static String getCron(String cronFromForm) {
        return null;
    }

}
