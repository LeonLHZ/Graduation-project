package com.dms.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.dms.dao.RepairDao;
import com.dms.entity.Admin;
import com.dms.entity.Building;
import com.dms.entity.Dormitory;
import com.dms.entity.Employer;
import com.dms.entity.Repair;
import com.dms.utils.DataSourceUtils;
import com.dms.utils.MyConventer;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.Select;

public class RepairDaoImpl implements RepairDao{

	@Override
	public void addRepair(Repair repair) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into repair (rid,name,reason,state,starttime,dorid,bid) values(?,?,?,?,?,?,?)";
		qr.update(sql,repair.getRid(),repair.getName(),repair.getReason(),repair.getState(),repair.getStarttime(),repair.getDorid(),repair.getBid());
	}

	@Override
	public List<Repair> findAllRepair(String bid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from building where bid = ?";
		String s = "select * from repair join building on repair.bid=building.bid join dormitory on repair.dorid=dormitory.dorid where repair.bid = ? and repair.state=2";
		//String s = "select * from repair join dormitory on repair.dorid=dormitory.dorid where bid = ? and state=2";
		Building building = qr.query(sql,new BeanHandler<>(Building.class),bid);
		List<Repair> rList = new ArrayList<>();
		//for (Building building : bList) {
			List<Map<String, Object>> mlist = qr.query(s, new MapListHandler(),bid);
			for (Map<String, Object> map :mlist) {
				Repair repair = new Repair();
				ConvertUtils.register(new MyConventer(), Date.class);
				BeanUtils.populate(repair, map);
				Building b = new Building();
				BeanUtils.populate(b, map);
				repair.setBuilding(building);
				Dormitory dormitory = new Dormitory();
				BeanUtils.populate(dormitory, map);
				repair.setDormitory(dormitory);
				rList.add(repair);
			}
		//}
	
		return rList;
	}

	@Override
	public void dealEmployer(String rid, String eid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update repair set eid = ?,state = ? where rid = ? ";
		qr.update(sql,eid,1,rid);
	}

	@Override
	public List<Repair> employerFindRepair(String eid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = "select * from repair join building on repair.bid=building.bid join dormitory on repair.dorid=dormitory.dorid where repair.eid = ? and repair.state=1";
		List<Map<String, Object>> list = qr.query(sql, new MapListHandler(),eid);
		List<Repair> rList = new ArrayList<>();
		for (Map<String, Object> map : list) {
			Repair repair = new Repair();
			ConvertUtils.register(new MyConventer(), Date.class);
			BeanUtils.populate(repair, map);
			Building b = new Building();
			BeanUtils.populate(b, map);
			repair.setBuilding(b);
			Dormitory dormitory = new Dormitory();
			BeanUtils.populate(dormitory, map);
			repair.setDormitory(dormitory);
			rList.add(repair);
			
		}
	
		
		
	
		return rList;
	}

	@Override
	public List<Repair> findrepairByDorid(String dorid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from repair r join dormitory d on r.dorid = d.dorid where r.dorid = ?";
		String s = "select * from building where bid = ?";
		String q = "select * from employer where eid = ?";
		
		List<Map<String,Object>>map = qr.query(sql,new MapListHandler(),dorid);
		List<Repair> rList = new ArrayList<>();
		for (Map<String, Object> m : map) {
			Repair repair = new Repair();
			ConvertUtils.register(new MyConventer(), Date.class);
			BeanUtils.populate(repair, m);
			Dormitory dormitory = new Dormitory();
			BeanUtils.populate(dormitory, m);
			repair.setDormitory(dormitory);
			Building building = qr.query(s,new BeanHandler<>(Building.class),repair.getBid());
			repair.setBuilding(building);
			Employer employer = qr.query(q, new BeanHandler<>(Employer.class),repair.getEid());
			repair.setEmployer(employer);
			rList.add(repair);
		}
		return rList;
	}

	@Override
	public void deal(String rid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update repair set state = 0 where rid = ?";
		qr.update(sql,rid);
	}

}
