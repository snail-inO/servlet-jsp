<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
	String name = "caterpillar";
	String password = "12345678";
	
	boolean checkUser(String name, String password) {
		return this.name.equals(name) && this.password.equals(password);
	}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>登入页面</title>
	</head>
	<body>
<%
	String name = request.getParameter("name");
	String password = request.getParameter("password");
	
	if (checkUser(name, password)) {
%>
		<h1><%= name %> 登入成功</h1>
<%
	} else {
%>
		<h1>登入失败</h1>
<%
	}
%>
	</body>
</html>