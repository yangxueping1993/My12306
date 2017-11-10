<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-1.11.3.js"></script>
<script type="text/javascript">
$(function(){
	$("#btn").click(function(){
		$("#box").load('txt.jsp',{name:'tom',age:10},function(respTxt,status,xhr){
			console.log(respTxt);
		});
	});
});
</script>
</head>
<body>
<button id="btn">异步加载数据</button>
<div id="box" style="color:red">测试</div>
</body>
</html>