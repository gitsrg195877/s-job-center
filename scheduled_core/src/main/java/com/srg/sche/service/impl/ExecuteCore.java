package com.srg.sche.service.impl;

import com.srg.sche.entity.Task;
import com.srg.sche.mapper.TaskMapper;
import com.srg.sche.utils.CronUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: EQ-SRG
 * @create: 2022/10/2
 * @description:
 **/
@Service
@Slf4j
public class ExecuteCore implements InitializingBean {

    public static final String TASK_STATUS_START = "start";
    public static final String TASK_STATUS_STOP = "stop";


    //如果要集群，此map可以放入redis中
    public static volatile Map<String, Task> TASK_MAP = new HashMap<>();


    @Autowired
    private TaskMapper taskMapper;


    /**
     * @fromInterface：InitializingBean
     * @description: 启动服务器时，需要将所有 overdue = N 的task 加载到map里，且启动START状态的task线程
     **/
    @Override
    public void afterPropertiesSet() {
        List<Task> tasks = taskMapper.findAll();   //需要针对task做筛选(condition : overdue = N)
        if (tasks == null || tasks.size() == 0) {
            log.info("定时任务目前为null，请添加定时任务");
            return;
        }
        for (Task task : tasks) {
            if (task.getOverdue().equals("N")) {
                TASK_MAP.put(task.getTaskId(), task);
                if (task.getStatus().equals(TASK_STATUS_START)) {
                    startTask(task);
                }
            }
        }
    }


    void startTask(Task task) {

        Thread thread = task.getThread();
        if (thread == null) {
            thread = new Thread(() -> {
                while (TASK_MAP.get(task.getTaskId()).getStatus().equals(TASK_STATUS_START)) {
                    //发现问题：同一时间，可能会执行两次（猜测是由于测试任务执行太快导致的）
                    Long sleepTime = CronUtil.getTimeToNextExecution(task.getCron());
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                    log.info("执行任务:" + task.getTaskName() + "-" + task.getTaskId());
                    Object obj = execute(TaskEnum.valueOf(task.getType()), task.getDetail());
                }
            });
            thread.setName(task.getTaskId());
            task.setThread(thread);
        }
        thread.start();
        log.info(task.getTaskName() + "-" + thread.getName() + " 启动成功");
    }


    public Object execute(TaskEnum type,String detail){
        return null;
    }

    public Object executeShell(String path){
        return null;
    }

    public Object executeCmd(String path){
        return null;
    }

    public Object executeSpringCloudService(String url){
        return null;
    }

    public Object executeHttpRequest(String url){
        return null;
    }


}
