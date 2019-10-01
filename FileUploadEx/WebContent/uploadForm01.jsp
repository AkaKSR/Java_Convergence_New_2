<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Commons-FileUpload 예제</title>
</head>
<body>
<%-- 파일 자체를 업로드 하려면 반드시 폼에 enctype="multipart/form-data"를 써줘야 한다. --%>
	<form method="POST" enctype="multipart/form-data" action="upload01.jsp">
		파일 선택: <input type="file" name="upfile"><br>
		파일 설명1: <input type="text" name="note1"><br>
		파일 설명2: <input type="text" name="note2"><br> <br>
		<input type="submit" value="파일 업로드">
	</form>
</body>
</html>