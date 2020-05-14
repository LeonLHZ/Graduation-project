package com.dms.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.dms.dao.SuperAdminDao;
import com.dms.entity.SuperAdmin;
import com.dms.entity.User;
import com.dms.utils.DataSourceUtils;

public class SuperAdminDaoImpl implements SuperAdminDao {

	@Override
	public SuperAdmin login(String said) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from superadmin where said = ? limit 1";
		
		return qr.query(sql, new BeanHandler<>(SuperAdmin.class),said);
	}

	@Override
	public void updatePassword(String username, String md5) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update superadmin set password=? where username=?";
		qr.update(sql,md5,username);
		
	}

	@Override
	public Boolean checkPassword(String username, String md5) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where username = ? and password = ? limit 1";
		 User user = qr.query(sql, new BeanHandler<>(User.class),username,md5);
		 if(user==null)
			 return false;
		 return true;
	}

	@Override
	public void update(SuperAdmin superAdmin) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql="update superadmin set name=?,old=?,sex=? where said=?";
		
		qr.update(sql,superAdmin.getName(),superAdmin.getOld(),superAdmin.getSex(),superAdmin.getSaid());
		
	}

}
