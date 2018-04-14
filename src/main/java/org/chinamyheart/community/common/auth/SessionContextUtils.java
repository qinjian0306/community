package org.chinamyheart.community.common.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 会话帮助类
 * @author qj
 */
@Component
public class SessionContextUtils {

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;

	/**
	 * 获取当前上下文请求
	 * @return HttpServletRequest
	 */
	public HttpServletRequest getContextRequest() {
		return request;
	}
	
	/**
	 * 获取当前上下文请求
	 * @return HttpServletRequest
	 */
	public String getContextPath() { 
		return request.getContextPath();
	}
	
	/**
	 * 获取当前上下文请求
	 * @return HttpServletRequest
	 */
	public String getContextBasePath() { 
		return getContextRequest().getScheme() + "://" + getContextRequest().getServerName() + ":" + getContextRequest().getServerPort() + getContextPath() + "/";
	}
	
	/**
	 * 获取当前上下文请求
	 * @return HttpServletRequest
	 */
	public HttpServletResponse getContextResponse() {
		return response;
	}
	
	/**
	 * 获取当前上下文SessionId
	 * @return
	 */
	public String getContextSessionId() { 
		return this.request.getSession().getId();
	}
	
	/**
	 * 获取当前Token
	 * @param tokenName
	 * @return
	 */
	public String getContextToken(String tokenName){
		Cookie[] cookies = request.getCookies();
		if(cookies == null){
			return null;
		}
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals(tokenName)) {
				return cookie.getValue();
			}
		}
		return null;
	}
	
	/**
	 * 获取当前Token
	 * @return
	 */
	public String getContextApiToken(){
		return request.getHeader("token");
	}

	/**
	 * 获取当前apikey
	 * @return
	 */
	public String getContextApiKey(){
		return request.getHeader("apikey");
	}
	
	/**
	 * 获取当前apisign
	 * @return
	 */
	public String getContextApiSign(){
		return request.getHeader("sign");
	}
	
	/**
	 * 设置当前Token
	 * @param tokenName
	 * @param token
	 */
	public void addContextToken(String tokenName,String token){
//		CookieUtils.setCookie(request,response,tokenName,token);
		this.response.addHeader("Set-Cookie", String.format("%s=%s;Path=/; HttpOnly", tokenName,token));
	}
}
