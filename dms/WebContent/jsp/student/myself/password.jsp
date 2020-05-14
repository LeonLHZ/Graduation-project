<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>更改密码</title>
<link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath}/css/style0.css" type="text/css" media="all" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>

<style type="text/css">
<!--
.atten {font-size:12px;font-weight:normal;color:#F00;}
-->
</style>
</head>

<body class="ContentBody">
  <form action="${pageContext.request.contextPath}/student?method=updatePassword" method="post"  name="form" >
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >更改密码界面</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
		
			<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>更改密码</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					 
					 
					    
					    
					  <tr>
					    <td nowrap="nowrap" align="right">原密码:</td>
					    <td><input class="text" name="username" style="width:154px" id="userAction_save_do_logonName" /></td>
					    
					  </tr>
					  <tr>
					   <td nowrap="nowrap" align="right">新密码:</td>
					    <td><input class="text" name="name" style="width:154px" id="userActio"/></td>
					  </tr>
					  <tr>
					   <td nowrap="nowrap" align="right">确认密码:</td>
					    <td><input class="text" name="password" style="width:154px" id="userAction"/></td>
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
<script type="text/javascript">
	
		$("#userAction_save_do_logonName").blur(function(){
			var old = $("#userAction_save_do_logonName").val();
			var params = {"sid":"${user.uid }","oldpassword":old};
			$.post("${pageContext.request.contextPath}/student?method=checkPassword",params,function(data){
			
			if(data==1){
				alert("密码验证通过");
			}else{
				alert("密码错误");
			}
			});

		});
		
		$("#userAction").blur(function(){
			var x = $(this).val();
			var y = $("#userActio").val();
			if(x!=y){
				 alert("两次密码不一致");
			}
			
		});
	</script>
</html>
