package com.dms.dao;

import java.util.List;

import com.dms.entity.Building;
import com.dms.entity.Dormitory;

public interface BuildingDao {
 void add(Building building)throws Exception;

List<Building> findAllBuilding(int currPage, int pageSize)throws Exception;

List<Building> findBuildingBySex(String sex)throws Exception;

List<Building> findBuidlingByAid(String aid)throws Exception;

int findBuildCount()throws Exception;

void cutAdminNumber(String bid)throws Exception;

void increatAdminnumber(String bid)throws Exception;

List<Building> findAllBuilding()throws Exception;

void updateStudentNumber(Dormitory dormitory)throws Exception;

void updateNowStudentNumber(String dorid)throws Exception;

Building findBuildByBid(String bid)throws Exception;

void cutNowStudentNumber(String bid)throws Exception;

Building findBuildingByNumber(int num)throws Exception;


}
