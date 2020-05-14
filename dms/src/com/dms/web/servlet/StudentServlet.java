package com.dms.web.servlet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import com.dms.entity.Admin;
import com.dms.entity.Announcement;
import com.dms.entity.Building;
import com.dms.entity.Dormitory;
import com.dms.entity.Position;
import com.dms.entity.Repair;
import com.dms.entity.RequestBean;
import com.dms.entity.Student;
import com.dms.entity.User;
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
import com.dms.utils.MD5Utils;
import com.dms.utils.UUIDUtils;
import com.sun.glass.ui.Application;

import net.sf.ehcache.search.parser.MCriteria.Simple;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.processors.JsonBeanProcessor;

/**
 * Servlet implementation class StudentServlet
 */
public class StudentServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String selectDormitory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		String bid = request.getParameter("bid");
		BuildingService bs = (BuildingService) BeanFactory.getBean("BuildingService");
		Building building = bs.findBuildByBid(bid);
		if(building.getSelectdormitory()==0) {
			response.getWriter().print("<script>alert('抱歉该功能还未开启！');window.location='" + request.getContextPath()
			+ "/jsp/student/welcome.jsp';</script>");
			return null;
		}
		
		DormitoryService ds = (DormitoryService) BeanFactory.getBean("DormitoryService");
		List<Dormitory> list = ds.findByDid(bid);

		request.setAttribute("dlist", list);
		return "/jsp/student/dormitory/dormitorylist.jsp";

	}

	public String showBuilding(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		String sex = user.getStudent().getSex();
		BuildingService bs = (BuildingService) BeanFactory.getBean("BuildingService");
		List<Building> list = bs.findBuildingBySex(sex);

		return null;

	}

	public String updateUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/jsp/student/myself/update.jsp";

	}

	public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		Student student = new Student();
		BeanUtils.populate(student, request.getParameterMap());

		StudentService ss = (StudentService) BeanFactory.getBean("StudentService");
		ss.update(student);
		User user = (User) request.getSession().getAttribute("user");
		Student newStudent = ss.findStudentById(user.getUid());
		
		user.setStudent(newStudent);
		request.getSession().setAttribute("user", user);
		response.getWriter().print("<script>alert('更改成功！');window.location='" + request.getContextPath()
		+ "/jsp/student/myself/update.jsp';</script>");
		return null;
	}

	public String selectUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BuildingService bs = (BuildingService) BeanFactory.getBean("BuildingService");
		User user = (User) request.getSession().getAttribute("user");
		List<Building> list = bs.findBuildingBySex(user.getStudent().getSex());
		request.setAttribute("blist", list);
		return "/jsp/student/dormitory/list.jsp";
	}

	public String selectThis(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		String dorid = request.getParameter("dorid");
		String sid = request.getParameter("sid");
		String pid = request.getParameter("pid");
		StudentService ss = (StudentService) BeanFactory.getBean("StudentService");
		Student student = ss.findStudentById(sid);
		if (student.getDormitory()== null) {

			//Student studen = ss.findStudentByDoridsid(dorid, sid);

			//if (studen == null) {
			DormitoryService ds = (DormitoryService) BeanFactory.getBean("DormitoryService");
			Position position = ds.findPositionByPid(pid);
         if(position.getSid()==null||position.getSid()=="") {
				
				ds.selectThis(dorid, sid, pid);
				response.getWriter().print("<script>alert('选择成功，祝你生活愉快！');window.location='" + request.getContextPath()
						+ "/jsp/student/welcome.jsp';</script>");
				return null;
			//}
         }
         response.getWriter().print("<script>alert('该位置已经有人了请重新选择');window.location='" + request.getContextPath()
 		  + "/jsp/student/welcome.jsp';</script>");
         return null;
		}
		response.getWriter().print("<script>alert('您已经选择过寝室了');window.location='" + request.getContextPath()
		+ "/jsp/student/welcome.jsp';</script>");
		return null;

	}

	public String changeUI(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return "/jsp/student/dormitory/change.jsp";

	}

	public void checkdor(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		String pid = request.getParameter("pid");
		String dorid = request.getParameter("dorid");
		User user = ((User) request.getSession().getAttribute("user"));
		String uid = user.getUid();
		RequestBeanService rs = (RequestBeanService) BeanFactory.getBean("RequestBeanService");
		int i = rs.findRequestByUsername(uid);
		if (i >= 1) {
			response.getWriter()
					.print("<script>alert('您之前已经申请过了，请等待处理结果再申请');window.location='" + request.getContextPath()
							+ "/dormitory?method=findAllDormitory&bid=" + user.getStudent().getBid()
							+ "&code=3';</script>");
			return;
		}

		StudentService ss = (StudentService) BeanFactory.getBean("StudentService");
		Student student = ss.findStudentById(uid);

		if (student.getDormitory().getDorid() == null) {
			response.getWriter().print("<script>alert('请选择寝室后再更换寝室');window.location='" + request.getContextPath()
					+ "/dormitory?method=findAllDormitory&bid=\"+user.getStudent().getBid()+\"&code=3';</script>");
			// return changeUI(request, response);
			// System.out.println("2231");
			return;
		}
		if (student.getDormitory().getDorid().equals(dorid)) {
			response.getWriter().print("<script>alert('你已在这个寝室');window.location='" + request.getContextPath()
					+ "/dormitory?method=findAllDormitory&bid="+user.getStudent().getBid()+"&code=3';</script>");
			// return changeUI(request, response);
			// System.out.println("2231");
			return;
		}
		DormitoryService ds = (DormitoryService) BeanFactory.getBean("DormitoryService");

		Position position = ds.findPositionByPid(pid);

		request.setAttribute("position", position);
		request.getRequestDispatcher("/jsp/student/dormitory/reason.jsp").forward(request, response);

	}

	public String change(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=utf-8");
		User user = (User) request.getSession().getAttribute("user");

		DormitoryService ds = (DormitoryService) BeanFactory.getBean("DormitoryService");
		List<Dormitory> list = ds.findByDid(user.getStudent().getBuilding().getBid());
		response.getWriter().print(JSONArray.fromObject(list));
		// request.setAttribute("dlist", list);
		return null;

	}

	public void addRequestBean(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		String pid = request.getParameter("pid");

		DormitoryService ds = (DormitoryService) BeanFactory.getBean("DormitoryService");
		Position position = ds.findPositionByPid(pid);

		RequestBean requestBean = new RequestBean();
		BeanUtils.populate(requestBean, request.getParameterMap());
		requestBean.setRid(UUIDUtils.getId());
		User user = (User) request.getSession().getAttribute("user");
		requestBean.setBid(user.getStudent().getBuilding().getBid());
		requestBean.setRnum(user.getUid());
		requestBean.setStarttime(new Date());
		requestBean.setDorid(position.getDorid());
		requestBean.setPid(position.getPid());
		int status;
		String sid = position.getSid();

		if (sid == null || "".equals(sid)) {
			status = 2;

		} else {
			status = 4;
			requestBean.setBernum(sid);
		}
		requestBean.setState(status);
		

		RequestBeanService rs = (RequestBeanService) BeanFactory.getBean("RequestBeanService");
		rs.addRequestBean(requestBean, status);
		response.getWriter().print("<script>alert('申请成功');window.location='" + request.getContextPath()
		+ "/jsp/student/welcome.jsp';</script>");
	}

	public void checknum(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		String num = request.getParameter("num");

		String dorid = request.getParameter("dorid");

		StudentService ss = (StudentService) BeanFactory.getBean("StudentService");
		Student student = ss.findStudentByUsername(num, dorid);
		if (student == null) {
			response.getWriter().print(1);
		}

	}

	public String repairUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/jsp/student/life/repair.jsp";
	}

	public void repair(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		String reason = request.getParameter("reason");
		String name = request.getParameter("name");
		User user = (User) request.getSession().getAttribute("user");
		StudentService ss = (StudentService) BeanFactory.getBean("StudentService");
		Student studen = ss.findStudentById(user.getUid());
		if (studen.getDorid() == null) {
			response.getWriter().print("<script>alert('请选择寝室后才有资格报修');window.location='" + request.getContextPath()
					+ "/jsp/student/life/repair.jsp';</script>");
			return;
		}
		Repair repair = new Repair();

		repair.setBid(user.getStudent().getBid());
		repair.setRid(UUIDUtils.getId());
		repair.setDorid(studen.getDorid());
		repair.setName(name);
		repair.setReason(reason);
		repair.setState(2);

		repair.setStarttime(new Date());
		RepairService rs = (RepairService) BeanFactory.getBean("RepairService");
		rs.addRepair(repair);
		response.getWriter().print("<script>alert('您的报修申请已成功提交！');window.location='" + request.getContextPath()
				+ "/student?method=findrepairByDorid';</script>");
		return;

	}

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

	public String passwordUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/jsp/student/myself/password.jsp";
	}

	public String getDorList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		User user = (User) request.getSession().getAttribute("user");
		CheckDorService cd = (CheckDorService) BeanFactory.getBean("CheckDorService");
		List<Dormitory> list = cd.beautifulDor(user.getStudent().getBid());
		response.getWriter().print(JSONArray.fromObject(list));

		return null;
	}

	public String checkPassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		String old = request.getParameter("oldpassword");
		String sid = request.getParameter("sid");
		StudentService ss = (StudentService) BeanFactory.getBean("StudentService");
		Boolean flag = ss.checkPassword(sid, MD5Utils.md5(old));
		if (flag)
			response.getWriter().print(1);
		else
			response.getWriter().print(0);

		return null;
	}

	public String findrepairByDorid(HttpServletRequest request, HttpServletResponse response) throws Exception {

		User user = (User) request.getSession().getAttribute("user");
		RepairService rs = (RepairService) BeanFactory.getBean("RepairService");
		StudentService ss = (StudentService) BeanFactory.getBean("StudentService");
		Student student = ss.findStudentById(user.getUid());
		List<Repair> list = rs.findrepairByDorid(student.getDorid());
		request.setAttribute("list", list);
		return "/jsp/student/request/repairlist.jsp";

	}
	public String getRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		RequestBeanService rs = (RequestBeanService) BeanFactory.getBean("RequestBeanService");
		User user = (User) request.getSession().getAttribute("user");
		List<RequestBean> list=rs.getRequestBybeId(user.getUid());
		response.getWriter().print(JSONArray.fromObject(list));
		return null;

	}
	
	public String bsdeal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		String code = request.getParameter("code");
		String rid = request.getParameter("rid");
		User user = (User) request.getSession().getAttribute("user");
		RequestBeanService rs = (RequestBeanService) BeanFactory.getBean("RequestBeanService");
		rs.bsdeal(code,rid,user.getUid());
		response.getWriter().print("<script>alert('处理成功');window.location ='" + request.getContextPath()
		+ "/jsp/student/welcome.jsp';</script>");
		return null;
		
	}
	
	public String findMyDormitory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		DormitoryService ds = (DormitoryService) BeanFactory.getBean("DormitoryService");
		User user = (User) request.getSession().getAttribute("user");
		Student student = ds.findMyDormitory(user);
		response.getWriter().print(JSONObject.fromObject(student));
		return null;
	}
	
	
	public String findAllAnnouncement(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		AnnouncementService s = (AnnouncementService) BeanFactory.getBean("AnnouncementService");
		User user = (User) request.getSession().getAttribute("user");
		List<Announcement> list = s.findAllAnnouncement(user.getStudent().getBid());
		response.getWriter().print(JSONArray.fromObject(list));
		return null;
		
	}
	
	
}
