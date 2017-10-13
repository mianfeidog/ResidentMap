<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>添加帮扶对象</title>
    
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

			
			
			
			//获取帮扶对象
   			var ajax_url =" ${pageContext.request.contextPath}/aid_residents/users/8111998110757888/map/type/1";
			var ajax_type = 'GET';
			$.ajax({
				type : ajax_type,
				url : ajax_url,
				contentType:'application/json',
				success : function(data) {
					if(data.code =='0'){
						//获取帮扶对象数组
						var residentArr = data.data;
						for(var i=0;i<residentArr.length;i++){
							console.log("id:"+residentArr[i].id+"  name:"+residentArr[i].name);
							var option = "<option value='"+residentArr[i].id+"'>"+residentArr[i].name+"</option>";
							$("#resident").append(option);
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
   		
   		
   		function addAidResident(){

            var base = getBase64Image();
            console.log(base);
            //添加扶贫记录
            var ajax_url =" ${pageContext.request.contextPath}/aid_residents";
            var ajax_type = 'POST';
            var data1 = {
                name:"hello222",
                img:base,
				createdBy:8111998110757888
            }
            $.ajax({
                type : ajax_type,
                url : ajax_url,
                data:JSON.stringify(data1),
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


        function getBase64Image() {
            //创建一个canvas
            var c=document.createElement("canvas");
            //设置canvas宽高为图片宽高
            c.width=540;c.height=258;
//将图片绘制到canvas
            var cxt=c.getContext("2d");
            var img=new Image();
            img.src="http://www.baidu.com/img/bdlogo.png";
            cxt.drawImage(img,0,0);
//得到图片的base64编码数据
            var dd=c.toDataURL();
//log出图片base64数据
            console.log(dd)
			return dd;
        }
   </script>
  </head>
  
  <body>
   		<form action=""  method="POST">
   			姓名: <input type="text" name="name"  id="name"><br/>
   			性别：<select name="gender"  id="gender">
				<option value="0">男</option>
			<option value="1">女</option>
   			</select>
			<input type="file" id="img"/>

   			<input type="button" value="提交"  onclick='addAidResident()'/>

   		</form>
  </body>
</html>
