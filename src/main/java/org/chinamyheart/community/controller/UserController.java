package org.chinamyheart.community.controller;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.chinamyheart.community.common.utils.ReturnResult;
import org.chinamyheart.community.mapper.UserMapper;
import org.chinamyheart.community.model.Case;
import org.chinamyheart.community.model.User;
import org.chinamyheart.community.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/user")
    public Object user() {
        User user = userMapper.selectByUsername("haha");
        return user;
    }

}
