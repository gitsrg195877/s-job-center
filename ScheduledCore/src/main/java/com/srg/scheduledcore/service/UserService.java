package com.srg.scheduledcore.service;

import com.srg.scheduledcore.entity.User;
import com.srg.scheduledcore.vo.RegisterVo;
import com.srg.scheduledcore.vo.ResponseBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: SRG
 * @create: 2022/9/14
 * @describe:
 **/
public interface UserService {

    ResponseBean login(String id, String password, HttpServletRequest request, HttpServletResponse response);

    ResponseBean add(RegisterVo user);

    ResponseBean update(RegisterVo user);

    ResponseBean delete(String userId);
}
