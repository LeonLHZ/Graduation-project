package com.dms.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.dms.dao.AdminDao;
import com.dms.entity.Admin;
import com.dms.entity.Building;
import com.dms.entity.CheckDor;
import com.dms.entity.Student;
import com.dms.entity.User;
import com.dms.utils.DataSourceUtils;

public class AdminDaoImpl implements AdminDao {

	@Override
	public Admin login(String aid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from admin where aid = ? limit 1";

		return qr.query(sql, new BeanHandler<>(Admin.class), aid);
	}

	@Override
	public List<Admin> findAllAdmin() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from admin";

		return qr.query(sql, new BeanListHandler<>(Admin.class));
	}

	@Override
	public void add(User user) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "insert into admin (aid,username,name,old,sex,telephone) values (?,?,?,?,?,?)";
		qr.update(DataSourceUtils.getConnection(), sql, user.getUid(), user.getUsername(), user.getAdmin().getName(),
				user.getAdmin().getOld(), user.getAdmin().getSex(), user.getAdmin().getTelephone());

	}

	@Override
	public void updatePassword(String aid, String md5) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update user set password = ? where uid = ?";
		qr.update(sql, md5, aid);

	}

	@Override
	public void update(Admin admin) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update admin set name=?,old=?,telephone=? where aid=?";
		qr.update(sql, admin.getName(), admin.getOld(), admin.getTelephone(), admin.getAid());

	}

	@Override
	public Admin findAdminById(String aid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from admin  left join building on admin.bid = building.bid where aid = ? limit 1";
		Map<String,Object>map = qr.query(sql, new MapHandler(),aid);
		Admin admin = new Admin();
		BeanUtils.populate(admin, map);
		Building building = new Building();
		BeanUtils.populate(building, map);
		admin.setBuilding(building);
		
		
		return admin;

	}

	@Override
	public Boolean checkPassword(String aid, String md5) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where uid = ? and password = ? limit 1";
		User user = qr.query(sql, new BeanHandler<>(User.class), aid, md5);
		if (user == null)
			return false;

		return true;
	}

	@Override
	public int findAdminTotalCount() throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from admin";
		return ((Long) qr.query(sql, new ScalarHandler())).intValue();
	}

	@Override
	public List<Admin> finfindAllAdminrByPage(int currPage, int pageSize) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from admin a left join building b on a.bid=b.bid  limit ?,?";
		List<Map<String,Object>> list = qr.query(sql, new MapListHandler(), (currPage - 1) * pageSize, pageSize);
		List<Admin> aList = new ArrayList<>();
		for (Map<String, Object> map : list) {
			Admin a = new Admin();
			BeanUtils.populate(a, map);
			Building building = new Building();
			BeanUtils.populate(building, map);
			a.setBuilding(building);
			aList.add(a);
		}
		return aList;
	}

	@Override
	public int findAdminTotalCountByBid(String bid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from admin where bid = ?";
		return ((Long) qr.query(sql, new ScalarHandler(), bid)).intValue();

	}

	@Override
	public List<Admin> findBuildAdmin(int currPage, int pageSize, String bid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from admin where bid = ? limit ?,?";
		return qr.query(sql, new BeanListHandler<>(Admin.class), bid, (currPage - 1) * pageSize, pageSize);
	}

	@Override
	public void releaseAdmin(String aid) throws SQLException {

		QueryRunner qr = new QueryRunner();
		String sql = "update admin set bid='' where aid = ?";
		qr.update(DataSourceUtils.getConnection(), sql, aid);
	}

	@Override
	public String getBidByaid(String aid) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "select bid from admin where aid = ?";

		Admin admin = qr.query(DataSourceUtils.getConnection(),sql, new BeanHandler<>(Admin.class), aid);
		return admin.getBid();
	}

	@Override
	public void updateBid(String bid,String aid) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "update admin set bid=? where aid = ?";
		qr.update(DataSourceUtils.getConnection(), sql,bid,aid);
	}

	@Override
	public void setBid(String aid, String bid) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "update admin set bid=? where aid = ?";
		qr.update(DataSourceUtils.getConnection(), sql,bid,aid);
		
	}

	@Override
	public void updateselectDormitory(int status,String bid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update building set selectdormitory=? where bid = ?";
		qr.update(sql,status,bid);
	}

	@Override
	public void updatechangeDormitory(int status,String bid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update building set changedormitory=? where bid = ?";
		qr.update(sql,status,bid);
	}

	@Override
	public Admin findAdminByUserName(String a) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from admin where username=? limit 1";
		return qr.query(sql,new BeanHandler<>(Admin.class),a);
		 
	}

	@Override
	public List<CheckDor> checkstudent(String sid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from checkdor where sid=? limit 1";
		return qr.query(sql,new BeanListHandler<>(CheckDor.class),sid);
	}

}
