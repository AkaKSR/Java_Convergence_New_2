<%@page import="kr.green.memo.vo.Paging"%>
<%@page import="kr.green.memo.service.MemoService"%>
<%@page import="kr.green.memo.vo.MemoVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include.jsp" %>
<%
	Paging<MemoVO> pvo = MemoService.getInstance().selectList(currentPage, pageSize, blockSize);
	request.setAttribute("pvo", pvo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메모장 리스트</title>
<style type="text/css">
	table { width: 800px; border: none; margin: auto;}
	.title{font-size: 18pt;text-align: center;border: none;}
	.sub_title{font-size: 10pt;text-align: right;border: none;}
	th {padding:2px;background-color: silver;border:1px solid gray;}
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" ></script>
<script type="text/javascript" src="js/comm.js"></script>
<script type="text/javascript">
	$(function(){
		
	});
	
	function updateForm(idx){ // 수정
		$("#myForm").attr("action","updateOk.jsp");
		$("#idx").val(idx);
		$("#name").val($("#name" + idx).text());
		$("#name").attr("readonly","readonly");
		$("#content").val($("#content" + idx).text());
		$("#submitBtn").val("수정하기");
		$("#password").focus();
	}
	function deleteForm(idx){ // 삭제
		$("#myForm").attr("action","deleteOk.jsp");
		$("#idx").val(idx);
		$("#name").val($("#name" + idx).text());
		$("#name").attr("readonly","readonly");
		$("#content").val($("#contents" + idx).text());
		$("#content").attr("readonly","readonly");
		$("#submitBtn").val("삭제하기");
		$("#password").focus();
	}
	function resetForm(){ // 취소
		$("#myForm").attr("action","insertOk.jsp");
		$("#idx").val(-1);
		$("#name").val("");
		$("#name").removeAttr("readonly");
		$("#content").val("");
		$("#content").removeAttr("readonly");
		$("#submitBtn").val("저장하기");
		$("#name").focus();
		
	}
	
</script>
</head>
<body>
		<table border="1">
			<tr>
				<td colspan="5"  class="title">
					재미없는 메모장 Ver 0.009
				</td>
			</tr>
			<tr>
				<td colspan="5"  class="sub_title">
					${pvo.pageInfo }
				</td>
			</tr>
			<tr>
				<th>No</th>
				<th>이름</th>
				<th width="60%">메모</th>
				<th>작성일</th>
				<th>IP</th>
			</tr>
			<c:if test="${pvo.totalCount==0 }">
				<tr>
					<td colspan="5"  align="center">
						아무도 글을 적지 않았어요!!!!
					</td>
				</tr>
			</c:if>
			<c:if test="${pvo.totalCount>0 }">
				<c:forEach var="vo" items="${pvo.list }">
					<tr>
						<td><span id="idx${vo.idx }">${vo.idx }</span></td>
						<td>
							<span id="name${vo.idx }"><c:out value="${vo.name }"/></span>
						</td>
						<td>
							<span id="content${vo.idx }"><c:out value="${vo.content }"/></span>
							<input type="button" id="cancelBtn" class="btn btn-warning btn-sm" value="수정" onclick="updateForm(${vo.idx })">
							<input type="button" id="cancelBtn" class="btn btn-warning btn-sm" value="삭제" onclick="deleteForm(${vo.idx })">
						</td>
						<td>
							<fmt:formatDate value="${vo.regDate }" pattern="MM-dd hh:mm"/>
						</td>
						<td>${vo.ip }</td>
					</tr>
				</c:forEach>
				<tr>
					<td align="center" style="border: none;" colspan="5">
					<%-- 페이지 이동 출력 --%>
					${pvo.pageList }
					</td>
				</tr>
			</c:if>
			<%-- 쓰기 버튼을 달아보자 --%>
			<tr>
				<td align="right" colspan="5" style="border: none;">
					<form action="insertOk.jsp" method="post" id="myForm">
						<input type="hidden" name="idx" id="idx" value="-1">
						<input type="hidden" name="p" value="${currentPage }">
						<input type="hidden" name="s" value="${pageSize }">
						<input type="hidden" name="b" value="${blockSize }">
						<div class="row" style="margin-top: 5px;">
							<div class="col">
								<input type="text" name="name" id="name" class="form-control" required="required" placeholder="이름">
							</div>
							<div class="col">
								<input type="password" name="password" id="password" class="form-control" required="required" placeholder="비밀번호">
							</div>
						</div>
						<div class="row"  style="margin-top: 5px;">
							<div class="col">
								<input type="text" name="content" id="content" class="form-control" required="required" placeholder="내용을 입력하세요">
							</div>
						</div>
						<div class="row"  style="margin-top: 5px;">
							<div class="col">
								<input type="submit" id="submitBtn" class="btn btn-success btn-sm" value="저장하기">
								<input type="button" id="cancelBtn" class="btn btn-success btn-sm" value="취소하기" onclick="resetForm()">
							</div>
						</div>
					</form>
				</td>
			</tr>
		</table>
</body>
</html>





