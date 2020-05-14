package com.dms.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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

import com.dms.dao.StudentDao;
import com.dms.entity.Building;
import com.dms.entity.Dormitory;
import com.dms.entity.Position;
import com.dms.entity.Student;
import com.dms.entity.User;
import com.dms.utils.DataSourceUtils;
import com.dms.utils.MyConventer;


public class StudentDaoImpl implements StudentDao{

	
	@Override
	public Student login(String sid) throws Exception {
	 QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
	String sql = "select * from student,building where student.bid=building.bid and sid=? limit 1";
	
	
		Map<String, Object> map= qr.query(sql, new MapHandler(),sid);
		if(map!=null) {
	Student student = new Student();

	ConvertUtils.register(new MyConventer(), Date.class);
	BeanUtils.populate(student, map);
	
	Building building = new Building();
	BeanUtils.populate(building, map);
	student.setBuilding(building);
	
	return student;
	}
		return null;
	}

	
	@Override
	public void add(User user) throws Exception {
		 QueryRunner qr = new QueryRunner();
		 String sql = "insert into student (sid,username,level,starttime,leavetime,name,sex,major,college,telephone,teacherphone,bid,year) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			

				Date date = sdf.parse((String)user.getStudent().getLeavetime());
		
		qr.update(DataSourceUtils.getConnection(),sql,user.getUid(),user.getUsername(),user.getStudent().getLevel(),user.getStudent().getStarttime(),date,user.getStudent().getName(),user.getStudent().getSex(),user.getStudent().getMajor(),user.getStudent().getCollege(),user.getStudent().getTelephone(),user.getStudent().getTeacherphone(),user.getStudent().getBid(),user.getStudent().getYear());
	}

	@Override
	public void update(Student student) throws Exception {
		 QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		 String sql = "update student set level=?,name=?,old=?,major=?,college=?,telephone=?,teacherphone=? where sid=?";
		 qr.update(sql,student.getLevel(),student.getName(),student.getOld(),student.getMajor(),student.getCollege()
				 ,student.getTelephone(),student.getTeacherphone(),student.getSid());
	}

	@Override
	public Student findStudentById(String sid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from student where sid=? limit 1";
		Student student = qr.query(sql, new BeanHandler<>(Student.class),sid);
		
		String s="select * from dormitory where dorid=? limit 1";
		Dormitory dormitory = qr.query(s, new BeanHandler<>(Dormitory.class),student.getDorid());
		student.setDormitory(dormitory);
		 return student;
		 
	}

	@Override
	public Student findStudentByDoridsid(String dorid, String sid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from student where sid = ? and dorid = ? limit 1";
		return qr.query(sql, new BeanHandler<>(Student.class),sid,dorid);
	}

	@Override
	public void updateDorid(String sid, String dorid) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "update student set dorid = ? where sid= ?";
		qr.update(DataSourceUtils.getConnection(),sql,dorid,sid);
		
	}

	@Override
	public Student findStudentByUsername(String bernum, String dorid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from student where username = ? and dorid = ? limit 1";
		return qr.query(sql, new BeanHandler<>(Student.class),bernum,dorid);
	}

	@Override
	public void updatePassword(String sid, String md5) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update student set password = ? where sid = ?";
		qr.update(sql,md5,sid);
	}


	@Override
	public Student findStudentByUsername(String studentNumber) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from student where username = ? limit 1";
		return qr.query(sql,new BeanHandler<>(Student.class),studentNumber);
	}


	@Override
	public Boolean checkPassword(String sid, String md5) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where uid = ? and password = ? limit 1";
		User user = qr.query(sql,new BeanHandler<>(User.class),sid,md5);
		if(user==null)
			return false;
		return true;
	}


	@Override
	public int findStudentCount(String aid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select count(*) from student where bid=(select bid from admin where aid = ? limit 1)";
		return ((Long)qr.query(sql,new ScalarHandler(),aid)).intValue();
	}


	@Override
	public List<Student> findAllStudent(int currPage, int pageSize, String aid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select * from student left join dormitory on student.dorid=dormitory.dorid left join position on student.pid=position.pid where student.bid=(select bid from admin where aid = ? limit 1) order by username limit ?,?";
		List<Map<String,Object>> list=qr.query(sql,new MapListHandler(),aid,(currPage-1)*pageSize,pageSize);
		List<Student> sList=new ArrayList<>();
		for (Map<String, Object> map : list) {
			Student student = new Student();
			 ConvertUtils.register(new MyConventer(), Date.class);
		
			BeanUtils.populate(student, map);
			Dormitory dormitory = new Dormitory();
			BeanUtils.populate(dormitory, map);
			student.setDormitory(dormitory);
			Position position = new Position();
			BeanUtils.populate(position, map);
			student.setPosition(position);
			sList.add(student);
		}
		return sList;
	}


	@Override
	public void updateDoridAndPid(String sid, String dorid, String pid) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "update student set dorid = ?,pid = ? where sid= ?";
		qr.update(DataSourceUtils.getConnection(),sql,dorid,pid,sid);
		
	}


	@Override
	public List<Student> findStudentDonotSelect(String uid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select * from student where bid=(select bid from admin where aid = ? limit 1) and dorid is null";
		
		return qr.query(sql, new BeanListHandler<>(Student.class),uid);
	}


	@Override
	public List<Object> excelOut(String bid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select * from student left join dormitory on student.dorid=dormitory.dorid left join position on student.pid=position.pid where student.bid=?";
		String string = "select * from building where bid = ?";
		List<Map<String,Object>> list=qr.query(sql,new MapListHandler(),bid);
		List<Object> sList=new ArrayList<>();
		for (Map<String, Object> map : list) {
			
			Student student = new Student();
			 ConvertUtils.register(new MyConventer(), Date.class);
		
			BeanUtils.populate(student, map);
			Dormitory dormitory = new Dormitory();
			BeanUtils.populate(dormitory, map);
			student.setDormitory(dormitory);
			Position position = new Position();
			BeanUtils.populate(position, map);
			student.setPosition(position);
			student.setBuilding(qr.query(string,new BeanHandler<>(Building.class),bid));
			sList.add(student);
		}
		return sList;
		
	}


	@Override
	public List<Student> findtimeoutStudent(String bid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select * from student left join dormitory on student.dorid=dormitory.dorid left join position on student.pid=position.pid where student.bid=? and now()>student.leavetime and student.dorid is not null";
		List<Map<String,Object>> list=qr.query(sql,new MapListHandler(),bid);
		List<Student> sList=new ArrayList<>();
		for (Map<String, Object> map : list) {
			Student student = new Student();
			 ConvertUtils.register(new MyConventer(), Date.class);
		
			BeanUtils.populate(student, map);
			
			Dormitory dormitory = new Dormitory();
			BeanUtils.populate(dormitory, map);
			student.setDormitory(dormitory);
			Position position = new Position();
			BeanUtils.populate(position, map);
			student.setPosition(position);
			sList.add(student);
		}
		return sList;
	}


	@Override
	public void deleteStudent(String sid) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "delete from student where sid = ?";
		qr.update(DataSourceUtils.getConnection(),sql,sid);
	}


	



	




}
