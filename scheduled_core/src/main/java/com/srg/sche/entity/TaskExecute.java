package com.srg.sche.entity;

import lombok.Data;
import java.util.Date;

/**
 * @author: EQ-SRG
 * @create: 2022/9/13
 * @description:
 *
 * create table task_exe(
 *     taskId int,
 *     startTime datetime,
 *     endTime datetime,
 *     exeStatus varchar(10),
 *     exeResult varchar(100),
 *     foreign key (taskId) references task(taskId)
 * );
 **/
@Data
public class TaskExecute {

    //task id
    private Integer taskId;

    //执行开始时间
    private Date startTime;

    //执行结束时间
    private Date endTime;

    //执行状态，是否成功
    private String exeStatus;

    //执行返回结果
    private String userId;

}
