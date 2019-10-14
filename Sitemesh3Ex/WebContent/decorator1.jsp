<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  	<meta charset="UTF-8">
    <title>-=[{<sitemesh:write property='title'/>}]=-</title>
    <sitemesh:write property='head'/>
    <style type="text/css">
    	div{ 
    	width: 500px; margin: auto; height: 100px; border: 1px solid gray;
    	background-color: pink; text-align: center;
    	}
    </style>
  </head>
  <body>
  	<a href="test1.jsp">첫번째 화면</a>
  	<a href="test2.jsp">두번째 화면</a>
  	<br><hr>
  	<div>
    <sitemesh:write property='body'/>
  	</div>
  </body>
</html>