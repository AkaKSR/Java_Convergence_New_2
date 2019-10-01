<%@page import="kr.green.ibatis.service.TestService"%>
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
	memo 개수 : <%=TestService.getInstance().selectCount() + "개<br>" %>
</body>
</html>