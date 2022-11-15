package com.srg.scheduledcore.mapper;

import com.srg.scheduledcore.entity.User;
import org.apache.ibatis.annotations.*;

/**
 * @author: SRG
 * @create: 2022/9/21
 * @describe:
 **/
@Mapper
public interface UserMapper {


    @Select("select id,password,salt,`group`,permission from user_bo where id = #{userId}")
    User findByUserId(String userId);

    @Insert("insert into user_bo (id,password,salt,`group`,permission) values(#{id},#{password},#{salt},#{group},#{permission})")
    Integer add(User user);

    @Update("update user_bo set password = #{password},salt = #{salt},`group` = #{group},permission = #{permission} where id = #{id}")
    Integer update(User user);

    @Delete("delete from user_bo where id = #{id}")
    Integer delete(String userId);
}
