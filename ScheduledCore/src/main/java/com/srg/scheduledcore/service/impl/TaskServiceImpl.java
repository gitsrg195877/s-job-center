package com.srg.scheduledcore.service.impl;

import com.srg.scheduledcore.entity.Task;
import com.srg.scheduledcore.mapper.TaskMapper;
import com.srg.scheduledcore.service.TaskService;
import com.srg.scheduledcore.utils.CronUtil;
import com.srg.scheduledcore.utils.DateUtil;
import com.srg.scheduledcore.utils.UUIDUtil;
import com.srg.scheduledcore.vo.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: EQ-SRG
 * @create: 2022/9/14
 * @description:
 **/

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public Task add(TaskVO taskVO) {
        Task task = new Task();
        task.setCreateTime(DateUtil.getNowTime());
        task.setUserId(taskVO.getUserId());
        task.setTaskId(UUIDUtil.uuid());
        task.setStatus(ExecuteCore.TASK_STATUS_START);
        task.setTaskName(taskVO.getTaskName());
        task.setDetail(taskVO.getDetail());
        task.setOverdue("N");
        //根据uuid随机生成taskId
        task.setType(taskVO.getType());
        task.setCron(CronUtil.getCron(taskVO.getCron()));    //此处待补充，cron表达式
        Integer add = taskMapper.add(task);
        return add == 1 ? task : null;
    }

    @Override
    public Integer delete(String taskId) {
        return taskMapper.delete(taskId);
    }

    @Override
    public List<Task> findAllByUser(String userId) {
        return null;
    }

    @Override
    public Task find(String taskId) {
        return taskMapper.find(taskId);
    }

    @Override
    public Integer update(Task task) {
        return taskMapper.update(task);
    }

    @Override
    public Integer update(String taskId, String status) {
        return taskMapper.updateStatus(taskId,status);
    }


}
