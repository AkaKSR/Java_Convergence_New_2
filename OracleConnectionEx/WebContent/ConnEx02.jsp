<%@page import="kr.green.jdbc.JdbcUtil"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
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
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			out.println("연결 성공: " + conn + "<br>");
			conn.setAutoCommit(false); // 자동커밋 취소
			// ============================================= //
			// 3. 사용
			String sql = "select SYSDATE from dual";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				out.println("현재 시간 : " + rs.getString(1) + "<br>");
			}
			// ============================================= //
			conn.commit(); // 에러 없으면 DB에 적용
		} catch (Exception e) {
			JdbcUtil.rollback(conn);
		} finally {
			// 4. 닫기
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(conn);
		}
	%>
</body>
</html>