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
		$.getScript("test.js");
		$.getJSON("test.json",function(resp,status,xhr){
			console.log(resp);
			console.log(resp[1].title);
		});
	}); 
});
</script>
</head>
<body>
<input id="btn" type="button" value="异步加载数据">
<a href="#" onclick="doClick()">测试</a>
</body>
</html>