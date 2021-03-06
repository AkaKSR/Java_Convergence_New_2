<%@page import="kr.green.service.GuestbookService"%>
<%@page import="kr.green.vo.GuestbookVO"%>
<%@page import="kr.green.vo.Paging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>
<%
	// 서비스 클래스를 이용하여 현재 페이지를 얻어온다.
	Paging<GuestbookVO> paging = GuestbookService.getInstance().selectList(currentPage, pageSize, blockSize);
	// EL로 출력하기 위해서 4개영역(pageContext, request, session, application) 중 한곳에 저장한다.
	request.setAttribute("paging", paging);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 목록 보기</title>
<%-- jsp 상단에 들어갈 자바스크립트와 스타일 --%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {

	});
	// name password content writeForm submitBtn
	function updateForm(idx, name, content) {
		$("#writeForm").attr('action', 'updateOk.jsp');
		$("#submitBtn").val("수정");
		$("#name").val(name);
		$("#name").attr('readonly', 'readonly');
		$("#content").val(content);
		$("#password").val("");
		$("#password").focus();
	};
	function deleteForm(idx, name, content) {
		$("#writeForm").attr('action', 'deleteOk.jsp');
		$("#submitBtn").val("삭제");
		$("#name").val(name);
		$("#name").attr('readonly', 'readonly');
		$("#content").val(content);
		$("#content").attr('readonly', 'readonly');
		$("#password").val("");
		$("#password").focus();
	};
</script>
<style type="text/css">
table {
	width: 800px;
	border: none;
	padding: 5px;
	margin: auto;
}

.title {
	border: none;
	text-align: center;
	font-size: 18pt;
}
</style>
<%-- jsp 상단에 들어갈 자바스크립트와 스타일 --%>
</head>
<body>
	<table class="table">
		<tr>
			<td class="title">방명록 프로그램 Ver 0.009</td>
		</tr>
		<%-- 쓰기 폼 --%>
		<tr>
			<td>
				<form id="writeForm" action="insertOk.jsp" method="post">
					<%-- 여기에 몇개를 숨겨서 가자 --%>
					<input type="hidden" name="p" value="1"/>
					<input type="hidden" name="s" value="${paging.pageSize }"/>
					<input type="hidden" name="b" value="${paging.blockSize }"/>
					<div class="form-row">
						<div class="input-group input-group-sm mb-3 col">
							<input type="text" name="name" id="name" class="form-control" placeholder="이름" aria-describedby="inputGroup-sizing-sm" required="required">
						</div>
						<div class="input-group input-group-sm mb-3 col">
							<input type="password" name="password" id="password" class="form-control" placeholder="비밀번호" aria-describedby="inputGroup-sizing-sm" required="required">
						</div>
					</div>
					<div>
						<textarea name="content" id="content" class="form-control" placeholder="남기실 말을 적어주시기 바랍니다." cols="100" rows="6" style="margin-top: 10px;" required="required"></textarea>
					</div>
					<div align="right" style="padding-top: 10px;">
					<button type="submit" id="submitBtn" class="btn btn-sm btn-primary" >쓰기</button>
					</div>
				</form>
			</td>
		</tr>
		<tr>
			<td align="right">${paging.pageInfo }</td>
		</tr>
		<%-- 글이 없다면 --%>
		<c:if test="${paging.totalCount==0 }">
			<tr>
				<td align="center" style="border: 1px solid gray;">등록된 글이 없습니다.</td>
			</tr>
		</c:if>
		<%-- 글이 있다면 --%>
		<c:if test="${paging.totalCount>0 }">
			<c:forEach var="vo" items="${paging.list }" varStatus="vs">
				<tr>
					<td>
						<div style="border: 1px solid lightgray; border-radius: 15px; padding: 5px;">
							<div style="background-color: silver; padding: 5px; border-radius: 10px; margin-bottom: 5px;">
								<%-- 제목 --%>
								${vo.idx }.&nbsp;
								"<b><c:out value="${vo.name }"/></b>"님이 
								${vo.ip }에서
								<fmt:formatDate value="${vo.regDate }" pattern="yyyy-MM-dd hh:mm:ss"/>
								에 남긴글
								<button type="button" name="updateBtn" id="updateBtn" class="btn btn-sm btn-success" onclick="updateForm(${vo.idx }, '${vo.name }', '${vo.content }')">수정</button>
								<button type="button" name="deleteBtn" id="deleteBtn" class="btn btn-sm btn-danger" onclick="deleteForm()">삭제</button>
							</div>
							<%-- 내용 : 태그를 무시하고 줄바꿈 처리를 위한 작업 --%>
							<c:set var="html" value="${vo.content }"></c:set>
							<c:set var="html" value="${fn:replace(html, '<', '&lt;') }"></c:set>
							<c:set var="html" value="${fn:replace(html, newLine, br) }"></c:set>
							${html }
						</div>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td style="padding-top: 20px;">
					${paging.pageList }
				</td>
			</tr>
		</c:if>
	</table>
</body>
</html>