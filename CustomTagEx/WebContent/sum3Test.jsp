<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="ct" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ct:sum3 end="100" begin="1">
	</ct:sum3><br>
		1부터 100까지의 합은 ${sum } 입니다.
</body>
</html>