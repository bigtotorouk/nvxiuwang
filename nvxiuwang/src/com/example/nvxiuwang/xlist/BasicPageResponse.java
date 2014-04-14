package com.example.nvxiuwang.xlist;

import java.util.List;
/**
 * 列表api的返回对象
 * @author bing
 *
 * @param <T>
 */
public class BasicPageResponse<T> {
	private String state;
	private String msg;
	private List<T> data;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
}
