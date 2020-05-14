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
							<strong>换寝申请进度信息</strong>
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
										请求人学号及姓名
									</td>
									
									<td align="center" width="10%">
										被请求人学号及姓名
									</td>
									
									<td align="center" width="5%">
										寝室号
									</td>
									<td align="center" width="5%">
										现住人数
									</td>
									<td align="center" width="5%">
										可住人数
									</td>
									<td align="center" width="5%">
										发起请求时间
									</td>
									<td width="7%" align="center">
										请求状态
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
												${r.rStudent.username }${r.rStudent.name }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												<c:if test="${r.bStudent.username==null}">被请求对象是空床</c:if>${r.bStudent.username }${r.bStudent.name }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												${r.dormitory.num }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												${r.dormitory.nownumber }
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												${r.dormitory.number }
											</td>
											
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												${r.starttime }
											</td>
											<td align="center" style="HEIGHT: 22px" width="5%">
											<c:if test="${r.state==2 }">等待处理</c:if>
												<c:if test="${r.state==1 }">同意</c:if>
												<c:if test="${r.state==0 }">请求被拒</c:if>
												<c:if test="${r.state==4 }">等待被求人</c:if>
												<c:if test="${r.state==3}">被请求人已经同意</c:if>
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

