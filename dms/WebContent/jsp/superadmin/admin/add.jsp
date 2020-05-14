<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增员工</title>
<link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath}/css/style0.css" type="text/css" media="all" />



<style type="text/css">
<!--
.atten {font-size:12px;font-weight:normal;color:#F00;}
-->
</style>
</head>

<body class="ContentBody">
  <form action="${pageContext.request.contextPath}/super?method=add" method="post"  name="form"  >
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >新增员工界面</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
		
			<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>新增员工</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					 
					  <tr>
					   <td align="right">员工类型:</td>
					    <td>
							<select name="type" >
							  <option >==请选择==</option>
							  <option value="admin" >寝室管理员</option>
							  <option value="employer" >维修员工</option>
							  
							</select>
						</td>
					    
					  </tr>
					    
					    
					  <tr>
					    <td nowrap="nowrap" align="right">工号:</td>
					    <td><input class="text" name="username" style="width:154px" /></td>
					    
					  </tr>
					  <tr>
					   <td nowrap="nowrap" align="right">姓名:</td>
					    <td><input class="text" name="name" style="width:154px" /></td>
					  </tr>
					  <tr>
					    <td align="right">性别:</td>
						<td>
							<select name="sex" >
								<option >==请选择==</option>
								<option value="男">男</option>
								<option  value="女">女</option>
								
							</select>
						</td>
					   
					  </tr>
					  <tr>
					  <td nowrap="nowrap" align="right">电话:</td>
					    <td><input class="text" name="telephone" style="width:154px" /></td>
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


<form action="${pageContext.request.contextPath}/super?method=addByExcel" method="post"  name="form"  enctype="multipart/form-data">
  
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >批量导员工</th>
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
