package org.chinamyheart.community.service.impl;

import org.chinamyheart.community.mapper.UserMapper;
import org.chinamyheart.community.model.User;
import org.chinamyheart.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	@Override
	public User getUser(String token) {
		return null;
	}

	@Override
	public User selectByUsername(String username) {
		User user = userMapper.selectByUsername(username);
		return user;
	}

	@Override
	public String insertByUser(User user) {
		userMapper.insertByUser(user);
		return "success";
	}
}
