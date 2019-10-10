<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag trimDirectiveWhitespaces="true" body-content="empty"%>
<%@ attribute name="begin" required="true" type="java.lang.Integer"%>
<%@ attribute name="end" required="true" type="java.lang.Integer"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- sum이란 EL변수를 추가한다. 타입은 Integer이고 태그 종료 후에만 사용 가능!! --%>
<%@ variable name-given="sum" variable-class="java.lang.Long" scope="AT_END"%>
<%--  변수 만들어 0으로 초기화 --%>
<c:set var="sum" value="${0}"/>

<%--  begin ~ end 까지의 합 구하기 --%>
<c:forEach var="i" begin="${begin }" end="${end }">
	<c:set var="sum" value="${sum + i}"/>
</c:forEach>
