<%@page import="kr.green.board.service.BoardService"%>
<%@page import="kr.green.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>
<%
	// DB에서 보여줄 VO를 받아와야 한다.
	BoardVO vo = BoardService.getInstance().selectByIdx(idx, mode);
	request.setAttribute("vo", vo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 내용보기</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/comm.js"></script>
<script type="text/javascript">
$(function() {
	$("#goodBtn").click(function(){
		// 여기서 Ajax호출
		$.ajax('increaseGood.jsp',{
			data:{'idx':${vo.idx}},
			type:"GET",
			success : function(data){
				$("#good").val("추천 : " + data + "회");
			}
		});
	});
	$("#badBtn").click(function(){
		// 여기서 Ajax호출
		$.ajax('increaseBad.jsp',{
			data:{'idx':${vo.idx}},
			type:"GET",
			success : function(data){
				$("#bad").val("비추천 : " + data + "회");
			}
		});
	});
});

function updateForm(idx) {
	var name = $("#name"+idx).text();
	var content = $("#content"+idx).text();
	$("#commentForm").attr("action", "commentUpdateOk.jsp");
	$("#idx").val(idx);
	$("#name").val(name);
	$("#name").attr("readonly", "readonly");
	$("#content").val(content);
	$("#content").removeAttr("readonly");
	$("#submitBtn").text("수정");
	$("#password").focus();
}

function deleteForm(idx) {
	var name = $("#name"+idx).text();
	var content = $("#content"+idx).text();
	$("#commentForm").attr("action", "commentDeleteOk.jsp");
	$("#idx").val(idx);
	$("#name").val(name);
	$("#name").attr("readonly", "readonly");
	$("#content").val(content);
	$("#content").attr("readonly", "readonly");
	$("#submitBtn").text("삭제");
	$("#password").focus();
}

function resetForm() {
	$("#commentForm").attr("action", "commentInsertOk.jsp");
	$("#idx").val(-1);
	$("#name").val("");
	$("#name").removeAttr("readonly");
	$("#content").val("");
	$("#content").removeAttr("readonly");
	$("#submitBtn").text("저장");
	$("#name").focus();
}
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
	<table>
		<tr>
			<td class="title">자유게시판 내용보기</td>
		</tr>
		<tr>
			<td class="no_border">
				<div class="row">
					<div class="col col-4">
						<input type="text" class="form-control" value="이름 : ${vo.name }"
							readonly="readonly">
					</div>
					<div class="col col-5">
						<fmt:formatDate value="${vo.regDate }" pattern="yy-MM-dd hh:mm"
							var="regDate" />
						<input type="text" class="form-control" value="작성일 : ${regDate }"
							readonly="readonly">
					</div>
					<div class="col col-3">
						<input type="text" class="form-control" value="IP : ${vo.ip }"
							readonly="readonly">
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td class="no_border">
				<div class="row">
					<div class="col">
						<input type="text" class="form-control" value="조회 : ${vo.hit }회"
							readonly="readonly">
					</div>
					<div class="col">
						<input type="text" class="form-control" value="추천 : ${vo.good }회"
							readonly="readonly" id="good">
					</div>
					<div class="col">
						<input type="text" class="form-control" value="비추천 : ${vo.bad }회"
							readonly="readonly" id="bad">
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td class="no_border">
				<div class="row">
					<div class="col input-group-prepend">
						<div class="input-group-text">제목</div>
						<input type="text" class="form-control" value="${vo.subject }"
							readonly="readonly">
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td class="no_border">
				<div class="row">
					<div class="col input-group-prepend">
						<div class="input-group-text">내용</div>
						<textarea class="form-control" rows="10" cols="90"
							readonly="readonly">${vo.content }</textarea>
					</div>
				</div>
			</td>
		</tr>
		<tr align="right">
			<td class="no_border">
				<button type="button" class="btn btn-primary btn-sm" id="goodBtn">추천</button>
				<button type="button" class="btn btn-secondary btn-sm" id="badBtn">비추천</button>
				<button type="button" class="btn btn-warning btn-sm" onclick='sendPost("updateForm.jsp",{"p":${currentPage },"b":${blockSize },"s":${pageSize },"idx":${vo.idx }});'>수정</button>
				<button type="button" class="btn btn-danger btn-sm" onclick='sendPost("deleteForm.jsp",{"p":${currentPage },"b":${blockSize },"s":${pageSize },"idx":${vo.idx }});'>삭제</button>
				<button type="button" class="btn btn-success btn-sm"
					onclick='sendPost("list.jsp",{"p":${currentPage },"b":${blockSize },"s":${pageSize }});'>목록</button>
			</td>
		</tr>
		<%-- 댓글 폼 --%>
		<tr><td style="height: 1px; background-color: skyblue; border: none;"></td></tr>
		<tr>
			<td class="no_border">
				<form action="commentInsertOk.jsp" method="post" id="commentForm">
				<input type="hidden" name="idx" id="idx" value="${vo.idx }"/>
				<input type="hidden" name="ref" id="ref" value="${vo.idx }"/>
				<input type="hidden" name="p" id="p" value="${currentPage }"/>
				<input type="hidden" name="s" id="s" value="${pageSize }"/>
				<input type="hidden" name="b" id="b" value="${blockSize }"/>
					<div class="row" style="margin-bottom: 3px;">
						<div class="col">
							<input type="text" name="name" id="name" class="form-control" placeholder="이름">
						</div>
						<div class="col">
							<input type="password" name="password" id="password" class="form-control" placeholder="비밀번호">
						</div>
					</div>
					<div style="margin-bottom: 3px;">
						<textarea name="content" id="content" class="form-control" rows="5" placeholder="내용을 입력하세요."></textarea>
					</div>
					<div>
						<button type="submit" id="submitBtn" name="submitBtn" class="btn btn-outline-success btn-sm">저장</button>
						<button type="button" id="cancelBtn" name="cancelBtn" class="btn btn-outline-danger btn-sm" onclick="resetForm()">취소</button>
					</div>
				</form>
			</td>
		</tr>
		<%-- 구분선 --%>
		<tr>
			<td style="height: 1px; background-color: skyblue; border: none;"></td>
		</tr>
		<tr>
			<td class="no_border">
				
			</td>
		</tr>
		<%-- 구분선 --%>
		<tr>
			<td style="height: 1px; background-color: skyblue; border: none;"></td>
		</tr>
		<%-- 댓글 리스트 --%>
		<c:if test="${empty vo.commentList or fn:length(vo.commentList)==0 }">
			<tr>
				<td class="no_border">
					등록된 댓글이 없습니다.
				</td>
			</tr>
		</c:if>
		<c:if test="${not empty vo.commentList or fn:length(vo.commentList)>0 }">
			<c:forEach var="cvo" items="${vo.commentList }">
				<tr>
					<td class="no_border">
						<span id="name${cvo.idx }">${cvo.name }</span>님이 
						<fmt:formatDate value="${cvo.regDate }" pattern="yyyy-MM-dd hh:mm:ss"/>에 남긴글<br>
						<c:set var="content" value="${cvo.content }"/>
						<c:set var="content" value="${fn:replace(content, '<','&lt;') }"/>
						<c:set var="content" value="${fn:replace(content, newLine, br) }"/>
						<div id="content${cvo.idx }">${content }</div>
						<button type="button" class="btn btn-outline-warning btn-sm" onclick='updateForm(${cvo.idx })'>수정</button>
						<button type="button" class="btn btn-outline-danger btn-sm" onclick='deleteForm(${cvo.idx })'>삭제</button>
						
					</td>
				</tr>
				<%-- 구분선 --%>
				<tr>
					<td style="height: 1px; padding: 1px; background-color: skyblue; border: none;"></td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</body>
</html>