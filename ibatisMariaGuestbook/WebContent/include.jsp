<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	request.setCharacterEncoding("UTF-8");
	int currentPage = 1;
	try {
		currentPage = Integer.parseInt(request.getParameter("p"));
	} catch (Exception e) {
		;
	}

	int pageSize = 5;
	try {
		pageSize = Integer.parseInt(request.getParameter("s"));
	} catch (Exception e) {
		;
	}

	int blockSize = 10;
	try {
		blockSize = Integer.parseInt(request.getParameter("b"));
	} catch (Exception e) {
		;
	}

	int idx = -1;
	try {
		idx = Integer.parseInt(request.getParameter("idx"));
	} catch (Exception e) {
		;
	}
	
	// EL에서 줄바꿈 처리를 위하여 두개의 변수를 request 영역에 저장
	request.setAttribute("newLine", "\n");
	request.setAttribute("br", "<br>");
	request.setAttribute("currentPage", currentPage);
	request.setAttribute("pageSize", pageSize);
	request.setAttribute("blockSize", blockSize);
%>