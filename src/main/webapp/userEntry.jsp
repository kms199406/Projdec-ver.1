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
	<h2>가입자 등록</h2>
</header>
<nav>
	<%@ include file="menu_header.jsp" %>
</nav>
<section>
<form action="userEntry.do" method="post" name="fm" onsubmit="return check(this)">
<input type="hidden" name="idChecked">
<table>
<tr height="40"><th>가입자ID</th><td><input type="text" name="ID" size="10"/>
	<input type="button" value="중복검사" onclick="idCheck()"/></td></tr>
<tr height="40"><th>암 호</th><td><input type="password" name="PWD" size="10"/>
	</td></tr>
<tr height="40"><th>암호 확인</th><td><input type="password" name="REPWD" 
	size="10"/></td></tr>
<tr height="40"><th>이 름</th><td><input type="text" name="NAME" size="10"/>
	</td></tr>
<tr height="40"><th>주 소</th><td><input type="text" name="ADDR" size="20"/>
	</td></tr>
<tr height="40"><th>이메일</th><td><input type="text" name="EMAIL" size="20"/>
	</td></tr>
<tr height="40"><th>연락처</th><td><input type="text" name="PHONE" size="10"/>
	</td></tr>
<tr height="40"><th>성 별</th><td>남<input type="radio" name="GENDER" value="M"/>
	여<input type="radio" name="GENDER" value="F"/></td></tr>
<tr height="40"><th>직 업</th><td><select name="JOB">
		<option>회사원</option><option>학생</option><option>자영업</option>
		<option>주부</option><option>기타</option></select></td></tr>
<tr height="40"><td colspan="2" align="center">
	<input type="submit" value="가입하기"/>
	<input type="reset" value="취 소"/></td></tr>	
</table>
</form>
<script type="text/javascript">
function idCheck(){
	if(document.fm.ID.value == ''){
		alert("계정을 입력하세요."); document.fm.ID.focus(); return;
	}
	var url = "idCheck.do?ID="+document.fm.ID.value;
	window.open(url,"_blank_","width=450,height=200");
}
function check(frm){
	if(frm.ID.value == ''){
		alert("계정을 입력하세요."); frm.ID.focus(); return false;
	}
	if(frm.idChecked.value == ''){
		alert("계정 중복검사를 해야 합니다."); return false;
	}
	if(frm.PWD.value == ''){
		alert("암호를 입력하세요."); frm.PWD.focus(); return false;
	}
	if(frm.PWD.value != frm.REPWD.value){
		alert("암호가 일치하지 않습니다."); frm.PWD.focus(); return false;
	}
	if(frm.NAME.value == ''){
		alert("이름을 입력하세요."); frm.NAME.focus(); return false;
	}
	if( ! frm.GENDER[0].checked && ! frm.GENDER[1].checked){
		alert("성별을 선택하세요."); frm.GENDER[0].focus(); return false;
	}
	if( ! confirm("정말로 가입하시겠습니까?")) return false;
}
</script>
</section>
</div>
</body>
</html>








