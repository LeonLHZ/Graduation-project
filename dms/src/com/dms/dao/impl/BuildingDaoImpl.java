package com.dms.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.dms.dao.BuildingDao;
import com.dms.entity.Admin;
import com.dms.entity.Building;
import com.dms.entity.Dormitory;
import com.dms.utils.DataSourceUtils;

public class BuildingDaoImpl implements BuildingDao {

	@Override
	public void add(Building building) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into building (bid,number,studentsex) values(?,?,?)";
		qr.update(sql,building.getBid(),building.getNumber(),building.getStudentsex());
	}

	

	@Override
	public List<Building> findBuildingBySex(String sex) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from building,admin where building.bid = admin.bid and building.studentsex = ? order by number";
		
		List<Map<String, Object>>mlist = qr.query(sql,new MapListHandler(),sex);
		List<Building> list = new ArrayList<>();
		for(Map<String,Object> map : mlist) {
			
			
			Building building = new Building();
			BeanUtils.populate(building, map);
			
			
			list.add(building);
			
		}
		return list;
	}

	@Override
	public List<Building> findBuidlingByAid(String aid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from building where aid = ? order by number";
		
		return qr.query(sql, new BeanListHandler<>(Building.class),aid );
	}

	@Override
	public int findBuildCount() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from building ";
		return ((Long) qr.query(sql, new ScalarHandler())).intValue();
	}

	@Override
	public List<Building> findAllBuilding(int currPage, int pageSize) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from building limit ?,?";
		
		return qr.query(sql, new BeanListHandler<>(Building.class),(currPage-1)*pageSize,pageSize );	}



	@Override
	public void cutAdminNumber(String bid) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql ="update building set adminnumber = adminnumber+? where bid = ?";
		qr.update(DataSourceUtils.getConnection(),sql,-1,bid);
		
	}



	@Override
	public void increatAdminnumber(String bid) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql ="update building set adminnumber = adminnumber+? where bid = ?";
		int i = 1;
		qr.update(DataSourceUtils.getConnection(),sql,i,bid);
		
	}



	@Override
	public List<Building> findAllBuilding() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from building";
		
		return qr.query(sql, new BeanListHandler<>(Building.class));
	}



	@Override
	public void updateStudentNumber(Dormitory dormitory) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql="update building set studentnumber = studentnumber+?,dornumber = dornumber+? where bid = ?";
		int i =1;
		qr.update(DataSourceUtils.getConnection(), sql,dormitory.getNumber(),i,dormitory.getBuilding().getBid());
		
	}



	@Override
	public void updateNowStudentNumber(String dorid) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql="update building set nowstudentnumber = nowstudentnumber+? where bid =(select bid from dormitory where dorid = ? limit 1)";
		int i =1;
		qr.update(DataSourceUtils.getConnection(), sql,i,dorid);
	}



	@Override
	public Building findBuildByBid(String bid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from building where bid=? limit 1";
		return qr.query(sql, new BeanHandler<>(Building.class),bid);
	}



	@Override
	public void cutNowStudentNumber(String bid) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql="update building set nowstudentnumber = nowstudentnumber+? where bid =?";
		int i =-1;
		qr.update(DataSourceUtils.getConnection(), sql,i,bid);
		
	}



	@Override
	public Building findBuildingByNumber(int num) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from building where number=? limit 1";
		return qr.query(sql, new BeanHandler<>(Building.class),num);
	}

}
