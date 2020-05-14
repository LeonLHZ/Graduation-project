<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>菜单</title>
<link href="${pageContext.request.contextPath}/css/left.css" rel="stylesheet" type="text/css"/>
<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" />
</head>
<body>
<table width="100" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="12"></td>
  </tr>
</table>
<table width="100%" border="0">
  <tr>
    <td>
<div class="dtree">

	<a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">关闭所有</a>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
	<script type="text/javascript">
	
		d = new dTree('d');
		d.add('01',-1,'系统菜单树');
		d.add('0102','01','学生管理','','','mainFrame');
		d.add('010201','0102','录入学生','${pageContext.request.contextPath}/admin?method=addUI','','mainFrame');
		d.add('010202','0102','查看学生信息','${pageContext.request.contextPath}/admin?method=findAllStudent','','mainFrame');
		d.add('010203','0102','查寝','${pageContext.request.contextPath}/dormitory?method=findAllDormitory&bid=${user.admin.bid}&code=1','','mainFrame');
		d.add('0104','01','请求管理');
		d.add('010401','0104','换寝请求处理中心','${pageContext.request.contextPath}/admin?method=findAllRequest','','mainFrame');
		d.add('010402','0104','报修请求处理中心','${pageContext.request.contextPath}/admin?method=repairRequest','','mainFrame');
		d.add('0105','01','权限管理');
		d.add('010501','0105','权限','${pageContext.request.contextPath}/admin?method=power','','mainFrame');
		d.add('010502','0105','发布公告','${pageContext.request.contextPath}/admin?method=announcement','','mainFrame');
		d.add('010503','0105','公告管理','${pageContext.request.contextPath}/admin?method=findAllAnnouncementUI','','mainFrame');
		d.add('010504','0105','到期学生处理','${pageContext.request.contextPath}/admin?method=findtimeoutStudent','','mainFrame');
		d.add('0106','01','个人信息信息管理');
		d.add('010601','0106','查看修改个人信息','${pageContext.request.contextPath}/admin?method=updateUI','','mainFrame');
		d.add('010602','0106','修改密码','${pageContext.request.contextPath}/admin?method=passwordUI','','mainFrame');
		
		document.write(d);
		
	</script>
</div>	</td>
  </tr>
</table>
</body>
</html>
