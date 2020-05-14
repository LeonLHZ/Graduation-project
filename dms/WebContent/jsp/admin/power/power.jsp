<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ÏîÄ¿¹ÜÀíÏµÍ³ by www.nongfuit.com</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.tabfont01 {	
	font-family: "ËÎÌå";
	font-size: 9px;
	color: #555555;
	text-decoration: none;
	text-align: center;
}
.font051 {font-family: "ËÎÌå";
	font-size: 12px;
	color: #333333;
	text-decoration: none;
	line-height: 20px;
}
.font201 {font-family: "ËÎÌå";
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}
.button {
	font-family: "ËÎÌå";
	font-size: 14px;
	height: 37px;
}
html { overflow-x: auto; overflow-y: auto; border:0;} 
-->
</style>

<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style0.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/xiangmu.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
</head>

<body onload="on_load()">

<table id="mainpage" width="100%" border="0" cellspacing="0" cellpadding="0">

  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="62" background="${pageContext.request.contextPath}/jsp/images/nav04.gif"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
          
            <td width="24">
          </td>
            
            <td width="519"><label>
              目前状态：<c:if test="${building.selectdormitory==0&&building.changedormitory==0}">未开启任何功能</c:if>
            <c:if test="${building.selectdormitory==1}">选寝开启</c:if>
            <c:if test="${building.changedormitory==1}">换寝开启</c:if> <button id="openChoice">开启选寝功能</button> &nbsp;&nbsp;&nbsp;<button id="openChange">开启换寝功能</button>
              </label>
         楼栋：${building.number}栋&nbsp;&nbsp;&nbsp;可住：${building.studentnumber}人&nbsp;&nbsp;&nbsp;现住：${building.nowstudentnumber}人 
                   
            <td width="679" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table id="subtree1" style="DISPLAY: " width="100%" border="0" cellspacing="0" cellpadding="0">

        <tr>
          <td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">

          	 
              <tr>
                <td height="40" class="font42"><table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646" class="newfont03">

					                  <tr>
                    <td height="20" colspan="8" align="center" bgcolor="#EEEEEE"class="tablestyle_title"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 未选寝学生信息表 &nbsp;</td>
                    </tr>
                   <tr>
				     <td width="4%" align="center" bgcolor="#EEEEEE">序号</td>
                    <td width="13%" height="20" align="center" bgcolor="#EEEEEE">学号</td>
                    <td width="10%" align="center" bgcolor="#EEEEEE">姓名</td>
                    <td width="7%" align="center" bgcolor="#EEEEEE">性别</td>
                  
                    
                      <td width="8%" align="center" bgcolor="#EEEEEE">电话号码</td>
                      
                  </tr>
                  
                    
                  <c:forEach items="${list}" var="b" varStatus="vs">
                  <tr>
				    <td width="4%" align="center" bgcolor="#EEEEEE">${vs.count }</td>
                    <td width="13%" height="20" align="center" bgcolor="#EEEEEE">${b.username }</td>
                    <td width="10%" align="center" bgcolor="#EEEEEE">${b.name }</td>
                    <td width="7%" align="center" bgcolor="#EEEEEE">${b.sex }</td>
                    
                    
                    </td>
                    
                   
                  <td width="8%" align="center" bgcolor="#EEEEEE">${b.telephone}
                  </tr>
                 
                 </c:forEach>
                  
                 
                  
                
				    
                </table></td>
              </tr>
            </table></td>
        </tr>
        
      </table>
      
      <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="6"><img src="${pageContext.request.contextPath}/jsp/images/spacer.gif" width="1" height="1" /></td>
        </tr>
        <tr>
          <td height="33"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="right-font08">
              
          </table></td>
        </tr>
      </table></td>
  </tr>
</table>




</body>
<script type="text/javascript">
$("#openChoice").click(function(){
	window.location="${pageContext.request.contextPath}/admin?method=changeChoice&code=1";
});

$("#openChange").click(function(){
	window.location="${pageContext.request.contextPath}/admin?method=changeChoice&code=3";
});


</script>
</html>
