<%@page import="kr.green.board.service.BoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="include.jsp" %>
<%
	int count = BoardService.getInstance().increaseBad(idx);
	out.println(count);
%>