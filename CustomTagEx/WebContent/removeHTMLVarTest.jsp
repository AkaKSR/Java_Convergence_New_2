<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="ct" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="today" class="java.util.Date"/>
	<ct:removeHTMLVar var="remove">
		<h1>현재 <b>시간은</b> <u>${today }</u>입니다.</h1>
	</ct:removeHTMLVar>
	${remove }
</body>
</html>