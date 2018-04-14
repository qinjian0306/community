package org.chinamyheart.community.common.auth;

import org.chinamyheart.community.model.User;

import java.io.Serializable;

/**
 * 登录后响应的用户信息
 * @author ZKF
 */
public class LoginResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// token令牌
	private String token;
	// 用户信息
	private User userinfo;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(User userinfo) {
		this.userinfo = userinfo;
	}

}
