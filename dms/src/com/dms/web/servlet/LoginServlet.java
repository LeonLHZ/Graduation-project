package com.dms.web.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dms.constant.Constant;
import com.dms.entity.Admin;
import com.dms.entity.Announcement;
import com.dms.entity.Dormitory;
import com.dms.entity.Employer;
import com.dms.entity.Student;
import com.dms.entity.SuperAdmin;
import com.dms.entity.User;
import com.dms.service.AdminService;
import com.dms.service.AnnouncementService;
import com.dms.service.CheckDorService;
import com.dms.service.EmployerService;
import com.dms.service.StudentService;
import com.dms.service.SuperAdminService;
import com.dms.service.UserService;
import com.dms.utils.BeanFactory;
import com.dms.utils.MD5Utils;
import com.dms.utils.CodeUtil;
import com.dms.utils.CookieUtils;

/**
 * Servlet implementation class UserServlet
 */
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 登录方法
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String code = request.getParameter("code");
		Cookie cookie = CookieUtils.getCookieByName("code", request.getCookies());
		if (cookie == null) {

			request.setAttribute("msg", "验证码过期");
			return "index.jsp";
		}

		String num = cookie.getValue();
		if (!code.equalsIgnoreCase(num)) {
			request.setAttribute("msg", "验证码错误");
			return "index.jsp";
		}
		String username = request.getParameter("username");
		String password = MD5Utils.md5((request.getParameter("password")));
		UserService us = (UserService) BeanFactory.getBean("UserService");
		
		User user = us.login(username,password);
		if (user==null) {

			request.setAttribute("msg", "用户名或密码错误");

			return "/index.jsp";
		}
		
		
	
			switch (user.getType()) {

			case Constant.STUDENT:
				StudentService ss = (StudentService) BeanFactory.getBean("StudentService");
				Student student = ss.login(user.getUid());

				user.setStudent(student);
				//AnnouncementService s = (AnnouncementService) BeanFactory.getBean("AnnouncementService");
				//List<Announcement> list = s.findAllAnnouncement(student.getBid());
				//request.setAttribute("alist",list);
				
				request.getSession().setAttribute("user",user);
				//if(student.getBid()!=null) {
				  
				response.sendRedirect(request.getContextPath() + "/jsp/student/home.jsp");
				// return "/student/home.jsp";
				
				//}
				return null;

			case Constant.ADMIN:
				AdminService as = (AdminService) BeanFactory.getBean("AdminService");
				Admin admin = as.login(user.getUid());
				user.setAdmin(admin);
				request.getSession().setAttribute("user", user);

				response.sendRedirect(request.getContextPath() + "/jsp/admin/home.jsp");
				return null;

			case Constant.SUPERADMIN:
				SuperAdminService sas = (SuperAdminService) BeanFactory.getBean("SuperAdminService");
				SuperAdmin superAdmin = sas.login(user.getUid());
				user.setSuperAdmin(superAdmin);
				request.getSession().setAttribute("user", user);

				response.sendRedirect(request.getContextPath() + "/jsp/superadmin/home.jsp");
				return null;

			case Constant.EMPLOYEE:
				EmployerService es = (EmployerService) BeanFactory.getBean("EmployerService");
				Employer employer = es.login(user.getUid());
				user.setEmployer(employer);
				request.getSession().setAttribute("user", user);

				response.sendRedirect(request.getContextPath() + "/jsp/employer/home.jsp");
				return null;

			default:
				request.setAttribute("msg", "不存在该用户");
				return "/index.jsp";
			}
	}

	/**
	 * 退出登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String logOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().invalidate();
		response.getWriter()
				.print("<script>window.top.location='" + request.getContextPath() + "/index.jsp';</script>");
		return null;

	}

	/**
	 * 获取验证码
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getImg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Map map = CodeUtil.getCode();
		String code = (String) map.get("msg");
		Cookie cookie = new Cookie("code", code);
		cookie.setMaxAge(60);
		cookie.setPath(request.getContextPath() + "/");
		response.addCookie(cookie);

		BufferedImage bufferedImage = (BufferedImage) map.get("bufferedImage");
		ImageIO.write(bufferedImage, "jpg", response.getOutputStream());

		// request.getSession().setAttribute("code", code);

	}

}
