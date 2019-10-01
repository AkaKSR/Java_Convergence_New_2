<%@page import="java.io.File"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COS를 이용한 업로드</title>
</head>
<body>
	<%
		String path = application.getRealPath("upload"); // 저장할 절대 경로
		int maxSize = 10 * 1024 * 1024; // 최대 파일크기

		// MultipartRequest를 생성하는 순간 모든 업로드가 완료된다.
		MultipartRequest multi = new MultipartRequest(request, path, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());
		
		// 일반 폼필드 받기
		String note1 = multi.getParameter("note1");
		String note2 = multi.getParameter("note2");
		out.println(note1 + ", " + note2 + "<br>");
		
		// 파일 필드라면
		Enumeration<String> em = multi.getFileNames();
		while (em.hasMoreElements()) {
			String fieldName = em.nextElement();
			File file = multi.getFile(fieldName); // 파일 얻기
			out.println("필드명 : " + fieldName + "<br>");
			out.println("파일 크기 : " + file.length() + "<br>"); // 파일 크기
			out.println("원본 파일 이름 : " + multi.getOriginalFileName(fieldName) + "<br>"); // 원본 이름
			out.println("저장 파일 이름 : " + multi.getFilesystemName(fieldName) + "<br>"); // 원본 이름
			out.println("파일 종류 : " + multi.getContentType(fieldName) + "<br>");
		}
	%>
</body>
</html>