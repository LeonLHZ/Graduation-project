<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script  src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
		
	</HEAD>
	<body>
		<br>
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/admin?method=deal" method="post">
		
		<input type="hidden" name="rid" value="${rb.rid }">
		
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>原因查看</strong>
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

									
									<td width="30%" align="center">
										具体原因
									</td>
									
									<td width="5%" align="center">
										处理
									</td>
								</tr>
								
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											
										
											
											
										
											
											
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												${rb.reason}
											</td>
											
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												<input type="submit" value="同意">
												<button  type="button" id="button">拒绝</button>
											</td>
											
											
											
											
											
										</tr>
									
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</body>
	<script type="text/javascript">
	$("button").click(function(){
		window.location.href="${pageContext.request.contextPath}/admin?method=refuse&rid=${rb.rid}";
	});
	
	</script>

</HTML>

