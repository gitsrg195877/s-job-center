package com.srg.sche.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author : SRG
 * @create : 2022/9/28
 * @describe : 
 **/

@ToString
@AllArgsConstructor
@Getter
public enum ResponseEnum {
    
    //登录
    ERROR(500,"服务端异常"),
    LOGIN_ERROR(500101,"用户名或者密码错误"),

    REGISTER_ERROR(500201,"用户已存在"),

    SUCCESS(200,"SUCCESS");

    private final Integer code;

    private final String message;
}
