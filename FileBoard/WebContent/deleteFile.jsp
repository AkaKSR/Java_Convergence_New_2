<%@page import="kr.green.file.service.FileBoardService"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%
	// 파일 삭제
	int idx = -1;
	try {
		idx = Integer.parseInt(request.getParameter("idx"));
	} catch (Exception e) {
		;
	}
	// 서비스에서 파일 삭제를 호출한다.
	int count = FileBoardService.getInstance().deleteFile(idx);
	out.println(count);
%>