package org.chinamyheart.community.controller;

import org.chinamyheart.community.common.PageUtils.Pagination;
import org.chinamyheart.community.common.utils.Constant;
import org.chinamyheart.community.common.utils.ReturnResult;
import org.chinamyheart.community.model.Case;
import org.chinamyheart.community.model.Doctor;
import org.chinamyheart.community.model.User;
import org.chinamyheart.community.model.UserVo;
import org.chinamyheart.community.service.CaseService;
import org.chinamyheart.community.service.DoctorService;
import org.chinamyheart.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController extends RedisBaseController {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private CaseService caseService;
    @Autowired
    private UserService userService;

    /**
     * 所有医生
     *
     * @return
     */
    @RequestMapping("/all")
    @ResponseBody
    public ReturnResult doctorAll() {
        List<Doctor> doctorList = doctorService.selectAll();
        if (doctorList.size() > 0) {
            return ReturnResult.SUCCESS("获取所有医生列表成功", doctorList);
        }
        return ReturnResult.FAILUER("获取所有医生列表失败");
    }

    /**
     * 所有待审核/已审核医生
     *
     * @return
     */
    @RequestMapping("/allPend")
    public String doctorAllPend(Model model,
                                @RequestParam(required = false, defaultValue = "3") Integer status,
                                @RequestParam(value = "pageNum", defaultValue = "1") Integer currentPage) {
        User user = new User();
        user.setDstatus(status);
        Pagination<UserVo> pageParm = new Pagination<>(currentPage, Constant.REVIEWPAGESIZE);
        Pagination<UserVo> pagination = userService.selectAllPendByPage(status, pageParm);
        model.addAttribute("doctorList", pagination);
        model.addAttribute("status", status);
        String location = "";
        if(status == 1){// 已认证
            location = "/review/review";
        }
        if(status == 3){// 已填写认证信息 等待审核
            location = "/review/unreview";
        }
        return location;
    }

    /**
     * 医生认证
     *
     * @param hospital
     * @param realName
     * @param mobile
     * @param detail
     * @return
     */
    @RequestMapping("/verify")
    @ResponseBody
    public ReturnResult doctorVerify(
            @RequestParam(required = false) String hospital,
            @RequestParam(required = false) String realName,
            @RequestParam(required = false) String mobile,
            @RequestParam(required = false) String detail) {
        User user = super.getCurrentUserInfoByToken();
        if (user != null) {
            Doctor doctor = new Doctor();
            doctor.setUserId(user.getId());
            doctor.setHospital(hospital);
            doctor.setMobile(mobile);
            doctor.setRealName(realName);
            doctor.setDetials(detail);
            user.setDstatus(3);// 已填写认证信息 正在审核
            try {
                int result = doctorService.insert(doctor);

                // 更新user 状态
                userService.update(user);
                if (result > 0) {
                    return ReturnResult.SUCCESS("添加认证成功");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return ReturnResult.FAILUER("添加认证失败");
    }

    /**
     * 批准/拒绝
     *
     * @param doctorId
     * @param action
     * @return
     */
    @RequestMapping("/pend")
    public String doctorPend(Model model,
                                    @RequestParam(required = true) Integer doctorId,
                                   @RequestParam(required = true) Integer action) {
        User user = userService.getUserById(doctorId);

        // 批准
        if (doctorId != null && action == 1) {
            user.setDstatus(1);
        }
        // 拒绝
        if (doctorId != null && action == 2) {
            user.setDstatus(2);
        }
        try {
            userService.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/doctor/allPend";
    }

    /**
     * 获取所有病历
     *
     * @return
     */
    @RequestMapping("/getCaseList")
    public String getAllCases(Model model,
                              @RequestParam(value = "pageNum", defaultValue = "1") Integer currentPage) {

        User user = super.getCurrentUserInfoByToken();
        if (user != null) {
            Pagination<Case> pageParm = new Pagination<>(currentPage, Constant.CASEPAGESIZE);
            Pagination<Case> pagination = caseService.getAllCases(pageParm);
            // 获取用户状态
            User userInfo = null;
            if (user.getRole() == 1) {
                userInfo = userService.getUserById(user.getId());
            }
            model.addAttribute("list", pagination);
            model.addAttribute("user", userInfo);
        }
        return "/user/doctor";
    }

    @RequestMapping("/downloadCase")
    @ResponseBody
    public Object downloadCase(@RequestParam(required = true) String url) {

        File file = new File(url);
        if (!file.exists()) {
            return ReturnResult.FAILUER("文件不存在，下载失败");
        }
        return caseService.downloadCase(file);
    }
}
