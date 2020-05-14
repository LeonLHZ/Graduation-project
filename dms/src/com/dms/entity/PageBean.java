package com.dms.entity;

import java.util.List;

public class PageBean<E> {
	private List<E> list;
	private Integer currPage;
	private Integer totalPage;
	private Integer totalCount;
	private Integer pageSize;
	public List<E> getList() {
		return list;
	}
	public void setList(List<E> list) {
		this.list = list;
	}
	public Integer getCurrPage() {
		return currPage;
	}
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	public Integer getTotalPage() {
		return (int) Math.ceil((totalCount*1.0)/pageSize);
	}
	
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public PageBean(List<E> list, Integer currPage, Integer totalCount, Integer pageSize) {
		super();
		this.list = list;
		this.currPage = currPage;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
	}
	public PageBean() {
		
	}
	
}