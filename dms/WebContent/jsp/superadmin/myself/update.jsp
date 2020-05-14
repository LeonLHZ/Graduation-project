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
  <form action="${pageContext.request.contextPath}/super?method=update" method="post"  name="form"  >
  <input type="hidden" name="said" value="${user.uid }">
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
					    <td nowrap="nowrap" align="right">姓名:</td>
					    <td><input class="text" name="name" style="width:154px" value="${user.superAdmin.name }"/></td>
					    
					  </tr>
					  <tr>
					   <td nowrap="nowrap" align="right">年龄:</td>
					    <td><input class="text" name="old" style="width:154px" value="${user.superAdmin.old }"/></td>
					  </tr>
					  <tr>
					   <td nowrap align="right">性别:</td>
					    <td><select name="sex" >
                          <option  selected="selected">==请选择==</option>
                          <option value="男">男</option>
                          <option value="女">女</option>
                          
                        </select></td>
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
