<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登入页面</title>
</head>
<body>
	<c:if test="${param.name == 'momor' && param.password == '12345678'}">
		<h1>${param.name} 登入成功</h1>
	</c:if>
</body>
</html>