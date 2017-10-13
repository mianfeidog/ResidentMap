<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>添加消息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   		<form action="${pageContext.request.contextPath}/msg/add"  method="POST" enctype=" text/plain">
   			发送对象: <input type="text" name="target"><br/>
   			标题：<input type="text" name="ttile"/><br/>
   			内容: <input type="text" name="content"><br/>
   			发送时间：<input type="text" name="sendTime"><br/>
   			<input type="submit" value="提交"/>
   		</form>
  </body>
</html>
