package com.srg.scheduledcore.vo;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * @author : SRG
 * @create : 2022/9/28
 * @describe :
 **/

@ToString
@AllArgsConstructor
public enum ResponseBean {

    ERROR(500,"服务端异常");

    SUCCESS(200,"SUCCESS");



    private int code;

    private String message;
}
