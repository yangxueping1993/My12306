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
//Java注释Java注释
/* Java注释 */

Date now=new Date();
String a=request.getParameter("a");
request.setCharacterEncoding("GB2312");

%>
<html>
<head>
<!-- html注释 -->
<%--JSP注释 --%>
<title>第一个jsp页面</title>


<style type="text/css">
h1{
color:red;
}
</style>

<script type="text/javascript" src="test.js"></script>
<script type="text/javascript">
alert("haah1");
 function test(){
	alert("哈哈3");
}
window.onload=function test(){
		alert("哈哈2");
	};
window.onload=test; 
</script>

</head>
<body>
<h1>JSP页面构成</h1>
<h1>当前系统时间1：<%=now %></h1>
<h1>当前系统时间2：<%=new Date() %></h1>
<h6><%=getHello("hello") %></h6>
<br>
<!-- 静态引入 -->
<%-- <%@include file="Third.jsp" %> --%>

<!-- 动态引入页面 -->
<%-- <jsp:include page="Third.jsp"/> --%>

</body>

</html>