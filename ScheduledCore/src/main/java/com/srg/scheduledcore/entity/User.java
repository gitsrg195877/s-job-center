package com.srg.scheduledcore.entity;

import lombok.Data;

/**
 * @author: EQ-SRG
 * @create: 2022/9/13
 * @description:
 *
 * create table user_bo(
 *     id varchar(11) not null primary key,
 *     password varchar(20) not null,
 *     `group` varchar(10),
 *     permission int    #1代表admin权限，0代表user权限
 * );
 **/

@Data
public class User {

    private String id;

    private String password;

    private String salt;

    private String group;

    private Integer permission;

}
