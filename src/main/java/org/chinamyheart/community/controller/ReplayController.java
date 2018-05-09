package org.chinamyheart.community.controller;

import org.chinamyheart.community.common.utils.ReturnResult;
import org.chinamyheart.community.model.Case;
import org.chinamyheart.community.model.Reply;
import org.chinamyheart.community.model.User;
import org.chinamyheart.community.service.CaseService;
import org.chinamyheart.community.service.ReplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 病历回复内容
 */
@Controller
@RequestMapping("/replay")
public class ReplayController extends RedisBaseController {

    @Autowired
    private ReplayService replayService;
    @Autowired
    private CaseService caseService;

    /**
     * 当前病历所有回复
     * @param caseId
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public ReturnResult replayList(@RequestParam(required = true) Integer caseId) {
        User user = super.getCurrentUserInfoByToken();
        List<Reply> replyList = replayService.selectByCaseId(caseId);
        if (replyList.size() > 0) {
            return ReturnResult.SUCCESS(replyList);
        }
        return ReturnResult.FAILUER("获取回复列表失败");
    }

    /**
     * 新增回复
     * @param caseId
     * @param userId
     * @param content
     * @return
     */
    @RequestMapping("/save")
    public String replaySave(Model model,
                           @RequestParam(required = false) Integer caseId,
                           @RequestParam Integer userId,
                           @RequestParam String content) {
        User user = super.getCurrentUserInfoByToken();
        Reply reply = new Reply();
        reply.setCaseId(caseId);
        reply.setUserId(userId);
        reply.setContent(content);
        try {
            int result = replayService.insert(reply);

            // 封装页面数据
            if(user != null){
                Case c =  caseService.getCaseById(caseId);
                List<Reply> replyList = replayService.selectByCaseId(caseId);
                model.addAttribute("case",c);
                model.addAttribute("user",user);
                model.addAttribute("replyList",replyList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/user/conversation";
    }

    /**
     * 刪除回复
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ReturnResult replayDelate(@RequestParam(required = true) Integer id) {
        try {
            replayService.deleteById(id);
            return ReturnResult.SUCCESS("删除回复成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return ReturnResult.FAILUER("删除回复失败");
    }

}
