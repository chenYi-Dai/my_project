package org.spring.boot.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.boot.demo.entity.LoginUserEntity;
import org.spring.boot.demo.entity.ResultVO;
import org.spring.boot.demo.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.spring.boot.demo.config.WebSecurityConfig;

@RestController
@RequestMapping("/")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class); 

	@Autowired
	private LoginServiceImpl loginService;

	@GetMapping("/")
	public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String account, Model model) {
		model.addAttribute("name", account);
		return "index";
	}

	@RequestMapping(value="login",method = RequestMethod.POST)
	public String login(@RequestBody LoginUserEntity userInfo,HttpSession session){
		logger.info("jinru 登录方法");
		List<LoginUserEntity> result = loginService.selectPwd(userInfo);
		if(result.size() == 1 && result.size()>0){
			session.setAttribute(WebSecurityConfig.SESSION_KEY,userInfo.getUserName());
			//logger.info("sakj"+session.getAttribute(WebSecurityConfig.SESSION_KEY));
			return "http://localhost:8081/emp/portal";
		}

		return "";
	}

	@RequestMapping(value="/login/regsitUser",method = RequestMethod.POST)
	public ResultVO regsitUserInfo(@RequestBody LoginUserEntity userInfo){
		logger.info("jinru 注册方法");
		ResultVO result = loginService.insertUserInfo(userInfo);
		return result;
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 移除session
		session.removeAttribute(WebSecurityConfig.SESSION_KEY);
		return "redirect:/login";
	}

}
