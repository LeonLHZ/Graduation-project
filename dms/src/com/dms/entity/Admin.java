package com.dms.entity;

public class Admin {
private String aid;//管理员ID
private String username;
private String name;//姓名
private String old;//年龄
private String sex;//性别
private String telephone;//电话
private String bid;
private Building building;


public String getBid() {
	return bid;
}
public void setBid(String bid) {
	this.bid = bid;
}
public Building getBuilding() {
	return building;
}
public void setBuilding(Building building) {
	this.building = building;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getAid() {
	return aid;
}
public void setAid(String aid) {
	this.aid = aid;
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getOld() {
	return old;
}
public void setOld(String old) {
	this.old = old;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getTelephone() {
	return telephone;
}
public void setTelephone(String telephone) {
	this.telephone = telephone;
}

}
