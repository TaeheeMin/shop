<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<div>
			<a href="${pageContext.request.contextPath}/NoticeList">공지목록</a>
			<a href="${pageContext.request.contextPath}/GoodList">상품목록</a>
			
		</div>
		<div>
			<a href="${pageContext.request.contextPath}/LoginEmp">직원로그인</a>
			<a href="${pageContext.request.contextPath}/AddEmp">직원등록(직원이상만 가능)</a>
			<a href="${pageContext.request.contextPath}/LoginCustomer">회원로그인</a>
			<a href="${pageContext.request.contextPath}/AddCustomer">회원가입</a>
		</div>
	</body>
</html>