package com.dms.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.dbutils.QueryRunner;import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.dms.dao.DormitoryDao;
import com.dms.entity.Admin;
import com.dms.entity.Building;
import com.dms.entity.CheckDor;
import com.dms.entity.Dormitory;
import com.dms.entity.Position;
import com.dms.entity.Student;
import com.dms.entity.User;
import com.dms.utils.DataSourceUtils;
import com.dms.utils.MyConventer;

import javafx.geometry.Pos;

public class DormitoryDaoImpl implements DormitoryDao{

	@Override
	public void add(Dormitory dormitory) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "insert into dormitory (dorid,bid,number,nownumber,num,integral,img) values (?,?,?,?,?,?,?)";
		qr.update(DataSourceUtils.getConnection(),sql,dormitory.getDorid(),dormitory.getBuilding().getBid(),dormitory.getNumber(),dormitory.getNownumber(),dormitory.getNum(),dormitory.getIntegral(),dormitory.getImg());
		
	}

	@Override
	public List<Dormitory> findByDid(String bid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		//String sql = "select * from dormitory where bid = ? and number>ifnull(nownumber,0)";
		String sql = "select * from dormitory where bid = ?";
		
		
		return qr.query(sql,new BeanListHandler<>(Dormitory.class),bid);
	}

	@Override
	public void update(Dormitory dormitory) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Dormitory findBybid(String dorid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from dormitory where dorid = ? limit 1";
		return qr.query(sql, new BeanHandler<>(Dormitory.class),dorid);
	}

	@Override
	public void updateNownumber(String dorid,int i) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "update dormitory set nownumber=nownumber+? where dorid = ?";
		qr.update(DataSourceUtils.getConnection(),sql,i,dorid);
		
	}

	@Override
	public Dormitory findByDorid(String dorid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from dormitory where dorid = ? limit 1";
		return qr.query(sql, new BeanHandler<>(Dormitory.class),dorid);
	}

	@Override
	public String findDoridByNum(String dornum,String bid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select dorid from dormitory where num = ? and bid = ? limit 1";
		Dormitory dormitory =  qr.query(sql,new BeanHandler<>(Dormitory.class),dornum ,bid);
		return dormitory.getDorid();
	}

	@Override
	public void updateIntegral(CheckDor c) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "update dormitory set integral=integral+? where dorid=?";
		qr.update(DataSourceUtils.getConnection(),sql,c.getIntegral(),c.getDorid());
		
	}

	@Override
	public List<Dormitory> beautifulDor(String bid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from dormitory where bid = ? and integral>8";
		
		return qr.query(sql, new BeanListHandler<>(Dormitory.class),bid);
	}

	@Override
	public List<Dormitory> findAllDormitory(String bid,int pageSize, int currPage) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
			String s = "select * from dormitory where bid = ? order by num limit ?,?";
			List<Dormitory> dList = qr.query(s,new BeanListHandler<>(Dormitory.class),bid,(currPage-1)*pageSize,pageSize);
			
		
		return dList;
	}

	@Override
	public int findPageCount(String bid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String s = "select count(*) from dormitory where bid = ?";
		return ((Long)qr.query(s, new ScalarHandler(),bid)).intValue();
	}

	@Override
	public void addPosition(Position position) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql="insert into position values (?,?,?,?,?,?)";
		qr.update(DataSourceUtils.getConnection(), sql,position.getPid(),position.getSid(),position.getDorid(),position.getBid(),position.getNumbering(),position.getStatus());
		
	}

	@Override

		public Dormitory lookposition(String dorid) throws Exception {
			QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
			String s = "select * from dormitory where dorid=?";
			Dormitory dormitory = qr.query(s, new BeanHandler<>(Dormitory.class),dorid);
			String sql="select * from position where dorid=? order by numbering";
			List<Position> list = qr.query(sql, new BeanListHandler<>(Position.class),dorid);
			dormitory.setList(list);
			return dormitory;
		}
	

	@Override
	public void updatePosition(String sid, String pid) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql="update position set sid=?,status=? where pid=?";
		int i =1;
		qr.update(DataSourceUtils.getConnection(), sql,sid,i,pid);
		
	}

	@Override
	public Position findPositionByPid(String pid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from position where pid=? limit 1";
		
		return qr.query(sql, new BeanHandler<>(Position.class),pid);
	}

	@Override
	public void updatePositionSid(String sid, int status, String pid) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql="update position set sid=?,status=? where pid=?";
		
		qr.update(DataSourceUtils.getConnection(), sql,sid,status,pid);
		
	}

	@Override
	public Student findMyDormitory(User user) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String qString="select * from Student where sid = ? limit 1";
		String sql="select * from position where pid = ? limit 1";
				String s="select * from dormitory where dorid = ? limit 1" ;
				String q="select * from building where bid = ? limit 1" ;
				Student student = qr.query(qString, new BeanHandler<>(Student.class),user.getUid());
				Dormitory dormitory = qr.query(s, new BeanHandler<>(Dormitory.class),student.getDorid());
				Position position = qr.query(sql, new BeanHandler<>(Position.class),student.getPid());
				Building building = qr.query(q, new BeanHandler<>(Building.class),student.getBid());
				student.setBuilding(building);
				student.setDormitory(dormitory);
				student.setPosition(position);
		return student;
	}

	@Override
	public Dormitory finDormitoryByBidNumber(String bid, String number) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String qString="select * from dormitory where bid = ? and num = ? limit 1";
		return qr.query(qString, new BeanHandler<>(Dormitory.class),bid,number);
	}

	@Override
	public Dormitory showStudent(String dorid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String s = "select * from dormitory where dorid=?";
		Dormitory dormitory = qr.query(s, new BeanHandler<>(Dormitory.class),dorid);
		String sql="select * from position left join student on position.pid = student.pid where position.dorid=? order by numbering";
		List<Map<String, Object>> list = qr.query(sql, new MapListHandler(),dorid);
		List<Position>pList=new ArrayList<>();
		for (Map<String, Object> map : list) {
			Position position = new Position();
			BeanUtils.populate(position, map);
			Student student = new Student();
			ConvertUtils.register(new MyConventer(), Date.class);
			BeanUtils.populate(student, map);
			position.setStudent(student);
			pList.add(position);
		}
		dormitory.setList(pList);
		return dormitory;
		
	}



}
