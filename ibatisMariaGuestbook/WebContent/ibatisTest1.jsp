<%@page import="kr.green.ibatis.IbatisApp"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.ibatis.sqlmap.client.SqlMapClientBuilder"%>
<%@page import="com.ibatis.sqlmap.client.SqlMapClient"%>
<%@page import="com.ibatis.common.resources.Resources"%>
<%@page import="java.io.Reader"%>
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
	SqlMapClient sqlMap = IbatisApp.getSqlMapClient();
	out.println("연결 : " + sqlMap + "<br>");
	%>
	
	현재 날짜 : <%=sqlMap.queryForObject("test.getToday", "") %><br>
</body>
</html>