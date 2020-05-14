package com.dms.service;

import java.util.List;

import com.dms.entity.Building;
import com.dms.entity.PageBean;
import com.dms.entity.Position;
import com.dms.entity.RequestBean;

public interface RequestBeanService {

	void addRequestBean(RequestBean requestBean, int status)throws Exception;

	PageBean<RequestBean> findAllRequest(String bid, int currPage, int pageSize)throws Exception;

	RequestBean findRequestByRid(String rid)throws Exception;

	void refuse(String rid)throws Exception;

	List<RequestBean> findRequestBySid(String rnum)throws Exception;

	int findRequestByUsername(String username)throws Exception;

	List<RequestBean> getRequestBybeId(String uid)throws Exception;

	void bsdeal(String code, String rid, String uid)throws Exception;





}
