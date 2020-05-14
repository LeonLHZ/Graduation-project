package com.dms.entity;

public class Employer {
private String eid;
private String username;
private String name;
private String old;
private String sex;
private String telephone;
private Integer state;


public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getEid() {
	return eid;
}
public void setEid(String eid) {
	this.eid = eid;
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
public Integer getState() {
	return state;
}
public void setState(Integer state) {
	this.state = state;
}

}
