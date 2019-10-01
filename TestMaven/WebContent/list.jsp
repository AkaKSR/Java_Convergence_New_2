<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메모장</title>
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
</script>
<style type="text/css">
table {
	width: 800px;
	border: none;
	padding: 5px;
	margin: auto;
}

.title {
	font-size: 18pt;
	border: none;
	text-align: center;
}

.sub_title {
	border: none;
	text-align: right;
}
th {
	border: 1px solid gray;
	text-align: center;
}
</style>
</head>
<body>
	<table>
		<tr>
			<td class="title" colspan="5">메모장</td>
		</tr>
		<tr>
			<td class='sub_title' colspan="5">전체: 0개(00/00)</td>
		</tr>
		<tr>
			<th width="10%">No</th>
			<th width="10%">작성자</th>
			<th width="50%">내용</th>
			<th width="15%">날짜</th>
			<th width="15%">IP</th>
		</tr>
	</table>
</body>
</html>