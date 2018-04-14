package org.chinamyheart.community.controller;

import org.chinamyheart.community.mapper.CaseMapper;
import org.chinamyheart.community.mapper.DoctorMapper;
import org.chinamyheart.community.mapper.ReplyMapper;
import org.chinamyheart.community.mapper.UserMapper;
import org.chinamyheart.community.model.Case;
import org.chinamyheart.community.model.Doctor;
import org.chinamyheart.community.model.Reply;
import org.chinamyheart.community.model.User;
import org.chinamyheart.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/common")
public class CommonController extends BaseController{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private DoctorMapper doctorMapper;
	@Autowired
	private CaseMapper caseMapper;
	@Autowired
	private ReplyMapper replyMapper;
	// 注册--
	@RequestMapping("/register")
	public Map<String,Object> registercomm(@RequestParam String username,
									   @RequestParam(required = false) String nickName,
									   @RequestParam String password,
									   @RequestParam int role,
									   @RequestParam(required = false) String email,
									   @RequestParam(required = false) String mobile) {
		HashMap<String, String> paramMap = new HashMap();
		paramMap.put("username", username);
		paramMap.put("password", password);
		paramMap.put("role", String.valueOf(role));
		if (nickName != null && !nickName.equals("")) {
			paramMap.put("nickName", nickName);
		}
		if (email != null && !email.equals("")) {
			paramMap.put("email", email);
		}
		if (mobile != null && !mobile.equals("")) {
			paramMap.put("mobile", mobile);
		}
		HashMap<String, Object> result = new HashMap();
		try {
			userMapper.insertByMap(paramMap);
			result.put("result", 0);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", 1);
		}
		return result;
	}

	// 登陆
	@RequestMapping("/login")
	public Map<String,Object> login(String username, String password) {
		User user = userMapper.selectByUsername(username);
		HashMap<String, Object> result = new HashMap();
		HashMap<String, Object> dataMap = new HashMap();
		if (user.getUsername().equals(password)) {
			result.put("result", 0);
			dataMap.put("role", user.getRole());
			dataMap.put("token", "");
			result.put("data", dataMap);
		} else {
			result.put("result", 1);
		}
		return result;
	}

	//检查用户名是否可用
	@RequestMapping("/ifExist")
	public Map<String,Object> ifExist(String username) {
		User user = userMapper.selectByUsername(username);
		HashMap<String, Object> result = new HashMap();
		if (user == null) {
			result.put("result", 0);
		} else {
			result.put("result", 1);
		}
		return result;
	}



}
