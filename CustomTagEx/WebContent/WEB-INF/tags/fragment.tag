<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag body-content="scriptless"%>
<%@ attribute name="title" fragment="true"%>
<u><i><b>
<jsp:invoke fragment="title"/>
</b></i></u><br/>
<jsp:invoke fragment="title" var="title" scope="request"/>
${title}