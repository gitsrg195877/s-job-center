package com.srg.scheduledcore.controller;

import com.srg.scheduledcore.service.UserService;
import com.srg.scheduledcore.vo.LoginVo;
import com.srg.scheduledcore.vo.RegisterVo;
import com.srg.scheduledcore.vo.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: EQ-SRG
 * @create: 2022/9/14
 * @description:
 **/

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/hello")
    public String test(){
        return "hello world";
    }

    @PostMapping("/login")
    public ResponseBean login(LoginVo user, HttpServletRequest request, HttpServletResponse response) {
        log.info("用户登录:id = " + user.getId() + " ip = " + request.getRequestURL().toString());
        return userService.login(user.getId(), user.getPwd(), request, response);
    }

    @PostMapping("/add")
    public ResponseBean register(RegisterVo user) {
        log.info("添加用户：" + user.getId());
        return userService.add(user);
    }

    @DeleteMapping("/delete")
    public ResponseBean delete(String userId){
        return null;
    }


}
