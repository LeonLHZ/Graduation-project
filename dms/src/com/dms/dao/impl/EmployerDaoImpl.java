package com.dms.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.dms.dao.EmployerDao;
import com.dms.entity.Admin;
import com.dms.entity.Employer;
import com.dms.entity.User;
import com.dms.utils.DataSourceUtils;

public class EmployerDaoImpl implements EmployerDao{

	@Override
	public void add(User user) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql="insert into employer (eid,username,name,sex,telephone,state) values(?,?,?,?,?,?)";
		qr.update(DataSourceUtils.getConnection(),sql,user.getUid(),user.getUsername(),user.getEmployer().getName(),user.getEmployer().getSex(),user.getEmployer().getTelephone(),0);
		
	}

	@Override
	public Employer login(String eid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select * from employer where eid=? limit 1";
		return qr.query(sql, new BeanHandler<>(Employer.class),eid);
	}

	

	@Override
	public void update(Employer employer) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="update employer set name=?,old=?,telephone=? where eid=?";
		qr.update(sql,employer.getName(),employer.getOld(),employer.getTelephone(),employer.getEid());
	}

	@Override
	public Employer findEmployerByEid(String eid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select * from employer where eid=?";
		return qr.query(sql, new BeanHandler<>(Employer.class),eid);
	}

	@Override
	public void updatePassword(String eid,String md5) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="update user set password=? where uid=?";
		qr.update(sql,md5,eid);
	}

	@Override
	public Employer checkPassword(String eid, String old) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where uid = ? and password = ? limit 1";
		return qr.query(sql, new BeanHandler<>(Employer.class),eid,old);
	}

	@Override
	public List<Employer> findByNumber(String number) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from employer where username like ?";
		return qr.query(sql, new BeanListHandler<>(Employer.class),"%"+number+"%");
	}

	@Override
	public int findTotalCount() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from employer";
		return ((Long) qr.query(sql, new ScalarHandler())).intValue();
	}

	@Override
	public List<Employer> findAllEmployerByPage(int currPage, int pageSize) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from employer limit ?,?";
		return qr.query(sql, new BeanListHandler<>(Employer.class),(currPage-1)*pageSize,pageSize);
	}

	@Override
	public List<Employer> findAllEmployer() throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from employer";
		return qr.query(sql, new BeanListHandler<>(Employer.class));
	}

	@Override
	public Employer findEmployerByUserName(String username) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from employer where username=? limit 1";
		return qr.query(sql,new BeanHandler<>(Employer.class),username);
	}

}
