<%@page import="kr.green.board.service.BoardService"%>
<%@page import="kr.green.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>
<%
	// GET방식의 접속 막기
	if (!request.getMethod().equals("POST")) {
		response.sendRedirect("list.jsp");
		return;
	}

	// 삭제하기 위해서 삭제할 글 1개를 가져온다.
	BoardVO vo = BoardService.getInstance().selectByIdx(idx, 0);
	request.setAttribute("vo", vo);
	
	// 해당 글번호의 글이 없다면
	if (vo == null) {
		response.sendRedirect("list.jsp");
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 글수정하기</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/comm.js"></script>
<script type="text/javascript">
	$(function(){
		$("#password").focus();
	});
</script>
<style type="text/css">
table {
	width: 600px;
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

.sub_title {
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
	<form action="deleteOk.jsp" method="post" id="myForm">
		<table>
			<tr>
				<td class="title">자유게시판 글삭제</td>
			</tr>
			<tr>
				<td class="no_border">
					<div class="row">
						<div class="col">
							<input type="text" class="form-control" name="name" id="name"
								placeholder="이름" value="${vo.name }" readonly="readonly">
						</div>
						<div class="col">
							<input type="password" class="form-control" name="password"
								id="password" placeholder="비밀번호" required="required">
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td class="no_border">
					<div class="row">
						<div class="col">
							<input type="text" class="form-control" name="subject"
								id="subject" placeholder="제목입력" readonly="readonly"
								value="${vo.subject }">
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td class="no_border">
					<div class="row">
						<div class="col">
							<textarea class="form-control" rows="15" cols="90" name="content"
								id="content" style="margin-top: 5px;" readonly="readonly">${vo.content }</textarea>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td align="right" class="no_border">
					<input type="hidden" name="p" value="${currentPage }">
					<input type="hidden" name="s" value="${pageSize }">
					<input type="hidden" name="b" value="${blockSize }">
					<input type="hidden" name="idx" value="${vo.idx }">
					<button type="submit" class="btn btn-success btn-sm" id="submitBtn">삭제하기</button>
					<button type="button" class="btn btn-success btn-sm"
						onclick='sendPost("view.jsp",{"p":${currentPage },"b":${blockSize },"s":${pageSize },"m":0,"idx":${vo.idx }});'>취소하기</button>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>