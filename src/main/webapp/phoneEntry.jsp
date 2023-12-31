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
	<h2>상품 등록 화면</h2>
</header>
<nav>
	<%@ include file="menu_header.jsp" %>
</nav>
<section>
<form action="phoneEntry.do" method="post" name="item" onsubmit="return check(this)">
<input type="hidden" name="codeChecked">
<table>
	<tr><th>제품번호</th><td><input type="text" name="CODE" size="6"/>
		<input type="button" value="중복 검사" onclick="codeCheck()"/></td></tr>
	<tr><th>카테고리</th><td><select name="CATEGORY">
		<option value="A">폰</option><option value="B">패드</option><option value="C">맥</option></select></td></tr>
	<tr><th>제품명</th><td><input type="text" name="P_NAME" size="5"/></td></tr>
	<tr><th>제품설명</th><td><textarea rows="5" cols="DESC" name="DESC"></textarea>
		</td></tr>
	<tr><th>이미지 파일명</th><td><input type="text" name="IMAGE" size="10"/>
		</td></tr>
	<tr><th>가 격</th><td><input type="text" name="PRICE" size="10"/></td></tr>
	<tr><th>분 류</th><td><input type="text" name="BUNRYU" size="5"/></td></tr>
	<tr><td colspan="2" align="center">
		<input type="submit" value="등 록"/>
		<input type="reset" value="취 소"/></td></tr>
</table>
</form>



<script type="text/javascript">
function check(frm){
	if( ! confirm("정말로 등록하시겠습니까?")) return false;
}
function codeCheck(){
	var code = document.item.CODE.value;
	if(code == ''){
		alert("상품코드를 입력하세요."); document.item.CODE.focus(); return;
	}
	var url = "codeCheck.do?CODE="+code;
	window.open(url,"_blank_","width=450,height=200");
}

</script>
</section>
</div>
</body>
</html>






