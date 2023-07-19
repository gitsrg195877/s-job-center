package com.srg.sche.service;

import com.srg.sche.service.impl.EventEnum;

/**
 * @author: SRG
 * @create: 2022/10/12
 * @describe: EventService Interface for Task
 **/
public interface EventService {

    /**
     * 事件处理接口
     **/
    <T> void eventHandle(T t, EventEnum eventEnum) throws Exception;
}
