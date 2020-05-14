package com.dms.entity;

import java.util.List;
import java.util.Map;

public class ExcelBean{
	
	private List<Object> list;
	private String sheelName;
	private String excelName;
	
	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
	}
	public String getSheelName() {
		return sheelName;
	}
	public void setSheelName(String sheelName) {
		this.sheelName = sheelName;
	}
	public String getExcelName() {
		return excelName;
	}
	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}
	public ExcelBean(List<Object> list, String sheelName, String excelName) {
		
		this.list = list;
		this.sheelName = sheelName;
		this.excelName = excelName;
	}
	public String[] getStudent() {
		 String[] student= {"学号","姓名","性别","所住楼栋","所住宿舍号","床位","电话"};
	return student;
	}
	
	public String[] getAdmin() {
		String[] admin= {"工号","姓名","性别","管理楼栋"};
	return admin;
	}
	 
	
	
	

}
