<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="serv.*" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<link href="./style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div align="center">
<header>
	<h2>공지 사항 보기</h2>
</header>
<nav>
	<%@ include file="menu_header.jsp" %>
</nav>
<section>
<%	Notice n = (Notice)request.getAttribute("NOTICE");	 
	if(n == null) { %>
	<h3>해당 공지사항은 존재하지 않습니다.</h3>
<%	} else { %>
	<table border="1">
	<tr><th class="header">글 번호</th>
		<td class="record"><%= n.getNo() %></td></tr>
	<tr><th class="header">제 목</th>
		<td class="record"><%= n.getTitle() %></td>
	<tr><th class="header">등록일</th>
		<td class="record"><%= n.getEntry_date() %></td></tr>
	<tr><th class="header">내 용</th>
		<td colspan="3" class="record"><textarea rows="5" cols="40" 
			readonly="readonly"><%= n.getContent() %></textarea></td></tr>
	</table>
<%	} %>
</section>
</div>
</body>
</html>









