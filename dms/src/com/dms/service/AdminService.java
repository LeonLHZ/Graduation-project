package com.dms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dms.entity.Admin;
import com.dms.entity.CheckDor;
import com.dms.entity.PageBean;
import com.dms.entity.Student;

public interface AdminService {
	Admin login(String aid) throws Exception;

	List<Admin> findAllAdmin() throws Exception;

	void updatePassword(String aid, String md5) throws Exception;

	void update(Admin admin) throws SQLException;

	Admin findAdminById(String aid) throws Exception;

	Boolean checkPassword(String aid, String md5) throws SQLException;

	PageBean<Admin> findAllAdminrByPage(int currPage, int pageSize) throws Exception;

	PageBean<Admin> findBuildAdmin(int currPage, int pageSize, String bid)throws Exception;

	void releaseAdmin(String aid)throws Exception;

	void changeBid(String bid, String bid2,String aid)throws Exception;

	void setBid(String aid, String bid)throws Exception;

	void changeChoice(String code, String bid)throws Exception;

	List<CheckDor>  checkstudent(String sid)throws Exception;

}
