<%@page import="java.time.LocalDateTime"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Page 指挥元素</title>
	</head>
	<body>
		<h1>现在时间：<%= LocalDateTime.now() %></h1>
	</body>
</html>