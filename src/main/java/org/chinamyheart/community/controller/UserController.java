package org.chinamyheart.community.controller;

import org.apache.commons.lang3.StringUtils;
import org.chinamyheart.community.common.auth.LoginResponse;
import org.chinamyheart.community.common.utils.ReturnResult;
import org.chinamyheart.community.common.utils.Utils;
import org.chinamyheart.community.model.User;
import org.chinamyheart.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.security.krb5.internal.PAData;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    /**
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ReturnResult login(@RequestParam(required = true) String username,
                              @RequestParam(required = true) String password) {
        // 是否为空
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return ReturnResult.FAILUER("用户名或密码不能为空");
        }

        User user = new User();
        LoginResponse login = null;
        try {
            user.setUsername(username);
            user.setPassword(Utils.MD5(password));
            login = userService.checkLogin(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        // 登录失败
        if(login == null){
            List<User> userList =  userService.getUserList(user);
            if (userList == null || userList.size() == 0) {
                return ReturnResult.FAILUER(" 帐号不存在，<a href\\=\"/user/register.html\">去注册>></a>！");
            } else {
                return ReturnResult.FAILUER("用户名或密码错误");
            }
        }

        //设置登录成功的Token
        sessionContextUtils.addContextToken("token",login.getToken());
        return ReturnResult.SUCCESS("登录成功");
    }
}
