<%@page import="kr.green.mybatis.MybatisApp"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="org.apache.ibatis.session.SqlSessionFactoryBuilder"%>
<%@page import="org.apache.ibatis.session.SqlSessionFactory"%>
<%@page import="org.apache.ibatis.io.Resources"%>
<%@page import="java.io.InputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	SqlSession sqlSession = null;
	try{
		sqlSession = MybatisApp.getSessionFactory().openSession();

		String today = sqlSession.selectOne("test.selectToday");
		out.println(today + "<br>");
		HashMap<String, Integer> map = new HashMap<>();
		map.put("num1", 123);
		map.put("num2", 222);
		map.put("num3", 333);
		out.print(sqlSession.selectOne("test.getSum", map) + "<br>");
		
		sqlSession.commit();
	}catch(Exception e){
		sqlSession.rollback();
		e.printStackTrace();
	}finally{
		sqlSession.close();
	}
	%>
</body>
</html>