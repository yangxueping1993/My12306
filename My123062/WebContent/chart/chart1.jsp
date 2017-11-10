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
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>程序员学历调查</title>
</head>
<%
//初始化图表数据
DefaultPieDataset data=new DefaultPieDataset();
data.setValue("高中以下", 380);
data.setValue("高中", 1620);
data.setValue("大专", 6100);
data.setValue("本科", 8310);
data.setValue("硕士", 3520);
//图表区域
PiePlot3D plot=new PiePlot3D(data);

//Image image=new Image("../images/cat.jpg");
//plot.setBackgroundImage(image);

//声明一个图表对象
JFreeChart  chart=new JFreeChart("程序员学历调查",JFreeChart.DEFAULT_TITLE_FONT,plot,true);
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