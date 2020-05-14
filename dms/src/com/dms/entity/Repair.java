package com.dms.entity;

import java.util.Date;

public class Repair {
private String rid;
private String name;
private String reason;
private Integer state;
private Date starttime;
private Date overtime;
private String dorid;
private String eid;
private String bid;
private Dormitory dormitory;
private Building building;
private Employer employer;


public Employer getEmployer() {
	return employer;
}
public void setEmployer(Employer employer) {
	this.employer = employer;
}
public Dormitory getDormitory() {
	return dormitory;
}
public void setDormitory(Dormitory dormitory) {
	this.dormitory = dormitory;
}
public Building getBuilding() {
	return building;
}
public void setBuilding(Building building) {
	this.building = building;
}
public String getBid() {
	return bid;
}
public void setBid(String bid) {
	this.bid = bid;
}
public String getRid() {
	return rid;
}
public void setRid(String rid) {
	this.rid = rid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getReason() {
	return reason;
}
public void setReason(String reason) {
	this.reason = reason;
}
public Integer getState() {
	return state;
}
public void setState(Integer state) {
	this.state = state;
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
public String getEid() {
	return eid;
}
public void setEid(String eid) {
	this.eid = eid;
}

}
