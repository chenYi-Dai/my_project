package org.spring.boot.demo.entity;

/**
 * 请求返回的最外层对象
 * @author 丶待定
 *
 */
public class ResultVO<T> {
	
	private String msg;
	private Integer code;
	private T Data;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public T getData() {
		return Data;
	}
	public void setData(T data) {
		Data = data;
	}
	
	
}
