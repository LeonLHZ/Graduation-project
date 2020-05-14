package com.dms.dao;

import java.sql.SQLException;
import java.util.List;

import com.dms.entity.Repair;

public interface RepairDao {

	void addRepair(Repair repair)throws Exception;

	List<Repair> findAllRepair(String aid)throws Exception;

	void dealEmployer(String rid, String eid)throws Exception;

	List<Repair> employerFindRepair(String eid)throws Exception;

	List<Repair> findrepairByDorid(String dorid) throws Exception;

	void deal(String rid) throws SQLException;

}
