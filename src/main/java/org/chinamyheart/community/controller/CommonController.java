package org.chinamyheart.community.controller;

import org.chinamyheart.community.dao.CaseMapper;
import org.chinamyheart.community.dao.DoctorMapper;
import org.chinamyheart.community.dao.ReplyMapper;
import org.chinamyheart.community.dao.UserMapper;
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
@RequestMapping("common")
public class CommonController {
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
	// --注册

	// 登陆--
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
	// --登陆

	//检查用户名是否可用--
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
	//--检查用户名是否可用

	// 获取病例列表--
	@RequestMapping("/getCaseList")
	public Map<String,Object> getCaseList() {
		String token = "";
		User user = UserService.getUser(token);
		HashMap<String, Object> result = new HashMap();
		HashMap<String, Object> dataMap = new HashMap();
		if (user.getRole() == 1) {
			// 患者
			List<Case> caseList = caseMapper.selectByUserId(user.getId());
			dataMap.put("data", caseList);
			result.put("result", 0);
			result.put("data", dataMap);
		} else if (user.getRole() == 2) {
			// 医生
			Doctor doctor = doctorMapper.selectByUserId(user.getId());
			if (doctor != null && doctor.getStatus() == 0) {
				List<Case> caseList = caseMapper.selectAll();
				dataMap.put("data", caseList);
				result.put("result", 0);
				result.put("data", dataMap);
			}
		} else if (user.getRole() == 0) {
			// 管理员
			List<Case> caseList = caseMapper.selectAll();
			dataMap.put("data", caseList);
			result.put("result", 0);
			result.put("data", dataMap);
		}
		return result;
	}
	// --获取病例列表

	// 查看病历--
	@RequestMapping("/viewCase")
	public Map<String,Object> viewCase(int id) {
		String token = "";
		User user = UserService.getUser(token);
		HashMap<String, Object> result = new HashMap();
		if (user == null) {
			result.put("result", 1);
			return result;
		}
		Case cas = caseMapper.selectById(id);
		if (user.getRole() == 1 && user.getId() != cas.getUserId()) {
			// 患者
			result.put("result", 1);
			return result;
		}
		result.put("result", 0);
		result.put("data", cas);
		return result;
	}
	// --查看病历

	// 删除病历--
	@RequestMapping("/removeCase")
	public Map<String,Object> removeCase(int id) {
		String token = "";
		User user = UserService.getUser(token);
		HashMap<String, Object> result = new HashMap();
		if (user == null) {
			result.put("result", 1);
			return result;
		}
		Case cas = caseMapper.selectById(id);
		if (user.getRole() == 1 && user.getId() == cas.getUserId() || user.getRole() == 0) {
			// 患者或管理员
			replyMapper.deleteByCaseId(id);
			caseMapper.deleteById(id);
			result.put("result", 0);
			return result;
		}
		result.put("result", 1);
		return result;
	}
	// --删除病历

	// 锁定病历--
	@RequestMapping("/lockCase")
	public Map<String,Object> lockCase(int id) {
		String token = "";
		User user = UserService.getUser(token);
		HashMap<String, Object> result = new HashMap();
		if (user == null) {
			result.put("result", 1);
			return result;
		}
		Case cas = caseMapper.selectById(id);
		if (user.getRole() == 1 && user.getId() == cas.getUserId() || user.getRole() == 0) {
			// 患者或管理员
			caseMapper.lock(id);
			result.put("result", 0);
			return result;
		}
		result.put("result", 1);
		return result;
	}
	// --锁定病历

	// 获取留言--
	@RequestMapping("/getReplies")
	public Map<String,Object> getReplies(int caseId) {
		String token = "";
		User user = UserService.getUser(token);
		HashMap<String, Object> result = new HashMap();
		if (user == null) {
			result.put("result", 1);
			return result;
		}
		Case cas = caseMapper.selectById(caseId);
		if (user.getRole() == 1 && user.getId() != cas.getUserId()) {
			// 患者
			result.put("result", 1);
			return result;
		}
		List<Reply> replies = replyMapper.selectByCaseId(caseId);
		result.put("result", 0);
		result.put("data", replies);
		return result;
	}
	// --获取留言

	// 新增留言--
	@RequestMapping("/addReply")
	public Map<String,Object> addReply(int caseId, String content) {
		String token = "";
		User user = UserService.getUser(token);
		HashMap<String, Object> result = new HashMap();
		if (user == null) {
			result.put("result", 1);
			return result;
		}
		Case cas = caseMapper.selectById(caseId);
		if (user.getRole() == 1 && user.getId() != cas.getUserId()) {
			// 患者
			result.put("result", 1);
			return result;
		}
		HashMap<String, String> paramMap = new HashMap();
		paramMap.put("userId", String .valueOf(user.getId()));
		paramMap.put("caseId", String .valueOf(caseId));
		paramMap.put("content", content);
		result.put("result", 0);
		return result;
	}
	// --新增留言

	// 删除留言--
	@RequestMapping("/removeReply")
	public Map<String,Object> removeReply(int id) {
		String token = "";
		User user = UserService.getUser(token);
		HashMap<String, Object> result = new HashMap();
		if (user == null) {
			result.put("result", 1);
			return result;
		}
		Reply reply = replyMapper.selectById(id);
		if (user.getRole() == 1 && user.getId() != reply.getUserId()) {
			// 患者
			result.put("result", 1);
			return result;
		}
		replyMapper.deleteById(id);
		result.put("result", 0);
		return result;
	}
	// --删除留言



}
