<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>用户登录</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/static/login/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/login/css/camera.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/login/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/login/matrix-login.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/login/font-awesome.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/static/login/js/jquery-1.5.1.min.js"></script>
<!-- 软键盘控件start -->
<link href="${pageContext.request.contextPath}/static/login/keypad/css/framework/form.css" rel="stylesheet" type="text/css"/>
<!-- 软键盘控件end -->
 <style type="text/css">
    /*
   body{
    -webkit-transform: rotate(-3deg);
    -moz-transform: rotate(-3deg);
    -o-transform: rotate(-3deg);
	padding-top:20px;
    }
    */
      .cavs{
    	z-index:1;
    	position: fixed;
    	width:95%;
    	margin-left: 20px;
    	margin-right: 20px;
    }
  </style>
  <script>
  		//window.setTimeout(showfh,3000); 
  		var timer;
		function showfh(){
			fhi = 1;
			//关闭提示晃动屏幕，注释掉这句话即可
			//timer = setInterval(xzfh2, 10); 
		};
		var current = 0;
		function xzfh(){
			current = (current)%360;
			document.body.style.transform = 'rotate('+current+'deg)';
			current ++;
			if(current>360){current = 0;}
		};
		var fhi = 1;
		var current2 = 1;
		function xzfh2(){
			if(fhi>50){
				document.body.style.transform = 'rotate(0deg)';
				clearInterval(timer);
				return;
			}
			current = (current2)%360;
			document.body.style.transform = 'rotate('+current+'deg)';
			current ++;
			if(current2 == 1){current2 = -1;}else{current2 = 1;}
			fhi++;
		};
	</script>
</head>
<body>

	<!--小键盘承载器-->
	<canvas class="cavs"></canvas>
	<div style="width:100%;text-align: center;margin: 0 auto;position: absolute;">
		<!-- 登录 -->
		<div id="windows1">
		<div id="loginbox" >
			<form action="" method="post" name="loginForm" id="loginForm">
				<div class="control-group normal_text">
					<h3>
						<img src="${pageContext.request.contextPath}/static/login/logo.png" alt="Logo" />
					</h3>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_lg">
							<i><img height="37" src="${pageContext.request.contextPath}/static/login/user.png" /></i>
							</span><input type="text" name="loginname" id="loginname" value="" placeholder="请输入用户名" />
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_ly">
							<i><img height="37" src="${pageContext.request.contextPath}/static/login/suo.png" /></i>
							</span><input type="password" name="password" id="password" placeholder="请输入密码" value=""/>
						</div>
					</div>
				</div>
				<div style="float:right;padding-right:10%;">
					<div style="float: left;margin-top:3px;margin-right:2px;">
						<font color="white">记住密码</font>
					</div>
					<div style="float: left;">
						<input name="form-field-checkbox" id="saveid" type="checkbox"
							onclick="savePaw();" style="padding-top:0px;" />
					</div>
				</div>
				<div class="form-actions">
					<div style="width:86%;padding-left:8%;">

						<div style="float: left;padding-top:2px;">
							<i><img src="${pageContext.request.contextPath}/static/login/yan.png" /></i>
						</div>
						<div style="float: left;" class="codediv">
							<input type="text" name="code" id="code" class="login_code"
								style="height:16px; padding-top:4px;" />
						</div>
						<div style="float: left;">
							<i><img style="height:26px;" id="codeImg" alt="点击更换" title="点击更换" src="" /></i>
						</div>
						<c:if test="${pd.isZhuce == 'yes' }">
						<span class="pull-right" style="padding-right:3%;"><a href="javascript:changepage(1);" class="btn btn-success">注册</a></span>
						</c:if>
						<span class="pull-right"><a onclick="severCheck();" class="flip-link btn btn-info" id="to-recover">登录</a></span>
					</div>
				</div>
			</form>
			<div class="controls">
				<div class="main_input_box">
					<font color="white"><span id="nameerr">Copyright © XX科技 2018</span></font>
				</div>
			</div>
		</div>
		</div>
		<!-- 注册 -->
		<div id="windows2" style="display: none;">
		<div id="loginbox">
			<form action="" method="post" name="loginForm" id="loginForm">
				<div class="control-group normal_text">
					<h3>
						<img src="${pageContext.request.contextPath}/static/login/logo.png" alt="Logo" />
					</h3>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_lg">
							<i>用户</i>
							</span><input type="text" name="username" id="USERNAME" value="" placeholder="请输入用户名" required/>
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_ly">
							<i>密码</i>
							</span><input type="password" name="password" id="PASSWORD" placeholder="请输入密码" required value=""/>
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_ly">
							<i>重输</i>
							</span><input type="password" name="chkpwd" id="chkpwd" placeholder="请重新输入密码" value="" required/>
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_lg">
								<i>性别</i>
							</span>
							<select id="SEX" name="sex" style="width: 303px;height: 39px;margin-left: -3px;margin-top: 5px;">
								<option value="1">男</option>
								<option value="2">女</option>
							</select>
							<!-- <input type="text" name="sex" id="SEX" value="" placeholder="请输入性别" required/> -->
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_lg">
							<i>年龄</i>
							</span><input type="text" name="age" id="AGE" value="" placeholder="请输入年龄" required/>
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_lg">
							<i>爱好</i>
							</span><input type="text" name="hobby" id="HOBBY" value="" placeholder="您感兴趣的事" />
						</div>
					</div>
				</div>
				<div class="form-actions">
					<div style="width:86%;padding-left:8%;">

						<div style="float: left;padding-top:2px;">
							<i><img src="${pageContext.request.contextPath}/static/login/yan.png" /></i>
						</div>
						<div style="float: left;" class="codediv">
							<input type="text" name="rcode" id="rcode" class="login_code"
								style="height:16px; padding-top:4px;" />
						</div>
						<div style="float: left;">
							<i><img style="height:26px;" id="zcodeImg" alt="点击更换" title="点击更换" src=""/></i>
						</div>
						<span class="pull-right" style="padding-right:3%;"><a href="javascript:changepage(2);" class="btn btn-success">取消</a></span>
						<span class="pull-right"><a onclick="register();" class="flip-link btn btn-info" id="to-recover">提交</a></span>
					</div>
				</div>
			</form>
			<div class="controls">
				<div class="main_input_box">
					<font color="white"><span id="nameerr">Copyright © XX科技 2018</span></font>
				</div>
			</div>
		</div>
		</div>
		
	</div>
	<div id="templatemo_banner_slide" class="container_wapper">
		<div class="camera_wrap camera_emboss" id="camera_slide">
			<!-- 背景图片 -->
			<div data-src="${pageContext.request.contextPath}/static/login/images/banner_slide_01.jpg"></div>
			<div data-src="${pageContext.request.contextPath}/static/login/images/banner_slide_02.jpg"></div>
			<div data-src="${pageContext.request.contextPath}/static/login/images/banner_slide_03.jpg"></div>
			<div data-src="${pageContext.request.contextPath}/static/login/images/banner_slide_04.jpg"></div>
			<div data-src="${pageContext.request.contextPath}/static/login/images/banner_slide_05.jpg"></div>
		</div>
		<!-- #camera_wrap_3 -->
	</div>

	<script type="text/javascript">
	
		//验证码
		
		//服务器校验
		function severCheck(){
			changeCode1();
			if(check()){
				var loginname = $("#loginname").val();
				var password = $("#password").val();
				var code = loginname+","+password+","+$("#code").val();
				$.ajax({
					type: "post",
					url: '/demo/login.action',
			    	data: {
			    		username:loginname,
			    		password:password
			    	},
					cache: false,
					success: function(data){
						if(200 == data.statusCode){
							saveCookie();
							//alert("登陆成功")
							window.location.href="/demo/user/home.html";
						}else if(400 == data.statusCode){
							$("#loginname").tips({
								side : 1,
								msg : "用户名或密码有误",
								bg : '#FF5080',
								time : 15
							});
							showfh();
							$("#loginname").focus();
						}else if("codeerror" == data.result){
							$("#code").tips({
								side : 1,
								msg : "验证码输入有误",
								bg : '#FF5080',
								time : 15
							});
							showfh();
							$("#code").focus();
						}else{
							$("#loginname").tips({
								side : 1,
								msg : "缺少参数",
								bg : '#FF5080',
								time : 15
							});
							showfh();
							$("#loginname").focus();
						}
					}
				});
			}
		}
	
		$(document).ready(function() {
			changeCode1();
			$("#codeImg").bind("click", changeCode1);
			$("#zcodeImg").bind("click", changeCode2);
		});

		$(document).keyup(function(event) {
			if (event.keyCode == 13) {
				$("#to-recover").trigger("click");
			}
		});

		function genTimestamp() {
			var time = new Date();
			return time.getTime();
		}

		function changeCode1() {
			$("#codeImg").attr("src", "${pageContext.request.contextPath}/login/getCodeImage.html?t=" + genTimestamp());
		}
		function changeCode2() {
			$("#zcodeImg").attr("src", "${pageContext.request.contextPath}/login/getCodeImage.html?t=" + genTimestamp());
		}

		//客户端校验
		function check() {

			if ($("#loginname").val() == "") {
				$("#loginname").tips({
					side : 2,
					msg : '用户名不得为空',
					bg : '#AE81FF',
					time : 3
				});
				showfh();
				$("#loginname").focus();
				return false;
			} else {
				$("#loginname").val(jQuery.trim($('#loginname').val()));
			}
			if ($("#password").val() == "") {
				$("#password").tips({
					side : 2,
					msg : '密码不得为空',
					bg : '#AE81FF',
					time : 3
				});
				showfh();
				$("#password").focus();
				return false;
			}
			/* if ($("#code").val() == "") {
				$("#code").tips({
					side : 1,
					msg : '验证码不得为空',
					bg : '#AE81FF',
					time : 3
				});
				showfh();
				$("#code").focus();
				return true;
			} */
			$("#loginbox").tips({
				side : 1,
				msg : '正在登录 , 请稍后 ...',
				bg : '#68B500',
				time : 10
			});

			return true;
		}

		function savePaw() {
			if (!$("#saveid").attr("checked")) {
				$.cookie('loginname', '', {
					expires : -1
				});
				$.cookie('password', '', {
					expires : -1
				});
				$("#loginname").val('');
				$("#password").val('');
			}
		}

		function saveCookie() {
			if ($("#saveid").attr("checked")) {
				$.cookie('loginname', $("#loginname").val(), {
					expires : 7
				});
				$.cookie('password', $("#password").val(), {
					expires : 7
				});
			}
		}
		
		jQuery(function() {
			var loginname = $.cookie('loginname');
			var password = $.cookie('password');
			if (typeof(loginname) != "undefined"
					&& typeof(password) != "undefined") {
				$("#loginname").val(loginname);
				$("#password").val(password);
				$("#saveid").attr("checked", true);
				$("#code").focus();
			}
		});
		
		//登录注册页面切换
		function changepage(value) {
			if(value == 1){
				$("#windows1").hide();
				$("#windows2").show();
				$("#windows2 input").each(function(){
					$(this).val('');
				});
				changeCode2();
			}else{
				$("#windows2").hide();
				$("#windows1").show();
				$("#windows1 input").each(function(){
					$(this).val('');
				});
				changeCode1();
			}
		}
		
	//注册
	function rcheck(){
		changeCode2();
		if($("#USERNAME").val()==""){
			$("#USERNAME").tips({
				side:3,
	            msg:'输入用户名',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#USERNAME").focus();
			$("#USERNAME").val('');
			return false;
		}else{
			$("#USERNAME").val(jQuery.trim($('#USERNAME').val()));
		}
		if($("#PASSWORD").val()==""){
			$("#PASSWORD").tips({
				side:3,
	            msg:'输入密码',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PASSWORD").focus();
			return false;
		}
		if($("#PASSWORD").val()!=$("#chkpwd").val()){
			$("#chkpwd").tips({
				side:3,
	            msg:'两次密码不相同',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#chkpwd").focus();
			return false;
		}
		/* if($("#AGE").val()==""){
			$("#AGE").tips({
				side:3,
	            msg:'输入姓名',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#name").focus();
			return false;
		} */
		if($("#AGE").val()==""){
			$("#AGE").tips({
				side:3,
	            msg:'输入年龄',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#AGE").focus();
			return false;
		}else if(!isNumber($("#AGE").val())){
			$("#AGE").tips({
				side:3,
	            msg:'年龄格式不正确',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#AGE").focus();
			return false;
		}
		/* if ($("#rcode").val() == "") {
			$("#rcode").tips({
				side : 1,
				msg : '验证码不得为空',
				bg : '#AE81FF',
				time : 3
			});
			$("#rcode").focus();
			return false;
		} */
		return true;
	}
	
	//提交注册
	function register(){
		if(rcheck()){
			$.ajax({
				type: "post",
				url: '/demo/register.action',
		    	data: {
		    		  username:$("#USERNAME").val(),
		    		  password:$("#PASSWORD").val(),
		    		  sex:$("#SEX").val(),
		    		  age:$("#AGE").val()
		        },
				cache: false,
				success: function(data){
					if(200 == data.statusCode){
						$("#windows2").hide();
						$("#windows1").show();
						$("#loginbox").tips({
							side : 1,
							msg : '注册成功,请登录',
							bg : '#68B500',
							time : 3
						});
						changeCode1();
					}else if(400 == data.statusCode){
						$("#USERNAME").tips({
							side : 1,
							msg : "注册失败",
							bg : '#FF5080',
							time : 15
						});
						showfh();
						$("#USERNAME").focus();
					}else if("06" == data.result){
						$("#rcode").tips({
							side : 1,
							msg : "验证码输入有误",
							bg : '#FF5080',
							time : 15
						});
						showfh();
						$("#rcode").focus();
					}
				}
			});
		}
	}
	
	//年龄校验
	function isNumber(number){
		return(new RegExp(/^[1-9][0-9]$/).test(number));
	}
	//js  日期格式
	</script>
	<script src="${pageContext.request.contextPath}/static/login/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
	<script src="${pageContext.request.contextPath}/static/login/js/jquery.easing.1.3.js"></script>
	<script src="${pageContext.request.contextPath}/static/login/js/jquery.mobile.customized.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/login/js/camera.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/login/js/templatemo_script.js"></script>
	<script src="${pageContext.request.contextPath}/static/login/js/ban.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jQuery.md5.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.tips.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.cookie.js"></script>
	
	<!-- 软键盘控件start -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/login/keypad/js/form/keypad.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/login/keypad/js/framework.js"></script>
	<!-- 软键盘控件end -->
</body>

</html>