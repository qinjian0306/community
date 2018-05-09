package org.chinamyheart.community.Intercepetor;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.chinamyheart.community.common.utils.ReturnResult;
import org.chinamyheart.community.redis.JedisCli;
import org.chinamyheart.community.redis.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统拦截器
 */
public class Intercepetor extends HandlerInterceptorAdapter {

    @Autowired
    private JedisCli jedisCli;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        // 定义拦截信息
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setContentType("application/json;charset=UTF-8");
//        response.setHeader("Cache-Control", "no-cache");
//        StringBuffer  s = request.getRequestURL();
//        String uri = request.getRequestURI();
//
//        // 跳过不需要验证的页面
//        if (UrlFilter.isValidationUrls(uri)) {
//            return true;
//        }
//
//        // cookie取值
//        String token = null;
//        Cookie[] cookies = request.getCookies();
//        boolean cookieFlag = false;
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("token")) {
//                    token = cookie.getValue();
//                    cookieFlag = true;
//                }
//                if (cookieFlag && token != null) {
//                    break;
//                }
//            }
//        }
//
//        if (StringUtils.isEmpty(token)) {
//            if (UrlFilter.isAjaxUrls(uri)) {
//                ReturnResult rst = ReturnResult.FAILUER(401, "登录已失效，请重新登录!");
//                response.getWriter().write(JSON.toJSONString(rst));
//            } else {
//                response.sendRedirect("/login.ftl?forwardUrl=" + request.getRequestURI().trim());
//            }
//            return false;
//        }else {
//            String json = jedisCli.get(token);
//            if(StringUtils.isEmpty(json)){
//                if (UrlFilter.isAjaxUrls(uri)) {
//                    ReturnResult rst = ReturnResult.FAILUER(401, "登录已失效，请重新登录!");
//                    response.getWriter().write(JSON.toJSONString(rst));
//                } else {
//                    response.sendRedirect("/login.ftl?forwardUrl=" + request.getRequestURI().trim());
//                }
//                return false;
//            }
//
//            // 通过验证 更新token过期时间
//            jedisCli.set(token, json, RedisConstant.EXPIRETIME);
//        }

        // 放行
        return true;
    }
}
