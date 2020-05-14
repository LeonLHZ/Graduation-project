package com.dms.entity;

public class Position {
private String pid;
private String sid;
private String dorid;
private String bid;
private Integer numbering;
private Integer status;
private Student student;

public Student getStudent() {
	return student;
}
public void setStudent(Student student) {
	this.student = student;
}
public String getPid() {
	return pid;
}
public void setPid(String pid) {
	this.pid = pid;
}
public String getSid() {
	return sid;
}
public void setSid(String sid) {
	this.sid = sid;
}
public String getDorid() {
	return dorid;
}
public void setDorid(String dorid) {
	this.dorid = dorid;
}
public String getBid() {
	return bid;
}
public void setBid(String bid) {
	this.bid = bid;
}
public Integer getNumbering() {
	return numbering;
}
public void setNumbering(Integer numbering) {
	this.numbering = numbering;
}
public Integer getStatus() {
	return status;
}
public void setStatus(Integer status) {
	this.status = status;
}

}