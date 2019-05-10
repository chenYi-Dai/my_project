package org.spring.boot.demo.entity;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;

public class PageResult<T> implements Serializable{

	private static final long serialVersionUID = -2387605377597876821L;
	
	private PageVO page;
	
	private List<T>result;

	public PageResult() {
		super();
	}

	public PageResult(List<T> result) {
		this.result = result;
	}
	
	public PageResult(Page<T> pageInfo){
		page = new PageVO();
		page.setCurrentIndex(pageInfo.getPageNum());
		page.setPageSize(pageInfo.getPages());
		page.setPageSize(pageInfo.getPages());
		page.setTotal(pageInfo.getTotal());
		this.result = pageInfo.getResult();
	}
	
	public PageVO getPageVo(){
		return page;
	}
	
	public List<T> getResult(){
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public PageVO getPage() {
		return page;
	}

	public void setPage(PageVO page) {
		this.page = page;
	}
	
	
}
