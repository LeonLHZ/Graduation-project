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
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.dms.dao.RequestBeanDao;
import com.dms.entity.Building;
import com.dms.entity.Dormitory;
import com.dms.entity.Position;
import com.dms.entity.RequestBean;
import com.dms.entity.Student;
import com.dms.utils.DataSourceUtils;
import com.dms.utils.MyConventer;
import com.sun.org.apache.xpath.internal.operations.And;

public class RequestBeanDaoImpl implements RequestBeanDao {

	@Override
	public void addRequestBean(RequestBean requestBean,int state) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		int i=2;
		String sql = "insert into requestbean (rid,reason,rnum,bernum,bid,starttime,dorid,pid,state) values(?,?,?,?,?,?,?,?,?)";
		qr.update(sql,requestBean.getRid(),requestBean.getReason(),requestBean.getRnum(),requestBean.getBernum(),requestBean.getBid(),
				requestBean.getStarttime(),requestBean.getDorid(),requestBean.getPid(),state
				
				);
	}

	@Override
	public List<RequestBean> findAllRequest(String bid,int currPage,int pageSize) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from requestbean where bid = ? and state in (2,3) limit ?,?";
		String s = "select * from student where sid=?";
		String q = "select * from dormitory where dorid = ?";
		
	
		
			List<RequestBean> rList = qr.query(sql, new BeanListHandler<>(RequestBean.class),bid,(currPage-1)*pageSize,pageSize);
			for (RequestBean requestBean : rList) {
				Student rStudent = qr.query(s, new BeanHandler<>(Student.class),requestBean.getRnum());
				requestBean.setrStudent(rStudent);
				Student bStudent = qr.query(s, new BeanHandler<>(Student.class),requestBean.getBernum());
				requestBean.setbStudent(bStudent);
				Dormitory dormitory = qr.query(q, new BeanHandler<>(Dormitory.class),requestBean.getDorid());
				requestBean.setDormitory(dormitory);
				
				
			}
			
		
		return rList;
	}

	@Override
	public RequestBean findRequestByRid(String rid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from requestbean where rid = ? ";
		String s = "select * from student where sid=?";
		String q = "select * from dormitory where dorid = ?";
		RequestBean requestBean = qr.query(sql, new BeanHandler<>(RequestBean.class),rid);
		Student rStudent = qr.query(s, new BeanHandler<>(Student.class),requestBean.getRnum());
		requestBean.setrStudent(rStudent);
		Student bStudent = qr.query(s, new BeanHandler<>(Student.class),requestBean.getBernum());
		requestBean.setbStudent(bStudent);
		Dormitory dormitory = qr.query(q, new BeanHandler<>(Dormitory.class),requestBean.getDorid());
		requestBean.setDormitory(dormitory);
		return requestBean;
	}

	@Override
	public void updateStateAndOvertime(int state, Date date,String rid) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "update requestbean set state=?,overtime=? where rid = ?";
		qr.update(DataSourceUtils.getConnection(),sql,state,date,rid);
		
	}

	@Override
	public List<RequestBean> findRequestBySid(String rnum) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from requestbean where rnum = ?";
		String s = "select * from student where sid=?";
		String q = "select * from dormitory where dorid = ?";
		List<RequestBean> list = qr.query(sql, new BeanListHandler<>(RequestBean.class),rnum);
		for (RequestBean requestBean : list) {
			Student rStudent = qr.query(s, new BeanHandler<>(Student.class),requestBean.getRnum());
			Student bStudent = qr.query(s, new BeanHandler<>(Student.class),requestBean.getBernum());
			requestBean.setrStudent(rStudent);
			requestBean.setbStudent(bStudent);
			requestBean.setDormitory(qr.query(q, new BeanHandler<>(Dormitory.class),requestBean.getDorid()));
		}
		
		return list;
	}

	@Override
	public int findRequestByUsername(String username) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from requestbean where rnum = ? and state=2";
		return ((Long)qr.query(sql, new ScalarHandler(),username)).intValue();
	}

	

	@Override
	public int findRequestCount(String bid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from requestbean where bid=? and state=2";
		return ((Long)qr.query(sql, new ScalarHandler(),bid)).intValue();
		
	}

	@Override
	public List<RequestBean> getRequestBybeId(String uid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from requestbean where bernum=? and state=4";
		String s = "select * from student s join position p on  s.pid=p.pid  join dormitory d on s.dorid = d.dorid where s.sid = ?";
		List<RequestBean> list = qr.query(sql, new BeanListHandler<>(RequestBean.class),uid);
	for (RequestBean requestBean : list) {
		Map<String, Object> map=qr.query(s, new MapHandler(),requestBean.getBernum());
		Student student = new Student();
		Position position = new Position();
		BeanUtils.populate(position, map);
		student.setPosition(position);
		Dormitory dormitory = new Dormitory();
		BeanUtils.populate(dormitory, map);
		student.setDormitory(dormitory);
		ConvertUtils.register(new MyConventer(), Date.class);
		BeanUtils.populate(student, map);
		requestBean.setrStudent(student);
		
		
	}
	return list;
	}

	@Override
	public void updateStateAndOvertime(Date date, String uid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update requestbean set overtime=?,state=? where bernum=? and state=4";
		int state=0;
		qr.update(sql,date,state,uid);
		
	}

	@Override
	public void dealStateAndOvertimeByBernum(Date date, String s1) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql="update requestbean set overtime=?,state=? where bernum=? and state in (2,3)";
		int state=0;
		qr.update(DataSourceUtils.getConnection(),sql,date,state,s1);
	}

	@Override
	public void dealStateAndOvertimeByRnum(Date date, String s1) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql="update requestbean set overtime=?,state=? where rnum=? and state in (2,3)";
		int state=0;
		qr.update(DataSourceUtils.getConnection(),sql,date,state,s1);
		
	}

}
