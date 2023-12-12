<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
	<h2>업데이트 사항 등록</h2>
</header>
<nav>
	<%@ include file="menu_header.jsp" %>
</nav>
<section>
<form action="noticeEntry.do" method="post" onsubmit="return check(this)">
<table border="1">
	<tr><th>제 목</th><td><input type="text" name="TITLE" size="105"/></td></tr>
	<tr><th>내 용</th><td><textarea rows="15" cols="107" name="CONTENT"></textarea>
		</td></tr>
	<tr><td colspan="2" align="center">
		<input type="submit" value="등 록"/>
		<input type="reset" value="취 소"/></td></tr>
</table>
</form>
<script type="text/javascript">
function check(frm){
	if(frm.TITLE.value == ''){
		alert("제목을 입력하세요."); frm.TITLE.focus(); return false;
	}
	if(frm.CONTENT.value == ''){
		alert("내용을 입력하세요."); frm.CONTENT.focus(); return false;
	}
	if( ! confirm("정말로 등록하시겠습니까?")) return false;
}
</script>
</section>
</div>
</body>
</html>







