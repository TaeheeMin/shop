<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<form action="${pageContext.request.contextPath}/AddressAdd" method="post">
		<div>
			주소추가
		</div>
		<div>
			<textarea rows="4" cols="40" name="address"></textarea>
		</div>
		<input type="hidden" name="customerId" value="${customerOne.customerId}">
		<div><button type="submit">추가</button></div>
	</form>	
	<hr>
	<div>
		주소목록
	</div>
	<c:forEach var="a" items="${list}">
		<p>
			${a.address}
			<span><a href="${pageContext.request.contextPath}/AddressRemove?customerId=${a.customerId}&address=${a.address}">삭제</a></span>
		</p>
	</c:forEach>
	<div>대표주소설정 및 최대3개로 제한하는 기능 구현예정 (미완)</div>
	
</body>
</html>