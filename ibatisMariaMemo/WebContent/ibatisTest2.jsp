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
	<%
		HashMap<String,Integer> map = new HashMap<>();
		map.put("num1", 33);
		map.put("num2", 33);
		map.put("num3", 33);
		
		int sum = (int)sqlMap.queryForObject("test.sum", map);
		out.println("합계 : " + sum + "<br>");
	%>
	
	memo 개수 : <%=(int)sqlMap.queryForObject("test.selectCount", "") + "개<br>" %>
</body>
</html>