package org.chinamyheart.community.controller;

import org.chinamyheart.community.common.utils.ReturnResult;
import org.chinamyheart.community.model.Reply;
import org.chinamyheart.community.service.ReplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 病历回复内容
 */
@RestController
@RequestMapping("/replay")
public class ReplayController extends BaseController {

    @Autowired
    private ReplayService replayService;

    /**
     * 当前病历所有回复
     * @param caseId
     * @return
     */
    @RequestMapping("/list")
    public ReturnResult replayList(@RequestParam(required = true) Integer caseId) {
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
    public ReturnResult replaySave(@RequestParam(required = true) Integer caseId,
                                   @RequestParam Integer userId,
                                   @RequestParam String content) {
        Reply reply = new Reply();
        reply.setCaseId(caseId);
        reply.setUserId(userId);
        reply.setContent(content);
        try {
            int result = replayService.insert(reply);
            if (result > 0) {
                return ReturnResult.SUCCESS("新增回复成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnResult.FAILUER("新增回复失败");
    }

    /**
     * 刪除回复
     * @param id
     * @return
     */
    @RequestMapping("/delete")
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
