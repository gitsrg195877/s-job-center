package com.srg.scheduledcore.service.impl;

/**
 * @author : SRG
 * @create : 2022/10/12
 * @describe : event 枚举类
 **/
public enum EventEnum {


    /**
     * NEW 事件，针对新增Task触发此事件，此事件依赖于START事件，每个新增的任务默认为开启状态
     **/
    NEW,

    /**
     * CANCEL 事件，针对取消Task触发此事件，此事件可能依赖于STOP事件，如果任务正在运行中，需要将任务终止
     **/
    CANCEL,

    /**
     * START 事件，针对启动Task触发此事件
     **/
    START,

    /**
     * STOP 事件，针对终止Task触发此事件
     **/
    STOP,

    /**
     * MODIFY 事件，针对修改Task触发此事件，此事件依赖于START事件，可能依赖于STOP事件，
     * 如果任务正在运行中，需要将任务先终止，然后进行启动
     **/
    MODIFY

}
