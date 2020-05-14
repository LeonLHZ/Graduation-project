package com.dms.web.servlet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;

import com.dms.constant.Constant;
import com.dms.entity.Admin;
import com.dms.entity.Announcement;
import com.dms.entity.Building;
import com.dms.entity.Employer;
import com.dms.entity.PageBean;
import com.dms.entity.Student;
import com.dms.entity.SuperAdmin;
import com.dms.entity.User;
import com.dms.service.AdminService;
import com.dms.service.AnnouncementService;
import com.dms.service.BuildingService;
import com.dms.service.EmployerService;
import com.dms.service.StudentService;
import com.dms.service.SuperAdminService;
import com.dms.service.UserService;
import com.dms.utils.BeanFactory;
import com.dms.utils.ExcelBuilding;
import com.dms.utils.ExcelUtil;
import com.dms.utils.MD5Utils;
import com.dms.utils.UUIDUtils;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class SuperAdminServlet
 */
@MultipartConfig
public class SuperAdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 进入到增加员工界面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/superadmin/admin/add.jsp";

	}

	/**
	 * 增加员工
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		UserService us = (UserService) BeanFactory.getBean("UserService");
		User user = new User();
           
		BeanUtils.populate(user, request.getParameterMap());
         User u = us.findUserByUsername(user.getUsername());
         if (u!=null) {
        	 response.getWriter().print("<script>alert('该用户已存在！');window.location ='"+request.getContextPath()+"/jsp/superadmin/admin/add.jsp';</script>");
        	 return null;
		}
		if (Constant.ADMIN.equals(request.getParameter("type"))) {
			Admin admin = new Admin();
			BeanUtils.populate(admin, request.getParameterMap());

			user.setAdmin(admin);

			user.setUid((UUIDUtils.getId()));
			user.setPassword(MD5Utils.md5("123456"));
		

			us.add(user);
			response.getWriter()
					.print("<script>alert('新增宿舍管理员成功！');window.location='super?method=findAllAdmin';</script>");
		}
		if (Constant.EMPLOYEE.equals(request.getParameter("type"))) {
			Employer employer = new Employer();

			BeanUtils.populate(employer, request.getParameterMap());

			user.setEmployer(employer);

			user.setUid((UUIDUtils.getId()));
			user.setPassword(MD5Utils.md5("123456"));
			

			us.add(user);
			response.getWriter()
					.print("<script>alert('新增维修人员成功！');window.location='super?method=findAllEmployer';</script>");
		}

		return null;

	}

	public String updatePasswordUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 String ip = request.getHeader("x-forwarded-for");  
		    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getHeader("Proxy-Client-IP");  
		    }  
		    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getHeader("WL-Proxy-Client-IP");  
		    }  
		    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getRemoteAddr();  
		    }  
		    
		    System.out.println(ip);
		return "/jsp/superadmin/myself/password.jsp";

	}

	public String updatePassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UserService us = (UserService) BeanFactory.getBean("UserService");
		User user = (User) request.getSession().getAttribute("user");
		user.setPassword(MD5Utils.md5(password));
		us.updatePassword(user);

		request.getSession().invalidate();
//	response.sendRedirect(request.getContextPath()+"/employer/test.jsp");
		// response.sendRedirect(request.getContextPath()+"/index.jsp");
		response.getWriter().print("<script>alert('密码修改成功，请重新登录');window.top.location ='" + request.getContextPath()
				+ "/index.jsp';</script>");

		return null;

	}

	public String checkPassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		String old = request.getParameter("oldpassword");
		String username = request.getParameter("username");

		SuperAdminService ss = (SuperAdminService) BeanFactory.getBean("SuperAdminService");
		Boolean flag = ss.checkPassword(username, MD5Utils.md5(old));
		if (flag)
			response.getWriter().print(1);
		else
			response.getWriter().print(0);

		return null;
	}

	public String findAllEmployer(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int currPage = 1;
		int pageSize = 10;
		EmployerService es = (EmployerService) BeanFactory.getBean("EmployerService");
		PageBean<Employer> pageBean = es.findAllEmployerByPage(currPage, pageSize);

		request.setAttribute("pg", pageBean);
		return "/jsp/superadmin/admin/employerlist.jsp";

	}

	public String findAllAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int currPage = 1;
		int pageSize = 10;
		AdminService as = (AdminService) BeanFactory.getBean("AdminService");
		PageBean<Admin> pageBean = as.findAllAdminrByPage(currPage, pageSize);
		request.setAttribute("pg", pageBean);
		return "/jsp/superadmin/admin/adminlist.jsp";

	}

	public String findByNumber(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=utf-8");
		String number = request.getParameter("number");
		System.out.println(number);
		EmployerService es = (EmployerService) BeanFactory.getBean("EmployerService");
		List<Employer> eList = es.findByNumber(number);
		System.out.println(eList);
		response.getWriter().print(JSONArray.fromObject(eList));
		return null;

	}

	public String findAllAdminByPg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		try {
			int currPage = Integer.parseInt(request.getParameter("currPage"));

			int pageSize = 10;
			AdminService as = (AdminService) BeanFactory.getBean("AdminService");
			 

			 PageBean<Admin> pageBean = as.findAllAdminrByPage(currPage, pageSize);
			request.setAttribute("pg", pageBean);
		} catch (Exception e) {
			response.getWriter()
					.print("<script>alert('页码不存在！');window.location='super?method=findAllAdmin';</script>");
			e.printStackTrace();
		}

		return "/jsp/superadmin/admin/adminlist.jsp";

	}
	
	public String findAdminByUserName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return null;
		
	}
	
	public String findAllEmployerByPg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		try {
			int currPage = Integer.parseInt(request.getParameter("currPage"));

			int pageSize = 10;
			EmployerService es =  (EmployerService) BeanFactory.getBean("EmployerService");
		

			 PageBean<Employer> pageBean = es.findAllEmployerByPage(currPage, pageSize);
			request.setAttribute("pg", pageBean);
		} catch (Exception e) {
			response.getWriter()
					.print("<script>alert('页码不存在！');window.location='super?method=findAllAdmin';</script>");
			e.printStackTrace();
		}
		return "/jsp/superadmin/admin/employerlist.jsp";
	}
		
		/**解除楼栋管理员
		 * @param request
		 * @param response
		 * @return
		 * @throws IOException
		 */
		public String releaseAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
			response.setContentType("text/html;charset=utf-8");
			String aid = request.getParameter("aid");
			AdminService as = (AdminService) BeanFactory.getBean("AdminService");
			as.releaseAdmin(aid);
			response.getWriter().print("<script>alert('成功解除！');window.location='"+request.getContextPath()+"/super?method=findAllAdmin';</script>");
			return null;
			
		}
		public String findAllBuilding(HttpServletRequest request, HttpServletResponse response) throws Exception {
			String aid = request.getParameter("aid");
			
			BuildingService bs = (BuildingService) BeanFactory.getBean("BuildingService");
			
			int currPage=1;
			int pageSize=10;
			PageBean<Building> pg = bs.findAllBuilding(currPage,pageSize);
			request.setAttribute("pg", pg);
			request.setAttribute("aid",aid);
			return "/jsp/superadmin/admin/list.jsp";
			
		}
		
		public String setBid(HttpServletRequest request, HttpServletResponse response) throws Exception {
			response.setContentType("text/html;charset=utf-8");
			String bid = request.getParameter("bid");
			String aid = request.getParameter("aid");
			AdminService as = (AdminService) BeanFactory.getBean("AdminService");
			Admin admin = as.findAdminById(aid);
			if(null!=admin.getBid()||"".equals(admin.getBid())) {
				as.changeBid(admin.getBid(),bid,aid);
				response.getWriter().print("<script>alert('修改管理楼栋成功！');window.location='"+request.getContextPath()+"/super?method=findAllAdmin';</script>");
				return null;
			}
			as.setBid(aid,bid);
			response.getWriter()
			.print("<script>alert('设置管理楼栋成功！');window.location='"+request.getContextPath()+"/super?method=findAllAdmin';</script>");
			return null;
			
		}
		
		
		public String announcement(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			return "/jsp/superadmin/admin/announcement.jsp";
		}
		
		public String release(HttpServletRequest request, HttpServletResponse response) throws Exception {
			response.setContentType("text/html; charset=UTF-8");
			String text = request.getParameter("html");
			User user = (User) request.getSession().getAttribute("user");
			Announcement announcement = new Announcement();
			announcement.setAnnid(UUIDUtils.getId());
			announcement.setAid(user.getUid());
			announcement.setBid(user.getUid());
			announcement.setContent(text);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = new Date();
			
			announcement.setReleasetime(simpleDateFormat.format(date));
		    AnnouncementService as = (AnnouncementService) BeanFactory.getBean("AnnouncementService");
		    as.release(announcement);
		
			response.getWriter().print("<script>alert('发布成功！');window.location='"+request.getContextPath()
			+ "/jsp/superadmin/admin/announcement.jsp'</script>");
			return null;
			
		}
		
		public String findAllAnnouncementUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			return "/jsp/superadmin/admin/alist.jsp";
			
		}
		
		public String findAllAnnouncement(HttpServletRequest request, HttpServletResponse response) throws Exception {
			response.setContentType("text/html;charset=utf-8");
			AnnouncementService s = (AnnouncementService) BeanFactory.getBean("AnnouncementService");
			
			List<Announcement> list = s.findAllAnnouncementBySuper("DCF0E994BAE543C8AC234E5952A910F4");
			response.getWriter().print(JSONArray.fromObject(list));
			return null;
			
		}
		
		public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
			response.setContentType("text/html;charset=utf-8");
			String aid = request.getParameter("aid");
			AnnouncementService s = (AnnouncementService) BeanFactory.getBean("AnnouncementService");
			s.delete(aid);
			response.getWriter().print("<script>alert('操作成功！');window.location='"+request.getContextPath()
			+ "/jsp/superadmin/admin/alist.jsp'</script>");
			return null;
			
		}
		
		
		public String updateUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
			return "/jsp/superadmin/myself/update.jsp";
		}
		
		
		public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
			response.setContentType("text/html;charset=utf-8");
			SuperAdmin superAdmin = new SuperAdmin();
			BeanUtils.populate(superAdmin, request.getParameterMap());
			SuperAdminService ss = (SuperAdminService) BeanFactory.getBean("SuperAdminService");
			SuperAdmin newadmin = ss.update(superAdmin);
			
			User user = (User) request.getSession().getAttribute("user");
			user.setSuperAdmin(newadmin);
			request.getSession().setAttribute("user", user);
			response.getWriter().print("<script>alert('更改成功');window.location ='"+request.getContextPath()+"/jsp/superadmin/myself/update.jsp';</script>");
			return null;
		}
		
		public String addByExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			response.setContentType("text/html;charset=utf-8");
			
			
	         Part part = request.getPart("excel");
			
			InputStream is= part.getInputStream();
	
		
			OutputStream os = new FileOutputStream("D:\\img\\employer.xlsx");
			
			IOUtils.copy(is, os);
			is.close();
			os.close();
			
			List<User>list = ExcelBuilding.AddStudentByExcel();
			
			
			UserService us = (UserService) BeanFactory.getBean("UserService");
			List<User>uList = us.addStudentByExcel(list);
			
			if (uList.size()!=0) {
				StringBuilder sb = new StringBuilder();
				for (User user : uList) {
					sb.append(user.getUsername()+",");
				}
				sb.delete(sb.length()-1, sb.length());
				response.getWriter().print("<script>alert('工号"+sb.toString()+"已存在无法增加，其余员工增加成功');window.location ='"+request.getContextPath()+"/jsp/superadmin/admin/add.jsp';</script>");
				return null;
			}
			response.getWriter().print("<script>alert('增加成功');window.location ='"+request.getContextPath()+"/jsp/superadmin/admin/add.jsp';</script>");
			return null;
		}
}

