package org.spring.boot.demo.entity;

import java.util.List;

public class PageVO {
	/**
	 * 数据总条数
	 */
	public long total;

	/**
	 * 每页显示的条数
	 */
	public int pageSize;

	/**
	 * 数据总页数
	 */
	public int totalPage;

	/**
	 * 当前是第几页
	 */
	public int currentIndex;

	/**
	 * 分页的数据
	 */
	//public List<?> dataObj;

	/**
	 * 需要显示的页数
	 */
//	public int displayNum = 5; // 显示的页数

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	/*public int getDisplayNum() {
		return displayNum;
	}

	public void setDisplayNum(int displayNum) {
		this.displayNum = displayNum;
	}*/

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public long getTotalPage() {
		return (this.total + this.pageSize - 1) / this.pageSize;
	}

	/*public List<?> getDataObj() {
		return dataObj;
	}

	public void setDataObj(List<?> dataObj) {
		this.dataObj = dataObj;
	}*/

	/**
	 * @return the total
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(Long total) {
		this.total = total;
	}

}