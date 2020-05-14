package com.dms.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dms.dao.BuildingDao;
import com.dms.dao.DormitoryDao;
import com.dms.dao.RequestBeanDao;
import com.dms.dao.StudentDao;
import com.dms.dao.UserDao;
import com.dms.entity.PageBean;
import com.dms.entity.RequestBean;
import com.dms.entity.Student;
import com.dms.entity.User;
import com.dms.service.StudentService;
import com.dms.utils.BeanFactory;
import com.dms.utils.DataSourceUtils;
import com.sun.jndi.cosnaming.RemoteToCorba;

public class StudentServiceImpl implements StudentService {

	

	

	@Override
	public void update(Student student) throws Exception {
		StudentDao sd = (StudentDao) BeanFactory.getBean("StudentDao");
		sd.update(student);
		
	}

	@Override
	public Student findStudentById(String sid) throws Exception {
		StudentDao sd = (StudentDao) BeanFactory.getBean("StudentDao");
		return sd.findStudentById(sid);
	}

	@Override
	public Student findStudentByDoridsid(String dorid, String sid) throws Exception {
		StudentDao sd = (StudentDao) BeanFactory.getBean("StudentDao");
		
		return sd.findStudentByDoridsid(dorid, sid);
	}

	@Override
	public void processingRequest(RequestBean bean) throws Exception  {
		
	}

	@Override
	public void changeDor(RequestBean rs) throws Exception  {
		Student rStudent = rs.getrStudent();
		Student bStudent = rs.getbStudent();
		DormitoryDao dd = (DormitoryDao) BeanFactory.getBean("DormitoryDao");
		String dorid=rs.getDormitory().getDorid();
		String s1=rStudent.getSid();
		String rid = rs.getRid();
		
		if(bStudent==null&&dorid!=null) {
			try {
				DataSourceUtils.startTransaction();
			StudentDao sd = (StudentDao) BeanFactory.getBean("StudentDao");
			
		
			Student student = sd.findStudentById(s1);
			
			
			int i=-1;
			dd.updateNownumber(student.getDormitory().getDorid(), i);
			i=1;
			dd.updateNownumber(dorid, i);
			sd.updateDoridAndPid(s1, dorid,rs.getPid());
			String sid = null;
			int status = 0;
			dd.updatePositionSid(sid,status, rStudent.getPid());
			status=1;
			dd.updatePositionSid(s1,status, rs.getPid());
			
			RequestBeanDao rd = (RequestBeanDao) BeanFactory.getBean("RequestBeanDao");
			int state=1;
			rd.updateStateAndOvertime(state,new Date(),rid);
			rd.dealStateAndOvertimeByBernum(new Date(), s1);
			DataSourceUtils.commitAndClose();
			} catch (Exception e) {
				
				e.printStackTrace();
				DataSourceUtils.rollbackAndClose();
				throw e;
			}
		}
		if(bStudent!=null&&dorid!=null) {
			try {
				
				String s2 = bStudent.getSid();
				DataSourceUtils.startTransaction();
			StudentDao sd = (StudentDao) BeanFactory.getBean("StudentDao");
			
			
			Student student1 = sd.findStudentById(s1);
			Student student2= sd.findStudentById(s2);
			
			sd.updateDoridAndPid(s1, student2.getDormitory().getDorid(),student2.getPid());
			sd.updateDoridAndPid(s2, student1.getDormitory().getDorid(),student1.getPid());
			RequestBeanDao rd = (RequestBeanDao) BeanFactory.getBean("RequestBeanDao");
			dd.updatePositionSid(s1,1,student2.getPid());
			dd.updatePositionSid(s2,1,student1.getPid());
			int state=1;
			Date date = new Date();
			rd.updateStateAndOvertime(state,date,rid);
			rd.dealStateAndOvertimeByBernum(date, s1);
			rd.dealStateAndOvertimeByRnum(date, s2);
			rd.dealStateAndOvertimeByBernum(date, s2);
			DataSourceUtils.commitAndClose();
           } catch (Exception e) {
				
				e.printStackTrace();
				DataSourceUtils.rollbackAndClose();
				throw e;
			}
		}
		
	}

	@Override
	public Student findStudentByUsername(String bernum, String dorid) throws Exception {
		StudentDao sd = (StudentDao) BeanFactory.getBean("StudentDao");
		
		
		
		return sd.findStudentByUsername(bernum,dorid);
	}

	@Override
	public void updatePassword(String sid, String md5) throws Exception {
		StudentDao sd = (StudentDao) BeanFactory.getBean("StudentDao");
		sd.updatePassword(sid,md5);
		
	}

	@Override
	public Boolean checkPassword(String sid, String md5) throws SQLException {
		StudentDao sd = (StudentDao) BeanFactory.getBean("StudentDao");
		return sd.checkPassword(sid,md5);
	}

	@Override
	public Student login(String uid) throws Exception {
		StudentDao sd = (StudentDao) BeanFactory.getBean("StudentDao");
		return sd.login(uid);
	}

	@Override
	public PageBean<Student> findAllStudent(int currPage, int pageSize,String aid) throws Exception {
		StudentDao sd = (StudentDao) BeanFactory.getBean("StudentDao");
		
		int totalCount = sd.findStudentCount(aid);
		List<Student> list = sd.findAllStudent( currPage,  pageSize,aid);
		return new PageBean<Student>(list, currPage, totalCount, pageSize);
	}

	@Override
	public List<Student> addStudentByExcel(List<User> list) throws Exception {
		StudentDao sd = (StudentDao) BeanFactory.getBean("StudentDao");
		UserDao ud = (UserDao) BeanFactory.getBean("UserDao");
		List<Student> cList= new ArrayList<>();
		try {
			DataSourceUtils.startTransaction();
		for (User user : list) {
			User user2 = ud.findUserByUserName(user.getUsername()); 
			
			if (user2!=null) {
				cList.add(user.getStudent());
				continue;
			}
			sd.add(user);
			
			ud.add(user);
		}
		DataSourceUtils.commitAndClose();
		} catch (Exception e) {
			
			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}
		return cList;
		
	}

	@Override
	public List<Student> findStudentDonotSelect(String uid) throws Exception {
		StudentDao sd = (StudentDao) BeanFactory.getBean("StudentDao");
		return sd.findStudentDonotSelect(uid);
	}

	@Override
	public List<Object> excelOut(String bid) throws Exception {
		StudentDao sd = (StudentDao) BeanFactory.getBean("StudentDao");
		return sd.excelOut(bid);
	}

	@Override
	public List<Student> findtimeoutStudent(String bid) throws Exception {
		StudentDao sd = (StudentDao) BeanFactory.getBean("StudentDao");
		return sd.findtimeoutStudent(bid);
	}

	@Override
	public void dealtimeout(String sid) throws Exception {
		StudentDao sd = (StudentDao) BeanFactory.getBean("StudentDao");
		Student student = sd.findStudentById(sid);
		
		try {
			DataSourceUtils.startTransaction();
		BuildingDao bd = (BuildingDao)BeanFactory.getBean("BuildingDao");
		
		bd.cutNowStudentNumber(student.getBid());
		DormitoryDao dd = (DormitoryDao) BeanFactory.getBean("DormitoryDao");
		dd.updateNownumber(student.getDorid(), -1);
		
		dd.updatePositionSid(null,0, student.getPid());
		
		sd.deleteStudent(sid);
		
		UserDao ud = (UserDao) BeanFactory.getBean("UserDao");
		ud.deleteUser(sid);
 		DataSourceUtils.commitAndClose();
		} catch (Exception e) {
			
			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}
		
	}

}
