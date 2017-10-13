<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>测试飞防团队接收任务</title>
    
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
   		
   		
   		function acceptTask(){
   			//获取飞防团队
   			var ajax_url =" ${pageContext.request.contextPath}/tasks/accept";
			var ajax_type = 'POST';
			var data = {
				"taskId":"8112951098911744",
				"userId":"8111998110757888",
				"taskType":"1",
				"airvechicleList":[
					{
						"airVehicleId":"8119164374079488",
						"parameter":"3.5"
					},{
						"airVehicleId":"8121808904203264",
						"parameter":"3.5"
					}
				]
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
   		<form action="###"  method="POST">
   			任务名称：<select name="task.id" id="task">
   			</select>
   			飞行器：<select name="air.id" id="air">
   			</select>
   			喷洒参数：<input type="text" id="parameter">
   			<br/>
   			<input type="button" onclick="acceptTask()"  value="接收任务"/>
   		</form>
  </body>
</html>
