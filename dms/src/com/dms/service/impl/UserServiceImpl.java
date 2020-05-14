package com.dms.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dms.constant.Constant;
import com.dms.dao.AdminDao;
import com.dms.dao.EmployerDao;
import com.dms.dao.StudentDao;
import com.dms.dao.UserDao;
import com.dms.entity.Admin;
import com.dms.entity.Employer;
import com.dms.entity.User;
import com.dms.service.UserService;
import com.dms.utils.BeanFactory;
import com.dms.utils.DataSourceUtils;

public class UserServiceImpl implements UserService {

	@Override
	public User login(String username, String password) throws SQLException {
		UserDao ud = (UserDao) BeanFactory.getBean("UserDao");
		return ud.login(username, password);
	}

	@Override
	public void add(User user) throws Exception {

		try {
			DataSourceUtils.startTransaction();
			UserDao ud = (UserDao) BeanFactory.getBean("UserDao");
			ud.add(user);

			switch (user.getType()) {
			case Constant.ADMIN:
				AdminDao ad = (AdminDao) BeanFactory.getBean("AdminDao");
				ad.add(user);
				break;
			case Constant.EMPLOYEE:
				EmployerDao ed = (EmployerDao) BeanFactory.getBean("EmployerDao");
				ed.add(user);
				break;
			case Constant.STUDENT:
				StudentDao sd = (StudentDao) BeanFactory.getBean("StudentDao");
				sd.add(user);
				break;
			default:
				break;
			}
			DataSourceUtils.commitAndClose();
		} catch (Exception e) {
			DataSourceUtils.rollbackAndClose();
			e.printStackTrace();
		}

	}

	@Override
	public void updatePassword(User user) throws Exception {
		UserDao ud = (UserDao) BeanFactory.getBean("UserDao");
		ud.updatePassword(user);

	}

	@Override
	public List<User> addStudentByExcel(List<User> list) throws Exception {
		UserDao ud = (UserDao) BeanFactory.getBean("UserDao");
		AdminDao ad = (AdminDao) BeanFactory.getBean("AdminDao");
		EmployerDao ed = (EmployerDao) BeanFactory.getBean("EmployerDao");
		List<User>ulList =new ArrayList<>();
		try {
			DataSourceUtils.startTransaction();
		
		c:for (User user : list) {
			switch (user.getType()) {
			case Constant.ADMIN:
                 User admin = ud.findUserByUserName(user.getUsername());
                 if (admin!=null) {
					ulList.add(user);
					continue c;
				}
                 
                 ud.add(user);
                 ad.add(user);
				break;
			case Constant.EMPLOYEE:
				 User employer = ud.findUserByUserName(user.getUsername());
                 if (employer!=null) {
 					ulList.add(user);
 					continue c;
 				}
                  
                  ud.add(user);
                  ed.add(user);
				break;
			default:
				break;
			}
		}
		DataSourceUtils.commitAndClose();
		} catch (Exception e) {
			DataSourceUtils.rollbackAndClose();
			e.printStackTrace();
		}

		return ulList;
	}

	@Override
	public User findUserByUsername(String username) throws Exception {
		UserDao ud = (UserDao) BeanFactory.getBean("UserDao");
		return ud.findUserByUserName(username);
	}

}
