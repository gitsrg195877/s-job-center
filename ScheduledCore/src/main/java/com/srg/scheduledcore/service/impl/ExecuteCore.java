package com.srg.scheduledcore.core;

import com.srg.scheduledcore.entity.Task;
import com.srg.scheduledcore.mapper.TaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: EQ-SRG
 * @create: 2022/10/2
 * @description:
 **/
@Component
@Slf4j
public class ExecuteCore implements InitializingBean {

    //以事件触发此集合的更改,如果是分布式，其实可以进行redis存储此map,实现更快的task查询功能
    protected static final Map<String, Task> TASK_LIST_MAP = new HashMap<>();

    public Map<String, Thread> THREAD_MAP = new HashMap<>();

    @Autowired
    private TaskMapper taskMapper;




    /**
     * @fromInterface：InitializingBean
     * @description: 实例加载到容器后进行的初始化
     **/
    @Override
    public void afterPropertiesSet() {
        List<Task> tasks = taskMapper.findAll();
        if (tasks == null || tasks.size() == 0) {
            System.out.println("定时任务为null");
        }
        for (Task task : tasks) {
            TASK_LIST_MAP.put(task.getTaskId(), task);
            Thread thread = createTask(task);
            thread.start();

        }
    }


    Thread createTask(Task task) {
        //解析task的cron表达式
        //通过解析cron表达式，得出下次执行的间隔时间
        //将task的detail加入到任务执行当中，进行任务的定时执行
        Thread thread = new Thread(() -> {
            Long sleepTime = 2000L;   //解析出来的间隔时间，需要拿到全局去修改
            while (true) {
                log.info("执行任务:" + task.getTaskName() + "-" + task.getTaskId());
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setName(task.getTaskId());

        THREAD_MAP.put(thread.getName(), thread);
        return thread;
    }

}
