<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user.js"></script>
<title>注册页面</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/register.html" onsubmit="return check1();" method="post">
		用户名：<input type="text" name="username" id="username"/><br/>
		密  &nbsp;码：<input type="password" name="password" id="password"/><br/>
		确认密 码：<input type="password" name="rePassword" id="rePassword"/><br/>
		年龄：<input type="text" name="age" id="age"/><br/>
		性别:<input type="text" name="sex" id="sex"/><br/>
		爱好：<textarea rows="5" cols="10" name="hobby" id="hobby"></textarea><br/>
		<input type="submit" value="注册"/><input type="reset" value="重置"/><br/>
	</form>
	<a href="${pageContext.request.contextPath}/page/index.html">去登录</a>
	
	<br/><br/>
	<span>${msg}</span>
	<script type="text/javascript">
		function check1(){
			var username = $("#username").val();debugger
			var password = $("#password").val();
			var rePassword = $("#rePassword").val();
			var age = $("#age").val();
			var sex = $("#sex").val();
			var hobby = $("#hobby").val();
			if(password == "" || password != rePassword){
				return false;
			}
			if(username == "" || age == "" || sex == ""){
				return false;
			}
			return true;
		}
	</script>
</body>
</html>