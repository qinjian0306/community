package org.chinamyheart.community.Intercepetor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class IntercepetorConfig extends WebMvcConfigurerAdapter {

    protected static final Logger logger = LoggerFactory.getLogger(IntercepetorConfig.class);

    /**
     * 注入自定义的拦截器对象
     * @return
     */
    @Bean
    public Intercepetor getMyIntercepetor(){
        return new Intercepetor();
    }

    /**
     * 静态资源放行
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/public/**").addResourceLocations("classpath:/public/**");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/**");
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/**");
        super.addResourceHandlers(registry);
    }

    /**
     * 注册 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptor = registry.addInterceptor(getMyIntercepetor());

        // 排除配置
        interceptor.excludePathPatterns("/user/login");
        interceptor.excludePathPatterns("/user/register");
        interceptor.excludePathPatterns("/user/ifExist");

        // 拦截配置
        interceptor.addPathPatterns("/*");
    }
}