package com.dms.entity;

import java.util.Date;

import com.dms.constant.Constant;

public class CheckDor {
	private String cid;
	private Student student;
	private String sid;
	private Dormitory dormitory;
	private String dorid;
	private String description;
	private String integral;
	private Date starttime;
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public Dormitory getDormitory() {
		return dormitory;
	}
	public void setDormitory(Dormitory dormitory) {
		this.dormitory = dormitory;
	}
	public String getDorid() {
		return dorid;
	}
	public void setDorid(String dorid) {
		this.dorid = dorid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public String getIntegral() {
		if(description==Constant.HIGH_POWER_APPLIANCES)
			return "-5";
		if(description==Constant.KEEP_A_PET)
			return "-3";
		if(description==Constant.SMOKES)
			return "-2";
		if(description==Constant.DIRTY)
				return "-1";
		return integral;
	}
	public void setIntegral(String integral) {
		this.integral = integral;
	}
	
	
   
}
