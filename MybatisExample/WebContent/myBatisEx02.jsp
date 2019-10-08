<%@page import="kr.green.mybatis.EmpVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.green.mybatis.MybatisApp"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="org.apache.ibatis.session.SqlSessionFactoryBuilder"%>
<%@page import="org.apache.ibatis.session.SqlSessionFactory"%>
<%@page import="org.apache.ibatis.io.Resources"%>
<%@page import="java.io.InputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function empSelect(obj){
		//alert(obj.value);
		location.href = "?job_id=" + obj.value;
	}
</script>
</head>
<body>
	직업 선택
	<select id="job_id" onchange="empSelect(this);">
		<option>직업선택</option>
		<option value="">전체</option>
		<option value="AC_ACCOUNT">AC_ACCOUNT</option>
		<option value="AC_MGR">AC_MGR</option>
		<option value="AD_ASST">AD_ASST</option>
		<option value="AD_PRES">AD_PRES</option>
		<option value="AD_VP">AD_VP</option>
		<option value="FI_ACCOUNT">FI_ACCOUNT</option>
		<option value="FI_MGR">FI_MGR</option>
		<option value="HR_REP">HR_REP</option>
		<option value="IT_PROG">IT_PROG</option>
		<option value="MK_MAN">MK_MAN</option>
		<option value="MK_REP">MK_REP</option>
		<option value="PR_REP">PR_REP</option>
		<option value="PU_CLERK">PU_CLERK</option>
		<option value="PU_MAN">PU_MAN</option>
		<option value="SA_MAN">SA_MAN</option>
		<option value="SA_REP">SA_REP</option>
		<option value="SH_CLERK">SH_CLERK</option>
		<option value="ST_CLERK">ST_CLERK</option>
		<option value="ST_MAN">ST_MAN</option>
	</select>
	<br><hr>
	<%
		String job_id = request.getParameter("job_id");
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisApp.getSessionFactory().openSession();
			HashMap<String, String> map = new HashMap<>();
			map.put("job_id", job_id);
			List<EmpVO> list = sqlSession.selectList("test.selectList", map);
			if(list!=null){
				for(EmpVO vo : list){
					out.print(vo.getFIRST_NAME()  + " " + vo.getLAST_NAME() + " : ");
					out.print(vo.getPHONE_NUMBER()  + " " + vo.getJOB_ID());
					out.print("<br>");
				}
			}
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	%>
</body>
</html>