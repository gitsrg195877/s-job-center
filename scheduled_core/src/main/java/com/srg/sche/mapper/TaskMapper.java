package com.srg.sche.mapper;

import com.srg.sche.entity.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author: SRG
 * @create: 2022/9/21
 * @describe:
 **/
@Mapper
public interface TaskMapper {


    //root
    @Select("select taskId,taskName,type,cron,detail,createTime,userId,status,overdue from task")
    List<Task> findAll();

    @Select("select taskId,taskName,type,cron,detail,createTime,userId,status from task where taskId = #{taskId}")
    Task find(String taskId);

    //user
    @Select("select taskId,taskName,type,cron,detail,createTime,userId,status from task where userId = #{userId}")
    List<Task> findAllByUser(String userId);

    @Select("select taskId,taskName,type,cron,detail,createTime,userId,status from task where taskId = #{taskId}")
    Task findById(String TaskId);

    @Insert("insert into task (taskId,taskName,type,cron,detail,createTime,userId,status,overdue) values(#{taskId},#{taskName},#{type},#{cron},#{detail},#{createTime},#{userId},#{status},#{overdue})")
    Integer add(Task task);

    @Insert("<script>" +
            "insert into task (taskId,taskName,type,cron,detail,createTime,userId,status) " +
            "values " +
            "<foreach collection='list' item='task' separator=','>" +
            "(#{task.taskId},#{task.taskName},#{task.type},#{task.cron},#{task.detail},#{task.createTime},#{task.userId},#{task.status})" +
            "</foreach>" +
            "</script>")
    Integer addTasks(@Param("list")List<Task> taskList);

    //伪删除，只是更新一个状态
    @Update("update task set overdue = 'N',status = 'stop' where taskId = #{taskId}")
    Integer delete(String taskId);

    Integer DeleteList(List<Integer> taskIds);

    @Update("update task set cron = #{cron},detail = #{detail},taskName = #{taskName},type = #{type} where taskId = #{taskId}")
    Integer update(Task task);

    @Update("update task set status = #{status} where taskId = #{taskId}")
    Integer updateStatus(String taskId, String status);



}
