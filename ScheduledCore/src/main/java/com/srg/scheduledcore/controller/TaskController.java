package com.srg.scheduledcore.controller;

import com.srg.scheduledcore.service.EventService;
import com.srg.scheduledcore.service.impl.EventEnum;
import com.srg.scheduledcore.service.impl.ExecuteCore;
import com.srg.scheduledcore.vo.ResponseBean;
import com.srg.scheduledcore.vo.ResponseEnum;
import com.srg.scheduledcore.vo.TaskVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: EQ-SRG
 * @create: 2022/9/13
 * @description:
 **/
@RestController
@Slf4j
@RequestMapping("/task")
public class TaskController {


    @Autowired
    private EventService eventService;

    /**
     * 新增任务，触发 NEW 事件
     **/
    @PostMapping("/add")
    public ResponseBean add(TaskVO taskVO) {
        try {
            eventService.eventHandle(taskVO, EventEnum.NEW);
            System.out.println(ExecuteCore.TASK_MAP);
            return ResponseBean.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error(ResponseEnum.ERROR);
        }

    }

    /**
     * 启动任务，触发 START 事件
     **/
    @PutMapping("/start")
    public ResponseBean start(String taskId){
        try {
            eventService.eventHandle(taskId, EventEnum.START);
            System.out.println(ExecuteCore.TASK_MAP);
            return ResponseBean.success();
        }catch (Exception e){
            return ResponseBean.error(ResponseEnum.ERROR);
        }
    }

    /**
     * 终止任务，触发 STOP 事件
     **/
    @PutMapping("/stop")
    public ResponseBean stop(String taskId) throws Exception {
        eventService.eventHandle(taskId,EventEnum.STOP);
        System.out.println(ExecuteCore.TASK_MAP);
        return ResponseBean.success();
    }

    /**
     * 修改任务，触发 MODIFY 事件
     * 可修改cron、taskName、detail,type 根据taskId
     **/
    @PutMapping("/modify")
    public ResponseBean modify(TaskVO taskVO) throws Exception{
        eventService.eventHandle(taskVO,EventEnum.MODIFY);
        return ResponseBean.success();
    }

    /**
     * 取消任务，触发 CANCEL 事件
     * 先进行 STOP 事件，然后在数据库作伪删除
     **/
    @PutMapping("/cancel")
    public ResponseBean cancel(String taskId) throws Exception {
        eventService.eventHandle(taskId,EventEnum.CANCEL);
        return ResponseBean.success();
    }


}
