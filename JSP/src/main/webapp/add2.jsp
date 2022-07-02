<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>加法网页</title>
</head>
<body>
	${param.a} + ${param.b} = ${param.a + param.b}
</body>
</html>