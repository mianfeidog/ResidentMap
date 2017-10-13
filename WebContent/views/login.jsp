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
            var ajax_url =" ${pageContext.request.contextPath}/residents/add";
            var ajax_type = 'POST';
            var data = {
                "type":$("#type").val(),
                "name":$("#name").val(),
                "birthday":$("#birthday").val(),
                "familyCnt":$("#familyCnt").val(),
                "telephone":$("#telephone").val(),
                "yearIncome":$("#yearIncome").val(),
                "address":$("#address").val(),
                "receivePolicyStandard":$("#receivePolicyStandard").val(),
                "isLowIncome":$("#isLowIncome").val(),
                "isDeformity":$("#isDeformity").val(),

            };
            $.ajaxFileUpload({
            //$.ajax({
                type : ajax_type,
                url : ajax_url,
                contentType:'application/json',
                data:JSON.stringify(data),
                fileElementId:'file', //文件选择框的id属性
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
urltest2: <input value="${pageContext.request.contextPath}" type="text" name="urltest" id="urltest"><br/>
<form action="${pageContext.request.contextPath}/residents/add"  method="POST" enctype="multipart/form-data">

	type: <input value="1" type="text" name="type" id="type"><br/>
	name: <input value="nihao" type="text" name="name" id="name"><br/>
	birthday: <input value="0" type="text" name="birthday" id="birthday"><br/>
	familyCnt: <input value="11" type="text" name="familyCnt" id="familyCnt"><br/>
	telephone: <input value="333" type="text" name="telephone" id="telephone"><br/>
	yearIncome: <input value="23" type="text" name="yearIncome" id="yearIncome"><br/>
	address: <input value="testadd" type="text" name="name" id="address"><br/>
	receivePolicyStandard: <input value="haha" type="receivePolicyStandard" name="receivePolicyStandard"><br/>
	isLowIncome: <input value="1" type="text" name="isLowIncome" id="isLowIncome"><br/>
	isDeformity: <input value="1" type="text" name="isDeformity" id="isDeformity"><br/>
	phototest: <input type="file" name="file" id="file"><br/>

	<input type="button"  onclick="addUser();" value="提交"/>
</form>
</body>
</html>
