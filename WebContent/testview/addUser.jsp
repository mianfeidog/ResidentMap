<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jQuery/jQuery-2.2.0.min.js"></script>
	<script>
			function addUser(){
   			//获取飞防团队
   			var ajax_url =" ${pageContext.request.contextPath}/users";
			var ajax_type = 'POST';
			var data = {
				"name":$("#name").val(),
				"password":$("#pwd").val(),
				"fullName":$("#truename").val(),
				"userType":$("#userType").val()
			};
			$.ajax({
				type : ajax_type,
				url : ajax_url,
				contentType:'application/json',
				data:JSON.stringify(data),
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
   		<form action="${pageContext.request.contextPath}/user/add"  method="POST" enctype=" text/plain">
   			账号: <input type="text" name="name" id="name"><br/>
   			密码：<input type="password" name="password" id="pwd"/><br/>
   			真实姓名: <input type="text" name="fullName" id="truename"><br/>
   			账号类型：<select name="userType" id="userType">
   				<option value="1">管理员</option>
   				<option value="2">业主</option>
   				<option value="3">飞防团队</option>
   			</select>
   			<br/>
   			联系地址：<input type="text" name="address"><br/>
   			联系电话：<input type="text" name="telephone"><br/>
   			联系人：<input type="text" name="contacts"><br/>
   			<input type="button"  onclick="addUser();" value="提交"/>
   		</form>
  </body>
</html>
