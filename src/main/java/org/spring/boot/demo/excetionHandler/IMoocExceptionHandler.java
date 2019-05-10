package org.spring.boot.demo.excetionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class IMoocExceptionHandler {
	
	public static final String IMOOC_ERROR_VIEW ="error";
	
	/**
	 * 捕获异常信息并对其进行视图返回
	 * @param request 请求参数
	 * @param response	响应参数
	 * @param e	异常
	 * @return	返回一个视图
	 * @throws Exception
	 */
	@ExceptionHandler(value=Exception.class)
	public Object errorHandler(HttpServletRequest request, HttpServletResponse response,Exception e) throws Exception{
		e.printStackTrace();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception",e);
		mav.addObject("url", request.getRequestURI());
		mav.setViewName(IMOOC_ERROR_VIEW);
		return mav;
	}
}
