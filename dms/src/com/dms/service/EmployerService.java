package com.dms.service;

import java.sql.SQLException;
import java.util.List;

import com.dms.entity.Employer;
import com.dms.entity.PageBean;

public interface EmployerService {

	

	Employer login(String eid)throws Exception;

	

	void update(Employer employer)throws Exception;

	Employer findEmployerByEid(String eid)throws Exception;

	void updatePassword(String eid,String md5)throws Exception;

	Boolean checkPassword(String eid, String old) throws SQLException;

	List<Employer> findByNumber(String number)throws Exception;

	PageBean<Employer> findAllEmployerByPage(int currPage, int pageSize)throws Exception;



	List<Employer> findAllEmployer()throws Exception;

	

}
