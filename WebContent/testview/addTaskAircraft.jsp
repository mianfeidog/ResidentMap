<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>飞行器任务分配</title>
    
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
   		$(function(){
   			//获取所有的任务
   			var ajax_url =" ${pageContext.request.contextPath}/task/list";
			var ajax_type = 'GET';
			$.ajax({
				type : ajax_type,
				url : ajax_url,
				contentType:'application/json',
				success : function(data) {
					if(data.code =='10'){
						//获取任务数组
						var taskArr = data.content;
						for(var i=0;i<taskArr.length;i++){
							console.log("id:"+taskArr[i].id+"  name:"+taskArr[i].name);
							var option = "<option value='"+taskArr[i].id+"'>"+taskArr[i].name+"</option";
							$("#task").append(option);
						}
						
						//默认获取一次
						getAirByTeam();
					}
					//console.log(data);
				},

				//调用出错执行的函数
				error : function() {
					alert("请求出错");
				}
			});
			
			
			//获取该任务可选的飞行器
			$("#task").change(function(){
			    $("#air").html("");
				//获取飞防团队的id
				//alert($("#task").val());
				getAirByTeam();
			});
   		});
   		
   		function getAirByTeam(){
   			//根据任务id  获取到团队id
   			var task_url =" ${pageContext.request.contextPath}/task/"+$("#task").val();
   			$.ajax({
					type : "GET",
					url : task_url,
					contentType:'application/json',
					success : function(data) {
					    var task = data.content;
						
						//console.log(task);
						
						var air_url =" ${pageContext.request.contextPath}/air/list/team/"+task.taskExecutor.id;
						$.ajax({
							type : "GET",
							url : air_url,
							contentType:'application/json',
							success : function(data) {
								 var airArr = data.content;
								for(var i=0;i<airArr.length;i++){
									console.log("id:"+airArr[i].id+"  name:"+airArr[i].name);
									var option = "<option value='"+airArr[i].id+"'>"+airArr[i].name+"</option";
									$("#air").append(option);
								}
							},
			
							//调用出错执行的函数
							error : function() {
								alert("请求出错");
							}
						});
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
   		<form action="${pageContext.request.contextPath}/taskAir/add"  method="POST">
   		    <input type="hidden"  id="team"/>
   			任务名称：<select name="task.id" id="task">
   			</select>
   			飞行器：<select name="air.id" id="air">
   			</select>
   			<br/>
   			<input type="submit" value="提交"/>
   		</form>
  </body>
</html>
