<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag body-content="empty" %>
<%-- content 속성에 내용을 필수로 --%>
<%@ attribute name="content" required="true" %>
<%-- length 속성에 내용을 표시할 길이로 --%>
<%@ attribute name="length" type="java.lang.Integer" %>
<%-- trim 속성에 내용을 true/false --%>
<%@ attribute name="trim" type="java.lang.Boolean" %>
<%-- trail 속성에 붙임 문자 --%>
<%@ attribute name="trail"%>
<%
	if(trim!=null && trim) content = content.trim();
	if(length!=null && length>0) content = content.substring(0,length);
	if(trail!=null) content = content + trail;
	out.println(content);
%>