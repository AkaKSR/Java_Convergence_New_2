<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.Random"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일을 받아 서버의 특정 위치에 저장한다.</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		// 일반 텍스트가 넘어오는지 파일이 넘어오는지를 판단해준다.
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			out.println("<script>alert('파일이 아님.');");
			out.println("location.href='uploadForm01.jsp'</script>");
			return;
		}

		// DiskFileItemFactory 객체 생성
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 시스템의 임시 폴더를 읽어 업로드 파일이 저장될 임시 폴더 지정
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);

		// 파일의 처리를 위해 DiskFileItemFactory 객체를 이용하여 ServletFileUpload 갹체를 생성한다.
		ServletFileUpload upload = new ServletFileUpload(factory);

		// request를 파일하여 넘어온 모든 정보를 FileItem 객체 리스트로 저장한다.
		List<FileItem> items = upload.parseRequest(request);

		// 리스트를 반복하며 원하는 작업을 수행한다.
		for (FileItem item : items) {
			// 일반 폼인지 파일인지를 판단한다.
			if (item.isFormField()) {
				// 일반폼 필드일 경우 input type이 file이 아닌 경우 값 받기
				String name = item.getFieldName();
				String value = item.getString("UTF-8"); // 항글을 받으려면 인코딩 타입을 인수로 준다.
				out.println(name + " : " + value + "<br>");
			} else {
				// 파일일 경우 input type이 file인 경우 값 받기
				String fieldName = item.getFieldName(); // 필드명
				String fileName = item.getName(); // 파일 이름
				String contentType = item.getContentType(); // 파일 종류
				boolean isInMemory = item.isInMemory(); // 메모리 또는 임시폴더에 저장되었는지 판단
				long sizeInBytes = item.getSize(); // 파일 크기

				out.println("필드명: " + fieldName + "<br>");
				out.println("파일 이름: " + fileName + "<br>");
				out.println("파일 종류: " + contentType + "<br>");
				out.println("저장 위치: " + (isInMemory ? "메모리" : "임시폴더") + "<br>");
				out.println("파일 크기: " + sizeInBytes + "<br>");

				// 저장된 파일을 사용자가 원하는 위치로 복사를 해주어야 한다.
				String path = application.getRealPath("upload");
				if (fileName.contains("\\")) { // fileName에 경로가 포함되어 있으면
					// 경로를 뺀 파일 이름만 출력
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
				}
				// String saveFileName = fileName;
				// 파일명 중복을 피하기 위하여 저장하는 파일의 이름을 변경해서
				// 저장을 하고 다운로드를 위해 원본이름과 저장이름을 둘다 DB에 저장한다.
				String saveFileName = fileName + "_" + System.nanoTime() + "_" + new Random().nextInt(101);
				out.println("저장 파일 이름 : " + saveFileName + "<br>");
				
				// 저장
				File uploadedFile = new File(path + "\\" + saveFileName);
			    item.write(uploadedFile);
			    out.println("저장 위치 : " + path + "<br>");
			    
				// 그리고 임시 폴더의 파일이면 임시 폴더의 파일을 삭제해 준다.
				if (!isInMemory) {
					File tempFile = new File(repository + "\\" + fileName);
					if (tempFile.exists()) tempFile.delete();
				}
				
				// 다운로드를 위해 링크를 걸어보자
// 				out.println("<a href='upload/" + saveFileName + "'>" + saveFileName + "</a><br>");
// 				out.println("<a href='upload/" + fileName + "'>" + fileName + "</a><br>");
				out.println("<a href='upload/" + saveFileName + "'>" + fileName + "</a><br>");
				out.println("<a href='download.jsp?s=" + URLEncoder.encode(saveFileName, "UTF-8") + "&o=" + fileName + "'>" + URLEncoder.encode(fileName, "UTF-8") + "</a><br>");
				out.println("<a href='download.jsp?s=" + saveFileName + "&o=" + fileName + "'>" + fileName + "</a><br>");
			}
		}
	%>
</body>
</html>