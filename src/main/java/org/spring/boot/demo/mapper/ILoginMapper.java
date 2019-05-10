package org.spring.boot.demo.mapper;

import java.util.List;

import org.spring.boot.demo.entity.LoginUserEntity;

public interface ILoginMapper {
	List<LoginUserEntity> vilaUser(String userName);
	
	int insertUserInfo(LoginUserEntity userInfo);
}
