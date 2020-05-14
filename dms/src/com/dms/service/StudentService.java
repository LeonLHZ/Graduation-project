package com.dms.service;

import java.sql.SQLException;
import java.util.List;

import com.dms.entity.Announcement;
import com.dms.entity.PageBean;
import com.dms.entity.RequestBean;
import com.dms.entity.Student;
import com.dms.entity.User;

public interface StudentService {



void update(Student student)throws Exception;

Student findStudentById(String sid)throws Exception;

Student findStudentByDoridsid(String dorid, String sid)throws Exception;

void processingRequest(RequestBean bean)throws Exception;

void changeDor(RequestBean rs)throws Exception;

Student findStudentByUsername(String bernum, String dorid)throws Exception;

void updatePassword(String sid, String md5)throws Exception;

Boolean checkPassword(String sid, String md5) throws SQLException;

Student login(String uid) throws Exception;

PageBean<Student> findAllStudent(int currPage, int pageSize,String aid)throws Exception;

List<Student> addStudentByExcel(List<User> list)throws Exception;

List<Student> findStudentDonotSelect(String uid)throws Exception;

List<Object> excelOut(String bid)throws Exception;

List<Student> findtimeoutStudent(String bid) throws Exception;

void dealtimeout(String sid)throws Exception;




}
