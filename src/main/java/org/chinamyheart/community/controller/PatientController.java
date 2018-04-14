package org.chinamyheart.community.controller;

import org.chinamyheart.community.dao.CaseMapper;
import org.chinamyheart.community.model.User;
import org.chinamyheart.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PatientController extends BaseController{
	@Autowired
	private CaseMapper caseMapper;
	// 添加病历--
	@RequestMapping("/register")
	public Map<String,Object> register(@RequestParam String title,
									   @RequestParam String name,
									   @RequestParam int gender,
									   @RequestParam String  contact,
									   @RequestParam String description,
									   @RequestParam(required = false) MultipartFile... attachment) {
		String token = "";
		User user = UserService.getUser(token);
		Map<String, String> paramMap = new HashMap();
		if (user.getRole() == 1) {
			paramMap.put("title", title);
			paramMap.put("name", name);
			paramMap.put("gender", String.valueOf(gender));
			paramMap.put("contact", contact);
			paramMap.put("description", description);
			// TODO
//			if (attachment != null && attachment.length > 0) {
//				Map<String, String> attachments = new HashMap<String, String>();
//				for (MultipartFile f : attachment) {
//
//					attachments.put(f.getName(), "");
//				}
//				paramMap.put("attachment", "");
//			}
		}
		HashMap<String, Object> result = new HashMap();
		try {
			caseMapper.insertByMap(paramMap);
			result.put("result", 0);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", 1);
		}
		return result;
	}
	// --添加病历
}
