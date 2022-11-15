package com.srg.scheduledcore.service.impl;


import com.srg.scheduledcore.entity.User;
import com.srg.scheduledcore.mapper.UserMapper;
import com.srg.scheduledcore.service.UserService;
import com.srg.scheduledcore.utils.CookieUtil;
import com.srg.scheduledcore.utils.EncryptUtil;
import com.srg.scheduledcore.utils.UUIDUtil;
import com.srg.scheduledcore.vo.RegisterVo;
import com.srg.scheduledcore.vo.ResponseBean;
import com.srg.scheduledcore.vo.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: EQ-SRG
 * @create: 2022/9/14
 * @description:
 **/

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseBean login(String id, String password, HttpServletRequest request, HttpServletResponse response) {

        //判断是否为null，或者格式判断

        User user = userMapper.findByUserId(id);
        if(user == null){
            return ResponseBean.error(ResponseEnum.LOGIN_ERROR);
        }
        if (!(user.getPassword().equals(EncryptUtil.encryptToDb(password,user.getSalt())))) {
            return ResponseBean.error(ResponseEnum.LOGIN_ERROR);
        }
        String ticket = UUIDUtil.uuid();
        request.getSession().setAttribute(ticket,user);
        CookieUtil.setCookie(request,response,"userTicket",ticket);
        return ResponseBean.success(user);
    }


    /**
     * 先判断用户是否存在，如果存在，则注册不成功
     * 否则成功
     **/
    @Override
    public ResponseBean add(RegisterVo user) {

        //首先需要判断用户是否存在，存在则注册不成功
        if(userMapper.findByUserId(user.getId()) != null){
            return ResponseBean.error(ResponseEnum.REGISTER_ERROR);
        }

        User user1 = new User();
        user1.setId(user.getId());
        user1.setGroup(user.getGroup());
        user1.setPermission(user.getPermission());
        String salt = EncryptUtil.getSalt();
        user1.setSalt(salt);
        user1.setPassword(EncryptUtil.encryptToDb(user.getPassword(),salt));
        Integer add = userMapper.add(user1);
        return add == 1 ? ResponseBean.success() : ResponseBean.error(ResponseEnum.ERROR);
    }

    /**
     * 更新用户密码，权限
     **/
    @Override
    public ResponseBean update(RegisterVo user) {
        return null;
    }

    /**
     * 删除用户
     **/
    @Override
    public ResponseBean delete(String userId) {
        return null;
    }
}
