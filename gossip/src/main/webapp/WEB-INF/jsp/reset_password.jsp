<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>重设密码</title>
</head>
<body>
	<h1>重设密码</h1>
	
	<c:if test="${requestScope.errors != null}">
		<ul style='color: rgb(255, 0, 0);'>
		<c:forEach var="error" items="${requestScope.errors}">
			<li>${error}</li>
		</c:forEach>
		</ul>
	</c:if>
	
	<form method='post' action='reset_password'>
		<input type='hidden' name='name' value='${requestScope.acct.name}'>
		<input type='hidden' name='email' value='${requestScope.acct.email}'>
		<input type='hidden' name='token' value='${sessionScope.token}'>
		<table>
			<tr>
				<td>密码（8 到 16 字元）：</td>
				<td><input type='password' name='password' size='25' maxlength='16'></td>
			</tr>
			<tr>
				<td>确认密码：</td>
				<td><input type='password' name='password2' size='25' maxlength='16'></td>
			</tr>
			<tr>
				<td colspan='2' align='center'>
					<input type='submit' value='确定'>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>