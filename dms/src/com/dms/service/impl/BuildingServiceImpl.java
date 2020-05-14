package com.dms.service.impl;

import java.util.List;

import com.dms.dao.BuildingDao;
import com.dms.entity.Building;
import com.dms.entity.PageBean;
import com.dms.service.BuildingService;
import com.dms.utils.BeanFactory;

public class BuildingServiceImpl implements BuildingService {

	@Override
	public void add(Building building) throws Exception {
		BuildingDao bd = (BuildingDao) BeanFactory.getBean("BuildingDao");
		bd.add(building);
		
	}

	

	@Override
	public List<Building> findBuildingBySex(String sex) throws Exception {
		BuildingDao bd = (BuildingDao) BeanFactory.getBean("BuildingDao");
		return bd.findBuildingBySex(sex);
	}

	@Override
	public List<Building> findBuidlingByAid(String aid) throws Exception {
		BuildingDao bd = (BuildingDao) BeanFactory.getBean("BuildingDao");
		return bd.findBuidlingByAid(aid);
	}

	@Override
	public PageBean<Building> findAllBuilding(int currPage, int pageSize) throws Exception {
		BuildingDao bd = (BuildingDao) BeanFactory.getBean("BuildingDao");
		
		int totalCount = bd.findBuildCount();
		
		List<Building> list = bd.findAllBuilding(currPage ,pageSize);
		
		PageBean< Building> pageBean = new PageBean<>(list, currPage, totalCount, pageSize);
		return pageBean;
	}



	@Override
	public List<Building> findAllBuilding() throws Exception {
		BuildingDao bd = (BuildingDao) BeanFactory.getBean("BuildingDao");
		
		return bd.findAllBuilding();
	}



	@Override
	public Building findBuildByBid(String bid) throws Exception {
		BuildingDao bd = (BuildingDao) BeanFactory.getBean("BuildingDao");
		return bd.findBuildByBid(bid);
	}



	@Override
	public Building findBuildingByNumber(int num) throws Exception {
		BuildingDao bd = (BuildingDao) BeanFactory.getBean("BuildingDao");
		return bd.findBuildingByNumber(num);
	}

}
