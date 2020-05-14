<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript">
			function addCategory(){
				window.location.href = "${pageContext.request.contextPath}/adminCategory?method=addUI";
			}
		</script>
	</HEAD>
	<body>
		<br>
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>分类列表</strong>
						</TD>
					</tr>
					<tr>
						<td class="ta_01" align="right">
							

						</td>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="18%">
										序号
									</td>
									<td align="center" width="17%">
										寝室号
									</td>
									<td width="7%" align="center">
										可住人数
									</td>
									<td width="7%" align="center">
										现住人数
									</td>
									<td width="7%" align="center">
										操作
									</td>
								</tr>
								<%-- <c:forEach var="d" items="${ dlist }" varStatus="vs">
								<div id="select">
										<tr  onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="18%">
												${vs.count }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												${d.num }
											</td>
											<td align="center" style="HEIGHT: 22px">
												${d.number }
											</td>
									
											<td align="center" style="HEIGHT: 22px">
											
											${d.nownumber}
											</td>
											<td align="center" style="HEIGHT: 22px">
											<a href="${pageContext.request.contextPath}/student?method=checkdor&sid=${student.sid}&dorid=${d.dorid}" >选择</a>
											</td>
										</tr>
										</div>
									</c:forEach>--%>
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
	</body>
	<script type="text/javascript">
/* 	$("a").each(function(){
		
		$(this).click(function(){
			
		
			var params = {"sid" : "${student.sid}"};
			$.post("${pageContext.request.contextPath}/student?method=checkdor",params,function(data){
				
				alert(data);
			});
		});
	});
 */	
		 /* $("#requestbean").click(function(){
			
		
			var params = {"sid" : "${student.sid}"};
			$.post("${pageContext.request.contextPath}/student?method=checkdor",params,function(data){
				
				alert(data);
			});
		});    */
		
$(function(){
	
	$.get("${pageContext.request.contextPath}/student?method=change",function(data){
		var i=1;
		$(data).each(function(){
		
		
			var sid = "${user.uid}";
			$("#DataGrid1").append(
					"<tr  onmouseover='this.style.backgroundColor = 'white''"+
						"onmouseout='this.style.backgroundColor = '#F5FAFE';'>"+
						"<td style='CURSOR: hand; HEIGHT: 22px' align='center'"+
							"width='18%'>"+
							i+
						"</td>"+
						"<td style='CURSOR: hand; HEIGHT: 22px' align='center'"+
							"width='17%''>"+
							this.num+
						"</td>"+
						"<td align='center' style='HEIGHT: 22px'>"+
							this.number+
						"</td>"+
				
						"<td align='center' style='HEIGHT: 22px'>"+
						
						this.nownumber+
						"</td>"+
						"<td align='center' style='HEIGHT: 22px'>"+
						"<a href='${pageContext.request.contextPath}/student?method=checkdor&sid="+sid+"&dorid="+this.dorid+"' >选择</a>"+
						"</td>"+
					"</tr>"
					);
		i++
		}); 
	},"json");
});
		
		
		
		
	</script>
	
</HTML>

