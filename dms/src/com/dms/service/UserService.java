package com.dms.service;

import java.sql.SQLException;
import java.util.List;

import com.dms.entity.User;

public interface UserService {

	User login(String username, String password) throws SQLException;

	void add(User user) throws Exception;

	void updatePassword(User user)throws Exception;

	List<User> addStudentByExcel(List<User> list)throws Exception;

	User findUserByUsername(String username)throws Exception;

}
