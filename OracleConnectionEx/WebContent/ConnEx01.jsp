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
			// 1. 드라이버를 로드한다.
			Class.forName("oracle.jdbc.OracleDriver");
			// 2. 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "jspuser", "123456");
			out.println("연결 성공: " + conn + "<br>");
			conn.setAutoCommit(false); // 자동커밋 취소
			// ============================================= //
			// 3. 사용
			String sql = "select SYSDATE from dual";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				out.println("현재 시간 : " + rs.getString(1) + "<br>");
			}
			// ============================================= //
			conn.commit(); // 에러 없으면 DB에 적용
		} catch(Exception e) {
			try {
				if(conn!=null) {
					conn.rollback(); // 에러 발생시 취소
				}
			} catch(Exception e1) {
				;
			}
		} finally {
			// 4. 닫기
			try {
				if(rs!=null) rs.close();
			} catch(Exception e) {;}
			try {
				if(stmt!=null) stmt.close();
			} catch(Exception e) {;}
			try {
				if(conn!=null) conn.close();
			} catch(Exception e) {;}
		}
	%>
</body>
</html>