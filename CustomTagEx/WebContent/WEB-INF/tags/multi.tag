<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag trimDirectiveWhitespaces="true"%>
<%@ attribute name="count" required="true" type="java.lang.Integer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach begin="${1 }" end="${count }" varStatus="vs">
	${vs.count }. <jsp:doBody/> <br>
</c:forEach>