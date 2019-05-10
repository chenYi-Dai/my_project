package org.spring.boot.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spring.boot.demo.entity.LoginUserEntity;
import org.spring.boot.demo.entity.ResultVO;
import org.spring.boot.demo.mapper.ILoginMapper;
import org.spring.boot.demo.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private ILoginMapper loginDao;
	
	@Override
	public List<LoginUserEntity> selectPwd(LoginUserEntity userInfo) {
		List<LoginUserEntity> vilaUser = loginDao.vilaUser(userInfo.getUserName());
		return vilaUser;
	}

	@Override
	public ResultVO insertUserInfo(LoginUserEntity userInfo) {
		ResultVO resultVo = new ResultVO();
		int insertUserInfo;
		List<LoginUserEntity> vilaUser = loginDao.vilaUser(userInfo.getUserName());
		if(vilaUser.size() > 0){
			if( vilaUser.get(0).getUserName().equals(userInfo.getUserName())){
				resultVo.setCode(002);
				resultVo.setMsg("错误002,该用户名已经存在!");
				return resultVo;
			}
			insertUserInfo = loginDao.insertUserInfo(userInfo);
		}
		insertUserInfo = loginDao.insertUserInfo(userInfo);
		if(insertUserInfo != 1){
			resultVo.setCode(002);
			resultVo.setMsg("错误002,该用户添加失败!");
		}else {
			resultVo.setCode(200);
			resultVo.setMsg("恭喜用户"+userInfo.getUserName()+",注册成功!");
		}
		return resultVo;
	}
	
	
	
}
