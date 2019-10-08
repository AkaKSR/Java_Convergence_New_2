<%@page import="kr.green.file.service.FileBoardService"%>
<%@page import="kr.green.file.vo.FileBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include.jsp" %>
<%
	// 글 1개를 가져온다
	FileBoardVO vo = FileBoardService.getInstance().selectByIdx(idx);
	if (vo == null) { // 글이 없는 경우
		response.sendRedirect("list.jsp");
		return;
	}
	request.setAttribute("vo", vo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료실 내용 보기</title>
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
				<td class="title">자료실 내용 보기</td>
			</tr>
			<tr>
				<td class="no_border">
					<div class="row">
						<div class="col">
							<input type="text" class="form-control" value="이름: ${vo.name }" readonly="readonly">						
						</div>					
						<div class="col">
							<input type="text" class="form-control" value='<fmt:formatDate value="${vo.regDate }" pattern="작성일: yyyy-MM-dd hh:mm"/>' readonly="readonly">						
						</div>					
						<div class="col">
							<input type="text" class="form-control" value="IP: ${vo.ip }" readonly="readonly">						
						</div>					
					</div>
				</td>
			</tr>
			<tr>
				<td class="no_border">
					<div class="row">
						<div class="col">
							<input type="text" class="form-control" value="${vo.subject }" readonly="readonly">						
						</div>					
					</div>
				</td>
			</tr>
			<tr>
				<td class="no_border">
					<div class="row">
						<div class="col">
							<textarea  class="form-control" rows="15" cols="90" style="margin-top: 5px;" readonly="readonly">${vo.content }</textarea>
						</div>					
					</div>
				</td>
			</tr>
			<%-- 첨부 파일이 있을때만 --%>
			<c:if test="${not empty vo.fileList }">
				<tr>
					<td class="no_border" id="fileBox">
						<div class="row">
							<c:forEach var="fvo" items="${vo.fileList }">
								<div class="col">
									<c:url var="url" value="download.jsp">
										<c:param name="s" value="${fvo.sfile }"/>
										<c:param name="o" value="${fvo.ofile }"/>
									</c:url>
									<a href="${url }" class="form-control">${fvo.ofile }</a> <br>
								</div>
							</c:forEach>
						</div>
					</td>
				</tr>
			</c:if>
			<tr>
				<td align="right" class="no_border">
					<button type="button" class="btn btn-warning btn-sm"  
					        onclick='sendPost("updateForm.jsp",{"p":${currentPage },"b":${blockSize },"s":${pageSize },"idx":${vo.idx }});'>수정</button>
					<button type="button" class="btn btn-danger btn-sm"  
					        onclick='sendPost("deleteForm.jsp",{"p":${currentPage },"b":${blockSize },"s":${pageSize },"idx":${vo.idx }});'>삭제</button>
					<button type="button" class="btn btn-success btn-sm"  
					        onclick='sendPost("list.jsp",{"p":${currentPage },"b":${blockSize },"s":${pageSize }});'>목록</button>
				</td>
			</tr>
		</table>
</body>
</html>