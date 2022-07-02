<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="util" uri="https://openhome.cc/util"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>自定EL函数</title>
</head>
<body>
	${ util:length([1, 2, 3, 5, 1]) }
</body>
</html>