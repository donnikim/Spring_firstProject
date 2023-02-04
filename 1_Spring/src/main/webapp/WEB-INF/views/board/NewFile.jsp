<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="insertProduct.do" method="post">
	<h1>상품 등록</h1>
	<div><input type="text" placeholder="상품명을 입력하세요."></div>
	<div><input type="number" min=0 placeholder="가격을 입력하세요"></div>
	<div><textarea placeholder="내용을 입력하세요"></textarea></div>
	<button>등록하기</button>
	</form>
</body>
</html>