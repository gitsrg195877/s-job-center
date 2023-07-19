package com.srg.sche.service;

import com.srg.sche.vo.RegisterVo;
import com.srg.sche.vo.ResponseBean;

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
