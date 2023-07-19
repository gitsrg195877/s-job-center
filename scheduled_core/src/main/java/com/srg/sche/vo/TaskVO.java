package com.srg.sche.vo;

import lombok.Data;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

/**
 * @author: EQ-SRG
 * @create: 2022/10/2
 * @description:
 **/
@Data
public class TaskVO {

    private String taskId;

    private String userId;

    private String taskName;

    private String type;

    private String detail;

    private String cron;

}
