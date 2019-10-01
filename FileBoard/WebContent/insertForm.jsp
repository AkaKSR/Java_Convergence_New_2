<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료실 글쓰기</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
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
	<form action="insertOk.jsp" method="post" id="myForm" enctype="multipart/form-data">
		<table>
			<tr>
				<td class="title">자료실 글쓰기</td>
			</tr>
			<tr>
				<td class="no_border">
					<div class="row">
						<div class="col">
							<input type="text" class="form-control" name="name" id="name" placeholder="이름" required="required" >						
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
							<input type="text" class="form-control" name="subject" id="subject" placeholder="제목입력" required="required" >						
						</div>					
					</div>
				</td>
			</tr>
			<tr>
				<td class="no_border">
					<div class="row">
						<div class="col">
							<textarea  class="form-control" rows="15" cols="90" name="content" id="content" style="margin-top: 5px;"></textarea>						
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
					<input type="hidden" name="p" value="1">
					<input type="hidden" name="s" value="${pageSize }">
					<input type="hidden" name="b" value="${blockSize }">
					<button type="submit" class="btn btn-success btn-sm"  id="submitBtn">저장하기</button>
					<button type="button" class="btn btn-success btn-sm"  
					        onclick='sendPost("list.jsp",{"p":${currentPage },"b":${blockSize },"s":${pageSize }});'>취소하기</button>
				</td>
			</tr>
			
		</table>
	</form>
</body>
</html>