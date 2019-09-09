<%@ page import="com.aboluo.attend.pojo.Emp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page isELIgnored="false"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path + "/";
    Emp emp_register = (Emp) session.getAttribute("register_emp");
%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>用户注册</title>
    <style>
        body{
           /*background-image: url(../../static/assets/imgs/timg.gif);*/
            background-position:center;
            background-attachment:fixed;
            background-size:cover;
            opacity: 0.95;
        }

    </style>
<link href="<%=basePath%>css/jl_bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>css/jl_gloab.css" rel="stylesheet">
<link href="<%=basePath%>css/jl_register.css" rel="stylesheet">
<link href="<%=basePath%>css/jl_font-awesome.min.css" rel="stylesheet">
<script src="<%=basePath%>js/jl_jquery-1.11.1.min.js"></script>
<script src="<%=basePath%>js/jl_register.js"></script>
<script src="<%=basePath%>js/jl_jquery.js"></script>
<script src="<%=basePath%>js/jl_verificationNumbers.js" tppabs="<%=basePath%>js/jl_verificationNumbers.js"></script>
<script src="<%=basePath%>js/jl_Particleground.js" tppabs="<%=basePath%>js/jl_Particleground.js"></script>
<script>
    if(<%=emp_register!=null%>){
        var a = alert("工号或用户名重复");
    }
  $(document).ready(function() {
    //验证码
    createCode();
    //测试提交，对接程序删除即可
    $(".item col-xs-12").click(function(){
      location.href="javascrpt:;"/*tpa=http://***register.jsp*/;
      });
  });
  </script>
</head>
<body class="bgf4" style=" background-image: url(<%=basePath%>imgs/timg.gif);">

<div class="login-box f-mt10 f-pb50">
    <form action="${pageContext.request.contextPath}/register" id="form1" method="post" class="login-form">
	<div class="main bgf">    
    	<div class="reg-box-pan display-inline">  
        	<div class="step">        	
                <ul>
                    <li class="col-xs-4 on">
                        <span class="num"><em class="f-r5"></em><i>1</i></span>                	
                        <span class="line_bg lbg-r"></span>
                        <p class="lbg-txt">填写账户信息</p>
                    </li>
                    <li class="col-xs-4">
                        <span class="num"><em class="f-r5"></em><i>2</i></span>
                        <span class="line_bg lbg-l"></span>
                        <span class="line_bg lbg-r"></span>
                        <p class="lbg-txt">验证、完善账户信息</p>
                    </li>
                    <li class="col-xs-4">
                        <span class="num"><em class="f-r5"></em><i>3</i></span>
                        <span class="line_bg lbg-l"></span>
                        <p class="lbg-txt">注册成功</p>
                    </li>
                </ul>
            </div>
        	<div class="reg-box" id="verifyCheck" style="margin-top:20px;">
            	<div class="part1">                	
                    <div class="item col-xs-12">
                        <span class="intelligent-label f-fl"><b class="ftx04">*</b>用户名：</span>    
                        <div class="f-fl item-ifo">
                            <input type="text" maxlength="20" class="txt03 f-r3 required" tabindex="1" data-valid="isNonEmpty||between:2-10||isUname" data-error="用户名不能为空||用户名长度2-10位||只能输入中文、字母、数字、下划线，且以中文或字母开头" value="${emp.getEmp_name()}"  name="emp_name" />                            <span class="ie8 icon-close close hide"></span>
                            <label class="icon-sucessfill blank hide"></label>
                            <label class="focus"><span>2-10位，中文、字母、数字、下划线的组合，以中文或字母开头</span></label>
                            <label class="focus valid"></label>
                        </div>
                    </div>
                     <div class="item col-xs-12">
                        <span class="intelligent-label f-fl"><b class="ftx04">*</b>工号：</span>    
                        <div class="f-fl item-ifo">
                            <input type="text" maxlength="20" class="txt03 f-r3 required" tabindex="1" data-valid="isNonEmpty||between:1-5||isInt" data-error="工号不能为空||长度1-5位||只能输入数字" name="emp_id" />                            <span class="ie8 icon-close close hide"></span>
                            <label class="icon-sucessfill blank hide"></label>
                            <label class="focus"><span>1-5位，数字的组合</span></label>
                            <label class="focus valid"></label>
                        </div>
                    </div>
                    <!-- <div class="item col-xs-12">
                        <span class="intelligent-label f-fl"><b class="ftx04">*</b>手机号：</span>    
                        <div class="f-fl item-ifo">
                            <input type="text" class="txt03 f-r3 required" keycodes="tel" tabindex="2" data-valid="isNonEmpty||between:10-11" data-error="手机号码不能为空||手机号码格式不正确" maxlength="11" id="phone" /> 
                            <span class="ie8 icon-close close hide"></span>                           
                            <label class="icon-sucessfill blank hide"></label>
                            <label class="focus">请填写11位有效的手机号码</label>
                            <label class="focus valid"></label>
                        </div>
                    </div> -->
                    <div class="item col-xs-12">
                        <span class="intelligent-label f-fl"><b class="ftx04">*</b>密码：</span>    
                        <div class="f-fl item-ifo">
                            <input type="password" id="password" maxlength="20" class="txt03 f-r3 required" tabindex="3" style="ime-mode:disabled;" onpaste="return  false" autocomplete="off" data-valid="isNonEmpty||between:6-20||level:2" data-error="密码不能为空||密码长度6-20位||该密码太简单，有被盗风险，建议字母+数字的组合" value="${emp.getPassword()}"/>
                            <span class="ie8 icon-close close hide" style="right:55px"></span>
                            <span class="showpwd" data-eye="password"></span>                        
                            <label class="icon-sucessfill blank hide"></label>
                            <label class="focus">6-20位英文（区分大小写）、数字、字符的组合</label>
                            <label class="focus valid"></label>
                            <span class="clearfix"></span>
                            <label class="strength">
                            	<span class="f-fl f-size12">安全程度：</span>
                            	<b><i>弱</i><i>中</i><i>强</i></b>
                            </label>    
                        </div>
                    </div>
                    <div class="item col-xs-12">
                        <span class="intelligent-label f-fl"><b class="ftx04">*</b>确认密码：</span>    
                        <div class="f-fl item-ifo">
                            <input type="password" maxlength="20" class="txt03 f-r3 required" tabindex="4" style="ime-mode:disabled;" onpaste="return  false" autocomplete="off" data-valid="isNonEmpty||between:6-16||isRepeat:password" data-error="密码不能为空||密码长度6-16位||两次密码输入不一致" name="password" value="${emp.getPassword()}"/>
                            <span class="ie8 icon-close close hide" style="right:55px"></span>
                            <span class="showpwd" data-eye="rePassword"></span>
                            <label class="icon-sucessfill blank hide"></label>
                            <label class="focus">请再输入一遍上面的密码</label> 
                            <label class="focus valid"></label>                          
                        </div>
                    </div>
                    <div class="item col-xs-12">
                        <span class="intelligent-label f-fl"><b class="ftx04">*</b>验证码：</span>    
                        <div class="f-fl item-ifo">
                            <input type="text" maxlength="4" class="txt03 f-r3 f-fl required" tabindex="4" style="width:167px" id="randCode" data-valid="isNonEmpty" data-error="验证码不能为空" /> 
                            <span class="ie8 icon-close close hide"></span>
                            <label class="f-size12 c-999 f-fl f-pl10">
                            	 <canvas class="J_codeimg" style="height: 35px;width: 80px;background-color: rgba(128, 122, 122, 0.31);text-align: center;padding-left: 10px;" id="myCanvas" onclick="createCode();">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas>                             
                            </label>                        
                            <label class="icon-sucessfill blank hide" style="left:380px"></label>
                            <label class="focusa" >看不清？<a href="javascript:;"  class="c-blue" onclick="createCode()">换一张</a></label>   
                            <label class="focus valid" style="left:370px"></label>                        
                        </div>
                    </div>
                   <!--  <div class="item col-xs-12" style="height:auto;margin-top: -20px;">
                       <span class="intelligent-label f-fl">&nbsp;</span>  
                       <p class="f-size14 required"  data-valid="isChecked" data-error="请先同意条款"> 
                         <input type="checkbox" style=" width: 39/75rem;height: 39/75rem;size: 15px;" checked /><a href="javascript:showoutc();" class="f-ml5">我已阅读并同意条款</a>
                       </p>                       
                       <label class="focus valid"></label> 
                   </div>  -->
                    <div class="item col-xs-12">
                        <span class="intelligent-label f-fl">&nbsp;</span>    
                        <div class="f-fl item-ifo">
                           <a href="javascript:;" class="btn btn-blue f-r3" id="btn_part1">下一步</a>                         
                        </div>
                    </div> 
                </div>
                <div class="part2" style="display:none">  
                <div class="item col-xs-12 f-mb10" style="height:auto">
                        <span class="intelligent-label f-fl"><b class="ftx04">&nbsp;</b>学号：</span>
                        <div class="f-fl item-ifo">
                            <input type="text" maxlength="20" class="txt03 f-r3 required" tabindex="1" <%--data-valid="isNonEmpty||between:10-12||isInt"--%><%-- data-error="学号不能为空||长度10-12位||只能输入数字"--%> name="stu_id" value="${emp.getStu_id()}" />                            <span class="ie8 icon-close close hide"></span>
                            <label class="icon-sucessfill blank hide"></label>
                            <label class="focus"><span>10-12位，数字的组合</span></label>
                            <label class="focus valid"></label>
                        </div>
                        </div>
            
                    <div class="item col-xs-12 f-mb10" style="height:auto">
                        <span class="intelligent-label f-fl"><b class="ftx04">*</b>手机号：</span>    
                        <div class="f-fl item-ifo c-blue">
                            <input type="text" class="txt03 f-r3 required" keycodes="tel" tabindex="2" data-valid="isNonEmpty||between:10-11||isInt" data-error="手机号码不能为空||手机号码格式不正确" maxlength="11" name="tel" value="${emp.getTel()}" />
                            <span class="ie8 icon-close close hide"></span>                           
                            <label class="icon-sucessfill blank hide"></label>
                            <label class="focus">请填写11位有效的手机号码</label>
                            <label class="focus valid"></label>
                        </div>
                    </div>
                    <div class="item col-xs-12 f-mb10" style="height:auto">
                        <span class="intelligent-label f-fl"><b class="ftx04">*</b>居住地址：</span>    
                        <div class="f-fl item-ifo c-blue">
                            <input type="text" class="txt03 f-r3 required" keycodes="tel" tabindex="2" data-valid="isNonEmpty||between:3-20||isUname" data-error="地址不能为空||只能输入中文、字母、数字、下划线，且以中文或字母开头" maxlength="21" name="address" value="${emp.getAddress()}" />
                            <label class="icon-sucessfill blank hide"></label>
                            <label class="focus"><span>中文、字母、数字、下划线的组合，以中文或字母开头</span></label>
                            <label class="focus valid"></label>
                        </div>
                    </div>
                    <div class="item col-xs-12">
                        <span class="intelligent-label f-fl">&nbsp;</span>    
                        <div class="f-fl item-ifo">
                           <a href="javascript:;" class="btn btn-blue f-r3" id="btn_part2">下一步</a>
                        </div>
                    </div> 
                </div>
                <div class="part3" style="display:none">
                    <div class="item col-xs-12">
                        <span class="intelligent-label f-fl"><b class="ftx04">*</b>所属部门：</span>    
                        <div class="f-fl item-ifo">
                             <select style="width: 110px;float: right;" name="dept">
                             <option value="宜春智能物联" style="height: 36px;">宜春智能物联</option>
                             <option value="宜春大数据">宜春大数据</option>
                             <option value="西安大数据">西安大数据</option>
                       </select>
                       <label class="icon-sucessfill blank hide"></label>
                            <label class="focus"><span>默认物联注意修改</span></label>
                            <label class="focus valid"></label>
                        </div>
                    </div>
                    <div class="item col-xs-12">
                        <span class="intelligent-label f-fl"><b class="ftx04">*</b>性别：</span>    
                        <div class="f-fl item-ifo">
                             <select style="width: 110px;float: right;" name="sex">
                             <option value="男" style="height: 36px;">男</option>
                             <option value="女">女</option>
                       </select>
                        <label class="icon-sucessfill blank hide"></label>
                            <label class="focus"><span>默认男性注意修改</span></label>
                            <label class="focus valid"></label>
                        </div>
                    </div>
                    <div class="item col-xs-12">
                        <span class="intelligent-label f-fl">&nbsp;</span>    
                        <div class="f-fl item-ifo">
                           <button type="submit" href="javascript:;" class="btn btn-blue f-r3" id="btn_part3">注册</button>
                        </div>
                    </div>
                </div>  
                <div class="part4 text-center" style="display:none">
                	<h3>已注册成功</h3>
                    <p class="c-666 f-mt30 f-mb50">页面将在 <strong id="times" class="f-size18">3</strong> 秒钟后，跳转到 <a href="${pageContext.request.contextPath}/#" class="c-blue">登录页面</a></p>
                </div>          
            </div>
        </div>
    </div>
</form>
</div>

<div class="m-sPopBg" style="z-index:998;"></div>
<div class="m-sPopCon regcon">
	<div class="m-sPopTitle"><strong>服务协议条款</strong><b id="sPopClose" class="m-sPopClose" onClick="closeClause()">×</b></div>
    <div class="apply_up_content">
    	<pre class="f-r0">
		<strong>同意以下服务条款，提交注册信息</strong>
        </pre>
    </div>
    <center><a class="btn btn-blue btn-lg f-size12 b-b0 b-l0 b-t0 b-r0 f-pl50 f-pr50 f-r3" href="javascript:closeClause();">已阅读并同意此条款</a></center>
</div>
<script>
$(function(){	
	//第一页的确定按钮
	$("#btn_part1").click(function(){						
		if(!verifyCheck._click()) return;
		$(".part1").hide();
		$(".part2").show();
		$(".step li").eq(1).addClass("on");
	});
	//第二页的确定按钮
	$("#btn_part2").click(function(){
		$(".part2").hide();
		$(".part3").show();	
	});	
	//第三页的确定按钮
	$("#btn_part3").click(function(){			
		if(!verifyCheck._click()) return;
		$(".part3").hide();
		$(".part4").show();
		$(".step li").eq(2).addClass("on");
		countdown({
			maxTime:3,
			ing:function(c){
				$("#times").text(c);
			},
			after:function(){
			    $("form1").submit();
				window.location.href="${pageContext.request.contextPath}/#";
			}
		});		
	});	
});
function showoutc(){$(".m-sPopBg,.m-sPopCon").show();}
function closeClause(){
	$(".m-sPopBg,.m-sPopCon").hide();		
}
</script>
</body>
</html>
