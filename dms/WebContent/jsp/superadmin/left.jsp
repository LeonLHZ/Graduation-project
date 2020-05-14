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
		d.add('0102','01','宿舍楼管理','','','mainFrame');
		d.add('010201','0102','新增寝室楼','${pageContext.request.contextPath}/build?method=addUI','','mainFrame');
		d.add('010202','0102','查看宿舍楼','${pageContext.request.contextPath}/build?method=findAllBuilding','','mainFrame');
		d.add('0104','01','宿舍管理');
		d.add('010401','0104','新增宿舍','${pageContext.request.contextPath}/dormitory?method=addUI','','mainFrame');
		
		d.add('0105','01','员工管理');
		d.add('010501','0105','新增员工','${pageContext.request.contextPath}/super?method=addUI','','mainFrame');
		d.add('010502','0105','查看维修员工','${pageContext.request.contextPath}/super?method=findAllEmployer','','mainFrame');
		d.add('010503','0105','查看宿舍管理员','${pageContext.request.contextPath}/super?method=findAllAdmin','','mainFrame');
		d.add('010504','0105','发布公告','${pageContext.request.contextPath}/super?method=announcement','','mainFrame');
		d.add('010505','0105','公告管理','${pageContext.request.contextPath}/super?method=findAllAnnouncementUI','','mainFrame');
		d.add('0106','01','个人信息管理');
		d.add('010601','0106','查看修改个人信息','${pageContext.request.contextPath}/super?method=updateUI','','mainFrame');
		d.add('010602','0106','更改个人密码','${pageContext.request.contextPath}/super?method=updatePasswordUI','','mainFrame');
		document.write(d);
		
	</script>
</div>	</td>
  </tr>
</table>
</body>
</html>
