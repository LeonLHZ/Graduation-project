package com.dms.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {
private String sid;//学生ID
private String username;
private Integer level;	//年级
private Date starttime;	//开始入住时间
private String leavetime;	//离开时间
private String name;//	姓名
private Integer old;//	年龄
private String sex;	//性别
private String major;	//专业
private String college;	//学院
private String telephone;	//电话
private String teacherphone;//	辅导员电话
private Dormitory dormitory;	//所属寝室
private Building building;//所属楼栋
private String dorid;
private String bid;
private String pid;
private Position position;
private Integer year;





public Integer getYear() {
	return year;
}
public void setYear(Integer year) {
	this.year = year;
}
public Position getPosition() {
	return position;
}
public void setPosition(Position position) {
	this.position = position;
}
public String getPid() {
	return pid;
}
public void setPid(String pid) {
	this.pid = pid;
}
public String getBid() {
	return bid;
}
public void setBid(String bid) {
	this.bid = bid;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getDorid() {
	return dorid;
}
public void setDorid(String dorid) {
	this.dorid = dorid;
}
public Building getBuilding() {
	return building;
}
public void setBuilding(Building building) {
	this.building = building;
}
public String getSid() {
	return sid;
}
public void setSid(String sid) {
	this.sid = sid;
}

public Integer getLevel() {
	return level;
}
public void setLevel(Integer level) {
	this.level = level;
}
public Date getStarttime() {
	return starttime;
}
public void setStarttime(Date starttime) {
	this.starttime = starttime;
}
public String getLeavetime() {
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date d = new Date(starttime.getYear()+(year-level+1),6, 30, 00,00, 00);
	
	return simpleDateFormat.format(d);
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Integer getOld() {
	return old;
}
public void setOld(Integer old) {
	this.old = old;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getMajor() {
	return major;
}
public void setMajor(String major) {
	this.major = major;
}
public String getCollege() {
	return college;
}
public void setCollege(String college) {
	this.college = college;
}
public String getTelephone() {
	return telephone;
}
public void setTelephone(String telephone) {
	this.telephone = telephone;
}
public String getTeacherphone() {
	return teacherphone;
}
public void setTeacherphone(String teacherphone) {
	this.teacherphone = teacherphone;
}
public Dormitory getDormitory() {
	return dormitory;
}
public void setDormitory(Dormitory dormitory) {
	this.dormitory = dormitory;
}
	
}
