<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>用户登录</title>
    
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
   		function login(){
   			var url = "http://115.28.168.146:18080/web/user/login";
   			var user = {};
			user.name = $("#name").val();
			user.password=$("#password").val();
			//user.userType =$("#userType").val();
			var ajax_type = 'POST';
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
   			账号: <input type="text" name="name" id="name"><br/>
   			密码：<input type="password" name="password" id="password"/><br/>
   			账号类型：<select name="userType" id="userType">
   				<option value="1">管理员</option>
   				<option value="2">业主</option>
   				<option value="3">飞防团队</option>
   			</select>
   			<input type="button" onclick="login()" value="提交"/>
   		</form>
  </body>
</html>
