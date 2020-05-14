package com.dms.dao.impl;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.dms.dao.UserDao;
import com.dms.entity.Admin;
import com.dms.entity.User;
import com.dms.utils.DataSourceUtils;

public class UserDaoImpl implements UserDao {

	@Override
	public User login(String username, String password) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where username = ? and password = ? limit 1";
		
		
		return qr.query(sql, new BeanHandler<>(User.class),username,password);
		
	}

	@Override
	public void add(User user) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "insert into user (uid,username,password,type) values (?,?,?,?)";
		
		qr.update(DataSourceUtils.getConnection(), sql,user.getUid(),user.getUsername(),user.getPassword(),user.getType());
		
	}

	@Override
	public void updatePassword(User user) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update user set password = ? where uid = ?";
		
		qr.update(sql,user.getPassword(),user.getUid());
		
	}

	@Override
	public void deleteUser(String sid) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "delete from user where uid = ?";
		qr.update(DataSourceUtils.getConnection(), sql,sid);
		
	}

	@Override
	public User findUserByUserName(String username) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where username = ? limit 1";
		
		
		return qr.query(sql, new BeanHandler<>(User.class),username);
	}

}
