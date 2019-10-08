<%@page import="com.ibatis.sqlmap.client.SqlMapClient"%>
<%@page import="kr.green.ibatis.IbatisApp"%>
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
	SqlMapClient sqlMap = null;
	try {
		sqlMap = IbatisApp.getSqlMapClient();
		sqlMap.startTransaction();
		// ============================================== //
		out.println(sqlMap.queryForObject("test.getToday", "") + "<br>");
		// ============================================== //
		sqlMap.commitTransaction();		
	} finally {
		sqlMap.endTransaction();
	}
%>
</body>
</html>