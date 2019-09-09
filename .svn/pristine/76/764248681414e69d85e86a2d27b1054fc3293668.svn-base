<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/22
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<%@include file="../common/emp_comm.jsp"%>
<div class="container">

    <div class="row" style="margin: 30px 0 0 0">
        <div style="width: 15%;float: left;margin-left: 20%">
            <h3><b>考勤情况</b></h3>
        </div>
        <hr width="75%" style="margin-left: 18%;float: left"/>
    </div>

    <div class="row col-lg-offset-2" style="text-align: center">
        <div class="col-md-2 atd_div">
            <div class="row">迟到次数</div>
            <div class="row" id="late_num">${attend.late_num}</div>
        </div>
        <div class="col-md-2 atd_div">
            <div class="row">早退次数</div>
            <div class="row" id="leave_early_num">${attend.leave_early_num}</div>
        </div>
        <div class="col-md-2 atd_div" >
            <div class="row">出差次数</div>
            <div class="row" id="attend_day">${attend.attend_day}</div>
        </div>
        <div class="col-md-2 atd_div">
            <div class="row">旷工次数</div>
            <div class="row" id="absent_day">${attend.absent_day}</div>
        </div>
        <div class="col-md-2 atd_div">
            <div class="row">请假次数</div>
            <div class="row" id="ask_leave_day">${attend.ask_leave_day}</div>
        </div>
    </div>

    <div class="row col-lg-offset-2" id="atd_chart" style="width: 400px;height: 300px;margin-top: 50px"></div>
    <div class="row" id="atd_chart2" style="width: 400px;height: 300px;margin-top: 50px;position: absolute;right: 10%;top: 40%"></div>
    <script type="application/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('atd_chart'));
        option = {
            title : {
                text: '考勤情况饼状图',
                subtext: '',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['迟到次数','早退次数','出差次数','旷工次数','请假次数']
            },
            series : [
                {
                    name: '访问来源',
                    type: 'pie',
                    radius : '55%',
                    center: ['60%', '60%'],
                    data:[
                        {value:parseInt($("#late_num").text()), name:'迟到次数'},
                        {value:parseInt($("#leave_early_num").text()), name:'早退次数'},
                        {value:parseInt($("#attend_day").text()), name:'出差次数'},
                        {value:parseInt($("#absent_day").text()), name:'旷工次数'},
                        {value:parseInt($("#ask_leave_day").text()), name:'请假次数'}
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
        myChart.setOption(option);

        var myChart2 = echarts.init(document.getElementById('atd_chart2'));
        // 指定图表的配置项和数据
        var option2 = {
            title: {
                text: '考勤柱形图'
            },
            tooltip: {},
            legend: {
                data:['次数']
            },
            xAxis: {
                data: ["迟到次数","早退次数","出差次数","旷工次数","请假次数"]
            },
            yAxis: {},
            series: [{
                name: '次数',
                type: 'bar',
                data: [parseInt($("#late_num").text()), parseInt($("#leave_early_num").text()), parseInt($("#attend_day").text()), parseInt($("#absent_day").text()), parseInt($("#ask_leave_day").text())]
            }]
        };
        myChart2.setOption(option2);
    </script>

</div>
</body>
</html>
