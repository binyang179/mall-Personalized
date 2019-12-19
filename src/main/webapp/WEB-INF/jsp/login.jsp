<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>登录</title>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
</head>
<body>
	<form action="${pageContext.request.contextPath}/user/login" method="post">
		<label>账号：</label> <input type="text" id="txtUsername" name="username"
			placeholder="请输入账号" /><br /> <label>密码：</label> <input
			type="password" id="txtPassword" name="password" placeholder="请输入密码" /><br />
		<input type="submit" value="提交" /> <input type="reset" value="重置" />
		
	</form>
</body>
</html>