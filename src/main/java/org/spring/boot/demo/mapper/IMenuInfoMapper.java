package org.spring.boot.demo.mapper;


import java.util.List;

import org.spring.boot.demo.entity.MenuEntity;

import com.github.pagehelper.PageInfo;


public interface IMenuInfoMapper {
	public List<MenuEntity> queryListAll(MenuEntity menuEntity);
	public int createMenuInfo(MenuEntity menuEntity);
}
