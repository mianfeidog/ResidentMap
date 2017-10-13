<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>统计多个飞行器的任务情况</title>
    
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
   			var ajax_url =" ${pageContext.request.contextPath}/taskresults/task/airvehicles/statistic";
   			//var ajax_url ="http://115.28.168.146:18080/AirVehicle/taskresults/task/airvehicles/statistic";
			var ajax_type = 'POST';
			var airIdList = new Array();
			airIdList.push("8148475018614784");
			airIdList.push("8148475298763776");
			var data = {
				"airvechicleIdList":airIdList,
				"taskId":"8148575111630848"
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
   		
   		
   		
   		function acceptTask2(){
   			//获取飞防团队
   			var ajax_url =" ${pageContext.request.contextPath}/taskresults/task/airvehicles/statistic";
			var ajax_type = 'POST';
			var airIdList = new Array();
			airIdList.push("8145362890335232");
			var data = {
				"airvechicleIdList":airIdList,
				"taskId":"8145238518431744"
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
