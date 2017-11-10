<%@page import="com.neuedu.business.service.UserService"%>
<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>用户表性别分析饼状图</title>
<script type="text/javascript" src="../js/jquery-1.11.3.js"></script>
<script type="text/javascript"  src="https://cdn.hcharts.cn/highcharts/highcharts.js"> </script>
<script type="text/javascript">
<%
UserService us=UserService.getInstance();
int[] sex=us.querySex();
int f=sex[0];
int m=sex[1];
%>
var fe=<%=f%>;
var me=<%=m%>;
$(function () {
    $('#container').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '用户表性别分析饼状图 '
        },
        tooltip: {
            headerFormat: '{series.name}<br>',
            pointFormat: '{point.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false
                },
                showInLegend: true
            }
        },
        series: [{
            type: 'pie',
            name: '性别占比',
            data: [
                ['男',      me],
                {
                    name: '女',
                    y: fe,
                    sliced: true,
                    selected: true
                }
            ]
        }]
    });
});

</script>
</head>
<body>
<div id="container" style="width:500px;height=:300px"></div>
</body>
</html>