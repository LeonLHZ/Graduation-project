package com.dms.dao;

import java.sql.SQLException;
import java.util.List;

import com.dms.entity.Student;
import com.dms.entity.User;

public interface StudentDao {
	

	void add(User user) throws Exception;

	void update(Student student) throws Exception;

	Student findStudentById(String sid) throws Exception;

	Student findStudentByDoridsid(String dorid, String sid) throws Exception;

	void updateDorid(String sid, String dorid) throws Exception;

	Student findStudentByUsername(String bernum, String dorid) throws Exception;

	void updatePassword(String sid, String md5) throws Exception;

	Student findStudentByUsername(String studentNumber) throws SQLException;

	Boolean checkPassword(String sid, String md5) throws SQLException;

	Student login(String uid) throws Exception;

	int findStudentCount(String aid)throws Exception;

	List<Student> findAllStudent(int currPage, int pageSize,String aid) throws Exception;

	void updateDoridAndPid(String sid, String dorid,String pid)throws Exception;

	List<Student> findStudentDonotSelect(String uid)throws Exception;

	List<Object> excelOut(String bid)throws Exception;

	List<Student> findtimeoutStudent(String bid)throws Exception;

	void deleteStudent(String sid)throws Exception;



}
