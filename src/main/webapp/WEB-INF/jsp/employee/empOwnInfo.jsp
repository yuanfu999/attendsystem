<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工考勤管理系统</title>
</head>
<body>

<%@include file="../common/emp_comm.jsp"%>
<c:set var="emp" scope="session" value="<%=emp%>"/>
<div class="container">

    <div class="row" style="margin: 30px 0 0 0">
        <div style="width: 15%;float: left;margin-left: 20%">
            <h3><b>个人信息</b></h3>
        </div>
        <hr width="55%" style="margin-left: 18%;float: left"/>
    </div>

    <%--修改个人信息--%>
    <div class="col-lg-6 col-md-offset-2" >
        <form class="form-horizontal" id="admin_info">
            <div class="form-group">
                <label for="emp_id" class="col-sm-3 control-label">工号</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="emp_id" name = "emp_id" placeholder="工号" value="<%=emp.getEmp_id()%>" readonly>
                </div>
            </div>
            <div class="form-group">
                <label for="emp_name" class="col-sm-3 control-label">姓名</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="emp_name" name = "emp_name" placeholder="名字" value="<%=emp.getEmp_name()%>">
                </div>
            </div>
            <div class="form-group">
                <label for="dept" class="col-sm-3 control-label">部门</label>
                <div class="col-sm-8">

                    <c:choose>
                        <c:when test="${empty emp.dept}">
                            <input type="text" class="form-control" id="dept" name = "dept" placeholder="部门" value="">
                        </c:when>
                        <c:otherwise>
                            <input type="text" class="form-control" id="dept" name = "dept" placeholder="部门" value="<%=emp.getDept()%>">
                         </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-group">
                <label for="gender" class="col-sm-3 control-label">性别</label>
                <div class="col-sm-8">
                    <c:choose >
                        <c:when test="${empty emp.gender}">
                            <input type="text" class="form-control" id="gender" name = "gender" placeholder="性别" value="">
                        </c:when>

                        <c:otherwise>
                            <input type="text" class="form-control" id="gender" name = "gender" placeholder="性别" value="<%=emp.getGender()%>">
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-group">
                <label for="tel" class="col-sm-3 control-label">电话</label>
                <div class="col-sm-8">
                    <c:choose>
                        <c:when test="${empty emp.tel}">
                            <input type="text" class="form-control" id="tel" name = "tel" placeholder="电话" value="">
                        </c:when>
                        <c:otherwise>
                            <input type="text" class="form-control" id="tel" name = "tel" placeholder="电话" value="<%=emp.getTel()%>">
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-group">
                <label for="address" class="col-sm-3 control-label">地址</label>
                <div class="col-sm-8">
                    <c:choose>
                        <c:when test="${empty emp.address}">
                            <input type="text" class="form-control" id="address" name = "address" placeholder="地址" value="">
                        </c:when>
                        <c:otherwise>
                            <input type="text" class="form-control" id="address" name = "address" placeholder="地址" value="<%=emp.getAddress()%>">
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-group">
                <label for="level" class="col-sm-3 control-label">身份等级</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="level" name="admin_level" placeholder="身份等级" value="<%=s%>" readonly>
                </div>
            </div>


            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-4">
                    <button type="button" class="btn btn-primary btn-default" onclick="updateInfo()">修改信息</button>
                </div>
                <div class="col-sm-4" style="float: left;">
                    <button type="button" class="btn btn-primary btn-default" id="edit_password">修改密码</button>
                </div>
            </div>
        </form>
    </div>

    <%--修改密码--%>
    <div class="col-md-4" style="position:absolute;right: 0;top: 28%" id="edit_psw_div" hidden="hidden">
        <form class="form-horizontal" >
            <div class="form-group">
                <label for="old_password" class="col-sm-3 control-label">旧密码</label>
                <div class="col-sm-8">
                    <input type="password" class="form-control" name ="old_password" id="old_password" placeholder="请输入旧密码"/>
                </div>
            </div>
            <div class="form-group">
                <label for="new_password" class="col-sm-3 control-label" >新密码</label>
                <div class="col-sm-8">
                    <input type="password" class="form-control" id="new_password" name = "new_password" placeholder="新密码"/>
                </div>
            </div>
            <div class="form-group">
                <label for="rel_password" class="col-sm-3 control-label">确认密码</label>
                <div class="col-sm-8">
                    <input type="password" id="rel_password" name="" class="form-control"  placeholder="请确认新密码"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-4">
                    <button type="button" class="btn btn-primary btn-default" onclick="edit_password()">确认修改</button>
                </div>
                <div class="col-sm-4">
                    <button type="reset" class="btn btn-primary btn-default">重置</button>
                </div>
            </div>
        </form>

    </div>

</div>

<script type="application/javascript">

    $("#edit_password").click(function(){
        $("#edit_psw_div").toggle()
    })

    function hide_div() {
        $("#rel_password").val("");
        $("#new_password").val("");
        $("#old_password").val("");
        $("#edit_psw_div").hide();
    }

    function updateInfo() {
        $.ajax({
            url:"${pageContext.request.contextPath}/manager/updateInfo",
            type:"post",
            data:$("#admin_info").serialize(),
            success:function (data) {
                alert("修改成功");
                $("#emp_name").val(data.emp_name);
                $("#dept").val(data.dept);
                $("#gender").val(data.gender);
                $("#tel").val(data.tel);
                $("#address").val(data.address);
            }
        })
    }

    function edit_password() {
        if($("#rel_password").val()!=$("#new_password").val())
        {
            alert("密码不一致")
            $("#rel_password").value = "";
            $("#new_password").value = "";
        }
        else {
            $.ajax({
                url:"${pageContext.request.contextPath}/manager/change",
                type:"post",
                data:{new_password:$("#new_password").val(),old_password:$("#old_password").val()},
                success:function (data) {
                    if(data=="success")
                    {
                        alert("修改成功");
                        hide_div();
                    }
                    else{
                        alert("密码错误");
                    }
                }
            })
        }
    }
</script>
</body>
</html>
