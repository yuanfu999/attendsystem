<%@ page import="com.aboluo.attend.pojo.Emp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path + "/";
    Emp emp = (Emp) session.getAttribute("session_emp");
    String s = null;
    if(emp.getLevel()==1){
        s = "超级管理员";
    }
    else if(emp.getLevel()==2){
        s = "管理员";
    }
    else if(emp.getLevel()==3){
        s = "职员";
    }
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>员工考勤管理系统</title>
    <!-- 引入css样式文件 -->
    <!-- Bootstrap Core CSS -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet" />
    <!-- MetisMenu CSS -->
    <link href="<%=basePath%>css/metisMenu.min.css" rel="stylesheet" />
    <!-- DataTables CSS -->
    <link href="<%=basePath%>css/dataTables.bootstrap.css" rel="stylesheet" />
    <!-- Custom CSS -->
    <link href="<%=basePath%>css/sb-admin-2.css" rel="stylesheet" />
    <!-- Custom Fonts -->
    <link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>css/boot-crm.css" rel="stylesheet" type="text/css" />

    <style>
        #jump_page_input{
            width: 35px;
            height:20px;
            border-radius: 5%;
            text-align: center;
            font-family: '黑体';
        }
        #emp_table tr{
            height: 39px;
        }
    </style>

    <!-- 引入js文件 -->
    <!-- jQuery -->
    <script src="<%=basePath%>js/jquery-1.11.3.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <!-- Metis Menu Plugin JavaScript -->
    <script src="<%=basePath%>js/metisMenu.min.js"></script>
    <!-- DataTables JavaScript -->
    <script src="<%=basePath%>js/jquery.dataTables.min.js"></script>
    <script src="<%=basePath%>js/dataTables.bootstrap.min.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="<%=basePath%>js/sb-admin-2.js"></script>
    <script src="<%=basePath%>js/myPage.js"></script>
    <script src="<%=basePath%>js/echarts.min.js"></script>
</head>
<body>
<script>
    $(document).ready(function(e) {
        var counter = 0;
        if (window.history && window.history.pushState) {
            $(window).on('popstate', function () {
                window.history.pushState('forward', null, '#');
                window.history.forward(1);
                //alert("不可回退");
            });
        }

        window.history.pushState('forward', null, '#'); //在IE中必须得有这两行
        window.history.forward(1);
    });
</script>

<nav class="navbar navbar-default navbar-static-top" role="navigation"
     style="margin-bottom: 0">
    <div class="navbar-header">
        <a class="navbar-brand" href="#">员工考勤管理系统</a>
    </div>
    <div style="float: right;margin:10px 80px 0 0;"><span>欢迎使用！<%=s%>,&nbsp;<%=emp.getEmp_name()%>&nbsp;</span><img src="<%=basePath%>imgs/tuichu.png" alt="" onclick="tuichu()" style="cursor: pointer;height: 25px; width: 25px;"></div>
    <script>
        function tuichu() {
            sessionStorage.clear();
            window.location.replace("${pageContext.request.contextPath }/#");
        }
    </script>



    <!-- 左侧显示列表部分 start-->
    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">
            <ul class="nav" id="side-menu">
                <li>
                    <div class="row">
                        <div class="col-md-12">
                            <h3 style="color: #337ab7;margin:20px 15px;">员工管理</h3>
                        </div>

                        <!-- /.col-lg-12 -->
                    </div>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath }/emp/own_atd?emp_id=<%=emp.getEmp_id()%>" >
                        <span class="glyphicon glyphicon-pencil" aria-hidden="true" style="padding: 3px 0 3px 0;"></span> 考勤情况
                    </a>
                </li>

                <li>
                    <a href="${pageContext.request.contextPath }/emp/ownInfo" >
                        <span class="glyphicon glyphicon-user" aria-hidden="true" style="padding: 3px 0 3px 0;"></span> 个人信息
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!-- 左侧显示列表部分 end-->
</nav>
</body>
</html>
