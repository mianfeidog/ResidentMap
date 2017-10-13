<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>添加任务</title>
    
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
							$("#taskExecutor").append(option);
						}
					}
					console.log(data);
				},

				//调用出错执行的函数
				error : function() {
					alert("请求出错");
				}
			});
			
			
			
			//获取业主
   			var ajax_url =" ${pageContext.request.contextPath}/users/type/2";
			var ajax_type = 'GET';
			$.ajax({
				type : ajax_type,
				url : ajax_url,
				contentType:'application/json',
				success : function(data) {
					if(data.code =='10'){
						//获取业主数组
						var teamArr = data.content;
						for(var i=0;i<teamArr.length;i++){
							console.log("id:"+teamArr[i].id+"  name:"+teamArr[i].fullName);
							var option = "<option value='"+teamArr[i].id+"'>"+teamArr[i].fullName+"</option";
							$("#taskOwner").append(option);
							$("#creator").append(option);
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
   		
   		
   		function addTask(){
   			//获取飞防团队
   			var ajax_url =" ${pageContext.request.contextPath}/tasks/";
			var ajax_type = 'POST';
			var data1 = {
			    areaEdgePoint : "[\n  {\n    \"longitude\" : -122.6453907488723,\n    \"latitude\" : 37.74398212087119\n  },\n  {\n    \"longitude\" : -122.61727378641,\n    \"latitude\" : 37.83973880553965\n  },\n  {\n    \"longitude\" : -122.61727378641,\n    \"latitude\" : 37.83973880553965\n  },\n  {\n    \"longitude\" : -122.5446457859283,\n    \"latitude\" : 37.96319890212961\n  },\n  {\n    \"longitude\" : -122.5305873046971,\n    \"latitude\" : 37.97988954570441\n  },\n  {\n    \"longitude\" : -122.5235625556091,\n    \"latitude\" : 37.98360121803737\n  },\n  {\n    \"longitude\" : -122.5141842460849,\n    \"latitude\" : 37.98452555143393\n  },\n  {\n    \"longitude\" : -122.5095040743779,\n    \"latitude\" : 37.98452555143393\n  },\n  {\n    \"longitude\" : -122.3501806091282,\n    \"latitude\" : 37.95484858880118\n  },\n  {\n    \"longitude\" : -122.327920598591,\n    \"latitude\" : 37.92515953003006\n  },\n  {\n    \"longitude\" : -122.3185512721219,\n    \"latitude\" : 37.86667096463864\n  },\n  {\n    \"longitude\" : -122.3185512721219,\n    \"latitude\" : 37.82951466683394\n  },\n  {\n    \"longitude\" : -122.3185512721219,\n    \"latitude\" : 37.78583046819664\n  },\n  {\n    \"longitude\" : -122.4649840533034,\n    \"latitude\" : 37.48304195780051\n  },\n  {\n    \"longitude\" : -122.4673286306845,\n    \"latitude\" : 37.4811734497068\n  },\n  {\n    \"longitude\" : -122.4696732080655,\n    \"latitude\" : 37.48023559837993\n  },\n  {\n    \"longitude\" : -122.4696732080655,\n    \"latitude\" : 37.48023559837993\n  },\n  {\n    \"longitude\" : -122.4708410052285,\n    \"latitude\" : 37.48023559837993\n  },\n  {\n    \"longitude\" : -122.4720177854465,\n    \"latitude\" : 37.47930489448728\n  },\n  {\n    \"longitude\" : -122.4720177854465,\n    \"latitude\" : 37.47930489448728\n  },\n  {\n    \"longitude\" : -122.4731855826095,\n    \"latitude\" : 37.47930489448728\n  },\n  {\n    \"longitude\" : -122.5961905560908,\n    \"latitude\" : 37.52410143657697\n  },\n  {\n    \"longitude\" : -122.6477353262533,\n    \"latitude\" : 37.54462621127222\n  },\n  {\n    \"longitude\" : -122.6477353262533,\n    \"latitude\" : 37.54462621127222\n  },\n  {\n    \"longitude\" : -122.6922463642727,\n    \"latitude\" : 37.56887801781085\n  },\n  {\n    \"longitude\" : -122.6922463642727,\n    \"latitude\" : 37.56887801781085\n  },\n  {\n    \"longitude\" : -122.715674171973,\n    \"latitude\" : 37.58379989939422\n  },\n  {\n    \"longitude\" : -122.7344218079662,\n    \"latitude\" : 37.59871161732178\n  },\n  {\n    \"longitude\" : -122.7344218079662,\n    \"latitude\" : 37.59871161732178\n  },\n  {\n    \"longitude\" : -122.8000250593599,\n    \"latitude\" : 37.70582855736676\n  },\n  {\n    \"longitude\" : -122.8000250593599,\n    \"latitude\" : 37.70582855736676\n  },\n  {\n    \"longitude\" : -122.8000250593599,\n    \"latitude\" : 37.70582855736676\n  },\n  {\n    \"longitude\" : -122.7988482791418,\n    \"latitude\" : 37.70582855736676\n  },\n  {\n    \"longitude\" : -122.7988482791418,\n    \"latitude\" : 37.70582855736676\n  },\n  {\n    \"longitude\" : -122.7988482791418,\n    \"latitude\" : 37.70582855736676\n  },\n  {\n    \"longitude\" : -122.7976804819788,\n    \"latitude\" : 37.70582855736676\n  },\n  {\n    \"longitude\" : -122.7976804819788,\n    \"latitude\" : 37.70582855736676\n  }\n]",
			    contactPhone : 13119159078,
			    createdBy : 34242,
			    description : "20170613任务添加测试",
			    finishedIn : "2019-06-13 00:00:00",
			    locatedAt : "{\n  \"longitude\" : -122.4064169999999,\n  \"latitude\" : 37.78583401977729\n}",
			    name : 34234,
			    startedAt : "2018-06-20 00:00:00",
			    taskOwnerId : 8112001945497600,
			    taskType : 0,
			    validateCode :3533
			};
			var data = {
				"name":$("#name").val(),
				"taskType":$("#taskType").val(),
				"taskOwnerId":$("#taskOwner").val(),
				"validateCode":$("#validateCode").val(),
				"createdBy":$("#createdBy").val(),
				"startedAt":$("#startedAt").val(),
				"finishedIn":$("#finishedIn").val(),
				"locatedAt":$("#locatedAt").val(),
				"areaEdgePoint":[
					  {
					    "longitude" : 109.0132183635876,
					    "latitude" : 34.52619753073007
					  },
					  {
					    "longitude" : 108.9077842458814,
					    "latitude" : 34.46509577694379
					  },
					  {
					    "longitude" : 108.9077842458814,
					    "latitude" : 34.46509577694379
					  },
					  {
					    "longitude" : 108.8820208438553,
					    "latitude" : 34.41365051885094
					  },
					  {
					    "longitude" : 108.8679623626241,
					    "latitude" : 34.35537133592852
					  },
					  {
					    "longitude" : 108.8913901703243,
					    "latitude" : 34.24356950336838
					  },
					  {
					    "longitude" : 108.9335566309627,
					    "latitude" : 34.20463925418301
					  },
					  {
					    "longitude" : 108.9745642774932,
					    "latitude" : 34.19003852912749
					  },
					  {
					    "longitude" : 109.0307892193627,
					    "latitude" : 34.19003852912749
					  },
					  {
					    "longitude" : 109.0799894121442,
					    "latitude" : 34.20853533530401
					  },
					  {
					    "longitude" : 109.1245094332187,
					    "latitude" : 34.24940337133977
					  },
					  {
					    "longitude" : 109.160818941932,
					    "latitude" : 34.31164068067518
					  },
					  {
					    "longitude" : 109.1819111553063,
					    "latitude" : 34.37868792186922
					  },
					  {
					    "longitude" : 109.1947928563194,
					    "latitude" : 34.43403297598706
					  },
					  {
					    "longitude" : 109.1947928563194,
					    "latitude" : 34.48546556939921
					  },
					  {
					    "longitude" : 109.1830789524693,
					    "latitude" : 34.51553494181542
					  },
					  {
					    "longitude" : 109.152626395681,
					    "latitude" : 34.53492584930356
					  },
					  {
					    "longitude" : 109.1245094332187,
					    "latitude" : 34.5407392595334
					  },
					  {
					    "longitude" : 109.0928800962124,
					    "latitude" : 34.5407392595334
					  },
					  {
					    "longitude" : 109.0577383846621,
					    "latitude" : 34.51747572475701
					  },
					  {
					    "longitude" : 109.0577383846621,
					    "latitude" : 34.51747572475701
					  }
					],
				"description":$("#description").val()
				
			};
			$.ajax({
				type : ajax_type,
				url : ajax_url,
				contentType:'application/json',
				data:JSON.stringify(data1),
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
   			任务名称: <input type="text" name="name"  id="name"><br/>
   			任务模式：<select name="taskType"  id="taskType">
   				<option value="0">预设模式</option>
   				<option value="1">自由模式</option>
   			</select>
   			<br/>
   			业主：<select name="taskOwnerId" id="taskOwner">
   			</select>
   			<br/>
   			联系人姓名：
   			<input type="text" name="createdBy" id="createdBy">
   			<br/>
   			<br/>
   			联系人电话:<input type="text" name="contactPhone" id="contactPhone">
   			<br/>
   			开始时间：<input type="text" name="startedAt" id="startedAt">
   			<br/>
   			结束时间：<input type="text" name="finishedIn"id="finishedIn">
   			<br/>
   			所在地标：<input type="text" name="locatedAt"id="locatedAt">
   			<br/>
   			设定坐标面积点：<input type="text" name="areaEdgePoint"id="areaEdgePoint">
   			<br/>
   			验证码：<input type="text" name="validateCode"id="validateCode">
   			<br/>
   			所属飞防团队：<select name="taskExecutorId" id="taskExecutor">
   			</select>
   			<br/>
   			任务接受时间：<input type="text" name="receivedAt"id="receivedAt">
   			<br/>
   			任务状态：<select name="status" id="status">
   				<option value="0">创建</option>
   				<option value="1">发布</option>
   				<option value="2">进行中</option>
   				<option value="3">已完成</option>
   			</select>
   			<br/>
   			审核状态：<select name="auditStatus" id="auditStatus">
   				<option value="0">无需审核</option>
   				<option value="1">待审核</option>
   				<option value="2">已审核</option>
   			</select>
   			<br/>
   			<input type="button" value="提交"  onclick='addTask()'/>
   		</form>
  </body>
</html>
