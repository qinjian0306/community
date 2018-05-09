package org.chinamyheart.community.controller;

import org.chinamyheart.community.common.utils.Utils;
import org.chinamyheart.community.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.chinamyheart.community.common.auth.LoginResponse;
import org.chinamyheart.community.common.utils.ReturnResult;
import org.chinamyheart.community.common.utils.Utils;
import org.chinamyheart.community.model.User;
import org.chinamyheart.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController extends RedisBaseController{

    @Autowired
    private UserService userService;

    /**
     * 注册
     * @param username
     * @param password
     * @param nickname
     * @param role
     * @param email
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    @ResponseBody
    public ReturnResult register(@RequestParam(required = true) String username,
                                 @RequestParam(required = true) String password,
                                 @RequestParam(required = true) String nickname,
                                 @RequestParam(required = true) Integer role,
                                 @RequestParam(required = false) String email,
                                 @RequestParam(required = false) String mobile){
        User user = new User();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setRole(role);
        user.setEmail(email);
        user.setMobile(mobile);
        try {
            user.setPassword(Utils.MD5(password));
            userService.insert(user);
            return ReturnResult.SUCCESS("注册成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnResult.FAILUER("注册失败");

    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ReturnResult login(@RequestParam(required = true) String username,
                              @RequestParam(required = true) String password,
                              @RequestParam(required = true) Integer role) {
        // 是否为空
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return ReturnResult.FAILUER("用户名或密码不能为空");
        }

        User user = new User();
        LoginResponse login = null;
        try {
            user.setUsername(username);
            user.setPassword(Utils.MD5(password));
            user.setRole(role);
            login = userService.checkLogin(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        // 登录失败
        if(login == null){
            List<User> userList =  userService.getUserByUsername(user);
            if (userList == null || userList.size() == 0) {
                return ReturnResult.FAILUER("帐号不存在");
            } else {
                return ReturnResult.FAILUER("用户名或密码错误");
            }
        }

        //设置登录成功的Token
        sessionContextUtils.addContextToken("token",login.getToken());
        return ReturnResult.SUCCESS("登录成功",role);
    }



    /**
     * 退出
     */
    @RequestMapping(value = "/logout")
    public String logout(Model model) throws Exception {

        try {
            // 获取当前用户
            User user = super.getCurrentUserInfoByToken();
            if (user == null) {
                model.addAttribute("code","7777");
                model.addAttribute("msg","token以过期");
            }
            super.deleteUserInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/login";

    }

    /**
     * 检查用户名是否可用
     * @param username
     * @return
     */
    @RequestMapping("/ifExist")
    @ResponseBody
    public ReturnResult ifExist(String username) {
        User user = new User();
        user.setUsername(username);
        List<User> userList = userService.getUserList(user);
        if(userList.size() > 0){// 已存在
            return ReturnResult.FAILUER("用户名已存在");
        }
        return ReturnResult.SUCCESS();
    }
}
