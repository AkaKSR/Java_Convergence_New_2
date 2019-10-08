<%@page import="kr.green.file.service.FileBoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/comm.js"></script>
</head>
<body>
	<jsp:useBean id="vo" class="kr.green.file.vo.FileBoardVO" />
	<jsp:setProperty property="*" name="vo" />
	<%
		// 서비스의 삭제를 부른다.
		FileBoardService.getInstance().delete(vo);
	%>
	<%-- list.jsp로 이동한다. --%>
	<script type="text/javascript">
	sendPost("list.jsp", {"p":${currentPage},"s":${pageSize},"b":${blockSize}});
</script>
</body>
</html>