<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>添加扶贫记录</title>
    
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
   		
   		
   		function addAidRecord(){
            //添加扶贫记录
            var ajax_url =" ${pageContext.request.contextPath}/aid_records";
            var ajax_type = 'POST';
            var data1 = {
                content:$("#content").val(),
                residentId:$("#resident").val(),
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

        function updateAidRecord(){
            //添加扶贫记录
            var ajax_url =" ${pageContext.request.contextPath}/aid_records";
            var ajax_type = 'PUT';
            var data1 = {
                id:8200540962422784,
                content:$("#content").val(),
                residentId:8189252860416000
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


        function deleteAidRecord(){
            //添加扶贫记录
            var ajax_url =" ${pageContext.request.contextPath}/aid_records/8200540962422784";
            var ajax_type = 'DELETE';
            var data1 = {
                id:8200540962422784
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
   </script>
  </head>
  
  <body>
   		<form action=""  method="POST">
   			内容: <input type="text" name="content"  id="content"><br/>
   			帮扶对象：<select name="resident"  id="resident">
   			</select>

   			<input type="button" value="提交"  onclick='addAidRecord()'/>
			<input type="button" value="修改"  onclick='updateAidRecord()'/>
			<input type="button" value="删除"  onclick='deleteAidRecord()'/>
   		</form>
  </body>
</html>
