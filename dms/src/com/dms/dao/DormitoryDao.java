package com.dms.dao;

import java.sql.SQLException;
import java.util.List;

import com.dms.entity.Building;
import com.dms.entity.CheckDor;
import com.dms.entity.Dormitory;
import com.dms.entity.Position;
import com.dms.entity.Student;
import com.dms.entity.User;

public interface DormitoryDao {
void add(Dormitory dormitory)throws Exception;

List<Dormitory> findByDid(String bid)throws Exception;

void update(Dormitory dormitory)throws Exception;

Dormitory findBybid(String dorid)throws Exception;

void updateNownumber(String dorid,int i)throws Exception;

Dormitory findByDorid(String dorid)throws Exception;

String findDoridByNum(String dornum,String bid)throws Exception;

void updateIntegral(CheckDor c) throws SQLException;



List<Dormitory> beautifulDor(String bid) throws SQLException;

List<Dormitory> findAllDormitory(String bid,int pageSize, int currPage) throws Exception;

int findPageCount(String bid)throws Exception;

void addPosition(Position position)throws Exception;

Dormitory lookposition(String dorid)throws Exception;

void updatePosition(String sid, String pid)throws Exception;

Position findPositionByPid(String pid)throws Exception;

void updatePositionSid(String sid, int status, String pid)throws Exception;



Student findMyDormitory(User user) throws Exception;

Dormitory finDormitoryByBidNumber(String bid, String number)throws Exception;

Dormitory showStudent(String dorid)throws Exception;



}
