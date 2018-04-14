package org.chinamyheart.community.controller;

import org.chinamyheart.community.dao.DoctorMapper;
import org.chinamyheart.community.model.Doctor;
import org.chinamyheart.community.model.User;
import org.chinamyheart.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DoctorController extends BaseController{
	@Autowired
	DoctorMapper doctorMapper;
	//获取医生状态--
	@RequestMapping("/doctorStatus")
	public Map<String,Object> doctorStatus() {
		String token = "";
		User user = UserService.getUser(token);
		Doctor doctor = doctorMapper.selectByUserId(user.getId());
		HashMap<String, Object> result = new HashMap();
		HashMap<String, Object> dataMap = new HashMap();
		if (doctor != null || doctor.getStatus()== 0) {
			result.put("result", 0);
		} else {
			result.put("result", 1);
		}
		return result;
	}
	//--获取医生状态

	//获取医生状态--
	@RequestMapping("/verify")
	public Map<String,Object> verify(String hospital, String realName, String mobile, String detials) {
		String token = "";
		User user = UserService.getUser(token);
		Doctor doctor = doctorMapper.selectByUserId(user.getId());
		Map<String, Object> result = new HashMap();
		Map<String, Object> dataMap = new HashMap();
		if (doctor != null || doctor.getStatus()== 0) {
			result.put("result", 0);
		} else {
			result.put("result", 1);
		}
		return result;
	}
	//--获取医生状态

}
