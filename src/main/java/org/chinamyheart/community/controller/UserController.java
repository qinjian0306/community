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

    @Autowired
    private CaseService caseService;

    @RequestMapping("/user")
    public Object user() {
        User user = userMapper.selectByUsername("haha");
        return user;
    }

    @RequestMapping(path = "/viewCase")
    public Object getCase(Integer caseId) {
        Case c = caseService.getCaseById(caseId);
        ReturnResult result = new ReturnResult();
        result.setTime(Calendar.getInstance().getTimeInMillis());
        result.setMsg("OK");
        result.setCode(ReturnResult.SUCCESS);
        result.setData(c);
        return result;
    }

    @RequestMapping(path = "/getCaseList")
    public Object getAllCases(Integer caseId) {
        List<Case> cases = caseService.getAllCases();
        ReturnResult result = new ReturnResult();
        result.setTime(Calendar.getInstance().getTimeInMillis());
        result.setMsg("OK");
        result.setCode(ReturnResult.SUCCESS);
        result.setData(cases);
        return result;
    }

    @RequestMapping(path = "/removeCase")
    public Object deleteCase(Integer caseId) {
        caseService.deleteCase(caseId);
        ReturnResult result = new ReturnResult();
        result.setTime(Calendar.getInstance().getTimeInMillis());
        result.setCode(ReturnResult.SUCCESS);
        result.setMsg(caseId + ":刪除成功");
        result.setTime(Calendar.getInstance().getTimeInMillis());
        return result;
    }

    @RequestMapping(path = "/lockCase")
    public Object lockCase(Integer caseId) {
        ReturnResult result = new ReturnResult();
        result.setMsg("锁定成功");
        result.setCode(ReturnResult.SUCCESS);
        result.setTime(Calendar.getInstance().getTimeInMillis());
        return result;
    }

    @RequestMapping(path = "/addCase")
    public Object addCase(Case c, MultipartFile[] files) {
        caseService.addCase(c);
        ReturnResult result = new ReturnResult();
        result.setTime(Calendar.getInstance().getTimeInMillis());
        try {
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                FileOutputStream fos = new FileOutputStream(new File(fileName));
                InputStream fis = file.getInputStream();
                FileCopyUtils.copy(fis, fos);
                result.setCode(ReturnResult.SUCCESS);
                result.setMsg("添加成功");
            }
        } catch (IOException e) {
            e.printStackTrace();
            result.setCode(ReturnResult.FAILURE);
            result.setMsg("添加失败");
        }
        return result;
    }

    @RequestMapping(path = "/addCase/upload/files")
    public Object uploadFiles(@RequestParam("files") MultipartFile[] files) {
        ReturnResult result = new ReturnResult();
        result.setTime(Calendar.getInstance().getTimeInMillis());
        try {
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                FileOutputStream fos = new FileOutputStream(new File(fileName));
                InputStream fis = file.getInputStream();
                FileCopyUtils.copy(fis, fos);
                result.setCode(ReturnResult.SUCCESS);
                result.setMsg("上传成功");
            }
        } catch (IOException e) {
            e.printStackTrace();
            result.setCode(ReturnResult.FAILURE);
            result.setMsg("上传失败");
        }
        return result;
    }

    @RequestMapping(path = "/addCase/upload/file")
    public Object uploadFile(MultipartFile file) {
        ReturnResult result = new ReturnResult();
        result.setTime(Calendar.getInstance().getTimeInMillis());
        try {
            String fileName = file.getOriginalFilename();
            FileOutputStream fos = new FileOutputStream(new File(fileName));
            InputStream fis = file.getInputStream();
            FileCopyUtils.copy(fis, fos);
            result.setCode(ReturnResult.SUCCESS);
            result.setMsg("上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            result.setCode(ReturnResult.FAILURE);
            result.setMsg("上传失败");
        }
        return result;
    }
}
