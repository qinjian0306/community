package org.chinamyheart.community.Intercepetor;

public class UrlFilter {
	/**
	 * 不需要验证url
	 */
	private static String[] VALIDATIONURLS = { 
			"/index.html", // 首页
			"/login.html", // 登录页
			"/register.html", // 注册页
			"/error/", // 404 500页面
			"/user/login", // 登录
			"/user/register", // 注册
			"/user/logout", // 退出
	};

	/**
	 * AJAX请求url
	 */
	private static String[] AJAXRLS = {
			"/user/ifExist", // 验证用户名唯一
	};

	public static boolean isValidationUrls(String url) {
		boolean flag = false;
		for (String s : VALIDATIONURLS) {
			if (url.startsWith(s)) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	public static boolean isAjaxUrls(String url) {
		boolean flag = false;
		for (String s : AJAXRLS) {
			if (url.startsWith(s)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

}
