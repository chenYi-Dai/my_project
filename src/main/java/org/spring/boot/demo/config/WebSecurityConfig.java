package org.spring.boot.demo.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter{

	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
	
	/**
	 * 登录session key
	 */
	public final static String SESSION_KEY = "user";

	@Bean
	public SecurityInterceptor getSecurityInterceptor() {
		return new SecurityInterceptor();
	}
	
	public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        // 排除配置
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login**");

        // 拦截配置 配置了拦截的路径会进入SecurityInterceptor拦截并进行判断是否拥有session
        addInterceptor.addPathPatterns("/**");
    }

	/**
	 * 拦截所有的路径如果登陆就给其一个标识 如果没有直接跳转登陆页面
	 * @author test
	 *
	 */
	private class SecurityInterceptor extends HandlerInterceptorAdapter {

		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			
			HttpSession session = request.getSession();
			if (session.getAttribute(SESSION_KEY) != null)
				return true;

			// 跳转登录
			String url = "/login";
			response.sendRedirect(url);
			return false;
		}
	}


}
