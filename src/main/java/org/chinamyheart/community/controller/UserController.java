package org.chinamyheart.community.controller;

import org.chinamyheart.community.mapper.UserMapper;
import org.chinamyheart.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/user")
    public Object user(){
        User user = userMapper.selectByUsername("haha");
        return user;
    }
}
