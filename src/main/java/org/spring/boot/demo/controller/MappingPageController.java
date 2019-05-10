package org.spring.boot.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/emp/portal")
public class MappingPageController {

	@RequestMapping
	public String index(){
		return "/pages/index";//starter
	}
	
	
	
	@RequestMapping(value="/test")
	@ResponseBody
	public String menu(){
		return "hello word";//starter
	}
	@RequestMapping(value="login")
	public String login(){
		return "/pages/login";//starter
	}
	
	@RequestMapping(value="menu/listUI",method = RequestMethod.GET)
	public String listUI(){
		return "pages/sys/menu_list";
	}
	
	@ResponseBody
	@RequestMapping("menu//shiwu")
	public String sajd(){
		return "nidate";
	}
	
	
	
}
