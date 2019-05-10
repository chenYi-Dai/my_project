package org.spring.boot.demo.service;


import java.util.List;

import org.spring.boot.demo.entity.MenuEntity;
import org.spring.boot.demo.entity.PageVO;

import com.github.pagehelper.PageInfo;

public interface IMenuService {
	public List<MenuEntity> findAll(MenuEntity menu);
}
