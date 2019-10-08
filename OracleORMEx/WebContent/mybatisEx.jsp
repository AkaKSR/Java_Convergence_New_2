<%@page import="java.util.HashMap"%>
<%@page import="kr.green.mybatis.MybatisApp"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
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
	SqlSession sqlSession = null;
	try {
		// Auto Commit 취소
		sqlSession = MybatisApp.getSessionFactory().openSession(false);
		// ======================================================= //
		out.println("연결: " + sqlSession + "<br>");
		out.println("날짜: " + sqlSession.selectOne("test.selectToday") + "<br>");
		HashMap<String,Integer> map = new HashMap<>();
		map.put("num1", 123);
		map.put("num2", 123);
		map.put("num3", 123);
		out.println("합계: " + sqlSession.selectOne("test.getSum", map) + "<br>");
		// ======================================================= //
		sqlSession.commit();
	} catch(Exception e) {
		sqlSession.rollback();
		e.printStackTrace();
	} finally {
		if(sqlSession!=null) sqlSession.close();
	}
%>
</body>
</html>