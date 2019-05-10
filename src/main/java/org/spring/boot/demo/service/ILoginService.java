package org.spring.boot.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spring.boot.demo.entity.LoginUserEntity;
import org.spring.boot.demo.entity.ResultVO;

public interface ILoginService {
	List<LoginUserEntity> selectPwd(LoginUserEntity userInfo);
	
	ResultVO insertUserInfo(LoginUserEntity userInfo);
}
