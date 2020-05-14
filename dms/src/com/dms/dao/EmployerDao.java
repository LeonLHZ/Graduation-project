package com.dms.dao;

import java.sql.SQLException;
import java.util.List;

import com.dms.entity.Employer;
import com.dms.entity.User;

public interface EmployerDao {

	void add(User user)throws Exception;

	Employer login(String eid)throws Exception;

	

	void update(Employer employer)throws Exception;

	Employer findEmployerByEid(String eid)throws Exception;

	void updatePassword(String eid,String md5)throws Exception;

	Employer checkPassword(String eid, String old) throws SQLException;

	List<Employer> findByNumber(String number)throws Exception;

	int findTotalCount()throws Exception;

	List<Employer> findAllEmployerByPage(int currPage, int pageSize)throws SQLException;

	List<Employer> findAllEmployer()throws SQLException;

	Employer findEmployerByUserName(String username)throws SQLException;





}
