package org.spring.boot.demo.sso;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginMapping {

	@RequestMapping("login")
	public String login(){
		return "/pages/login";
	}
	
}
