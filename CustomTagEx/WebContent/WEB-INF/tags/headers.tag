<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag body-content="empty" %>
<%@ attribute name="title" required="true" %>
<%@ attribute name="level" type="java.lang.Integer" %>
<%
	String header = "";
	if(level==null || level>=7 || level<=0) {
		header = "<h3>" + title + "</h3>";
	} else {
		header = "<h" + level + ">" + title + "</h" + level + ">";
	}
	
	out.println(header);
%>