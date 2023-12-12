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
<h2>계정 중복 확인</h2>
<%	String id = (String)request.getAttribute("ID");
	String dup = (String)request.getAttribute("DUP"); %>
<form action="idCheck.do" name="frm">
	계 정 : <input type="text" name="ID" value="<%= id %>"/>
	<input type="submit" value="중복 검사"/>
</form>	
<%	if(dup == null){ %>
	<%= id %>는 사용 가능합니다.
	<input type="button" value="사용" onclick="idOK()"/>
<%	} else { %>
	<%= id %>는 이미 사용 중입니다.
<%	} %>
</div>
<script type="text/javascript">
function idOK(){
	var id = document.frm.ID.value;
	opener.document.fm.ID.value = id;
	opener.document.fm.ID.readOnly = true;
	opener.document.fm.idChecked.value = "OK";//중복검사용 파라미터에 값을 넣는다.
	self.close();//팝업창을 닫는다.
}
</script>
</body>
</html>










