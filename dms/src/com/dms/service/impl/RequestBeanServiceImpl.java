package com.dms.service.impl;

import java.util.Date;
import java.util.List;

import com.dms.dao.RequestBeanDao;
import com.dms.entity.Building;
import com.dms.entity.PageBean;
import com.dms.entity.RequestBean;
import com.dms.service.RequestBeanService;
import com.dms.utils.BeanFactory;
import com.dms.utils.DataSourceUtils;

public class RequestBeanServiceImpl implements RequestBeanService {

	@Override
	public void addRequestBean(RequestBean requestBean, int status) throws Exception {
		RequestBeanDao rd = (RequestBeanDao) BeanFactory.getBean("RequestBeanDao");
		rd.addRequestBean(requestBean, status);

	}

	@Override
	public PageBean<RequestBean> findAllRequest(String bid, int currPage, int pageSize) throws Exception {
		RequestBeanDao rd = (RequestBeanDao) BeanFactory.getBean("RequestBeanDao");

		int totalCount = rd.findRequestCount(bid);

		List<RequestBean> list = rd.findAllRequest(bid, currPage, pageSize);

		PageBean<RequestBean> pageBean = new PageBean<>(list, currPage, totalCount, pageSize);
		return pageBean;
	}

	@Override
	public RequestBean findRequestByRid(String rid) throws Exception {
		RequestBeanDao rd = (RequestBeanDao) BeanFactory.getBean("RequestBeanDao");
		return rd.findRequestByRid(rid);
	}

	@Override
	public void refuse(String rid) throws Exception {
		RequestBeanDao rd = (RequestBeanDao) BeanFactory.getBean("RequestBeanDao");
		int state = 0;
		rd.updateStateAndOvertime(state, new Date(), rid);

	}

	@Override
	public List<RequestBean> findRequestBySid(String rnum) throws Exception {
		RequestBeanDao rd = (RequestBeanDao) BeanFactory.getBean("RequestBeanDao");
		return rd.findRequestBySid(rnum);
	}

	@Override
	public int findRequestByUsername(String username) throws Exception {
		RequestBeanDao rd = (RequestBeanDao) BeanFactory.getBean("RequestBeanDao");
		return rd.findRequestByUsername(username);
	}

	@Override
	public List<RequestBean> getRequestBybeId(String uid) throws Exception {
		RequestBeanDao rd = (RequestBeanDao) BeanFactory.getBean("RequestBeanDao");
		return rd.getRequestBybeId(uid);
	}

	@Override
	public void bsdeal(String code, String rid, String uid) throws Exception {
		RequestBeanDao rd = (RequestBeanDao) BeanFactory.getBean("RequestBeanDao");
		Date date = new Date();
		if ("1".equals(code)) {
			
				rd.updateStateAndOvertime(3, date, rid);
				rd.updateStateAndOvertime(date, uid);
				
			
		}
		if ("0".equals(code))
			rd.updateStateAndOvertime(0, date, rid);
	}

}
