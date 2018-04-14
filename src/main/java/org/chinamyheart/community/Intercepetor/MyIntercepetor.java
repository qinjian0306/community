package org.chinamyheart.community.Intercepetor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 */
public class MyIntercepetor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 定义拦截信息
//        System.out.println("拦截器拦截...");
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("code",-1);
//        jsonObject.put("token",null);
//        if(jsonObject != null){
//            PrintWriter writer = response.getWriter();
//            writer.write(jsonObject.toString());
//            writer.close();
//            return false;
//        }
        // 放行
        return true;
    }
}
