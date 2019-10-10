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
	${sum }<br>
	</ct:sum3>
	1부터 100까지 합은 ${sum }입니다.<br/>
</body>
</html>