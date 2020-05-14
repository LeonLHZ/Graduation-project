package com.dms.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.dms.entity.Admin;
import com.dms.entity.Building;
import com.dms.entity.PageBean;
import com.dms.service.AdminService;
import com.dms.service.BuildingService;
import com.dms.utils.BeanFactory;
import com.dms.utils.UUIDUtils;

/**
 * Servlet implementation class BuildingServlet
 */
public class BuildingServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 新增宿舍楼
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		int num = Integer.parseInt(request.getParameter("number"));
		BuildingService bs = (BuildingService) BeanFactory.getBean("BuildingService");
		Building b = bs.findBuildingByNumber(num);
		if (b!=null) {
			 response.getWriter().print("<script>alert('该楼栋已存在！');window.location ='"+request.getContextPath()+"/jsp/superadmin/building/add.jsp';</script>");
	       	 return null;
		}
		String sex = request.getParameter("studentsex");
       
		Building building = new Building();

		building.setBid(UUIDUtils.getId());
		building.setStudentsex(sex);
		building.setNumber(num);

		
		bs.add(building);
		response.getWriter().print("<script>alert('处理成功');window.location='build?method=findAllBuilding';</script>");

		
		return null;

	}

	/**
	 * 跳到增加寝室楼界面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return "/jsp/superadmin/building/add.jsp";

	}

	public String findAllBuilding(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BuildingService bs = (BuildingService) BeanFactory.getBean("BuildingService");
		
		int currPage=1;
		int pageSize=10;
		PageBean<Building> pg = bs.findAllBuilding(currPage,pageSize);
		request.setAttribute("pg", pg);
		return "/jsp/superadmin/building/list.jsp";

	}

}
