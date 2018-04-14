package org.chinamyheart.community.service;

import org.chinamyheart.community.common.utils.Utils;
import org.chinamyheart.community.mapper.UserMapper;
import org.chinamyheart.community.model.Doctor;
import org.chinamyheart.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	//TODO
	public static User getUser(String token) {
		return null;
	}

	public String register(User user){
		userMapper.selectByUsername(user.getUsername());
		String password = user.getPassword();
		try {
			String newPassword = Utils.MD5(password);
			user.setPassword(newPassword);
		}catch (Exception e){
			e.printStackTrace();
		}
		userMapper.insertByUser(user);
		return "success";
	}
}
