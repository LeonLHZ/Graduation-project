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
  <form action="${pageContext.request.contextPath}/admin?method=addstudent" method="post"  name="form"  >
  <input name="bid" type="hidden" value="${user.admin.bid}">
  <input type="hidden" name="type" value="student">
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >添加学生</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
		
			<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>学生信息</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					 
					   <tr>
					    <td nowrap="nowrap" align="right">学号:</td>
					    <td><input class="text" name="username" style="width:154px" /></td>
					    
					  </tr>
					    
					    
					  <tr>
					    <td nowrap="nowrap" align="right">姓名:</td>
					    <td><input class="text" name="name" style="width:154px" /></td>
					    
					  </tr>
					  <tr>
					   <td nowrap align="right">年级:</td>
					 <td><select name="level" >
                          <option  selected="selected">==请选择==</option>
                          <option value="1">大一</option>
                          <option value="2">大二</option>
                          <option value="3">大三</option>
                          <option value="4">大四</option>
                          
                        </select></td>
					  <tr>
					  
					   <tr>
					   <td nowrap align="right">学制:</td>
					 <td><select name="year" >
                          <option  selected="selected">==请选择==</option>
                          <option value="1">一年</option>
                          <option value="2">二年</option>
                          <option value="3">三年</option>
                          <option value="4">四年</option>
                          
                        </select></td>
					  <tr>
					  
					  <td nowrap="nowrap" align="right">专业:</td>
					    <td><input class="text" name="major" style="width:154px" /></td>
					  </tr>
					  <td nowrap="nowrap" align="right">学院:</td>
					    <td><input class="text" name="college" style="width:154px" /></td>
					  </tr>
					   <td nowrap="nowrap" align="right">电话:</td>
					    <td><input class="text" name="telephone" style="width:154px" /></td>
					  </tr>
					  <td nowrap="nowrap" align="right">辅导员电话:</td>
					    <td><input class="text" name="teacherphone" style="width:154px" /></td>
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

<form action="${pageContext.request.contextPath}/admin?method=addStudentByExcel" method="post"  name="form"  enctype="multipart/form-data">
  
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >批量导入学生</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
		
			<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>导入文件</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					 
					  
					    
					    
					 <tr>
					   
					  
					    <td nowrap align="right">批量导入:</td>
						<td align="center"><input name="excel" type="file" class="button" id="xxx" size="20" /></td>	        	
					    <td align="center">&nbsp;</td>
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
