package org.chinamyheart.community.controller;

import org.chinamyheart.community.model.User;
import org.chinamyheart.community.redis.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisBaseController extends BaseController {

	@Autowired
	private RedisHelper redisHelper;

	public User getCurrentUserInfoByToken() {
		String token = sessionContextUtils.getContextToken("token");
		return redisHelper.getUserByToken(token);
	}


	public void deleteUserInfo() {
		String token = sessionContextUtils.getContextToken("token");
		redisHelper.deleteUserInfo(token);
	}

	public void updateUserInfo(User user) {
		String token = sessionContextUtils.getContextToken("token");
		redisHelper.updateUserInfo(token, user);
	}

}
