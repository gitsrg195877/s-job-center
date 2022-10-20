package com.srg.scheduledcore.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author: EQ-SRG
 * @create: 2022/9/13
 * @description:
 *
 * create table task(
 *     taskId int not null primary key,
 *     taskName varchar(20),
 *     type int,
 *     cron varchar(20),
 *     detail text,
 *     createTime datetime,
 *     userId varchar(11),
 *     foreign key (userId) references user_bo(id)
 * );
 **/
@Data
public class Task {

    //task id
    private String taskId;

    //task name
    private String taskName;

    //类型,如shell,http请求等
    private String type;

    //cron表达式
    private String cron;

    //执行内容
    private String detail;

    //创建时间
    private Date createTime;

    //所属用户id
    private String userId;

    //是否执行（start or stop）
    private String status;

}
