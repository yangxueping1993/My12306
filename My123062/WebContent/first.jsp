<%@page contentType="text/html; charset=GB2312" language="java" import="java.util.Calendar,java.util.Date"
errorPage="Third.jsp" 
%>
<%!
public String getHello(String name){
	return "hi:"+name;
}
String getHello2(String name){
	return "hi:"+name;
}
%>
<%
//Javaע��Javaע��
/* Javaע�� */

Date now=new Date();
String a=request.getParameter("a");
request.setCharacterEncoding("GB2312");

%>
<html>
<head>
<!-- htmlע�� -->
<%--JSPע�� --%>
<title>��һ��jspҳ��</title>


<style type="text/css">
h1{
color:red;
}
</style>

<script type="text/javascript" src="test.js"></script>
<script type="text/javascript">
alert("haah1");
 function test(){
	alert("����3");
}
window.onload=function test(){
		alert("����2");
	};
window.onload=test; 
</script>

</head>
<body>
<h1>JSPҳ�湹��</h1>
<h1>��ǰϵͳʱ��1��<%=now %></h1>
<h1>��ǰϵͳʱ��2��<%=new Date() %></h1>
<h6><%=getHello("hello") %></h6>
<br>
<!-- ��̬���� -->
<%-- <%@include file="Third.jsp" %> --%>

<!-- ��̬����ҳ�� -->
<%-- <jsp:include page="Third.jsp"/> --%>

</body>

</html>