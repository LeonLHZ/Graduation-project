<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<head>
<link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath}/css/style0.css" type="text/css" media="all" />
<meta charset="UTF-8">
<title>Insert title here</title>
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
<script type="text/javascript">
$(function(){
	$.post("${pageContext.request.contextPath}/admin?method=findAllAnnouncement",function(data){
		$(data).each(function(){
			$("#divhtm").append(
					"<div><div>"+this.content+"</div>"+
					"<div>发布时间：<span>"+this.releasetime+"</span>发布人：<span>"+this.admin.name+"</span><a href='${pageContext.request.contextPath}/admin?method=delete&aid="+this.annid+"'>删除</a></div></div>"
			);
		});
	},"json");
});

</script>
</html>