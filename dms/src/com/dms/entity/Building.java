package com.dms.entity;

import java.util.ArrayList;
import java.util.List;

public class Building {
private String bid;//楼栋id
private Integer number;//楼栋号码
private String studentsex;//所住学生性别
private Integer adminnumber;
private List<Dormitory> list;
private List<Admin> aList;
private Integer nowstudentnumber;
private Integer studentnumber;
private Integer dornumber;
private Integer selectdormitory;
private Integer changedormitory;

public Integer getSelectdormitory() {
	return selectdormitory;
}
public void setSelectdormitory(Integer selectdormitory) {
	this.selectdormitory = selectdormitory;
}
public Integer getChangedormitory() {
	return changedormitory;
}
public void setChangedormitory(Integer changedormitory) {
	this.changedormitory = changedormitory;
}
public Integer getNowstudentnumber() {
	return nowstudentnumber;
}
public void setNowstudentnumber(Integer nowstudentnumber) {
	this.nowstudentnumber = nowstudentnumber;
}
public Integer getStudentnumber() {
	return studentnumber;
}
public void setStudentnumber(Integer studentnumber) {
	this.studentnumber = studentnumber;
}
public Integer getDornumber() {
	return dornumber;
}
public void setDornumber(Integer dornumber) {
	this.dornumber = dornumber;
}
public Integer getAdminnumber() {
	return adminnumber;
}
public void setAdminnumber(Integer adminnumber) {
	this.adminnumber = adminnumber;
}
public List<Admin> getaList() {
	return aList;
}
public void setaList(List<Admin> aList) {
	this.aList = aList;
}
public String getBid() {
	return bid;
}
public void setBid(String bid) {
	this.bid = bid;
}
public Integer getNumber() {
	return number;
}
public void setNumber(Integer number) {
	this.number = number;
}
public String getStudentsex() {
	return studentsex;
}
public void setStudentsex(String studentsex) {
	this.studentsex = studentsex;
}

public List<Dormitory> getList() {
	return list;
}
public void setList(List<Dormitory> list) {
	this.list = list;
}

}
