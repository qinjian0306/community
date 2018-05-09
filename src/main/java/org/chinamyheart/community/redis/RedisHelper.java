package org.chinamyheart.community.redis;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.chinamyheart.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedisHelper {

	@Autowired
	private JedisCli jedisCli;

	/***************** User操作 *******************/
	/**
	 * 获取登录用户信息
	 * 
	 * @param token
	 *            登录token
	 * @return
	 */
	public User getUserByToken(String token) {
		if (StringUtils.isEmpty(token)) {
			return null;
		}
		String json = jedisCli.get(token);
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		return JSON.parseObject(json, User.class);
	}

	/**
	 * 更新用户信息
	 * 
	 * @param token
	 *            登录token
	 * @param user
	 *            修改后的用户信息
	 */
	public void updateUserInfo(String token, User user) {
		if (token == null || StringUtils.isEmpty(token)) {
			return;
		}
		String json = jedisCli.get(token);
		if (StringUtils.isEmpty(json)) {
			return;
		}
		jedisCli.set(token, json, RedisConstant.EXPIRETIME);
	}

	/**
	 * 删除登陆中的用户
	 * 
	 * @param token
	 *            登录token
	 */
	public void deleteUserInfo(String token) {
		if (StringUtils.isEmpty(token)) {
			return;
		}
		jedisCli.delete(token);
	}
}
