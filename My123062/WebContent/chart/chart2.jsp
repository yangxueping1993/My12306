<%@page import="java.awt.Font"%>
<%@page import="org.jfree.chart.StandardChartTheme"%>
<%@page import="org.jfree.chart.ChartFactory"%>
<%@page import="org.jfree.data.category.DefaultCategoryDataset"%>
<%@page import="java.awt.Image"%>
<%@page import="org.jfree.chart.servlet.ServletUtilities"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.jfree.chart.JFreeChart"%>
<%@page import="org.jfree.chart.plot.PiePlot3D"%>
<%@page import="org.jfree.data.general.DefaultPieDataset"%>
<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>����Աѧ������</title>
</head>
<%
//��ʼ��ͼ������
DefaultCategoryDataset data=new DefaultCategoryDataset();
data.setValue(1000,"count","192.168.0.1");
data.setValue(2000,"count","192.168.0.2");
data.setValue(3000,"count","192.168.0.3");
data.setValue(4000,"count","192.168.0.4");
//����������ʽ����ʾ���ģ�
StandardChartTheme them=new StandardChartTheme("CN");
//���ñ�����ʽ
them.setExtraLargeFont(new Font("����",Font.BOLD,20));
//����ͼ��������ʽ
them.setRegularFont(new Font("����",Font.BOLD,15));
//��������������ʽ
them.setLargeFont(new Font("����",Font.BOLD,15));
ChartFactory.setChartTheme(them);

//ͨ��ChartFactory������״ͼ����
JFreeChart  chart=ChartFactory.createBarChart3D("��¼ipͳ��", "IP","����", data);

//����ͼ��ı�����ɫ
chart.setBackgroundPaint(java.awt.Color.pink);
//���ͼ��
//PrintWriter pw=new PrintWriter(out);
String fileName=ServletUtilities.saveChartAsPNG(chart,500,300,session);
String graphUrl=request.getContextPath()+"/Admin/DisplayChart?filename="+fileName;
%>
<body>
<p align="center">����
<img alt="" src="<%=graphUrl %>" width="500" height="300">
</p>

</body>
</html>