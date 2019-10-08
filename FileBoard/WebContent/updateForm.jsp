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
<title>자료실 글수정</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" ></script>
<script type="text/javascript" src="js/comm.js"></script>
<script type="text/javascript">
	var count = 2;
	$(function(){
		$("#addFile").click(function() {
			if (count>5) {
				alert(count + "개 까지만 추가가 가능합니다.");
				return;
			}
			count++;
			$('<div class="row"><div class="col"><input type="file" name="file" class="form-control"/></div></div>').appendTo($("#fileBox"));
		});
		$("#removeFile").click(function() {
			if (count<=2) {
				alert(count + "개는 반드시 있어야 합니다.");
				return;
			}
			$("#fileBox div.row").last().remove();
			count--;
		});
	});
	function fileDelete(filename, idx){
		if(confirm(filename + "을 정말 삭제할까요?")){
			$.ajax('deleteFile.jsp',{
				data:{'idx': idx},
				type:"GET",
				success : function(data){
					$("#file"+idx).remove();
				}
			});
		}
	}
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
	<form action="updateOk.jsp" method="post" id="myForm" enctype="multipart/form-data">
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
							<input type="text" class="form-control" name="subject" id="subject" value="${vo.subject }" required="required" >						
						</div>					
					</div>
				</td>
			</tr>
			<tr>
				<td class="no_border">
					<div class="row">
						<div class="col">
							<textarea  class="form-control" rows="15" cols="90" name="content" id="content" style="margin-top: 5px;" required="required">${vo.content }</textarea>						
						</div>					
					</div>
					<div>
						<div align="right">
							<input type="button" id="addFile" value="+" class="btn btn-info btn-sm"/>
							<input type="button" id="removeFile" value="-" class="btn btn-info btn-sm"/>
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
									<input type="button" class="form-control" value="${fvo.ofile } 삭제" onclick="fileDelete('${fvo.ofile }', ${fvo.idx})"/>
								</div>
							</c:forEach>
						</div>
					</td>
				</tr>
			</c:if>
			<tr>
				<td class="no_border" id="fileBox">
					<div class="row">
						<div class="col">
							<input type="file" name="file" class="form-control"/>						
						</div>					
					</div>
					<div class="row">
						<div class="col">
							<input type="file" name="file" class="form-control"/>						
						</div>					
					</div>
				</td>
			</tr>
			<tr>
				<td align="right" class="no_border">
					<input type="hidden" name="idx" value="${vo.idx }">
					<input type="hidden" name="p" value="${currentpage }">
					<input type="hidden" name="s" value="${pageSize }">
					<input type="hidden" name="b" value="${blockSize }">
					<button type="submit" class="btn btn-warning btn-sm"  id="submitBtn">수정하기</button>
					<button type="button" class="btn btn-success btn-sm"  
					        onclick='sendPost("view.jsp",{"p":${currentPage },"b":${blockSize },"s":${pageSize },"idx":${vo.idx }});'>취소하기</button>
				</td>
			</tr>
			
		</table>
	</form>
</body>
</html>