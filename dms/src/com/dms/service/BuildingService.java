package com.dms.service;

import java.util.List;

import com.dms.entity.Building;
import com.dms.entity.PageBean;

public interface BuildingService {
	void add(Building building)throws Exception;

	

	List<Building> findBuildingBySex(String sex)throws Exception;

	List<Building> findBuidlingByAid(String aid)throws Exception;

	PageBean<Building> findAllBuilding(int currPage, int pageSize)throws Exception;



	List<Building> findAllBuilding()throws Exception;



	Building findBuildByBid(String bid)throws Exception;



	Building findBuildingByNumber(int num)throws Exception;;

}
