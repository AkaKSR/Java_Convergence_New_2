<%@page import="kr.green.jdbc.DBCPUtil"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	Connection conn = DBCPUtil.getConnection();
	out.println("연결 성공: " + conn + "<br>");
	DBCPUtil.close(conn);
	%>
</body>
</html>