<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<%@page import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>错误</title>
</head>
<body>
	<h1>网页发生错误：</h1><%= exception %>
	<h2>显示例外堆叠追踪：</h2>
<%
	exception.printStackTrace(new PrintWriter(out));
%>
</body>
</html>