package com.dms.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.loading.PrivateClassLoader;

import javafx.collections.ListChangeListener.Change;

public class RequestBean implements Serializable{
private String rid;
private String reason;
private String rnum;
private String bernum;
private String bid;
private Date starttime;
private Date overtime;
private String dorid;
private Integer state;//0是拒绝，1是同意，2是等待处理
private Dormitory dormitory;
private String pid;


public String getPid() {
	return pid;
}
public void setPid(String pid) {
	this.pid = pid;
}
private Student rStudent;
private Student bStudent;


public Student getrStudent() {
	return rStudent;
}
public void setrStudent(Student rStudent) {
	this.rStudent = rStudent;
}
public Student getbStudent() {
	return bStudent;
}
public void setbStudent(Student bStudent) {
	this.bStudent = bStudent;
}
public Dormitory getDormitory() {
	return dormitory;
}
public void setDormitory(Dormitory dormitory) {
	this.dormitory = dormitory;
}

public Integer getState() {
	return state;
}
public void setState(Integer state) {
	this.state = state;
}
public String getRid() {
	return rid;
}
public void setRid(String rid) {
	this.rid = rid;
}
public String getReason() {
	return reason;
}
public void setReason(String reason) {
	this.reason = reason;
}
public String getRnum() {
	return rnum;
}
public void setRnum(String rnum) {
	this.rnum = rnum;
}
public String getBernum() {
	return bernum;
}
public void setBernum(String bernum) {
	this.bernum = bernum;
}
public String getBid() {
	return bid;
}
public void setBid(String bid) {
	this.bid = bid;
}
public Date getStarttime() {
	return starttime;
}
public void setStarttime(Date starttime) {
	this.starttime = starttime;
}
public Date getOvertime() {
	return overtime;
}
public void setOvertime(Date overtime) {
	this.overtime = overtime;
}
public String getDorid() {
	return dorid;
}
public void setDorid(String dorid) {
	this.dorid = dorid;
}


}


