<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"%>
    <%
List<Map<String,String>> list=new ArrayList<Map<String,String>>();
Map<String,String> map=new HashMap<String,String>();
map.put("title","java���˼��");
map.put("author","tom");
list.add(map);

map=new HashMap<String,String>();
map.put("title","�ȸ�");
map.put("author","jack");
list.add(map);

/* ����gson�����������߰����������õ����ģʽ����Java Map���϶���ת��Ϊjson�ַ��� */
Gson gson=new Gson();
String str=gson.toJson(list);
    %>
   <%=str %>
