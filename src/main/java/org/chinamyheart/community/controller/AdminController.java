package org.chinamyheart.community.controller;

import org.chinamyheart.community.common.PageUtils.Pagination;
import org.chinamyheart.community.common.utils.Constant;
import org.chinamyheart.community.model.Case;
import org.chinamyheart.community.model.User;
import org.chinamyheart.community.service.CaseService;
import org.chinamyheart.community.service.DoctorService;
import org.chinamyheart.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/admin")
public class AdminController extends RedisBaseController{

    @Autowired
    private CaseService caseService;
    @Autowired
    private UserService userService;

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
            model.addAttribute("list", pagination);
            model.addAttribute("user", user);
        }
        return "/user/caseList";
    }


}
