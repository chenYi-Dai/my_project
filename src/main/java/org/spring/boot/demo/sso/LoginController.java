/*package org.spring.boot.demo.sso;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	private String rightUserName = "admin";
	private String rightPassword = "admin";


	@RequestMapping("/login")
	public String login(HttpServletRequest request){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (null == username || null == password) {
			return "redirect:/login";
		}
		String md5info = rightUserName.toLowerCase() + rightPassword.toLowerCase();
		String realPassword = DigestUtils.md5DigestAsHex(md5info.getBytes());
		if (!password.equals(realPassword)) {
			return "redirect:/login";
		}

		// 校验通过时，在session里放入一个标识
		// 后续通过session里是否存在该标识来判断用户是否登录
		request.getSession().setAttribute("loginName", "admin");
		return "redirect:/emp/portal";

	}
}
*/