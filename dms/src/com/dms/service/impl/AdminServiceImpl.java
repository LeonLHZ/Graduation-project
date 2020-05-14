package com.dms.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dms.dao.AdminDao;
import com.dms.dao.BuildingDao;
import com.dms.dao.UserDao;
import com.dms.entity.Admin;
import com.dms.entity.CheckDor;
import com.dms.entity.PageBean;
import com.dms.entity.Student;
import com.dms.service.AdminService;
import com.dms.utils.BeanFactory;
import com.dms.utils.DataSourceUtils;

public class AdminServiceImpl implements AdminService {

	@Override
	public Admin login(String aid) throws Exception {
		AdminDao ad = (AdminDao) BeanFactory.getBean("AdminDao");
		return ad.login(aid);
	}

	@Override
	public List<Admin> findAllAdmin() throws Exception {
		AdminDao ad = (AdminDao) BeanFactory.getBean("AdminDao");
		return ad.findAllAdmin();
	}

	@Override
	public void updatePassword(String aid, String md5) throws Exception {
		AdminDao ad = (AdminDao) BeanFactory.getBean("AdminDao");
		ad.updatePassword(aid, md5);

	}

	@Override
	public void update(Admin admin) throws SQLException {
		AdminDao ad = (AdminDao) BeanFactory.getBean("AdminDao");
		ad.update(admin);

	}

	@Override
	public Admin findAdminById(String aid) throws Exception {
		AdminDao ad = (AdminDao) BeanFactory.getBean("AdminDao");
		return ad.findAdminById(aid);
	}

	@Override
	public Boolean checkPassword(String aid, String md5) throws SQLException {
		AdminDao ad = (AdminDao) BeanFactory.getBean("AdminDao");
		return ad.checkPassword(aid, md5);
	}

	@Override
	public PageBean<Admin> findAllAdminrByPage(int currPage, int pageSize) throws Exception {
		AdminDao ad = (AdminDao) BeanFactory.getBean("AdminDao");
		int totalCount = ad.findAdminTotalCount();
		List<Admin> list = ad.finfindAllAdminrByPage(currPage, pageSize);
		PageBean<Admin> pageBean = new PageBean<>(list, currPage, totalCount, pageSize);

		return pageBean;
	}

	@Override
	public PageBean<Admin> findBuildAdmin(int currPage, int pageSize, String bid) throws Exception {
		AdminDao ad = (AdminDao) BeanFactory.getBean("AdminDao");
		int totalCount = ad.findAdminTotalCountByBid(bid);
		List<Admin> list = ad.findBuildAdmin(currPage, pageSize, bid);
		PageBean<Admin> pageBean = new PageBean<>(list, currPage, totalCount, pageSize);
		return pageBean;
	}

	@Override
	public void releaseAdmin(String aid) throws Exception {
		try {
			DataSourceUtils.startTransaction();
			AdminDao ad = (AdminDao) BeanFactory.getBean("AdminDao");

			String bid = ad.getBidByaid(aid);

			ad.releaseAdmin(aid);
			BuildingDao bd = (BuildingDao) BeanFactory.getBean("BuildingDao");

			bd.cutAdminNumber(bid);
			DataSourceUtils.commitAndClose();
		} catch (Exception e) {

			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}
	}

	@Override
	public void changeBid(String oldbid, String bid, String aid) throws Exception {
		try {
			DataSourceUtils.startTransaction();
			AdminDao ad = (AdminDao) BeanFactory.getBean("AdminDao");
			ad.updateBid(bid, aid);
			BuildingDao bd = (BuildingDao) BeanFactory.getBean("BuildingDao");
			bd.increatAdminnumber(bid);
			bd.cutAdminNumber(oldbid);
			DataSourceUtils.commitAndClose();
		} catch (Exception e) {

			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}
	}

	@Override
	public void setBid(String aid, String bid) throws Exception {
		try {
			DataSourceUtils.startTransaction();
			AdminDao ad = (AdminDao) BeanFactory.getBean("AdminDao");
			ad.setBid(aid, bid);
			BuildingDao bd = (BuildingDao) BeanFactory.getBean("BuildingDao");
			bd.increatAdminnumber(bid);
			DataSourceUtils.commitAndClose();

		} catch (Exception e) {

			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}
	}

	@Override
	public void changeChoice(String code,String bid) throws Exception {
		AdminDao ad = (AdminDao) BeanFactory.getBean("AdminDao");
		int status;
		switch (code) {
		case "1":
			status=1;
			ad.updateselectDormitory(status,bid);
			status=0;
			ad.updatechangeDormitory(status,bid);
			return ;
		
		case "3":
			status=1;
			ad.updatechangeDormitory(status,bid);
			status=0;
			ad.updateselectDormitory(status,bid);
			return ;
		

		default:
			break;
		}

	}

	@Override
	public List<CheckDor>  checkstudent(String sid) throws Exception {
		AdminDao ad = (AdminDao) BeanFactory.getBean("AdminDao");
		return ad.checkstudent(sid);
	}

}
