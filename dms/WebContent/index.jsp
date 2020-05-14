<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>学生宿舍管理系統</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<link href="css/login.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />

<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>

<script type="text/javascript" src="js/jquery.SuperSlide.js"></script>
<script type="text/javascript" src="js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var $tab_li = $('#tab ul li');
	$tab_li.hover(function(){
		$(this).addClass('selected').siblings().removeClass('selected');
		var index = $tab_li.index(this);
		$('div.tab_box > div').eq(index).show().siblings().hide();
	});	
});
</script>


</head>

<body onload="init()">
<div id="tab">

  <div class="tab_box"> 
    <!-- 学生登录开始 -->
    <div>
      <div class="stu_error_box">${msg}</div>
      <form action="${pageContext.request.contextPath }/login?method=login" method="post" class="stu_login_error" id="form">
        <div id="username">
          <label>账&nbsp;&nbsp;&nbsp;号：</label>
          
          <input type="text" id="stu_username_hide" name="username" placeholder="请输入账号" nullmsg="学号不能为空！" datatype="s6-18"  sucmsg="学号验证通过！"/>
          <!--ajaxurl="demo/valid.jsp"--> 
        </div>
        <div id="password">
          <label>密&nbsp;&nbsp;&nbsp;码：</label>
          <input type="password" id="stu_password_hide" name="password" placeholder="请输入密码" nullmsg="密码不能为空！" datatype="*6-16"  sucmsg="密码验证通过！"/>
        </div>
        <div id="code">
          <label>验证码：</label>
          <input type="text" id="stu_code_hide" name="code"  placeholder="输入验证码" nullmsg="验证码不能为空！" datatype="*4-4" errormsg="验证码有4位数！" sucmsg="验证码验证通过！"/>
          <img src="${pageContext.request.contextPath }/login?method=getImg" onclick="changeImg(this)" title="看不清楚，换一张" alt="验证码" id="img"/> <span id="divdiv"></span></div>
          
        
        <div id="login">
          <button type="submit" id="sub">登录</button>
        </div>
      </form>
    </div>
   <!-- 学生登录结束-->
   
  </div>
</div>
<div class="bottom"><a href="http://www.miitbeian.gov.cn/" target="_blank" title="模板之家">鄂ICP备19002374号</a></div>
<div class="screenbg">
  <ul>
    <li><a href="javascript:;"><img src="images/0.jpg"></a></li>
    <li><a href="javascript:;"><img src="images/1.jpg"></a></li>
    <li><a href="javascript:;"><img src="images/2.jpg"></a></li>
  </ul>
</div>
</body>
<script>

var i = 60;
function init(){
	
	setInterval(show,1000);
}

function show(){
	i--;
	if(i==0){
		i=60;
		img();
	}
	$("#divdiv").html(i);
	
}

function img(){
	$("#img").attr("src","${pageContext.request.contextPath }/login?method=getImg&i="+Math.random());
}




function changeImg(obj){
	i=60;
	obj.src="${pageContext.request.contextPath }/login?method=getImg&i="+Math.random();
}




	

</script>
</html>
