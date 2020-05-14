package com.dms.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.dms.dao.EmployerDao;
import com.dms.entity.Employer;
import com.dms.entity.PageBean;
import com.dms.service.EmployerService;
import com.dms.utils.BeanFactory;

public class EmployerServiceImpl implements EmployerService{

	

	@Override
	public Employer login(String eid) throws Exception {
		EmployerDao ed = (EmployerDao) BeanFactory.getBean("EmployerDao");
		return ed.login(eid);
	}

	

	@Override
	public void update(Employer employer) throws Exception {
		EmployerDao ed = (EmployerDao) BeanFactory.getBean("EmployerDao");
		ed.update(employer);
	}

	@Override
	public Employer findEmployerByEid(String eid) throws Exception {
		EmployerDao ed = (EmployerDao) BeanFactory.getBean("EmployerDao");
		return ed.findEmployerByEid(eid);
	}

	@Override
	public void updatePassword(String eid,String md5) throws Exception {
		EmployerDao ed = (EmployerDao) BeanFactory.getBean("EmployerDao");
		ed.updatePassword(eid,md5);
		
	}

	@Override
	public Boolean checkPassword(String eid, String old) throws SQLException {
		EmployerDao ed = (EmployerDao) BeanFactory.getBean("EmployerDao");
		Employer employer = ed.checkPassword(eid,old);
		if(employer!=null)
			return true;
		return false;
	}

	@Override
	public List<Employer> findByNumber(String number) throws Exception {
		EmployerDao ed = (EmployerDao) BeanFactory.getBean("EmployerDao");
		return ed.findByNumber(number);
	}



	@Override
	public PageBean<Employer> findAllEmployerByPage(int currPage, int pageSize) throws Exception {
		EmployerDao ed = (EmployerDao) BeanFactory.getBean("EmployerDao");
		
		int totalCount = ed.findTotalCount();
		
		List<Employer> list = ed.findAllEmployerByPage( currPage,  pageSize);
		
		PageBean< Employer> pageBean = new PageBean<>(list, currPage, totalCount, pageSize);
		return pageBean;
	}



	@Override
	public List<Employer> findAllEmployer() throws Exception {
		EmployerDao ed = (EmployerDao) BeanFactory.getBean("EmployerDao");
		return ed.findAllEmployer();
	}

}
