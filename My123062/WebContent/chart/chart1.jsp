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
<title>����Աѧ������</title>
</head>
<%
//��ʼ��ͼ������
DefaultPieDataset data=new DefaultPieDataset();
data.setValue("��������", 380);
data.setValue("����", 1620);
data.setValue("��ר", 6100);
data.setValue("����", 8310);
data.setValue("˶ʿ", 3520);
//ͼ������
PiePlot3D plot=new PiePlot3D(data);

//Image image=new Image("../images/cat.jpg");
//plot.setBackgroundImage(image);

//����һ��ͼ�����
JFreeChart  chart=new JFreeChart("����Աѧ������",JFreeChart.DEFAULT_TITLE_FONT,plot,true);
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