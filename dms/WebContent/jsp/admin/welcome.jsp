<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath}/css/style0.css" type="text/css" media="all" />
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<link href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet" />
<style type="text/css">
<!--
body {
	background-color: #FFFFFF;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
body,td,th {
	color: #000000;
}
-->
    </style>
<style>
BODY {SCROLLBAR-FACE-COLOR: #cccccc; SCROLLBAR-HIGHLIGHT-COLOR: #ffffFF; SCROLLBAR-SHADOW-COLOR: #ffffff; SCROLLBAR-3DLIGHT-COLOR: #cccccc; SCROLLBAR-ARROW-COLOR:  #ffffff; SCROLLBAR-TRACK-COLOR: #ffffFF; SCROLLBAR-DARKSHADOW-COLOR: #cccccc; }
</style>
</head>

<body>


<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >公告栏</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%"  id="anntable">
		
			<TR>
			
				<fieldset style="height:100%;">
				<legend>公告</legend>
					 <div id="divhtm">
					  </div>
				</fieldset>			
				
		</TR>

		</TABLE>
	
	
	 </td>
  </tr>
  
  
		
		
		
		<TR>
			<TD colspan="2" align="center" height="50px">
			
			
			
		</TR>
		</TABLE>
	
	
	 </td>
  </tr>
  
  
  
  </table>

</div>

</body>
<script>
$(function(){
	
	$.post("${pageContext.request.contextPath}/super?method=findAllAnnouncement",function(data){
		$(data).each(function(){
			$("#divhtm").append(
					"<div><div>"+this.content+"</div>"+
					"<div>发布时间：<span>"+this.releasetime+"</span>发布人：<span>"+this.superadmin.name+"</span></div><HR></div>"
			);
		});
	},"json");
});

</script>
</html>