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
	색상선택 : 
	<select name="color">
		<option value="red">빨강</option>
		<option value="green">초록</option>
		<option value="blue">파랑</option>
	</select><br>
	<ct:dynamic name="color" red="빨강" green="초록" blue='파랑'></ct:dynamic>
	<ct:dynamic name="month" m1="1월" m2="2월" m3="3월" m4="4월" m5="5월"></ct:dynamic>
</body>
</html>