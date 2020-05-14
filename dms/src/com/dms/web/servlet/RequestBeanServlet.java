package com.dms.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dms.entity.RequestBean;
import com.dms.entity.Student;
import com.dms.entity.User;
import com.dms.service.RequestBeanService;
import com.dms.utils.BeanFactory;

/**
 * Servlet implementation class RequestBeanServlet
 */
public class RequestBeanServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String findRequestBySid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		String rnum = user.getUid();
		RequestBeanService rs = (RequestBeanService) BeanFactory.getBean("RequestBeanService");
		List<RequestBean> list = rs.findRequestBySid(rnum);
		request.setAttribute("rlist", list);
		return "/jsp/student/request/requestlist.jsp";
		
	}


}
