<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="tFileInfo" class="cc.openhome.TFilesInfo"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Metadata</title>
</head>
<body>
	<table style="text-align: left;" border="1" cellpadding="2" cellspacing="2">
		<tbody>
			<tr>
				<td>栏位名称</td>
				<td>栏位形态</td>
				<td>可否为空</td>
				<td>预设数值</td>
			</tr>
			<c:forEach var="columnInfo" items="${tFileInfo.allColumnInfo}">
				<tr>
					<td>${columnInfo.name}</td>
					<td>${columnInfo.type}</td>
					<td>${columnInfo.nullable}</td>
					<td>${columnInfo.def}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>