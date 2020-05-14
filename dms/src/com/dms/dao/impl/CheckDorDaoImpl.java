package com.dms.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.dms.dao.CheckDorDao;
import com.dms.entity.CheckDor;
import com.dms.entity.Student;
import com.dms.utils.DataSourceUtils;

public class CheckDorDaoImpl implements CheckDorDao {

	@Override
	public void checkDor(CheckDor c) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "insert into checkDor (cid,sid,dorid,description,integral,starttime) values (?,?,?,?,?,?)";
		qr.update(DataSourceUtils.getConnection(), sql,c.getCid(),c.getSid(),c.getDorid(),c.getDescription(),c.getIntegral(),c.getStarttime());
	}

}
