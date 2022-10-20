package com.srg.scheduledcore.service;

import com.srg.scheduledcore.entity.Task;
import com.srg.scheduledcore.vo.TaskVO;

/**
 * @author: SRG
 * @create: 2022/10/12
 * @describe: Event Interface for Task
 **/
public interface Event {

    /**
     * 新增(new) Task事件，默认自动开启（start事件）
     **/
    public void newTask(TaskVO taskVO) throws Exception;

    /**
     * 修改(modify) Task事件，首先进行stop事件，... 再进行start事件
     **/
    public void modifyTask(Task task);

    /**
     * 删除(cancel) Task事件，首先进行stop事件...
     **/
    public void cancelTask(String taskId);

    /**
     * 启动(start) Task事件
     **/
    public void startTask(String taskId) throws Exception;

    /**
     * 终止(stop) 事件
     **/
    public void stopTask(String taskId);


    public void eventProcess();
}
