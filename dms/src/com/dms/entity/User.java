package com.dms.entity;

public class User {
private String uid;
private String username;
private String password;
private String type;
private Admin admin;
private Student student;
private Employer employer;
private SuperAdmin superAdmin ;
public String getUid() {
	return uid;
}
public void setUid(String uid) {
	this.uid = uid;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public Admin getAdmin() {
	return admin;
}
public void setAdmin(Admin admin) {
	this.admin = admin;
}
public Student getStudent() {
	return student;
}
public void setStudent(Student student) {
	this.student = student;
}

public Employer getEmployer() {
	return employer;
}
public void setEmployer(Employer employer) {
	this.employer = employer;
}
public SuperAdmin getSuperAdmin() {
	return superAdmin;
}
public void setSuperAdmin(SuperAdmin superAdmin) {
	this.superAdmin = superAdmin;
}

}
