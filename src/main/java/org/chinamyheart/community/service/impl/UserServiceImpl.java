package org.chinamyheart.community.service.impl;

import com.alibaba.fastjson.JSON;
import org.chinamyheart.community.common.auth.LoginResponse;
import org.chinamyheart.community.common.utils.GUIDUtils;
import org.chinamyheart.community.common.utils.MD5Util;
import org.chinamyheart.community.mapper.UserMapper;
import org.chinamyheart.community.model.User;
import org.chinamyheart.community.redis.JedisCli;
import org.chinamyheart.community.redis.RedisConstant;
import org.chinamyheart.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private JedisCli jedisCli;

	public List<User> getUserList(User user){
		return userMapper.getUserList(user);
	}

	public List<User> getUserByUsername(User user){
		return userMapper.getUserByUsername(user);
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public LoginResponse checkLogin(User user) {

		// 登录类型
		LoginResponse result = new LoginResponse();
		// 查询用户
		List<User> userList = userMapper.getUserList(user);
		if (userList != null && userList.size() == 1) {//按理只能查出一条数据
			user = userList.get(0);
		} else {
			return null;
		}

		// 组装
		String md5UserId = MD5Util.md5(String.valueOf(user.getId()));
		String userTokenInfo = RedisConstant.USER_TOKEN_KEY + md5UserId + "_";
		String token = userTokenInfo + GUIDUtils.getGUIDString();

		// redis
		jedisCli.removeByPattern(userTokenInfo);
		user.setPassword(null);
		jedisCli.set(token, JSON.toJSONString(user), RedisConstant.EXPIRETIME);

		// 封装LoginResponse
		result.setToken(token);
		result.setUserinfo(user);
		return result;
	}
}
