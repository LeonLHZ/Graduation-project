<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增宿舍</title>
<link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath}/css/style0.css" type="text/css" media="all" />



<style type="text/css">
<!--
.atten {font-size:12px;font-weight:normal;color:#F00;}
-->
</style>
</head>

<body class="ContentBody">
  <form action="${pageContext.request.contextPath}/dormitory?method=add" method="post"  name="fom" id="fom" enctype="multipart/form-data"  >
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >添加宿舍界面</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
		

		<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>添加宿舍</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					 
					  <tr>
					    <td nowrap align="right" width="13%">宿舍号:</td>
					    <td width="41%"><input name="num" class="text" style="width:250px" type="text" size="40" />
				        <span class="red"> *（三位数，第一位代表楼层，后两位代表号码）</span></td>
					    <td nowrap align="right">选择分布图:</td>
						<td align="center"><input name="img" type="file" class="button" id="xxx" size="20" /></td>	        	
					    <td align="center">&nbsp;</td>
					    </tr>
					  <tr>
					    <td align="right" width="19%">可住人数:</td>
					    <td width="27%"><input name="number" id="Input22" class="text" style="width:154px" /></td>
					    
					  </tr>
					   <tr>
					    <td nowrap align="right">选择所属楼栋:</td>
					    <td><select name="bid" >
                          <option>--请选择--</option>
						<c:forEach items="${blist}" var="b">
						<option value="${b.bid}">${b.number}</option>
						</c:forEach>
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
