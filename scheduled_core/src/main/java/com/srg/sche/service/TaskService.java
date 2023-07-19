package com.srg.sche.service;

import com.srg.sche.entity.Task;
import com.srg.sche.vo.TaskVO;

import java.util.List;

/**
 * @author: SRG
 * @create: 2022/9/14
 * @describe:
 **/
public interface TaskService {

    Task add(TaskVO taskVO);

    Integer delete(String taskId);

    List<Task> findAllByUser(String userId);

    Task find(String taskId);

    Integer update(Task task);

    Integer update(String taskId,String status);
}
