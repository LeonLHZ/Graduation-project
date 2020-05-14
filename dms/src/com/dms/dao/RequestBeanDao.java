package com.dms.dao;

import java.util.Date;
import java.util.List;

import com.dms.entity.Building;
import com.dms.entity.RequestBean;

public interface RequestBeanDao {

	void addRequestBean(RequestBean requestBean, int status)throws Exception;

	

	RequestBean findRequestByRid(String rid)throws Exception;

	void updateStateAndOvertime(int state, Date date,String rid)throws Exception;

	List<RequestBean> findRequestBySid(String rnum)throws Exception;

	int findRequestByUsername(String username)throws Exception;

	int findRequestCount(String bid)throws Exception;

	List<RequestBean> findAllRequest(String bid, int currPage, int pageSize)throws Exception;



	List<RequestBean> getRequestBybeId(String uid)throws Exception;



	void updateStateAndOvertime(Date date, String uid)throws Exception;



	void dealStateAndOvertimeByBernum(Date date, String s1)throws Exception;



	void dealStateAndOvertimeByRnum(Date date, String s1) throws Exception;

}
