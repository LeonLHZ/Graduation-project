<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增宿舍</title>
<link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath}/css/style0.css" type="text/css" media="all" />


<script language="JavaScript" type="text/javascript">




</script>
<style type="text/css">
<!--
.atten {font-size:12px;font-weight:normal;color:#F00;}
-->
</style>
</head>

<body class="ContentBody">
  <form action="${pageContext.request.contextPath}/build?method=add" method="post"   >
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >新增宿舍楼界面</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
		<tr><td align="left">
		
		</td></tr>

		<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>新增宿舍楼</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					 
					  <tr>
					    <td nowrap align="right" width="13%">楼栋号：</td>
					    <td width="41%"><input name="number" class="text" style="width:250px" type="text" size="40" />
				      </td>
					   
					    </tr>
					    <tr>
					     <td align="right" width="19%">性别:</td>
					    <td width="27%"><input type="radio" name="studentsex"  value="男" class="text" />男
						<input type="radio" name="studentsex"  value="女"  class="text"/>女</td>
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
