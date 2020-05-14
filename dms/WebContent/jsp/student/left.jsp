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
		d.add('0102','01','个人信息管理','','','mainFrame');
		d.add('010201','0102','查看修改个人信息','${pageContext.request.contextPath}/student?method=updateUI','','mainFrame');
		d.add('010202','0102','修改密码','${pageContext.request.contextPath}/student?method=passwordUI','','mainFrame');
		d.add('0104','01','寝室管理');
		d.add('010401','0104','选择寝室','${pageContext.request.contextPath}/student?method=selectDormitory&bid=${user.student.bid}','','mainFrame');
		d.add('010402','0104','申请更换寝室','${pageContext.request.contextPath}/dormitory?method=findAllDormitory&bid=${user.student.bid}&code=3','','mainFrame');
		d.add('0105','01','生活管理');
		d.add('010502','0105','障碍报修','${pageContext.request.contextPath}/student?method=repairUI','','mainFrame');
		d.add('0106','01','请求管理');
		d.add('010601','0106','查看请求处理进度','${pageContext.request.contextPath}/requestbean?method=findRequestBySid','','mainFrame');
		d.add('010602','0106','查看报修处理进度','${pageContext.request.contextPath}/student?method=findrepairByDorid','','mainFrame');
	
		
		document.write(d);
		
	</script>
</div>	</td>
  </tr>
</table>
</body>
</html>
