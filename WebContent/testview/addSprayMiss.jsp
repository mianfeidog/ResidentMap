<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>添加漏喷统计</title>
    
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
					}
				},

				//调用出错执行的函数
				error : function() {
					alert("请求出错");
				}
			});
			
			

   		});
   		
   		
   </script>
  </head>
  
  <body>
   		<form action="${pageContext.request.contextPath}/sprayMiss/add"  method="POST">
   			任务名称：<select name="task.id" id="task">
   			</select>
   			<br/>
   			漏喷面积：<input type="text"  name="missArea"/>
   			<br/>
   			漏喷百分比：<input type="text"  name="missPercentage"/>
   			<br/>
   			
   			<input type="submit" value="提交"/>
   		</form>
  </body>
</html>
