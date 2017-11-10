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
		$.get(
				"txt.jsp",
				{name:"汤姆",age:10},
				function(respTxt,status,xhr){
					console.log(respTxt);
					$("#box").html(respTxt);
				}
		); 
		/*  $.post(
					"txt.jsp",
					{name:"汤姆",age:10},//post传中文没有乱码
					function(respTxt,status,xhr){
						console.log(respTxt);
						$("#box").html(respTxt);
					}
			); */
		 /* $.post(
					"txt.jsp",
					{name:"汤姆",age:10},//post传中文没有乱码
					function(respTxt,status,xhr){
						console.log(respTxt);
						$("#box").html(respTxt);
					},
					"html"//第四个参数一般不需要，一旦填写，就要保证和后台一致
			); */
		/*  $.post(
					"xml.jsp",
					{name:"汤姆",age:10},//post传中文没有乱码
					function(respTxt,status,xhr){
						 //console.log(respTxt); 
						//$("#box").html(respTxt); 
						//respTxt=xhr.responseTxt,对于xml数据，respTxt为空
						//把xml对象转为jquery对象，然后通过find方法找到对应的标签和标签的值
						$("#box").html($(respTxt).find("book:first").find("title").text());
					},
					"xml"
			); */
		/*  $.post(
					"json.jsp",
					{name:"汤姆",age:10},//post传中文没有乱码
					function(respTxt,status,xhr){
						 console.log(respTxt); 
						var json=JSON.parse(respTxt);
					 	$("#box").html(json[0].title);
					}
			); */
	});
});
</script>
</head>
<body>
<a href="#" onclick="myTest();">测试JS</a>
<button id="btn">异步加载数据</button>
<div id="box" style="color:red">测试</div>
</body>
</html>