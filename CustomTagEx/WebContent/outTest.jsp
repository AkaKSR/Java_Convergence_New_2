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
	<jsp:useBean id="today" class="java.util.Date" scope="request"/>
	<ct:out>
		<jsp:body><u>현재시간</u>은 <b>${today }</b>입니다.</jsp:body>
	</ct:out>
</body>
</html>