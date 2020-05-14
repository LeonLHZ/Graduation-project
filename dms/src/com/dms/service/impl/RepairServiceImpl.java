package com.dms.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.dms.dao.AdminDao;
import com.dms.dao.RepairDao;
import com.dms.entity.Repair;
import com.dms.service.RepairService;
import com.dms.utils.BeanFactory;

public class RepairServiceImpl implements RepairService {

	@Override
	public void addRepair(Repair repair) throws Exception {
		RepairDao rd = (RepairDao) BeanFactory.getBean("RepairDao");
		rd.addRepair(repair);
		
	}

	@Override
	public List<Repair> findAllRepair(String bid) throws Exception {
		RepairDao rd = (RepairDao) BeanFactory.getBean("RepairDao");
		
		return rd.findAllRepair(bid);
	}

	@Override
	public void dealEmployer(String rid, String eid) throws Exception {
		RepairDao rd = (RepairDao) BeanFactory.getBean("RepairDao");
		rd.dealEmployer(rid,eid);
		
	}

	@Override
	public List<Repair> employerFindRepair(String eid) throws Exception {
		RepairDao rd = (RepairDao) BeanFactory.getBean("RepairDao");
		return rd.employerFindRepair(eid);
	}

	@Override
	public List<Repair> findrepairByDorid(String dorid) throws Exception {
		RepairDao rd = (RepairDao) BeanFactory.getBean("RepairDao");
		return rd.findrepairByDorid(dorid);
	}

	@Override
	public void deal(String rid) throws SQLException {
		RepairDao rd = (RepairDao) BeanFactory.getBean("RepairDao");
		rd.deal(rid);
		
	}

}
