<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>����Աѧ������</title>
<script type="text/javascript" src="../js/jquery-1.11.3.js"></script>
<script type="text/javascript"  src="https://cdn.hcharts.cn/highcharts/highcharts.js"> </script>
<script type="text/javascript">
$(function () {
    $('#container').highcharts({
        chart: {
            type: 'area',
            spacingBottom: 30
        },
        title: {
            text: 'С�ź�С�˼�ˮ����������� *'
        },
        subtitle: {
            text: '* С�˼ҵ��㽶����δ֪',
            floating: true,
            align: 'right',
            verticalAlign: 'bottom',
            y: 15
        },
        legend: {
            layout: 'vertical',
            align: 'left',
            verticalAlign: 'top',
            x: 150,
            y: 100,
            floating: true,
            borderWidth: 1,
            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || 'pink'
        },
        xAxis: {
            categories: ['ƻ��', '��', '����', '�㽶', '����', '����', '��ݮ', '��ݮ']
        },
        yAxis: {
            title: {
                text: 'Y-Axis'
            },
            labels: {
                formatter: function () {
                    return this.value;
                }
            }
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.series.name + '</b><br/>' +
                    this.x + ': ' + this.y;
            }
        },
        plotOptions: {
            area: {
                fillOpacity: 0.5
            }
        },
        credits: {
            enabled: false
        },
        series: [{
            name: 'С��',
            data: [0, 1, 4, 4, 5, 2, 3, 7]
        }, {
            name: 'С��',
            data: [1, 0, 3, null, 3, 1, 2, 1]
        }]
    });
});

</script>
</head>
<body>
<div id="container" style="width:500px;height=:300px"></div>
</body>
</html>