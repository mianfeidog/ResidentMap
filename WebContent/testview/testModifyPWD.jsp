<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>修改密码测试</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jQuery/jQuery-2.2.0.min.js"></script>
   <script type="text/javascript">
   		function modifyPwd(){
   			var url = "${pageContext.request.contextPath}/users/modifypassword";
   			var user = {};
			user.name = $("#name").val(),
			user.validatecode = $("#validatecode").val(),
			user.password=$("#password").val()
			var ajax_type = 'PUT';
			$.ajax({
				type : ajax_type,
				url : url,
				data : JSON.stringify(user),
				contentType:'application/json',
				success : function(data) {
					console.log(data);
				},

				//调用出错执行的函数
				error : function() {
					alert("请求出错");
				}
			});
   		}
   </script>
  </head>
  
  <body>
   		<form action=""  method="POST">
   			用户名称：<input type="text" name="name" id="name"/><br/>
   			验证码：<input type="text" name="validatecode" id="validatecode"/><br/>
   			新密码：<input type="password" name="password" id="password"/><br/>
   			<input type="button" onclick="modifyPwd()" value="提交"/>
   		</form>
  </body>
</html>
