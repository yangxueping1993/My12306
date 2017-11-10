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
<title>程序员学历调查</title>
</head>
<%
//初始化图表数据
DefaultCategoryDataset data=new DefaultCategoryDataset();
data.setValue(1000,"count","192.168.0.1");
data.setValue(2000,"count","192.168.0.2");
data.setValue(3000,"count","192.168.0.3");
data.setValue(4000,"count","192.168.0.4");
//创建主题样式（显示中文）
StandardChartTheme them=new StandardChartTheme("CN");
//设置标题样式
them.setExtraLargeFont(new Font("隶书",Font.BOLD,20));
//设置图例字体样式
them.setRegularFont(new Font("宋书",Font.BOLD,15));
//设置轴上字体样式
them.setLargeFont(new Font("宋书",Font.BOLD,15));
ChartFactory.setChartTheme(them);

//通过ChartFactory创建柱状图对象
JFreeChart  chart=ChartFactory.createBarChart3D("登录ip统计", "IP","数量", data);

//设置图表的背景颜色
chart.setBackgroundPaint(java.awt.Color.pink);
//输出图表
//PrintWriter pw=new PrintWriter(out);
String fileName=ServletUtilities.saveChartAsPNG(chart,500,300,session);
String graphUrl=request.getContextPath()+"/Admin/DisplayChart?filename="+fileName;
%>
<body>
<p align="center">哈奥
<img alt="" src="<%=graphUrl %>" width="500" height="300">
</p>

</body>
</html>