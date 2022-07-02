<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<jsp:useBean id="user" class="cc.openhome.User"/>
<jsp:setProperty name="user" property="*"/>
<html>
<head>
<meta charset="UTF-8">
<title>登入页面</title>
</head>
<body>
	<c:choose>
		<c:when test="${user.valid}">
			<h1>
				${user.name}登入成功
			</h1>
		</c:when>
		<c:otherwise>
			<h1>登入失败</h1>
		</c:otherwise>
	</c:choose>
</body>
</html>