package org.spring.boot.demo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.boot.demo.entity.MenuEntity;
import org.spring.boot.demo.entity.PageVO;
import org.spring.boot.demo.entity.ResultVO;
import org.spring.boot.demo.service.impl.MenuServiceImpl;
import org.spring.boot.demo.servlet.UploadServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/menu")
public class menuController {
	private static final Logger logger = LoggerFactory.getLogger(menuController.class);
	
	@Autowired
	private MenuServiceImpl menuServiceImpl;
	
	
	@RequestMapping(value="/queryList/{pageSize}/{currentIndex}",method = RequestMethod.GET)
	public ResultVO<PageInfo> listUI(PageVO vo ,MenuEntity menuEntity){
		logger.info("queryList--开始-->");
		ResultVO<PageInfo> res = new ResultVO<PageInfo>(); 
		PageHelper.startPage(vo.getCurrentIndex(),vo.pageSize);
		List<MenuEntity> list = menuServiceImpl.findAll(menuEntity);
		PageInfo<MenuEntity> pageInfo = new PageInfo<>(list);
		res.setCode(200);
		res.setMsg("请求成功!");
		res.setData(pageInfo);
		return res;
	}
	
	@RequestMapping(value="/createInfo",method = RequestMethod.POST)
	public Map<String, ResultVO> createMenuInfo(@RequestBody MenuEntity menuEntity){
		logger.info("menuEntity---"+menuEntity.toString());
		Map<String, ResultVO> createMenuInfo = menuServiceImpl.createMenuInfo(menuEntity);
		return createMenuInfo;
	}
	
}
