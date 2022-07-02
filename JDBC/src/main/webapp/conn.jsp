<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="db" class="cc.openhome.DbBean"/>
<c:set target="${db}" property="jdbcUri" value="jdbc:h2:tcp://localhost/F:/h2/test"/>
<c:set target="${db}" property="username" value="root"/>
<c:set target="${db}" property='password' value="123"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>测试资料库连线</title>
</head>
<body>
	<c:choose>
		<c:when test="${db.connectedOK}">连线成功！</c:when>
		<c:otherwise>连线失败！</c:otherwise>
	</c:choose>
</body>
</html>