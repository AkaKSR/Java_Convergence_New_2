<%@ tag language="java" pageEncoding="UTF-8"%>
<%-- EL이나 액션태그를 일반 문자열로 처리해라 --%>
<%@ tag body-content="tagdependent" %>
<%-- 소스 보기시 상단의 빈줄을 없애라!! --%>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 몸체의 내용을 bodyText란 변수에 저장 --%>
<jsp:doBody var="bodyText" />
<%-- 출력 --%>
<c:out value="${bodyText }" />