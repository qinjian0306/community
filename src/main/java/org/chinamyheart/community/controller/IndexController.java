package org.chinamyheart.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends RedisBaseController{

    /**
     * 登录
     * @return
     */
    @RequestMapping("/")
    public String Index(){
        return "/login";
    }


    /**
     * 注册
     * @return
     */
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "/register";
    }

    /**
     * 跳转 医生页面
     * @return
     */
    @RequestMapping("/toDoctor")
    public String toDoctor(){
        return "/user/doctor";
    }

    /**
     * 跳转 医生页面
     * @return
     */
    @RequestMapping("/toPatient")
    public String toPatient(){
        return "/user/patient";
    }

}
