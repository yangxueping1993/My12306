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
			//Object��String�����͵������������ݣ���ֵ�Ի��ַ�������
			data:$("#form1").serialize(),   
			
			type:'POST',                        ����ʽ��Ĭ��get 
			async:false,                    �Ƿ��첽����Ĭ��Ϊtrue��false Ϊͬ������
			success:function(){},              ��ɹ��Ļص����� 
			error:function(){}                ����ʧ�ܵĻص�����
		}); 
		 $("#btn").click(function(){
			$.ajax({
				url:"form.jsp",
				data:{userName:$("#userName").val(),email:$("#email").val()},       
				type:'POST',                        
				async:false,
				success:function(resp,status,xhr){
					console.log("resp="+resp.trim());
					console.log("ִ�гɹ���");
				},           
				error:function(){
					console.log("ִ��ʧ�ܣ�");              
				}
			}); */
			
			 console.log("$(#form1).serialize():"+$("#form1").serialize());
			console.log($("input[name='sex']").serialize());
			console.log($("input[name='sex']").serializeArray());

			
			//ͨ�ò�������
			/* $.ajaxSetup({
				type:"post",
				url:"form.jsp",
				data:$("#form1").serialize(),
				async:false
			});
			$.ajax({
				success:function(){
					console.log("��һ��ִ�гɹ���");
				},  
			});
			$.ajax({
				success:function(){
					console.log("�ڶ���ִ�гɹ���");
				},  
			}); */
			
			var obj={name:"tom",age:10,sex:"��"};
			console.log($.param(obj));
	});
});
</script>
</head>
<body>
<form action="" id="form1" >
�û�����<input type="text" name="userName" id="userName"/>
���䣺<input type="text" name="email" id="email"/> 
�У�<input type="radio" value="��" name="sex"/>
Ů��<input type="radio" value="Ů" name="sex"/>
<br>
A<input type="checkbox" value="A" name="CK"> 
B<input type="checkbox" value="B" name="CK"> 
C<input type="checkbox" value="C" name="CK"> 
<!-- ��ʼ��Ϊ��ť�涨 type ���ԡ�Internet Explorer ��Ĭ�������� "button"��
������������У����� W3C �淶����Ĭ��ֵ�� "submit"�� -->
<input type="button" id="btn" value="�ύ">
</form>
<!-- <button id="btn">�ύ</button> -->
</body>
</html>