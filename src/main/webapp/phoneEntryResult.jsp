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
<%	String result = request.getParameter("R");
	if(result.equals("0")){ %>
	<script type="text/javascript">
		alert("상품이 등록되지 않았습니다. 관리자에게 문의하세요.");
		location.href="PhonListServlet";//목록으로 돌아간다.
	</script>
<%	} else { %>
	<script type="text/javascript">
		alert("새 상품이 등록되었습니다.");
		location.href="PhonListServlet";//목록으로 돌아간다.
	</script>
<%	} %>
</body>
</html>






