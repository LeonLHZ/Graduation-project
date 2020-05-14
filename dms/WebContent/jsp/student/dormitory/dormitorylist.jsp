<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
	
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
							<strong>宿舍列表</strong>
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
								<c:forEach var="d" items="${ dlist }" varStatus="vs">
								<div id="select" >
										<tr  
											 background="yellow">
											<td  align="center"
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
											<a href="${pageContext.request.contextPath }/dormitory?method=lookposition&dorid=${d.dorid}&code=1">查看床位</a>
											
											</td>
										</tr>
										</div>
									</c:forEach>
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
			<a id="qeqw">导出表格</a>
	</body>
	<script type="text/javascript">
	// 使用outerHTML属性获取整个table元素的HTML代码（包括<table>标签），然后包装成一个完整的HTML文档，设置charset为urf-8以防止中文乱码
    var html = "<html><head><meta charset='utf-8' /></head><body>" + document.getElementsByTagName("table")[1].outerHTML + "</body></html>";
    // 实例化一个Blob对象，其构造函数的第一个参数是包含文件内容的数组，第二个参数是包含文件类型属性的对象
    var blob = new Blob([html], { type: "application/vnd.ms-excel" });
    var a = document.getElementById("qeqw");
    // 利用URL.createObjectURL()方法为a元素生成blob URL
    a.href = URL.createObjectURL(blob);
    // 设置文件名
    a.download = "学生成绩表.xls";
		
	</script>
	
</HTML>

