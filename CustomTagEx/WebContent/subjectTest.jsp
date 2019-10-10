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
	<% request.setAttribute("content", "123456789012345678901234567890"); %>
	<ct:subject content="${content }" /><br>
	<ct:subject content="${content }" trim="false"/><br>
	<ct:subject content="${content }" trim="true"/><br>
	<ct:subject content="${content }" length="20"/><br>
	<ct:subject content="${content }" length="20" trail="..."/><br>
</body>
</html>