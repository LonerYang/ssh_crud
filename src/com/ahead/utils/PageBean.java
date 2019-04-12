package com.ahead.utils;

import java.util.List;

public class PageBean {
	
	//当前页数
	private Integer currentPage;
	//每页的条数
	private Integer pageSize;
	//总页数
	private Integer totalPage;
	//总条数
	private Integer totalCount;
	//每页的数据
	private List list;
	public PageBean(Integer currentPage, Integer pageSize, Integer totalCount) {
		this.totalCount = totalCount;
		
		this.currentPage = currentPage;
		
		if(this.currentPage == null){
			//如果没有传当前页数过来就初始化当前页数
			this.currentPage = 1;
		}
		
		this.pageSize = pageSize;
		if(this.pageSize == null){
			//如果没有传每页条数过来就初始化每页条数
			this.pageSize = 3;
		}
		
		//给总页数赋值
		//通过数学的方法来灵活的完成业务逻辑
		this.totalPage = (this.totalCount + this.pageSize - 1)/this.pageSize;
		
		if(this.currentPage < 1){
			//如果当前页数低于1就赋值为1
			this.currentPage = 1;
		}
		if(this.currentPage > this.totalPage){
			//如果当前页数大于总页数就赋值为总页数
			this.currentPage = this.totalPage;
		}
	}
	
	//获得起始索引
	public int getStart(){
		return (this.currentPage - 1) * this.pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
}
