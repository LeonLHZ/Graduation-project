package com.dms.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.dms.dao.CheckDorDao;
import com.dms.dao.DormitoryDao;
import com.dms.dao.StudentDao;
import com.dms.entity.CheckDor;
import com.dms.entity.Dormitory;
import com.dms.entity.Student;
import com.dms.service.CheckDorService;
import com.dms.utils.BeanFactory;
import com.dms.utils.DataSourceUtils;

public class CheckDorServiceImpl implements CheckDorService {

	@Override
	public Student check(String dornum, String studentNumber, String bid) throws Exception {

		StudentDao sd = (StudentDao) BeanFactory.getBean("StudentDao");
		Student student = sd.findStudentByUsername(studentNumber);
		if(student==null)
			return student;

		DormitoryDao dd = (DormitoryDao) BeanFactory.getBean("DormitoryDao");
		String dorid = dd.findDoridByNum(dornum, bid);
	
		if (student.getDorid().equals(dorid)){

			return student;

		}

		return null;
	}

	@Override
	public void checkDor(CheckDor c) {

		try {
			DataSourceUtils.startTransaction();
			CheckDorDao cd = (CheckDorDao) BeanFactory.getBean("CheckDorDao");
			cd.checkDor(c);
			DormitoryDao dd = (DormitoryDao) BeanFactory.getBean("DormitoryDao");
			dd.updateIntegral(c);

			DataSourceUtils.commitAndClose();
		} catch (Exception e) {
			DataSourceUtils.rollbackAndClose();
			e.printStackTrace();
		}

	}

	@Override
	public List<Dormitory> beautifulDor(String bid) throws SQLException {
		DormitoryDao dd = (DormitoryDao) BeanFactory.getBean("DormitoryDao");
		return dd.beautifulDor(bid);
	}
}
