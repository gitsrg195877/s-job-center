package com.srg.sche.vo;

import lombok.Data;

/**
 * @author: EQ-SRG
 * @create: 2022/9/28
 * @description:
 **/
@Data
public class RegisterVo {

    private String id;

    private String password;

    private String group;

    private Integer permission;

}
