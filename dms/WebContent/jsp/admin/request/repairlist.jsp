<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		
	</HEAD>
	<body>
		<br>
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>商品列表</strong>
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

									<td align="center" width="1%">
										序号
									</td>
									<td align="center" width="10%">
										所在楼栋
									</td>
									
									<td align="center" width="10%">
										需要维修的寝室号
									</td>
									
									<td align="center" width="5%">
										损坏物品
									</td>
									<td align="center" width="5%">
										原因
									</td>
									<td align="center" width="5%">
										报修时间
									</td>
									
									<td width="7%" align="center">
										请求状态
									</td>
									
									<td width="5%" align="center">
										指派维修员工
									</td>
								</tr>
								<c:forEach items="${rlist }" var="r" varStatus="vs">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												${vs.count }
											</td>
											
											
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												${r.building.number }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												${r.dormitory.num }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												${r.name }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												${r.reason }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												${r.starttime }
											</td>
											
											
											<td align="center" style="HEIGHT: 22px" width="5%">
											<c:if test="${r.state==2 }"></c:if>
												等待处理
											</td>
									
											
											<td align="center" style="HEIGHT: 22px" width="10%">
												
												<a href="${pageContext.request.contextPath}/admin?method=getEmployer&rid=${r.rid}" >指派师傅</a>
											
											</td>
										</tr>
									</c:forEach>
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</body>

</HTML>

