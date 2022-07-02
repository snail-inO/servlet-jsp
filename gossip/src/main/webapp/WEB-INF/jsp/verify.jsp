<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>启用账号</title>
</head>
<body>
	<c:choose>
		<c:when test="${requestScope.acct.present}">
			<h1>账号启用成功</h1>
		</c:when>
		<c:otherwise><h1>账号启用失败</h1></c:otherwise>
	</c:choose>
	<a href='/gossip'>回首页</a>
</body>
</html>