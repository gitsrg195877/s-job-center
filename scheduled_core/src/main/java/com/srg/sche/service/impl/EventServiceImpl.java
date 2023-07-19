package com.srg.sche.service.impl;


import com.srg.sche.entity.Task;
import com.srg.sche.service.EventService;
import com.srg.sche.service.TaskService;
import com.srg.sche.vo.TaskVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: EQ-SRG
 * @create: 2022/10/11
 * @description: 定时任务事件处理类, 通过事件, 进行任务的添加, 修改, 删除,启动,停止
 * <p>
 * 添加：在数据库添加,status = start , overdue = N;然后启动task
 * 修改：
 * 删除：
 * 启动：
 * 停止：
 **/

@Component
@Slf4j
public class EventServiceImpl implements EventService {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ExecuteCore executeCore;

    @Override
    public <T> void eventHandle(T t, EventEnum event) throws Exception {
        switch (event) {
            case NEW:
                newTask((TaskVO) t);
                break;
            case STOP:
                stopTask((String) t);
                break;
            case CANCEL:
                cancelTask((String) t);
                break;
            case START:
                startTask((String) t);
                break;
            case MODIFY:
                modifyTask((Task) t);
                break;
        }
    }

    /**
     * NEW EVENT:
     *  更新数据库;
     *  更新TASK_MAP集合;
     *  创建并启动线程;
     **/
    private void newTask(TaskVO taskVO) throws Exception {
        Task task = taskService.add(taskVO);
        ExecuteCore.TASK_MAP.put(task.getTaskId(),task);
        executeCore.startTask(task);
    }


    private void startTask(String taskId) throws Exception {
        Task task = ExecuteCore.TASK_MAP.get(taskId);
        if (task == null) {
            log.error("task-" + taskId + " 不存在");
            return;
        }
        if (task.getStatus().equals(ExecuteCore.TASK_STATUS_START) && task.getThread() != null) {
            log.info("此任务已经在运行");
            return;
        }
        //如果task是stop状态，需要将其变为start状态，放入map中，用以启动task
        //首先更新数据库，再更新task的status='start';然后执行
        Integer update = taskService.update(taskId, ExecuteCore.TASK_STATUS_START);
        task.setStatus(ExecuteCore.TASK_STATUS_START);
        if (update == 1) {
            executeCore.startTask(task);
        } else {
            throw new Exception("task-" + taskId + " 启动失败");
        }

    }

    private void stopTask(String taskId) {
        Task task = ExecuteCore.TASK_MAP.get(taskId);
        Thread thread = task.getThread();
        if (thread == null || task.getStatus().equals(ExecuteCore.TASK_STATUS_STOP)) {
            log.info("task-" + taskId + " 没有运行");
            return;
        }
        task.setStatus(ExecuteCore.TASK_STATUS_STOP);
        taskService.update(taskId, ExecuteCore.TASK_STATUS_STOP);
        //如果任务线程这时处于休眠状态，则通过interrupt使其终止，如果任务正在执行中，则让它执行完毕，
        // 如果是阻塞状态怎么办？
        if(thread.getState() == Thread.State.TIMED_WAITING){
            thread.interrupt();
        }

    }

    /**
     * 进行Modify操作，对taskName,taskCron,taskDetail进行改动
     * 任务线程的状态是否需要改变
     *
     **/
    private void modifyTask(Task task) {
        Integer update = taskService.update(task);
        if(update == 1){
            Task tempTask = ExecuteCore.TASK_MAP.get(task.getTaskId());
            tempTask.setTaskName(task.getTaskName());
            tempTask.setCron(task.getCron());
            tempTask.setDetail(task.getDetail());    //是否需要增加task的最近修改时间
        }
    }


    /**
     * CANCEL EVENT:
     *   STOP EVENT;
     *   更新数据库task为 overdue = N;
     *   移除map中对task的缓存；
     **/
    private void cancelTask(String taskId) {
        stopTask(taskId);
        Integer delete = taskService.delete(taskId);
        if(delete == 1){
            ExecuteCore.TASK_MAP.remove(taskId);
        }
    }


}


