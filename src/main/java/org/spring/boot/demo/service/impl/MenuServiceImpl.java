package org.spring.boot.demo.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.boot.demo.entity.MenuEntity;
import org.spring.boot.demo.entity.PageVO;
import org.spring.boot.demo.entity.ResultVO;
import org.spring.boot.demo.mapper.IMenuInfoMapper;
import org.spring.boot.demo.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

@Service
public class MenuServiceImpl implements IMenuService{
	
	private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
	
	@Autowired
	private IMenuInfoMapper menuInfoDao;

	@Override
	public List<MenuEntity> findAll(MenuEntity menu) {
		logger.info("---findAll------>begin");
		return menuInfoDao.queryListAll(menu);
	}
	
	public Map<String, ResultVO> createMenuInfo(MenuEntity menu){
		Map<String, ResultVO> map = new HashMap<>();
		ResultVO vo = new ResultVO();
		if(menu.getName() == "" || menu.getName() == null){
			vo.setCode(100);
			vo.setMsg("名字不能为空~~");
			map.put("result", vo);
			return map;
		}
		int createMenuInfo = menuInfoDao.createMenuInfo(menu);
		if(createMenuInfo > 0){
			vo.setCode(200);
			vo.setMsg("sucess");
		}else{
			vo.setCode(100);
			vo.setMsg("fail");
		}
		 map.put("result", vo);
		return map;
	}
}
