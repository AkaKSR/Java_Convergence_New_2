<%@page import="kr.green.board.service.CommentService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/comm.js"></script>
</head>
<body>
	<%
		if( ! request.getMethod().equals("POST")){
			// POST전송이 아니라면 누군가가 이 파일을 직접 실행한 것이다. 
			// 장난을 치고있구나.... 아무짓도 안하고  list로 보내버리자!!
			response.sendRedirect("list.jsp");
			return;
		}
	%>
	<jsp:useBean id="vo" class="kr.green.board.vo.CommentVO" scope="request"/>
	<jsp:setProperty property="*" name="vo"/>
	<%
		CommentService.getInstance().update(vo);
	%>
	<script type="text/javascript">
		sendPost("view.jsp", {"p":${currentPage},"s":${pageSize},"b":${blockSize},"idx":${vo.ref},"m":0});
	</script>
</body>
</html>