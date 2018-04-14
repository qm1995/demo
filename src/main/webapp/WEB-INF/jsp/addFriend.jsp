<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/login/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/login/css/camera.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/login/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/login/matrix-login.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/login/font-awesome.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/static/login/js/jquery-1.5.1.min.js"></script>
<title>添加好友</title>
<script type="text/javascript">
</script>
</head>
<body>
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
							</span><input type="text" name="loginname" style="margin-top: 3px;" id="loginname" value="" placeholder="请输入所查找的用户名" />
						</div>
					</div>
				</div>
				<div class="form-actions">
					<div style="width:86%;padding-left:8%;">
						<span class="pull-center"><a onclick="findByUsername();" class="flip-link btn btn-info" id="to-recover">查找</a></span>
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
	<div style="display: none" id="windows3">
		<center><span>查找的结果如下</span></center>
		<div>
		<a href="#" style="font-size: 10px;">
			<span>朱晓明</span></a>
			<a href="#"><span>添加</span></a>
			</div>
	</div>
	<script type="text/javascript">
			function findByUsername(){
				$("#windows1").css("display","none");
				$("#windows3").css("display","block");
			}
	</script>
</body>
</html>