package com.dms.web.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
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
import com.dms.entity.CheckDor;
import com.dms.entity.Dormitory;
import com.dms.entity.Employer;
import com.dms.entity.ExcelBean;
import com.dms.entity.PageBean;
import com.dms.entity.Repair;
import com.dms.entity.RequestBean;
import com.dms.entity.Student;
import com.dms.entity.User;
import com.dms.service.AdminService;
import com.dms.service.AnnouncementService;
import com.dms.service.BuildingService;
import com.dms.service.CheckDorService;
import com.dms.service.DormitoryService;
import com.dms.service.EmployerService;
import com.dms.service.RepairService;
import com.dms.service.RequestBeanService;
import com.dms.service.StudentService;
import com.dms.service.UserService;
import com.dms.utils.BeanFactory;
import com.dms.utils.ExcelOut;
import com.dms.utils.ExcelUtil;
import com.dms.utils.MD5Utils;
import com.dms.utils.UUIDUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class AdminServlet
 */
@MultipartConfig
public class AdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 进入添加员工界面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(checkAdmin(request, response))
            return null;
		return "/jsp/admin/student/add.jsp";
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public String findAllRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(checkAdmin(request, response))
            return null;
		int currPage;
		String string = request.getParameter("currPage");
		if (string==null) {
			currPage = 1;
		}else {
			currPage= Integer.parseInt(string);
		}
		response.setContentType("text/html; charset=UTF-8");
		BuildingService bs = (BuildingService) BeanFactory.getBean("BuildingService");
		User user = (User) request.getSession().getAttribute("user");
		// List<Building> blist = bs.findBuidlingByAid(user.getAdmin().getAid());
		RequestBeanService rs = (RequestBeanService) BeanFactory.getBean("RequestBeanService");

		
		
		AdminService as = (AdminService) BeanFactory.getBean("AdminService");
		Admin admin = as.findAdminById(user.getUid());
		/*if (admin.getBid()==null||admin.getBid()=="") {
			response.getWriter().print("<script>alert('未授权的管理员！');window.location='" + request.getContextPath()
			+ "'/jsp/admin/welcom.jsp;</script>");
			return null;
		}*/
		
		int pageSize=10;

		PageBean<RequestBean> pg= rs.findAllRequest(admin.getBid(),currPage,pageSize);

		request.setAttribute("pg", pg);
		return "/jsp/admin/request/changerequestlist.jsp";

	}

	/**
	 * 增加学生
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String addstudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		String bid = request.getParameter("bid");
		AdminService as = (AdminService) BeanFactory.getBean("AdminService");
		UserService us = (UserService) BeanFactory.getBean("UserService");
		BuildingService bs = (BuildingService) BeanFactory.getBean("BuildingService");
		Building building = bs.findBuildByBid(bid);
		User user = new User();

		BeanUtils.populate(user, request.getParameterMap());
		 User u = us.findUserByUsername(user.getUsername());
		if (u!=null) {
       	 response.getWriter().print("<script>alert('该学号已存在！');window.location ='"+request.getContextPath()+"/jsp/admin/student/add.jsp';</script>");
       	 return null;
		}
		Student student = new Student();
		student.setBid(bid);
		student.setSex(building.getStudentsex());
		BeanUtils.populate(student, request.getParameterMap());
        student.setStarttime(new Date());
		user.setPassword(MD5Utils.md5("123456"));
		user.setUid(UUIDUtils.getId());

		user.setStudent(student);
		
		us.add(user);
		response.getWriter().print("<script>alert('增加成功');window.location ='"+request.getContextPath()+"/admin?method=findAllStudent';</script>");
		return null;

	}

	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void deal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		String rid = request.getParameter("rid");
		
		RequestBeanService rs = (RequestBeanService) BeanFactory.getBean("RequestBeanService");
		RequestBean requestBean = rs.findRequestByRid(rid);

		
		StudentService ss = (StudentService) BeanFactory.getBean("StudentService");
		ss.changeDor(requestBean);

		response.getWriter().print("<script>alert('处理成功');window.location='admin?method=findAllRequest';</script>");

	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String findRequestByRid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String rid = request.getParameter("rid");
		RequestBeanService rs = (RequestBeanService) BeanFactory.getBean("RequestBeanService");
		RequestBean requestBean = rs.findRequestByRid(rid);
		request.setAttribute("rb", requestBean);
		return "/jsp/admin/request/deal.jsp";

	}

	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void refuse(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		String rid = request.getParameter("rid");
		RequestBeanService rs = (RequestBeanService) BeanFactory.getBean("RequestBeanService");
		rs.refuse(rid);
		response.getWriter().print("<script>alert('处理成功');window.location='admin?method=findAllRequest';</script>");

	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String repairRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(checkAdmin(request, response))
			return null;
		response.setContentType("text/html;charset=utf-8");
		RepairService rs = (RepairService) BeanFactory.getBean("RepairService");
		String bid = ((User) request.getSession().getAttribute("user")).getAdmin().getBid();

		if (bid == null||"".equals(bid))
			response.getWriter().print("<script>alert('处理成功');window.location='" + request.getContextPath()
					+ "'/jsp/admin/welcom.jsp;</script>");
		List<Repair> list = rs.findAllRepair(bid);
		System.out.println(list);
		request.setAttribute("rlist", list);
		return "/jsp/admin/request/repairlist.jsp";

	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String getEmployer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String rid = request.getParameter("rid");
		EmployerService es = (EmployerService) BeanFactory.getBean("EmployerService");
		List<Employer> list = es.findAllEmployer();
		request.setAttribute("rid", rid);
		request.setAttribute("elist", list);
		return "/jsp/admin/request/employerlist.jsp";

	}

	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dealEmployer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		String rid = request.getParameter("rid");
		String eid = request.getParameter("eid");
		RepairService rs = (RepairService) BeanFactory.getBean("RepairService");
		rs.dealEmployer(rid, eid);
		response.getWriter().print("<script>alert('处理成功');window.location='admin?method=repairRequest';</script>");

	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String updatePassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		String aid = ((User)request.getSession().getAttribute("user")).getUid();
		String password = request.getParameter("password");

		AdminService as = (AdminService) BeanFactory.getBean("AdminService");
		as.updatePassword(aid, MD5Utils.md5(password));

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
	public String passwordUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/jsp/admin/myself/password.jsp";
	}

	/**
	 * 检查寝室
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String checkDor(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		String dorid = request.getParameter("dorid");
		String sid = request.getParameter("sid");
		String description = request.getParameter("description");
		User user = (User) request.getSession().getAttribute("user");
		switch (description) {
		case "-5":
			description = Constant.HIGH_POWER_APPLIANCES;
			break;
		case "-3":
			description = Constant.KEEP_A_PET;
			break;
		case "-2":
			description = Constant.SMOKES;
			break;
		case "-1":
			description = Constant.DIRTY;
			break;

		default:
			break;
		}

		CheckDorService cs = (CheckDorService) BeanFactory.getBean("CheckDorService");

		CheckDor checkDor = new CheckDor();
		checkDor.setStarttime(new Date());
		checkDor.setDorid(dorid);
		checkDor.setDescription(description);
		checkDor.setSid(sid);
		checkDor.setCid(UUIDUtils.getId());
		cs.checkDor(checkDor);
		response.getWriter().print("<script>alert('检查记录完毕');window.location ='"+request.getContextPath()+"/dormitory?method=findAllDormitory&bid="+user.getAdmin().getBid()+"&code=1';</script>");
		return null;

	}

	/**
	 * ajax通过性别查询楼栋
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/*
	 * public String ajaxshowBysex(HttpServletRequest request, HttpServletResponse
	 * response) throws Exception {
	 * response.setContentType("text/html;charset=utf-8"); String sex =
	 * request.getParameter("sex"); BuildingService bs = (BuildingService)
	 * BeanFactory.getBean("BuildingService"); List<Building> list =
	 * bs.findBuildingBySex(sex);
	 * 
	 * response.getWriter().print(JSONArray.fromObject(list)); return null;
	 * 
	 * }
	 */

	public String checkUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String dorid = request.getParameter("dorid");
		DormitoryService ds=(DormitoryService) BeanFactory.getBean("DormitoryService");
		Dormitory dormitory = ds.showStudent(dorid);
		request.setAttribute("dormitory", dormitory);
		
		return "/jsp/admin/student/postionlist.jsp";

	}

	public String updateUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/jsp/admin/myself/update.jsp";
	}

	public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		Admin admin = new Admin();
		BeanUtils.populate(admin, request.getParameterMap());
		AdminService as = (AdminService) BeanFactory.getBean("AdminService");
		as.update(admin);
		Admin newadmin = as.findAdminById(admin.getAid());
		User user = (User) request.getSession().getAttribute("user");
		user.setAdmin(newadmin);
		request.getSession().setAttribute("user", user);
		response.getWriter().print("<script>alert('更改成功');window.location ='"+request.getContextPath()+"/jsp/admin/myself/update.jsp';</script>");
		return null;
	}

	public String checkPassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		String old = request.getParameter("oldpassword");
		String aid = request.getParameter("aid");
		System.out.println(old + aid);
		AdminService as = (AdminService) BeanFactory.getBean("AdminService");
		Boolean flag = as.checkPassword(aid, MD5Utils.md5(old));
		if (flag)
			response.getWriter().print(1);
		else
			response.getWriter().print(0);

		return null;
	}

	public String findAllStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(checkAdmin(request, response))
			return null;
		
		int currPage;
		String string = request.getParameter("currPage");
		if (string==null) {
			currPage = 1;
		}else {
			currPage= Integer.parseInt(string);
		}
		StudentService ss = (StudentService) BeanFactory.getBean("StudentService");
		User user = (User) request.getSession().getAttribute("user");
	
		int pageSize = 10;
		PageBean<Student> pg = ss.findAllStudent(currPage, pageSize, user.getAdmin().getAid());
		request.setAttribute("pg", pg);
		return "/jsp/admin/student/studentlist.jsp";

	}

	public String findBuildAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String bid = request.getParameter("bid");
		int currPage = 1;
		int pageSize = 10;
		AdminService as = (AdminService) BeanFactory.getBean("AdminService");
		PageBean<Admin> pageBean = as.findBuildAdmin(currPage, pageSize, bid);
		request.setAttribute("pg", pageBean);
		return "/jsp/superadmin/building/adminlist.jsp";

	}
	public String addStudentByExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		User user = (User) request.getSession().getAttribute("user");
		BuildingService bs = (BuildingService) BeanFactory.getBean("BuildingService");
		Building building = bs.findBuildByBid(user.getAdmin().getBid());
		
         Part part = request.getPart("excel");
		
		InputStream is= part.getInputStream();
		
	
		OutputStream os = new FileOutputStream("D:\\img\\excel.xlsx");
		
		IOUtils.copy(is, os);
		is.close();
		os.close();
		
		List<User>list = ExcelUtil. AddStudentByExcel(building);
		
		
		StudentService ss = (StudentService) BeanFactory.getBean("StudentService");
		
		List<Student> clList=ss.addStudentByExcel(list);
		if (clList.size()!=0) {
			StringBuilder sb = new StringBuilder();
			for (Student student : clList) {
				sb.append(student.getUsername()+",");
			}
			sb.delete(sb.length()-1, sb.length());
			response.getWriter().print("<script>alert('学号为"+sb.toString()+"已存在无法增加，其余学生增加成功');window.location ='"+request.getContextPath()+"/admin?method=findAllStudent';</script>");
			return null;
		}
		response.getWriter().print("<script>alert('增加成功');window.location ='"+request.getContextPath()+"/admin?method=findAllStudent';</script>");
		return null;
		
	}
	public String power(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(checkAdmin(request, response))
			return null;
		User user = (User) request.getSession().getAttribute("user");
		response.setContentType("text/html;charset=utf-8");
		
		BuildingService bs = (BuildingService) BeanFactory.getBean("BuildingService");
		Building building = bs.findBuildByBid(user.getAdmin().getBid());
		
		StudentService ss = (StudentService) BeanFactory.getBean("StudentService");
		
		List<Student> list = ss.findStudentDonotSelect(user.getUid());
		request.setAttribute("list", list);
		request.setAttribute("building", building);
		return "/jsp/admin/power/power.jsp";
		
	}
	
	public String changeChoice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		User user = (User) request.getSession().getAttribute("user");
		   String code = request.getParameter("code");
		   AdminService as = (AdminService) BeanFactory.getBean("AdminService");
		   as.changeChoice(code,user.getAdmin().getBid());
		
		   response.getWriter().print("<script>alert('修改权限成功');window.location ='" + request.getContextPath()+"/admin?method=power';</script>");
		return null;
		
	}
	
	
	public Boolean checkAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html; charset=UTF-8");
		User user = (User) request.getSession().getAttribute("user");
		
		AdminService as = (AdminService) BeanFactory.getBean("AdminService");
		Admin admin = as.findAdminById(user.getUid());
		if (admin.getBid()==null||"".equals(admin.getBid())) {
			response.getWriter().print("<script>alert('未授权的管理员！');window.location='" + request.getContextPath()
			+ "/jsp/admin/welcome.jsp';</script>");
			return true;
		}else {
			user.setAdmin(admin);
			request.getSession().setAttribute("user", user);
			return false;
		}
	}
	public String excelOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(checkAdmin(request, response))
			return null;
		StudentService ss = (StudentService) BeanFactory.getBean("StudentService");
		User user = (User) request.getSession().getAttribute("user");
		List<Object> list = ss.excelOut(user.getAdmin().getBid());
		ExcelBean excelBean = new ExcelBean(list,"学生信息表","学生信息表");
		ExcelOut.make(excelBean, response);
		return null;
		
	}
	public String announcement(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(checkAdmin(request, response))
			return null;
		return "/jsp/admin/power/announcement.jsp";
	}
	
	public String release(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		String text = request.getParameter("html");
		User user = (User) request.getSession().getAttribute("user");
		Announcement announcement = new Announcement();
		announcement.setAnnid(UUIDUtils.getId());
		announcement.setAid(user.getUid());
		announcement.setBid(user.getAdmin().getBid());
		announcement.setContent(text);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		
		announcement.setReleasetime(simpleDateFormat.format(date));
	    AnnouncementService as = (AnnouncementService) BeanFactory.getBean("AnnouncementService");
	    as.release(announcement);
	
		response.getWriter().print("<script>alert('发布成功！');window.location='"+request.getContextPath()
		+ "/jsp/admin/power/announcement.jsp'</script>");
		return null;
		
	}
	
	public String findAllAnnouncement(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		AnnouncementService s = (AnnouncementService) BeanFactory.getBean("AnnouncementService");
		User user = (User) request.getSession().getAttribute("user");
		List<Announcement> list = s.findAllAnnouncement(user.getAdmin().getBid());
		response.getWriter().print(JSONArray.fromObject(list));
		return null;
		
	}
	
	public String findAllAnnouncementUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(checkAdmin(request, response))
			return null;
		return "/jsp/admin/power/list.jsp";
		
	}
	
	public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		String aid = request.getParameter("aid");
		AnnouncementService s = (AnnouncementService) BeanFactory.getBean("AnnouncementService");
		s.delete(aid);
		response.getWriter().print("<script>alert('操作成功！');window.location='"+request.getContextPath()
		+ "/jsp/admin/power/list.jsp'</script>");
		return null;
		
	}
	
	public String findtimeoutStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(checkAdmin(request, response))
			return null;
		StudentService ss = (StudentService) BeanFactory.getBean("StudentService");
		User user = (User) request.getSession().getAttribute("user");
		
		List<Student>student = ss.findtimeoutStudent(user.getAdmin().getBid());
		
		request.setAttribute("list", student);
		return "/jsp/admin/power/timeout.jsp";
		
	}
	
	public String dealtimeout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		String sid = request.getParameter("sid");
		StudentService ss = (StudentService) BeanFactory.getBean("StudentService");
		ss.dealtimeout(sid);
		
		response.getWriter().print("<script>alert('操作成功！');window.location='"+request.getContextPath()
		+ "/admin?method=findtimeoutStudent'</script>");
		return null;
		
	}
	
	public String ajaxfindadmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		
		AdminService as = (AdminService) BeanFactory.getBean("AdminService");
		User user = (User) request.getSession().getAttribute("user");
		Admin admin = as.findAdminById(user.getAdmin().getAid());
		response.getWriter().print(JSONObject.fromObject(admin));
		return null;
	}
	

	public String checkstudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminService as = (AdminService) BeanFactory.getBean("AdminService");
		String sid = request.getParameter("sid");
		List<CheckDor>  list = as.checkstudent(sid);
		request.setAttribute("list", list);
		return "/jsp/admin/student/checkdorlist.jsp";
		
	}
}
