package com.dms.service.impl;

import java.sql.SQLException;

import com.dms.dao.SuperAdminDao;
import com.dms.entity.SuperAdmin;
import com.dms.service.SuperAdminService;
import com.dms.utils.BeanFactory;

public class SuperAdminServiceImpl implements SuperAdminService {

	@Override
	public SuperAdmin login(String said) throws Exception {
		SuperAdminDao sad = (SuperAdminDao) BeanFactory.getBean("SuperAdminDao");
		
		return sad.login(said);
	}

	@Override
	public void updatePassword(String username, String md5) throws SQLException {
		SuperAdminDao sad = (SuperAdminDao) BeanFactory.getBean("SuperAdminDao");
		sad.updatePassword(username,md5);
		
	}

	@Override
	public Boolean checkPassword(String username, String md5) throws SQLException {
		SuperAdminDao sad = (SuperAdminDao) BeanFactory.getBean("SuperAdminDao");
		
		return sad.checkPassword(username,md5);
	}

	@Override
	public SuperAdmin update(SuperAdmin superAdmin) throws Exception {
		SuperAdminDao sad = (SuperAdminDao) BeanFactory.getBean("SuperAdminDao");
		sad.update(superAdmin);
		return sad.login(superAdmin.getSaid());
	}

}
