<%@page import="kr.green.service.GuestbookService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- vo 객체 생성 --%>
	<jsp:useBean id="vo" class="kr.green.vo.GuestbookVO"></jsp:useBean>
	<%-- vo 안의 모든 setter를 불러 채운다 --%>
	<jsp:setProperty property="*" name="vo"/>
	<%-- ip를 불러오기 --%>
	<jsp:setProperty property="ip" name="vo" value="${pageContext.request.remoteAddr }"/>
	<%
		// 서비스 클래스를 불러와 저장을 한다.
		int count = GuestbookService.getInstance().insert(vo);
		request.setAttribute("count", count);
	%>
	<c:if test="${count==0 }">
		<script type="text/javascript">
			alert('저장실패');
			location.href="list.jsp?p=1&s=" + ${pageSize } + "&b=" + ${blockSize };
		</script>
	</c:if>
	<c:if test="${count!=0 }">
		<script type="text/javascript">
			alert('저장성공');
			location.href="list.jsp?p=1&s=" + ${pageSize } + "&b=" + ${blockSize };
		</script>
	</c:if>
</body>
</html>