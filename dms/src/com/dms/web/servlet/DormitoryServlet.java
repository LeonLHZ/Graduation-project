package com.dms.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import com.dms.entity.Admin;
import com.dms.entity.Building;
import com.dms.entity.Dormitory;
import com.dms.entity.PageBean;
import com.dms.entity.User;
import com.dms.service.AdminService;
import com.dms.service.BuildingService;
import com.dms.service.DormitoryService;
import com.dms.utils.BeanFactory;
import com.dms.utils.UUIDUtils;

/**
 * Servlet implementation class DormitoryServlet
 */
@MultipartConfig
public class DormitoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 增加新的寝室
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		String num = request.getParameter("num");
		String bid = request.getParameter("bid");
		
		
		DormitoryService ds = (DormitoryService) BeanFactory.getBean("DormitoryService");
		Dormitory d= ds.finDormitoryByBidNumber(bid,num);
		if (d!=null) {
			 response.getWriter().print("<script>alert('该宿舍已存在！');window.location ='"+request.getContextPath()+"/jsp/superadmin/dormitory/add.jsp';</script>");
	       	 return null;
		}
		int number = Integer.parseInt(request.getParameter("number"));
		Part part = request.getPart("img");

		InputStream is = part.getInputStream();

		String string = part.getHeader("Content-Disposition");

		String filename = string.substring(string.indexOf("filename=") + 9, string.length() - 1);
		System.out.println(filename);

		String suffix = filename.substring(filename.lastIndexOf(".") - 1);

		String newfilename = UUIDUtils.getId() + suffix;

		String realpath = newfilename;

		OutputStream os = new FileOutputStream("D:\\img\\" + newfilename);

		IOUtils.copy(is, os);
		is.close();
		os.close();

		Dormitory dormitory = new Dormitory();
		dormitory.setDorid(UUIDUtils.getId());
		dormitory.setNumber(number);
		dormitory.setNum(num);
		dormitory.setNownumber(0);
		dormitory.setIntegral(10);
		dormitory.setImg(newfilename);
		Building building = new Building();
		building.setBid(bid);

		dormitory.setBuilding(building);
		try {
			

			ds.add(dormitory);
		} catch (Exception e) {
			request.setAttribute("msg", "增加寝室失败");
			request.getRequestDispatcher("").forward(request, response);
			e.printStackTrace();
		}
		response.getWriter().print("<script>alert('新增宿舍成功！');window.location='build?method=findAllBuilding';</script>");

		return null;
	}

	public String addUI(HttpServletRequest request, HttpServletResponse response) throws Exception {

		BuildingService bs = (BuildingService) BeanFactory.getBean("BuildingService");
		List<Building> list = bs.findAllBuilding();
		request.setAttribute("blist", list);
		return "/jsp/superadmin/dormitory/add.jsp";

	}

	public String findAllDormitory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String bid = request.getParameter("bid");
		int currPage;
		String string = request.getParameter("currPage");
		if (string==null) {
			currPage = 1;
		}else {
			currPage= Integer.parseInt(string);
		}
		String code = request.getParameter("code");
		DormitoryService ds = (DormitoryService) BeanFactory.getBean("DormitoryService");
		int pageSize = 10;
		
		PageBean<Dormitory> pageBean = ds.findAllDormitory(bid, pageSize, currPage);
		request.setAttribute("pg", pageBean);
		if ("2".equals(code))
			return "/jsp/superadmin/building/dlist.jsp";
		if ("1".equals(code)) {
			if(checkAdmin(request, response))
				return null;
			return "/jsp/admin/student/dlist.jsp";
		}
		if ("3".equals(code)) {
			response.setContentType("text/html;charset=utf-8");
              
			BuildingService bs = (BuildingService) BeanFactory.getBean("BuildingService");
			Building building = bs.findBuildByBid(bid);
			if (building.getChangedormitory() == 0) {
				response.getWriter().print("<script>alert('抱歉该功能还未开启！');window.location='" + request.getContextPath()
						+ "/jsp/student/welcome.jsp';</script>");
				return null;
			}
			return "/jsp/student/dormitory/change.jsp";
		}
		return null;

	}

	public String lookposition(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String code = request.getParameter("code");

		String dorid = request.getParameter("dorid");

		DormitoryService ds = (DormitoryService) BeanFactory.getBean("DormitoryService");

		Dormitory dormitory = ds.lookposition(dorid);

		request.setAttribute("dormitory", dormitory);
		if ("1".equals(code))
			return "/jsp/student/dormitory/postionlist.jsp";
		if ("2".equals(code))
			return "/jsp/student/dormitory/changepostion.jsp";
		return null;

	}
    public Boolean checkAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html; charset=UTF-8");
		User user = (User) request.getSession().getAttribute("user");
		
		AdminService as = (AdminService) BeanFactory.getBean("AdminService");
		Admin admin = as.findAdminById(user.getUid());
		if (admin.getBid()==null||"".equals(admin.getBid())) {
			response.getWriter().print("<script>alert('未授权的管理员！');window.location='" + request.getContextPath()
			+ "'/jsp/admin/welcom.jsp;</script>");
			return true;
		}else {
			user.setAdmin(admin);
			request.getSession().setAttribute("user", user);
			return false;
		}
		}
}
