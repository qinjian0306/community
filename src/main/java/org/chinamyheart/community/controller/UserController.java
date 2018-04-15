package org.chinamyheart.community.controller;

import org.chinamyheart.community.common.utils.Utils;
import org.chinamyheart.community.mapper.UserMapper;
import org.chinamyheart.community.model.User;
import org.chinamyheart.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String register(User user){
            String password = user.getPassword();
            try {
                String newPassword = Utils.MD5(password);
                user.setPassword(newPassword);
            } catch (Exception e) {
                e.printStackTrace();
            }
            userService.insertByUser(user);
            return "success";

        }
}
