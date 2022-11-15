package com.srg.scheduledcore.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: EQ-SRG
 * @create: 2022/9/28
 * @description: 公共返回对象
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseBean {

    private Integer code;

    private String message;

    private Object object;

    public static ResponseBean success(){
        return new ResponseBean(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMessage(),null);
    }

    public static ResponseBean success(Object obj){
        return new ResponseBean(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMessage(),obj);
    }

    public static ResponseBean error(ResponseEnum responseEnum){
        return new ResponseBean(responseEnum.getCode(), responseEnum.getMessage(),null);
    }

    public static ResponseBean error(ResponseEnum responseEnum,Object obj){
        return new ResponseBean(responseEnum.getCode(), responseEnum.getMessage(),obj);
    }
}
