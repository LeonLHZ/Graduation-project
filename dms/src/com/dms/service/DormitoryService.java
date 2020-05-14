package com.dms.service;

import java.sql.SQLException;
import java.util.List;

import com.dms.entity.Building;
import com.dms.entity.Dormitory;
import com.dms.entity.PageBean;
import com.dms.entity.Position;
import com.dms.entity.Student;
import com.dms.entity.User;

public interface DormitoryService {
void add(Dormitory dormitory)throws Exception;

List<Dormitory> findByDid(String bid)throws Exception;


void selectThis(String dorid, String sid, String pid)throws Exception;

Dormitory findByDorid(String dorid)throws Exception;

PageBean<Dormitory> findAllDormitory(String bid, int pageSize, int currPage) throws  Exception;

Dormitory lookposition(String dorid)throws  Exception;

Position findPositionByPid(String pid)throws  Exception;

Student findMyDormitory(User user)throws  Exception;

Dormitory finDormitoryByBidNumber(String bid, String number)throws  Exception;

Dormitory showStudent(String dorid)throws  Exception;


}
