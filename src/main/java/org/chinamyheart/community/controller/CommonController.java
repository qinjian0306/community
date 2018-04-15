package org.chinamyheart.community.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/common")
public class CommonController extends BaseController {

    // 测试
    @RequestMapping("/test")
    public String login(HttpServletRequest request, String password) {
        String uri = request.getRequestURI();
        return uri;
    }


}
