package com.dms.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.dms.dao.BuildingDao;
import com.dms.dao.DormitoryDao;
import com.dms.dao.StudentDao;
import com.dms.entity.Building;
import com.dms.entity.Dormitory;
import com.dms.entity.PageBean;
import com.dms.entity.Position;
import com.dms.entity.Student;
import com.dms.entity.User;
import com.dms.service.DormitoryService;
import com.dms.utils.BeanFactory;
import com.dms.utils.DataSourceUtils;
import com.dms.utils.UUIDUtils;

public class DormitoryServiceImpl implements DormitoryService {

	@Override
	public void add(Dormitory dormitory) throws Exception {
		try {
			DataSourceUtils.startTransaction();
			DormitoryDao dd = (DormitoryDao) BeanFactory.getBean("DormitoryDao");
			dd.add(dormitory);

			int time = dormitory.getNumber();

			for (int i = 1; i <= time; i++) {
				Position position = new Position();
				position.setBid(dormitory.getBuilding().getBid());
				position.setDorid(dormitory.getDorid());
				position.setPid(UUIDUtils.getId());
				position.setStatus(0);
				position.setNumbering(i);
				dd.addPosition(position);
			}
			BuildingDao bd = (BuildingDao) BeanFactory.getBean("BuildingDao");
			bd.updateStudentNumber(dormitory);
			DataSourceUtils.commitAndClose();
		} catch (Exception e) {

			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}

	}

	@Override
	public List<Dormitory> findByDid(String bid) throws Exception {
		DormitoryDao dd = (DormitoryDao) BeanFactory.getBean("DormitoryDao");

		return dd.findByDid(bid);
	}

	@Override
	public void selectThis(String dorid, String sid,String pid) throws Exception {

		try {
			DataSourceUtils.startTransaction();
			DormitoryDao dd = (DormitoryDao) BeanFactory.getBean("DormitoryDao");

			int i = 1;
			dd.updateNownumber(dorid, i);
			BuildingDao bd = (BuildingDao) BeanFactory.getBean("BuildingDao");
			bd.updateNowStudentNumber(dorid);

			StudentDao sd = (StudentDao) BeanFactory.getBean("StudentDao");
			sd.updateDoridAndPid(sid, dorid,pid);
			
			
			
			dd.updatePosition(sid,pid);

			DataSourceUtils.commitAndClose();
		} catch (Exception e) {

			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}

	}

	@Override
	public Dormitory findByDorid(String dorid) throws Exception {
		DormitoryDao dd = (DormitoryDao) BeanFactory.getBean("DormitoryDao");
		return dd.findByDorid(dorid);
	}

	@Override
	public PageBean<Dormitory> findAllDormitory(String bid, int pageSize, int currPage) throws Exception {
		DormitoryDao dd = (DormitoryDao) BeanFactory.getBean("DormitoryDao");
		int totalCount = dd.findPageCount(bid);

		List<Dormitory> list = dd.findAllDormitory(bid, pageSize, currPage);
		PageBean<Dormitory> pageBean = new PageBean<>(list, currPage, totalCount, pageSize);
		return pageBean;
	}

	@Override
	public Dormitory lookposition(String dorid) throws Exception {
		DormitoryDao dd = (DormitoryDao) BeanFactory.getBean("DormitoryDao");

		return dd.lookposition(dorid);
		

	}

	@Override
	public Position findPositionByPid(String pid) throws Exception {
		DormitoryDao dd = (DormitoryDao) BeanFactory.getBean("DormitoryDao");

		return dd.findPositionByPid(pid);
	}

	@Override
	public Student findMyDormitory(User user) throws Exception {
		DormitoryDao dd = (DormitoryDao) BeanFactory.getBean("DormitoryDao");
		return dd.findMyDormitory(user);
	}

	@Override
	public Dormitory finDormitoryByBidNumber(String bid, String number) throws Exception {
		DormitoryDao dd = (DormitoryDao) BeanFactory.getBean("DormitoryDao");
		return dd.finDormitoryByBidNumber(bid,number);
	}

	@Override
	public Dormitory showStudent(String dorid) throws Exception {
		DormitoryDao dd = (DormitoryDao) BeanFactory.getBean("DormitoryDao");
		return dd.showStudent(dorid);
	}

}
