<%@page import="kr.green.file.service.FileBoardService"%>
<%@page import="kr.green.file.vo.FileBoardVO"%>
<%@page import="kr.green.file.vo.Paging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>
<%
	Paging<FileBoardVO> paging = FileBoardService.getInstance().selectList(currentPage, pageSize, blockSize);
	request.setAttribute("paging", paging);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료실 목록 보기</title>
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
			<td colspan="5" class="title">자료실 목록보기</td>
		</tr>
		<tr>
			<td colspan="5" class="sub_title">${paging.pageInfo }</td>
		</tr>
		<tr>
			<th>No</th>
			<th>작성자</th>
			<th width="55%">제목</th>
			<th>작성일</th>
			<th>첨부파일</th>
		</tr>
		<c:if test="${paging.totalCount==0 }">
			<tr>
				<td colspan="5" align="center">글 없다!!!!</td>
			</tr>
		</c:if>
		<c:if test="${paging.totalCount!=0 }">
			<c:set var="no" value="${paging.totalCount-(paging.currentPage-1)*paging.pageSize }"/>
			<c:forEach var="vo" items="${paging.list }" varStatus="vs">
				<tr align="center" onmouseover="this.style.backgroundColor='skyblue'"  onmouseout="this.style.backgroundColor='white'">
					<td>${no-vs.index }</td>
					<td>
						<c:out value="${vo.name }"/>
					</td>
					<td align="left">
						<%-- 글자수가 길면 잘라서 뒤에 ...을 붙이는 기능 추가 --%>
						<span onclick='sendPost("view.jsp",{"p":${paging.currentPage},"b":${paging.blockSize },"s":${paging.pageSize },"idx":${vo.idx }});' style="cursor: pointer;">
						<c:out value="${vo.subject }"/>
						</span>
						<%-- 최근글에는 new 아이콘을 붙이는 기능 추가 --%>
					</td>
					<td>
						<fmt:formatDate value="${vo.regDate }" pattern="MM-dd"/>
					</td>
					<td>
						${vo.fileCount }
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="5" align="center" class="no_border">
					${paging.pageList }
				</td>
			</tr>
		</c:if>
		<tr>
			<td colspan="5" align="right" class="no_border">
				<button type="button" class="btn btn-success btn-sm" 
				        onclick='sendPost("insertForm.jsp",{"p":${paging.currentPage },"b":${paging.blockSize },"s":${paging.pageSize }});'>새글 쓰기</button>
			</td>
		</tr>
	</table>
</body>
</html>