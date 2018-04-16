package org.chinamyheart.community.controller;

import org.chinamyheart.community.common.utils.ReturnResult;
import org.chinamyheart.community.mapper.CaseMapper;
import org.chinamyheart.community.model.Case;
import org.chinamyheart.community.model.User;
import org.chinamyheart.community.service.CaseService;
import org.chinamyheart.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;

@RestController
@RequestMapping("/patient")
public class PatientController extends BaseController {
    @Autowired
    private CaseService caseService;

    @RequestMapping(path = "/viewCase")
    public Object getCase(@RequestParam(required = true) Integer caseId) {
        Case c = caseService.getCaseById(caseId);
        return ReturnResult.SUCCESS(c);
    }

    @RequestMapping(path = "/downloadCase")
    public Object downloadCase(@RequestParam(required = true) String url) {

        File file = new File(url);
        if (!file.exists()) {
            return ReturnResult.FAILUER("文件不存在，下载失败");
        }
        return caseService.downloadCase(file);
    }

    @RequestMapping(path = "/getCaseList")
    public Object getAllCases(@RequestParam(required = true) Integer userId) {
        List<Case> cases = caseService.getCasesByUserId(userId);
        return ReturnResult.SUCCESS(cases);
    }

    @RequestMapping(path = "/removeCase")
    public Object deleteCase(@RequestParam(required = true) Integer caseId) {
        caseService.deleteCase(caseId);
        return ReturnResult.SUCCESS("刪除成功");
    }

    @RequestMapping(path = "/lockCase")
    public Object lockCase(@RequestParam(required = true) Integer caseId) {
        Case c = new Case();
        c.setId(caseId);
        c.setStatus(1);
        caseService.lockCase(c);
        return ReturnResult.SUCCESS("锁定成功");
    }

    @RequestMapping(path = "/addCase")
    public Object addCase(Case c, @RequestParam("files") MultipartFile[] files) {
        Date date = Calendar.getInstance().getTime();
        c.setCreateTime(date);
        c.setUpdateTime(date);
        c.setStatus(0);
        StringBuilder url = new StringBuilder("");
        try {
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                if (fileName.trim().length() == 0) continue;
                File f = new File(fileName);
                if (url.length() > 0) {
                    url.append(",");
                }
                url.append(f.getPath());
                FileOutputStream fos = new FileOutputStream(f);
                InputStream fis = file.getInputStream();
                FileCopyUtils.copy(fis, fos);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ReturnResult.FAILUER("添加失败");
        }
        c.setUrl(url.toString());
        caseService.addCase(c);
        return ReturnResult.SUCCESS("添加成功");
    }

    @RequestMapping(path = "/addCase/upload/files")
    public Object uploadFiles(@RequestParam("files") MultipartFile[] files) {
        try {
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                if (fileName.trim().length() == 0) continue;
                FileOutputStream fos = new FileOutputStream(new File(fileName));
                InputStream fis = file.getInputStream();
                FileCopyUtils.copy(fis, fos);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ReturnResult.FAILUER("上传失败");
        }
        return ReturnResult.SUCCESS("上传成功");
    }

    @RequestMapping(path = "/addCase/upload/file")
    public Object uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            if (fileName.trim().length() == 0) {
                return ReturnResult.SUCCESS("上传的文件名为空");
            }
            FileOutputStream fos = new FileOutputStream(new File(fileName));
            InputStream fis = file.getInputStream();
            FileCopyUtils.copy(fis, fos);
        } catch (IOException e) {
            e.printStackTrace();
            ReturnResult.SUCCESS("上传失败");
        }
        return ReturnResult.SUCCESS("上传成功");
    }
}
