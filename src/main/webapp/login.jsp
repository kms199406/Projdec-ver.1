<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<link href="./style.css" rel="stylesheet" type="text/css"></head>
<body>
<div align="center">
<header>
	<h2>로그인</h2>
</header>
<nav>
	<%@ include file="menu_header.jsp" %>
</nav>
<section>
<%	String check = request.getParameter("CHECK");
	if(check !=null) { %>
	<script type="text/javascript">
		alert("이 서비스를 이용하시려면, 로그인 해야 합니다.");
	</script>
<%	} %>
<form action="login.do" method="post" onsubmit="return check(this)">
<table>
	<tr height="40"><th>사용자ID</th>
		<td><input type="text" name="ID" size="10"/></td></tr>
	<tr height="40"><th>암 호</th>
		<td><input type="password" name="PWD" size="10"/></td></tr>
</table>
<table>
	<tr><td align="center"><input type="submit" value="로그인"/>
		<input type="reset" value="취 소"/></td></tr>
</table><br/>
</form>
	<a href="userEntry.jsp">■ 신규 가입자 등록</a>
</section>
</div>
</body>
</html>








