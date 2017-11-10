<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"%>
<%
String userName=request.getParameter("userName");
String email=request.getParameter("email");
String sex=request.getParameter("sex");
System.out.println("{userName:"+userName+",email:"+email+",sex:"+sex+"}");
boolean flag=false;
if("admin".equals(userName)){
	flag=true;
}

%>
<%=flag%>
<%="hahahahahh"%>