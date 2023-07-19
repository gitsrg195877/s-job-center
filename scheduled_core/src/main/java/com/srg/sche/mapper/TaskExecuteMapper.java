package com.srg.sche.mapper;

import com.srg.sche.entity.TaskExecute;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @author: SRG
 * @create: 2022/9/21
 * @describe:
 **/

@Mapper
public interface TaskExecuteMapper {

    public List<TaskExecute> findAll();

    public List<TaskExecute> findByUser(String userId);

    public List<TaskExecute> findByTask(String taskId);

    public Integer add(TaskExecute taskExecute);

    public Integer update(TaskExecute taskExecute);

    public Integer deleteByTask(String taskId);

    public Integer deleteByTime(Date dateTime);

}
