<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>申请更换寝室</title>
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
				     <td width="4%" align="center" bgcolor="#EEEEEE">序号</td>
                    <td width="13%" height="20" align="center" bgcolor="#EEEEEE">寝室号</td>
                
                    <td width="10%" align="center" bgcolor="#EEEEEE">可住人数</td>
                    <td width="10%" align="center" bgcolor="#EEEEEE">现住人数</td>
                      <td width="10%" align="center" bgcolor="#EEEEEE">选择</td>
                   
                    
                   
                  </tr>
                  
                    
                  <c:forEach items="${pg.list}" var="b" varStatus="vs">
                  <tr>
				    <td width="4%" align="center" bgcolor="#EEEEEE">${vs.count }</td>
                    <td width="13%" height="20" align="center" bgcolor="#EEEEEE">${b.num }</td>
                    <td width="10%" align="center" bgcolor="#EEEEEE">${b.number }</td>
                    <td width="10%" align="center" bgcolor="#EEEEEE">${b.nownumber }</td>
                     <td width="10%" align="center" bgcolor="#EEEEEE"><a href="${pageContext.request.contextPath}/dormitory?method=lookposition&&dorid=${b.dorid}&code=2" >选择</a></td>
             
              
                    
                    
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
              <tr>
                <td width="50%">共<span class="right-text09">${pg.totalPage}</span> 页 | 第 <span class="right-text09">${pg.currPage}</span> 页</td>
                <td width="49%" align="right">[<a href="${pageContext.request.contextPath}/super?method=findAllEmployerByPg&currPage=1" class="right-font08">首页</a> | <c:if test="${pg.currPage!=1 }"><a href="#" class="${pageContext.request.contextPath}/super?method=findAllEmployerByPg&currPage=${pg.currPage-1}">上一页</a></c:if> | <c:if test="${pg.currPage<pg.totalPage }"><a href="${pageContext.request.contextPath}/super?method=findAllEmployerByPg&currPage=${pg.currPage+1}" class="right-font08">下一页</a></c:if> | <a href="${pageContext.request.contextPath}/super?method=findAllEmployerByPg&currPage=${pg.totalPage}" class="right-font08">末页</a>]
                     
                      </td>
                    </tr>
                </table></td>
              </tr>
          </table></td>
        </tr>
      </table></td>
  </tr>
</table>
</form>



</body>
</html>
