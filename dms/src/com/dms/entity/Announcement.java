package com.dms.entity;

import java.util.Date;

public class Announcement {
private String bid;
private String content;
private String aid;
private Admin admin;
private String releasetime;
private SuperAdmin superadmin;
private String annid;



public String getAnnid() {
	return annid;
}
public void setAnnid(String annid) {
	this.annid = annid;
}
public SuperAdmin getSuperadmin() {
	return superadmin;
}
public void setSuperadmin(SuperAdmin superadmin) {
	this.superadmin = superadmin;
}
public Admin getAdmin() {
	return admin;
}
public void setAdmin(Admin admin) {
	this.admin = admin;
}
public String getBid() {
	return bid;
}
public void setBid(String bid) {
	this.bid = bid;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getAid() {
	return aid;
}
public void setAid(String aid) {
	this.aid = aid;
}

public String getReleasetime() {
	return releasetime;
}
public void setReleasetime(String releasetime) {
	this.releasetime = releasetime;
}

}
