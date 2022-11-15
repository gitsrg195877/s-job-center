package com.srg.scheduledcore.utils;

import java.util.UUID;

/**
 * @author: EQ-SRG
 * @create: 2022/9/28
 * @description: UUID工具类
 **/
public class UUIDUtil {

    /**
     * 获取无"-"的uuid
     **/
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }


}
