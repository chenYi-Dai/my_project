package org.spring.boot.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginViewController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginViewController.class);

	@RequestMapping("/loginUser")
	public String loginUer(HttpServletRequest request){
		logger.info("name =>"+request.getParameter("userName")+"==pwd=>"+request.getParameter("password"));
		request.getParameter("userName");
		request.getParameter("password");
		return null;
	}
}
