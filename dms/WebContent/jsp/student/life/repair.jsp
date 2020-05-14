<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>障碍保修</title>
<link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath}/css/style0.css" type="text/css" media="all" />



<style type="text/css">
<!--
.atten {font-size:12px;font-weight:normal;color:#F00;}
-->
</style>
</head>

<body class="ContentBody">
  <form action="${pageContext.request.contextPath}/student?method=repair" method="post"  name="form"  >
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >障碍报修界面</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
		
		
			<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>障碍报修</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					 
					  
					    
					    
					 
					  <tr>
					    <td align="right">请选择报修类别:</td>
					    <td>
							<select name="name" >
							  <option  selected="selected">==请选择==</option>
							  <option value="空调">空调</option>
						<option value="日光">日光灯</option>
						<option value="水龙头">水龙头</option>
						<option value="厕所">厕所</option>
						<option value="床">床</option>
						<option value="洗漱池">洗漱池</option>
						<option value="板凳">板凳</option>
						<option value="供电">供电</option>
							</select>
						</td>
					   
					  </tr>
					 
					  
					    <td align="right">问题描述:</td>
					    <td colspan="3"><textarea name="reason" cols="100" rows="8"></textarea></td>
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
			<input type=submit name="Submit" value="提交" class="button" />
			
			</TD>
		</TR>
		</TABLE>
	
	
	 </td>
  </tr>
  
  
  
  </table>

</div>
</form>
</body>
</html>
