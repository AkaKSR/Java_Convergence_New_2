<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    var 속성 필수, rtexprvalue는 거짓이어야 한다.
	rtexprvalue는 속성 값으로 표현식 사용여부 지정 기본값 true
--%>
<%@ attribute name="var" type="java.lang.String" required="true" rtexprvalue="false"%>
<%-- var의 값으로 넘온 값을 변수로 사용하겠다. 여기서는 그 값의 별칭을  result로 사용한다. --%>
<%@ variable name-from-attribute="var" alias="result" 
             variable-class="java.lang.String" scope="AT_END"%>
<jsp:doBody var="content" scope="page" />
<%
	// 페이지 영역에서 값 얻기
	String content = (String)jspContext.getAttribute("content");
	// 정규표현식을 이용한 태그제거하기
	content = content.replaceAll("<(/)?([a-zA-Z0-9]*)(\\s[a-zA-Z0-9]*=[^>]*)?>","");
%>
<%-- result 변수에 값 저장 --%>
<c:set var="result" value="<%=content %>"/>
