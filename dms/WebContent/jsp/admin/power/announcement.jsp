<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath}/css/style0.css" type="text/css" media="all" />
<meta charset="utf-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/wangEditor.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/admin?method=release" method="post"  name="form" >
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >发布公告</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
		
			<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>编辑公告</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					 
					 <tr>
					    <div id="div1">
   

</div>
<textarea id="text1" style="width:100%; height:200px;" hidden name="html"></textarea>
					    
					 </tr>
					 
					  
					  </table>
			  <br />
				</fieldset>			</TD>
		</TR>
		</TABLE>
	
	
	 </td>
  </tr>
  
  
		
		
		
		<TR>
			<TD colspan="2" align="center" height="50px">
			<input type="submit" name="Submit" value="发布" class="button" />
			
			
		</TR>
		</TABLE>
	
	
	 </td>
  </tr>
  
  
  
  </table>

</div>
</form>

</body>

<script type="text/javascript">
    var E = window.wangEditor
    var editor = new E('#div1')
    editor.customConfig.uploadImgShowBase64 = true
    
   // editor.create()
   var $text1 = $('#text1')
    editor.customConfig.onchange = function (html) {
            // 监控变化，同步更新到 textarea
            $text1.val(html)
        }
    editor.customConfig.linkImgCallback = function (url) {
        console.log(url) // url 即插入图片的地址
    }
        editor.create()
        // 初始化 textarea 的值
        $text1.val(editor.txt.html())
        
 
</script>
</html>