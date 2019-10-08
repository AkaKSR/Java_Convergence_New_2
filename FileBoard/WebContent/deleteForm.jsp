<%@page import="kr.green.file.service.FileBoardService"%>
<%@page import="kr.green.file.vo.FileBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include.jsp" %>
<%
	// 수정할 글 1개를 가져와야 한다.
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
<title>자료실 글삭제</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" ></script>
<script type="text/javascript" src="js/comm.js"></script>
<script type="text/javascript">
	$(function(){
		
	});
</script>
<style type="text/css">
	table { width: 600px; margin: auto;}
	th { border: 1px solid gray;padding: 3px;text-align: center; background-color: silver;}
	td { border: 1px solid gray;padding: 3px; }
	.title{font-size: 18pt; text-align: center;border: none;padding: 5px;}
	.sub_title{text-align: right;border: none;padding: 5px;}
	.no_border{border: none;}
</style>
</head>
<body>
	<form action="deleteOk.jsp" method="post" id="myForm">
		<table>
			<tr>
				<td class="title">자료실 글수정</td>
			</tr>
			<tr>
				<td class="no_border">
					<div class="row">
						<div class="col">
							<input type="text" class="form-control" name="name" id="name" value="${vo.name }" readonly="readonly">						
						</div>					
						<div class="col">
							<input type="password" class="form-control" name="password" id="password" placeholder="비밀번호" required="required" >	
						</div>					
					</div>
				</td>
			</tr>
			<tr>
				<td class="no_border">
					<div class="row">
						<div class="col">
							<input type="text" class="form-control" name="subject" id="subject" value="${vo.subject }" readonly="readonly" >						
						</div>					
					</div>
				</td>
			</tr>
			<tr>
				<td class="no_border">
					<div class="row">
						<div class="col">
							<textarea  class="form-control" rows="15" cols="90" name="content" id="content" style="margin-top: 5px;" readonly="readonly">${vo.content }</textarea>						
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
								<div class="col" id="file${fvo.idx }">
									<input type="button" class="form-control" value="${fvo.ofile }"/>
								</div>
							</c:forEach>
						</div>
					</td>
				</tr>
			</c:if>
			<tr>
				<td align="right" class="no_border">
					<input type="hidden" name="idx" value="${vo.idx }">
					<input type="hidden" name="p" value="${currentpage }">
					<input type="hidden" name="s" value="${pageSize }">
					<input type="hidden" name="b" value="${blockSize }">
					<button type="submit" class="btn btn-danger btn-sm"  id="submitBtn">삭제하기</button>
					<button type="button" class="btn btn-success btn-sm"  
					        onclick='sendPost("view.jsp",{"p":${currentPage },"b":${blockSize },"s":${pageSize },"idx":${vo.idx }});'>취소하기</button>
				</td>
			</tr>
			
		</table>
	</form>
</body>
</html>