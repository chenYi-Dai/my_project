package org.spring.boot.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.boot.demo.controller.LoginController;

@WebFilter(filterName="loginFilter",urlPatterns="/emp/*")
public class LoginFilter implements Filter{

	private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class); 
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		//System.out.println("测试过滤器2---请求开始");
		//logger.info("request.getParameter==>"+request.getParameter("userName"));
        chain.doFilter(request, response);
        //System.out.println("测试过滤器2---请求结束");
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
