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
				{name:"��ķ",age:10},
				function(respTxt,status,xhr){
					console.log(respTxt);
					$("#box").html(respTxt);
				}
		); 
		/*  $.post(
					"txt.jsp",
					{name:"��ķ",age:10},//post������û������
					function(respTxt,status,xhr){
						console.log(respTxt);
						$("#box").html(respTxt);
					}
			); */
		 /* $.post(
					"txt.jsp",
					{name:"��ķ",age:10},//post������û������
					function(respTxt,status,xhr){
						console.log(respTxt);
						$("#box").html(respTxt);
					},
					"html"//���ĸ�����һ�㲻��Ҫ��һ����д����Ҫ��֤�ͺ�̨һ��
			); */
		/*  $.post(
					"xml.jsp",
					{name:"��ķ",age:10},//post������û������
					function(respTxt,status,xhr){
						 //console.log(respTxt); 
						//$("#box").html(respTxt); 
						//respTxt=xhr.responseTxt,����xml���ݣ�respTxtΪ��
						//��xml����תΪjquery����Ȼ��ͨ��find�����ҵ���Ӧ�ı�ǩ�ͱ�ǩ��ֵ
						$("#box").html($(respTxt).find("book:first").find("title").text());
					},
					"xml"
			); */
		/*  $.post(
					"json.jsp",
					{name:"��ķ",age:10},//post������û������
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
<a href="#" onclick="myTest();">����JS</a>
<button id="btn">�첽��������</button>
<div id="box" style="color:red">����</div>
</body>
</html>