<%@tag import="java.util.Date"%>
<%@tag import="java.text.SimpleDateFormat"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag body-content="empty" %>
<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일(E)");
	out.println(sdf.format(new Date()));
%>