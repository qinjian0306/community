package org.chinamyheart.community.controller;

import org.chinamyheart.community.common.PageUtils.Pagination;
import org.chinamyheart.community.common.utils.Constant;
import org.chinamyheart.community.common.utils.ReturnResult;
import org.chinamyheart.community.mapper.CaseMapper;
import org.chinamyheart.community.model.Case;
import org.chinamyheart.community.model.Reply;
import org.chinamyheart.community.model.User;
import org.chinamyheart.community.service.CaseService;
import org.chinamyheart.community.service.ReplayService;
import org.chinamyheart.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/patient")
public class PatientController extends RedisBaseController {

    @Value("${filepath}")
    private String filepath;

    @Autowired
    private CaseService caseService;
    @Autowired
    private ReplayService replayService;

    @RequestMapping("/viewCase")
    public String getCase(Model model, @RequestParam(required = true) Integer caseId) {
        User user = super.getCurrentUserInfoByToken();
        if (user != null) {
            Case c = caseService.getCaseById(caseId);
            List<Reply> replyList = replayService.selectByCaseId(caseId);
            model.addAttribute("case", c);
            model.addAttribute("user", user);
            model.addAttribute("replyList", replyList);
        }
        return "/user/conversation";
    }

    @RequestMapping("/downloadCase")
    @ResponseBody
    public Object downloadCase(@RequestParam(required = true) String url) {
        try {
            File fir = ResourceUtils.getFile(filepath);
            String rootPath = fir.getParent();
            File file = new File(rootPath + File.separator + url);
            if (!file.exists()) {
                return ReturnResult.FAILUER("文件不存在，下载失败");
            }
            return caseService.downloadCase(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return ReturnResult.FAILUER("文件不存在，下载失败");
    }

    @RequestMapping("/getCaseList")
    public String getAllCases(Model model,
                              @RequestParam(value = "pageNum", defaultValue = "1") Integer currentPage) {

        User user = super.getCurrentUserInfoByToken();
        if (user != null) {
            Pagination<Case> pageParm = new Pagination<>(currentPage, Constant.CASEPAGESIZE);
            Pagination<Case> pagination = caseService.getCasesByUserId(user.getId(), pageParm);
            model.addAttribute("list", pagination);
            model.addAttribute("user", user);
        }
        return "/user/patient";
    }

    @RequestMapping("/removeCase")
    public String deleteCase(Model model, @RequestParam(required = true) Integer caseId) {
        caseService.deleteCase(caseId);
        return "redirect:/admin/getCaseList";
    }

    @RequestMapping("/lockCase")
    public String lockCase(@RequestParam(required = true) Integer caseId) {
        Case c = new Case();
        c.setId(caseId);
        c.setStatus(1);
        caseService.lockCase(c);
        return "redirect:/patient/getCaseList";
    }

    @PostMapping("/addCase")
    public String addCase(Model model, Case c, @RequestParam("files") MultipartFile[] files) {
        StringBuilder url = new StringBuilder("");
        try {
//            File fir = ResourceUtils.getFile("classpath:static/fileupload/readme.txt");
            File fir = ResourceUtils.getFile(filepath);
            String rootPath = fir.getParent();
            Date date = Calendar.getInstance().getTime();
            c.setCreateTime(date);
            c.setUpdateTime(date);
            c.setStatus(0);

            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                String fileExtension = fileName.substring(fileName.lastIndexOf("."));
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
                fileName = LocalDateTime.now().format(dtf) + fileName;
                if (fileName.trim().length() == 0) continue;
                File f = new File(rootPath + File.separator + fileName);
                if (url.length() > 0) {
                    url.append(",");
                }
//                url.append(f.getPath());
                url.append("/fileupload/"+fileName);
                FileOutputStream fos = new FileOutputStream(f);
                InputStream fis = file.getInputStream();
                FileCopyUtils.copy(fis, fos);
                if (fis != null) {
                    fis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        c.setUrl(url.toString());
        caseService.addCase(c);
        return "redirect:/patient/getCaseList";
    }

    @PostMapping("/addCase/upload/files")
    @ResponseBody
    public Object uploadFiles(@RequestParam("files") MultipartFile[] files) {
        try {
            File fir = ResourceUtils.getFile("classpath:static/fileupload/readme.txt");
            String rootPath = fir.getParent();
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                if (fileName.trim().length() == 0) continue;
                FileOutputStream fos = new FileOutputStream(new File(rootPath + File.separator + fileName));
                InputStream fis = file.getInputStream();
                FileCopyUtils.copy(fis, fos);
                if (fis != null) {
                    fis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ReturnResult.FAILUER("上传失败");
        }
        return ReturnResult.SUCCESS("上传成功");
    }

    @RequestMapping("/addCase/upload/file")
    @ResponseBody
    public Object uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            File fir = ResourceUtils.getFile("classpath:static/fileupload/readme.txt");
            String rootPath = fir.getParent();
            String fileName = file.getOriginalFilename();
            if (fileName.trim().length() == 0) {
                return ReturnResult.SUCCESS("上传的文件名为空");
            }
            FileOutputStream fos = new FileOutputStream(new File(rootPath + File.separator + fileName));
            InputStream fis = file.getInputStream();
            FileCopyUtils.copy(fis, fos);
            if (fis != null) {
                fis.close();
            }
            if (fos != null) {
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            ReturnResult.SUCCESS("上传失败");
        }
        return ReturnResult.SUCCESS("上传成功");
    }
}
