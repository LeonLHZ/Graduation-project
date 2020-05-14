package com.dms.web.servlet;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.dms.entity.Admin;
import com.dms.entity.Employer;
import com.dms.entity.Repair;
import com.dms.entity.User;
import com.dms.service.EmployerService;
import com.dms.service.RepairService;
import com.dms.service.UserService;
import com.dms.utils.BeanFactory;
import com.dms.utils.MD5Utils;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class EmployerServlet
 */
public class EmployerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		Employer employer = new Employer();
		BeanUtils.populate(employer, request.getParameterMap());
		EmployerService es = (EmployerService) BeanFactory.getBean("EmployerService");
		es.update(employer);

		Employer newEmployer = es.findEmployerByEid(employer.getEid());
		
		User user = (User) request.getSession().getAttribute("user");
		
		user.setEmployer(newEmployer);
		request.getSession().setAttribute("user", user);
		response.getWriter().print("<script>alert('更改成功');window.location ='" + request.getContextPath()
		+"/jsp/employer/myself/update.jsp';</script>");
		return null;
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String updatePassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
	
		String password = request.getParameter("password");

		User user = (User) request.getSession().getAttribute("user");
		
		user.setPassword(MD5Utils.md5(password));
		
		UserService us = (UserService) BeanFactory.getBean("UserService");
		
		us.updatePassword(user);
		request.getSession().invalidate();
//	response.sendRedirect(request.getContextPath()+"/employer/test.jsp");
		// response.sendRedirect(request.getContextPath()+"/index.jsp");
		response.getWriter().print("<script>alert('密码修改成功，请重新登录');window.top.location ='" + request.getContextPath()
				+ "/index.jsp';</script>");

		return null;

	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String updateUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/jsp/employer/myself/update.jsp";
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String passwordUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/jsp/employer/myself/password.jsp";
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {

		RepairService rs = (RepairService) BeanFactory.getBean("RepairService");

		List<Repair> list = rs.employerFindRepair(((User) request.getSession().getAttribute("user")).getUid());

		request.setAttribute("rlist", list);
		return "/jsp/employer/task/repairlist.jsp";

	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String checkPassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		String old = request.getParameter("oldpassword");
		String eid = request.getParameter("eid");
		EmployerService es = (EmployerService) BeanFactory.getBean("EmployerService");
		Boolean flag = es.checkPassword(eid, MD5Utils.md5(old));
		if (flag)
			response.getWriter().print(1);
		else
			response.getWriter().print(0);

		return null;
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String deal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String rid = request.getParameter("rid");
		RepairService rs = (RepairService) BeanFactory.getBean("RepairService");
		rs.deal(rid);
		response.sendRedirect(request.getContextPath() + "/employer?method=findAll");
		return null;

	}
}
