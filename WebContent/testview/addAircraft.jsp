<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>添加飞行器</title>
    
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
   			//获取飞防团队
   			var ajax_url =" ${pageContext.request.contextPath}/users/type/3";
			var ajax_type = 'GET';
			$.ajax({
				type : ajax_type,
				url : ajax_url,
				contentType:'application/json',
				success : function(data) {
					if(data.code =='10'){
						//获取团队数组
						var teamArr = data.content;
						for(var i=0;i<teamArr.length;i++){
							console.log("id:"+teamArr[i].id+"  name:"+teamArr[i].fullName);
							var option = "<option value='"+teamArr[i].id+"'>"+teamArr[i].fullName+"</option";
							$("#owner").append(option);
						}
					}
					console.log(data);
				},

				//调用出错执行的函数
				error : function() {
					alert("请求出错");
				}
			});
   		});
   		
   		function addAir(){
   			//获取飞防团队
   			var ajax_url =" ${pageContext.request.contextPath}/airvehicles/";
			var ajax_type = 'POST';
			var data = {
				"name":$("#name").val(),
				"ownerId":$("#owner").val(),
				"responsiblePerson":$("#responsiblePerson").val(),
				"responsiblePersonTel":$("#responsiblePersonTel").val(),
				"gpsId":$("#gpsId").val()
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
   		<form action="${pageContext.request.contextPath}/airVehicles/add"  method="POST">
   			飞行器名称: <input type="text" name="name" id="name"><br/>
   			所属飞防团队：<select name="owner.id" id="owner">
   			</select>
   			<br/>
   			责任人：<input type="text" name="responsiblePerson" id="responsiblePerson"><br/>
   			责任人电话：<input type="text" name="responsiblePersonTel" id="responsiblePersonTel"><br/>
   			绑定的GPS模块Id：<input type="text" name="gpsId" id="gpsId"><br/>
   			<input type="button" onclick="addAir()"value="提交"/>
   		</form>
  </body>
</html>
