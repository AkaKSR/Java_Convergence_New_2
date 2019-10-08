<%@page import="kr.green.memo.service.MemoService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include.jsp" %>
<%
	if(!request.getMethod().equals("POST")){
		response.sendRedirect("list.jsp");
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/comm.js"></script>
</head>
<body>
	<jsp:useBean id="vo" class="kr.green.memo.vo.MemoVO"/>
	<jsp:setProperty property="*" name="vo"/>
	<jsp:setProperty property="ip" name="vo" value="${pageContext.request.remoteAddr }"/>
	<%
		MemoService.getInstance().delete(vo);	
	%>
	<script type="text/javascript">
		sendPost("list.jsp",{"p":${currentPage},"s":${pageSize},"b":${blockSize}});
	</script>
</body>
</html>