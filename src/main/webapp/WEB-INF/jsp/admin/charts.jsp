<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/24
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>员工考勤管理系统</title>

    <style>
        .atd_div{
            background-color: #337ab7;
            border-radius: 10px;
            color: #ffffff;
            margin:0 10px;
            padding: 10px;
            height: 70px;
            font-size: 18px;
        }
    </style>
</head>
<body>

<%@include file="../common/admin_comm.jsp"%>

<div class="container">

    <div class="row" style="margin: 30px 0 0 0">
        <div style="width: 15%;float: left;margin-left: 20%">
            <h3><b>员工统计分析</b></h3>
        </div>
        <hr width="75%" style="margin-left: 18%;float: left"/>
    </div>

    <div class="row col-lg-offset-2" style="text-align: center">
        <div class="col-md-2 atd_div">
            <div class="row">JAVA大数据人数</div>
            <div class="row" id="dsj_dept">${charts.dsj_dept}</div>
        </div>
        <div class="col-md-2 atd_div">
            <div class="row">智能物联人数</div>
            <div class="row" id="zn_dept">${charts.zn_dept}</div>
        </div>
        <div class="col-md-2 atd_div" >
            <div class="row">男生人数</div>
            <div class="row" id="man">${charts.man}</div>
        </div>
        <div class="col-md-2 atd_div">
            <div class="row">女生人数</div>
            <div class="row" id="woman">${charts.woman}</div>
        </div>
        <div class="col-md-2 atd_div">
            <div class="row">禁用人数</div>
            <div class="row" id="disable">${charts.disable}</div>
        </div>
        <div class="col-md-2 atd_div" hidden>
            <div class="row">员工总数</div>
            <div class="row" id="total">${charts.total}</div>
        </div>
    </div>

    <div class="row " >
        <div class="col-lg-offset-2 col-md-4" id="dept_chart" style="width: 450px;height: 300px;margin-top: 50px"></div>
        <div class="col-lg-offset-8 col-md-4"  id="gender_chart" style="width: 400px;height: 300px;margin-top: -300px"></div>
    </div>
</div>

<script type="application/javascript">
    // 基于准备好的dom，初始化echarts实例
    var dept = echarts.init(document.getElementById('dept_chart'));
    option = {
        title: {
            text: '部门人数饼状图',
            subtext: '',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['JAVA大数据', '智能物联']
        },
        series: [
            {
                name: '访问来源',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: [
                    {value: parseInt($("#dsj_dept").text()), name: 'JAVA大数据'},
                    {value: parseInt($("#zn_dept").text()), name: '智能物联'}

                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    dept.setOption(option);

    var gender = echarts.init(document.getElementById('gender_chart'));
    // 指定图表的配置项和数据
    var option2 = {
        title: {
            text: '性别柱形图'
        },
        tooltip: {},
        legend: {
            data: ['人数']
        },
        xAxis: {
            data: ["男", "女"]
        },
        yAxis: {},
        series: [{
            name: '人数',
            type: 'bar',
            data: [parseInt($("#man").text()), parseInt($("#woman").text())]
        }]
    };
    gender.setOption(option2);
</script>

</body>
</html>
