package com.dms.dao;

import java.sql.SQLException;

import com.dms.entity.User;

public interface UserDao {

	User login(String username, String password) throws SQLException;

	void add(User user) throws Exception;

	void updatePassword(User user)throws Exception;

	void deleteUser(String sid)throws Exception;

	User findUserByUserName(String username)throws Exception;

}
