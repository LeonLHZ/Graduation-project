package com.dms.service;

import java.sql.SQLException;
import java.util.List;

import com.dms.entity.CheckDor;
import com.dms.entity.Dormitory;
import com.dms.entity.Student;

public interface CheckDorService {



	public Student check(String dornum, String studentNumber,String bid) throws Exception;



	public void checkDor(CheckDor checkDor);






	public List<Dormitory> beautifulDor(String bid) throws SQLException;



}
