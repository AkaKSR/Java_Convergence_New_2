<%@page import="kr.green.board.service.BoardService"%>
<%@page import="kr.green.board.vo.BoardVO"%>
<%@page import="kr.green.board.vo.Paging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="include.jsp"%>
<%
	Paging<BoardVO> paging = BoardService.getInstance().selectList(currentPage, pageSize, blockSize);
	request.setAttribute("paging", paging);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 목록 보기</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/comm.js"></script>
<script type="text/javascript">
	$(function() {
		
	});
</script>
<style type="text/css">
	table {
		width: 900px;
		margin: auto;
	}
	th {
		border: 1px solid gray;
		padding: 3px;
		text-align: center;
		background-color: silver;
	}
	td {
		border: 1px solid gray;
		padding: 3px;
	}
	.title {
		font-size: 18pt;
		text-align: center;
		border: none;
		padding: 5px;
	}
	.subtitle {
		text-align: right;
		border: none;
		padding: 5px;
	}
	.no_border {
		border: none;
	}
</style>
</head>
<body>
	<table>
		<tr>
			<td colspan="5" class="title">자유게시판 목록 보기</td>
		</tr>
		<tr>
			<td colspan="5" class="subtitle">${paging.getPageInfo() }</td>
		</tr>
		<tr>
			<th>No</th>
			<th>작성자</th>
			<th width="55%">제목</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:if test="${paging.totalCount == 0 }">
			<tr>
				<td colspan="5" align="center">작성된 글이 없습니다.</td>
			</tr>
		</c:if>
		<c:if test="${paging.totalCount != 0 }">
		<c:set var="no" value="${paging.totalCount-(paging.currentPage-1)*paging.pageSize }"></c:set>
			<c:forEach var="vo" items="${paging.list }" varStatus="vs">
				<tr style="text-align: center" onmouseover="this.style.backgroundColor='skyblue'" onmouseout="this.style.backgroundColor='white'">
					<td>${no-vs.index }</td>
					<td>
						<c:out value="${vo.name }"></c:out>
					</td>
					<td align="left">
						<%-- 글자수가 길면 잘라서 뒤에 ...을 붙이는 기능 추가 --%>
						<span onclick='sendPost("view.jsp",{"p":${paging.currentPage},"b":${paging.blockSize },"s":${paging.pageSize },"idx":${vo.idx },"m":1});' style="cursor: pointer;">
						<c:out value="${vo.subject }" ></c:out><%-- 댓글 갯수 들어갈 부분 --%>
					</span>
					<%-- 댓글의 개수를 댓글이 있을때만 출력한다. --%>
					<c:if test="${vo.commentCount > 0 }">
					- (${vo.commentCount })
					</c:if>
					<%-- 최근글에는 new 아이콘을 붙이는 기능 추가 --%>
					</td>
					<td>
						<fmt:formatDate value="${vo.regDate }" pattern="MM-dd"/>
					</td>
					<td>${vo.hit }</td>
				</tr>
			</c:forEach>
				<tr>
					<td colspan="5" align="center" class="no_border" style="padding-top: 10px;">
					${paging.pageList }</td>
				</tr>
		</c:if>
				<tr>
					<td colspan="5" align="right" class="no_border" style="padding-top: 10px;">
					<button type="button" class="btn btn-sm btn-success" onclick='sendPost("insertForm.jsp", {"p":${paging.currentPage },"b":${paging.blockSize }, "s":${paging.pageSize }});'>쓰기</button>
					</td>
				</tr>
	</table>
</body>
</html>