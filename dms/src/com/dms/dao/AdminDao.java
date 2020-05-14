package com.dms.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dms.entity.Admin;
import com.dms.entity.CheckDor;
import com.dms.entity.Student;
import com.dms.entity.User;

public interface AdminDao {
Admin login(String aid)throws Exception ;

List<Admin> findAllAdmin()throws Exception;

void add(User user)throws Exception;

void updatePassword(String aid, String md5)throws Exception;

void update(Admin admin) throws SQLException;

Admin findAdminById(String aid) throws Exception;

Boolean checkPassword(String aid, String md5) throws SQLException;

int findAdminTotalCount()throws SQLException;

List<Admin> finfindAllAdminrByPage(int currPage, int pageSize)throws SQLException, Exception;

int findAdminTotalCountByBid(String bid)throws SQLException;

List<Admin> findBuildAdmin(int currPage, int pageSize, String bid)throws SQLException;

void releaseAdmin(String aid)throws SQLException;

String getBidByaid(String aid)throws SQLException;

void updateBid(String bid,String aid)throws SQLException;

void setBid(String aid, String bid)throws SQLException;

void updateselectDormitory(int status,String bid)throws SQLException;

void updatechangeDormitory(int status,String bid)throws SQLException;

Admin findAdminByUserName(String a)throws SQLException;

List<CheckDor>  checkstudent(String sid)throws SQLException;




}
