<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<jsp:useBean id="messageService" class="cc.openhome.MessageService"/>
<html>
<head>
<meta charset="UTF-8">
<title>留言板</title>
</head>
<body>
	<table style="text-align: left; width: 100%;" border="1">
		<tr>
			<td>名称</td><td>讯息</td>
		</tr>
		<c:forEach var="message" items="${messageService.messages}">
			<tr>
				<td>${message.name}</td><td>${message.text}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>