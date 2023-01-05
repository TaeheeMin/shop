<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>MY PAGE</h1>
	<div>
		${customerOne.customerName}(${customerOne.customerId})님
	</div>
	<div>
		보유 포인트 : ${customerOne.point}
	</div>
	<div>
		가입날짜 : ${customerOne.createdate}
	</div>
	<div>
		<a href="">로그아웃</a>
	</div>
	<div>
		<a href="">회원정보수정</a>
	</div>
	<div>
		<a href="">주소보기</a>
	</div>
</body>
</html>