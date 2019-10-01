<%@page import="kr.green.file.service.FileBoardService"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Random"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.green.file.vo.FileBoardFileVO"%>
<%@page import="kr.green.file.vo.FileBoardVO"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/comm.js"></script>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		// 일반 텍스트가 넘어오는지 파일이 넘어오는지를 판단해준다.
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			out.println("<script>alert('파일이 아님.');");
			out.println("location.href='list.jsp'</script>");
			return;
		}
		//DiskFileItemFactory 객체 생성
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// 시스템의 임시 폴더를 읽어 업로드 파일이 저장될 임시 폴더 지정
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);

		// 파일의 처리를 위해 DiskFileItemFactory 객체를 이용하여 ServletFileUpload 갹체를 생성한다.
		ServletFileUpload upload = new ServletFileUpload(factory);

		// request를 파일하여 넘어온 모든 정보를 FileItem 객체 리스트로 저장한다.
		List<FileItem> items = upload.parseRequest(request);

		FileBoardVO vo = new FileBoardVO();
		List<FileBoardFileVO> list = new ArrayList<>();
		for (FileItem item : items) {
			if (item.isFormField()) {
				// 일반 폼필드는 vo에 넣어주고
				String name = item.getFieldName();
				String value = item.getString("UTF-8");
				switch (name) {
				case "name":
					vo.setName(value);
					break;
				case "password":
					vo.setPassword(value);
					break;
				case "subject":
					vo.setSubject(value);
					break;
				case "content":
					vo.setContent(value);
					break;
				}
			} else {
				long sizeInBytes = item.getSize(); // 파일 크기
				// 넘어오지 않은 빈 파일을 검사해서 스킵해야 한다.
				if (sizeInBytes > 0) {

					String ofile = item.getName(); // 원본 파일 이름
					// 저장된 파일을 사용자가 원하는 위치로 복사를 해주어야 한다.
					if (ofile.contains("\\")) { // fileName에 경로가 포함되어 있으면
						// 경로를 뺀 파일 이름만 출력
						ofile = ofile.substring(ofile.lastIndexOf("\\") + 1);
					}

					String path = application.getRealPath("upload"); // 저장 폴더

					// 저장 파일이름을 만들자
					String sfile = System.nanoTime() + "_" + new Random().nextInt(101);

					// 실제 서버로 복사를 해주어야 한다.
					File uploadedFile = new File(path + "\\" + sfile);
					item.write(uploadedFile);
					System.out.println("저장 위치 : " + path + "<br>");
					
					// 임시 폴더에 임시 파일이 있다면 파일을 삭제해 준다.
					if (!item.isInMemory()) {
						System.out.println("임시 저장 위치 : " + repository.getAbsolutePath() + "<br>");
						File tempFile = new File(repository.getAbsolutePath() + "\\" + ofile);
						if (tempFile.exists()) tempFile.delete();
					}
					

					// 파일인 경우에는 list에 넣어준다.
					FileBoardFileVO fvo = new FileBoardFileVO();

					fvo.setOfile(ofile);
					fvo.setSfile(sfile);
					list.add(fvo);
				} // 사이즈 검사
			} // 파일 검사
		} // for 종료
		// 아이피는 수동으로 넣어주자
		vo.setIp(request.getRemoteAddr());
		
		// 여기서 DB에 저장해주는 코드가 필요하다.
		FileBoardService.getInstance().insert(vo, list);
	%>
<script type="text/javascript">
	sendPost("list.jsp", {"p":1,"s":${pageSize},"b":${blockSize}});
</script>
</body>
</html>