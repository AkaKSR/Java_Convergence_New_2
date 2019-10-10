<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag body-content="scriptless"%>
<%-- 태그 안의 내용을 content 변수에 저장 --%>
<jsp:doBody var="content" scope="page" />
<%
	String content = (String)jspContext.getAttribute("content");
	// 정규표현식을 이용한 태그제거하기
	content = content.replaceAll("<(/)?([a-zA-Z0-9]*)(\\s[a-zA-Z0-9]*=[^>]*)?>", "");
	out.println(content);
%>
<br>
<jsp:doBody/>