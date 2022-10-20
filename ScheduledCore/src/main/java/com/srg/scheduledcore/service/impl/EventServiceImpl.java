package com.srg.scheduledcore.service.impl;


import com.srg.scheduledcore.entity.Task;
import com.srg.scheduledcore.service.Event;
import com.srg.scheduledcore.service.TaskService;
import com.srg.scheduledcore.vo.TaskVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: EQ-SRG
 * @create: 2022/10/11
 * @description: 定时任务事件类, 通过事件, 进行任务的添加, 修改, 删除
 * <p>
 * 添加：直接往数据库添加,并更新map值,然后创建线程执行此任务
 * 修改：通知线程终止任务,并释放所占资源,并更新数据库,更新map值,然后创建新线程执行任务
 * 删除：通知线程终止任务,释放所占资源,更新数据库,更新map值
 * 启动：
 * 停止：
 **/

@Component
@Slf4j
public class EventImpl implements Event {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ExecuteCore executeCore;

    public void newTask(TaskVO taskVO) throws Exception {
        Task task = taskService.add(taskVO);
        if(task != null){
            executeCore.startTask(task);
        }else {
            throw new Exception("task-" + taskVO.getTaskName() + " 创建失败");
        }
    }

    public void modifyTask(Task task) {

    }

    public void cancelTask(String taskId) {

    }

    public void startTask(String taskId) throws Exception {
        Task task = taskService.find(taskId);
        if (task == null) {
            log.error("task-" + taskId + " 不存在");
            return;
        }
        Integer update = taskService.update(taskId, ExecuteCore.TASK_STATUS_START);
        if (update == 1) {
            executeCore.startTask(task);
        } else {
            throw new Exception("task-" + taskId + " 启动失败");
        }

    }

    public void stopTask(String taskId) {

    }


}


