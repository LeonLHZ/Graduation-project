<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看修改个人信息</title>
<link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath}/css/style0.css" type="text/css" media="all" />



<style type="text/css">
<!--
.atten {font-size:12px;font-weight:normal;color:#F00;}
-->
</style>
</head>

<body class="ContentBody">
  <form action="${pageContext.request.contextPath}/student?method=update" method="post"  name="form"  >
  <input type="hidden" name="sid" value="${user.student.sid }">
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >个人信息界面</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
		
			<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>个人信息</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					 
					  <tr>
					    <td nowrap="nowrap" align="right">年级:</td>
					    <td><input class="text" name="level" style="width:154px" value="${user.student.level}"/></td>
					    
					  </tr>
					    <tr>
					    <td nowrap="nowrap" align="right">姓名:</td>
					    <td><input class="text" name="name" style="width:154px" value="${user.student.name }"/></td>
					    
					  </tr>
					    
					 
					  <tr>
					   <td nowrap="nowrap" align="right">年龄:</td>
					    <td><input class="text" name="old" style="width:154px" value="${user.student.old }"/></td>
					  </tr>
					   <tr>
					    <td nowrap="nowrap" align="right">专业:</td>
					    <td><input class="text" name="major" style="width:154px" value="${user.student.major }"/></td>
					    
					  </tr>
					   <tr>
					    <td nowrap="nowrap" align="right">学院:</td>
					    <td><input class="text" name="college" style="width:154px" value="${user.student.college }"/></td>
					    
					  </tr>
					   
					   
					  <tr>
					   <td nowrap="nowrap" align="right">电话:</td>
					    <td><input class="text" name="telephone" style="width:154px" value="${user.student.telephone }"/></td>
					  </tr>
					  <tr>
					    <td nowrap="nowrap" align="right">辅导员电话:</td>
					    <td><input class="text" name="teacherphone" style="width:154px" value="${user.student.teacherphone }"/></td>
					    
					  </tr>
					 
					 
					  
					  </table>
			  <br />
				</fieldset>			</TD>
		</TR>
		</TABLE>
	
	
	 </td>
  </tr>
  
  
		
		
		
		<TR>
			<TD colspan="2" align="center" height="50px">
			<input type="submit" name="Submit" value="提交" class="button" />
			
			
		</TR>
		</TABLE>
	
	
	 </td>
  </tr>
  
  
  
  </table>

</div>
</form>
</body>
</html>
