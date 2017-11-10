<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>Insert title here</title>
<script type="text/javascript"  src="../js/jquery-1.11.3.js"></script>
<script type="text/javascript">
$(function(){
 	$("#btn").click(function(){
		/* $.ajax({
			url:"form.jsp",
			//Object或String，发送到服务器的数据，键值对或字符串对象
			data:$("#form1").serialize(),   
			
			type:'POST',                        请求方式，默认get 
			async:false,                    是否异步处理。默认为true，false 为同步处理
			success:function(){},              求成功的回调函数 
			error:function(){}                请求失败的回调函数
		}); 
		 $("#btn").click(function(){
			$.ajax({
				url:"form.jsp",
				data:{userName:$("#userName").val(),email:$("#email").val()},       
				type:'POST',                        
				async:false,
				success:function(resp,status,xhr){
					console.log("resp="+resp.trim());
					console.log("执行成功！");
				},           
				error:function(){
					console.log("执行失败！");              
				}
			}); */
			
			 console.log("$(#form1).serialize():"+$("#form1").serialize());
			console.log($("input[name='sex']").serialize());
			console.log($("input[name='sex']").serializeArray());

			
			//通用参数设置
			/* $.ajaxSetup({
				type:"post",
				url:"form.jsp",
				data:$("#form1").serialize(),
				async:false
			});
			$.ajax({
				success:function(){
					console.log("第一次执行成功！");
				},  
			});
			$.ajax({
				success:function(){
					console.log("第二次执行成功！");
				},  
			}); */
			
			var obj={name:"tom",age:10,sex:"男"};
			console.log($.param(obj));
	});
});
</script>
</head>
<body>
<form action="" id="form1" >
用户名：<input type="text" name="userName" id="userName"/>
邮箱：<input type="text" name="email" id="email"/> 
男：<input type="radio" value="男" name="sex"/>
女：<input type="radio" value="女" name="sex"/>
<br>
A<input type="checkbox" value="A" name="CK"> 
B<input type="checkbox" value="B" name="CK"> 
C<input type="checkbox" value="C" name="CK"> 
<!-- 请始终为按钮规定 type 属性。Internet Explorer 的默认类型是 "button"，
而其他浏览器中（包括 W3C 规范）的默认值是 "submit"。 -->
<input type="button" id="btn" value="提交">
</form>
<!-- <button id="btn">提交</button> -->
</body>
</html>