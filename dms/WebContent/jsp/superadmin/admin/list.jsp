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
</head>
<SCRIPT language=JavaScript>
function sousuo(){
	window.open("gaojisousuo.htm","","depended=0,alwaysRaised=1,width=800,height=510,location=0,menubar=0,resizable=0,scrollbars=0,status=0,toolbar=0");
}
function selectAll(){
	var obj = document.fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "delid"){
			obj[i].checked = true;
		}
	}
}

function unselectAll(){
	var obj = document.fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "delid"){
			if (obj[i].checked==true) obj[i].checked = false;
			else obj[i].checked = true;
		}
	}
}

function link(){
    document.getElementById("fom").action="xiangmu.htm";
   document.getElementById("fom").submit();
}

function on_load(){
	var loadingmsg=document.getElementById("loadingmsg");
	var mainpage=document.getElementById("mainpage");
	loadingmsg.style.display="";
	mainpage.style.display="none";
	
	loadingmsg.style.display="none";
	mainpage.style.display="";
}
</SCRIPT>

<body onload="on_load()">

<table id="mainpage" width="100%" border="0" cellspacing="0" cellpadding="0">

  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="62" background="${pageContext.request.contextPath}/jsp/images/nav04.gif"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
          
            <td width="24"><img src="${pageContext.request.contextPath}/jsp/images/ico07.gif" width="20" height="18" /></td>
            <form name="fom" id="fom" method="post" action="${pageContext.request.contextPath}/super?method=findAdminByUserName">
            <td width="519"><label>工号:
                <input name="username" type="text" nam="gongs" />
              </label>
                </input>
                <input name="Submit3" type="button" class="right-button02" value="查询" /></td>
                </form>
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
                    <td width="13%" height="20" align="center" bgcolor="#EEEEEE">寝室楼栋号</td>
                    <td width="10%" align="center" bgcolor="#EEEEEE">所住学生性别</td>
                     <td width="10%" align="center" bgcolor="#EEEEEE">楼栋可住人数</td>
                      <td width="10%" align="center" bgcolor="#EEEEEE">已住人数</td>
                    <td width="10%" align="center" bgcolor="#EEEEEE">管理员人数</td>
                    <td width="7%" align="center" bgcolor="#EEEEEE">操作</td>
                   
                    
                  </tr>
                  
                    
                  <c:forEach items="${pg.list}" var="b" varStatus="vs">
                  <tr>
				    <td width="4%" align="center" bgcolor="#EEEEEE">${vs.count }</td>
                    <td width="13%" height="20" align="center" bgcolor="#EEEEEE">${b.number }</td>
                    <td width="10%" align="center" bgcolor="#EEEEEE">${b.studentsex }</td>
                    <td width="10%" align="center" bgcolor="#EEEEEE">${b.studentnumber}</td>
                     <td width="10%" align="center" bgcolor="#EEEEEE">${b.nowstudentnumber }</td>
                      
                    <td width="13%" height="20" align="center" bgcolor="#EEEEEE">${b.adminnumber }</td>
              
                    <td width="8%" align="center" bgcolor="#EEEEEE"><a href="${pageContext.request.contextPath}/super?method=setBid&bid=${b.bid}&aid=${aid}">选择</a></td>
                    
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
                <td width="49%" align="right">[<a href="${pageContext.request.contextPath}/super?method=findAllEmployerByPg&currPage=1" class="right-font08">首页</a> | <c:if test="${pg.currPage!=1 }"><a href="#" class="${pageContext.request.contextPath}/super?method=findAllEmployerByPg&currPage=${pg.currPage-1}">上一页</a></c:if> | <c:if test="${pg.currPage<pg.totalPage }"><a href="${pageContext.request.contextPath}/super?method=findAllEmployerByPg&currPage=${pg.currPage+1}" class="right-font08">下一页</a></c:if> | <a href="${pageContext.request.contextPath}/super?method=findAllEmployerByPg&currPage=${pg.totalPage}" class="right-font08">末页</a>] 转至：</td>
                <td width="1%"><table width="20" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="1%"><input name="currPage" type="text" class="right-textfield03" size="1" /></td>
                      <td width="87%"><input name="Submit23222" type="submit" class="right-button06" value=" " />
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
