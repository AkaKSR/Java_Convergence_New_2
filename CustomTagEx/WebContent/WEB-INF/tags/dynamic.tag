<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag trimDirectiveWhitespaces="true"%>
<%@ tag dynamic-attributes="map" %>
<%@ attribute name="name" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<select name="${name }">
	<c:forEach var="op" items="${map }">
		<option value="${op.key }">${op.value }</option>
	</c:forEach>
</select>
<br>