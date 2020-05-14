<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>位置信息</title>
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
</head>


<body onload="on_load()">

<table id="mainpage" width="100%" border="0" cellspacing="0" cellpadding="0">

  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="62" background="${pageContext.request.contextPath}/jsp/images/nav04.gif"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
          
           
            
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
                    <td height="20" colspan="8" align="center" bgcolor="#EEEEEE"class="tablestyle_title"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 宿舍管理员信息表 &nbsp;</td>
                    </tr>
                   <tr>
				     <td width="4%" align="center" bgcolor="#EEEEEE">位置</td>
				     <td width="13%" height="20" align="center" bgcolor="#EEEEEE">姓名</td>
				      <td width="13%" height="20" align="center" bgcolor="#EEEEEE">学号</td>
                    <td width="13%" height="20" align="center" bgcolor="#EEEEEE">状态</td>
                   
                    <td width="7%" align="center" bgcolor="#EEEEEE">操作</td>
                   
                    
                  </tr>
                  
                    
                  <c:forEach items="${dormitory.list}" var="b" varStatus="vs">
                  <tr>
                  <form name="fom" id="fom" method="post" action="${pageContext.request.contextPath}/admin?method=checkDor">
                  <input type="hidden" name="dorid" value="${b.dorid }"/>
                   <input type="hidden" name="sid" value="${b.sid }"/>
				    <td width="4%" align="center" bgcolor="#EEEEEE">${b.numbering }</td>
				    <td width="4%" align="center" bgcolor="#EEEEEE"> <c:if test="${b.student.name==null}">无人</c:if>${b.student.name}</td>
				    <td width="4%" align="center" bgcolor="#EEEEEE"> <c:if test="${b.student.username==null}">无人</c:if>${b.student.username}</td>
                    <td width="13%" height="20" align="center" bgcolor="#EEEEEE">
                 <select name="description" >
						
						<option >--请选择--</option>
						<option value="-2">抽烟</option>
						<option value="-1">位置脏乱</option>
						<option value="-5">使用大功率电器</option>
						<option value="-3">养宠物</option>
						
						</select>
                    
                    </td>
                    
              
                    <td width="8%" align="center" bgcolor="#EEEEEE"><input type="submit"></td>
                   </form> 
                  </tr>
                 </c:forEach>
                  
                 
                  
                
				    
                </table></td>
              </tr>
            </table></td>
        </tr>
        
      </table>
      
     </td>
        </tr>
      </table></td>
  </tr>
</table>
</form>

<br>
 <div style="text-align: center; ">
 <img src="/pic/${dormitory.img}" width=500 height=500 align="top"/>
</div>

</body>
</html>
