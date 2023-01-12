<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>${loginCustomer.customerId}님의 배송지목록</h3>
	<!-- 주소추가 -->
	<form action="${pageContext.request.contextPath}/AddressAddByOrder" method="post">
		<textarea rows="4" cols="40" name="address"></textarea>
		<input type="hidden" name="customerId" value="${loginCustomer.customerId}">
		<div>
			<button type="submit">배송지 추가</button>
		</div>
	</form>
	<hr>
	
	<!-- 주소목록 -->
	<h5>배송지 목록</h5>
	<form action="${pageContext.request.contextPath}/orders/ordersAdd" method="get">
		<c:forEach var="a" items="${list}">
			<p>
				<span><input type="radio" name="addressCode" class="addressCode" value="${a.addressCode}"></span>
				${a.address}
				<span><a href="${pageContext.request.contextPath}/AddressRemoveOrder?customerId=${a.customerId}&address=${a.address}">삭제</a></span>
			</p>
		</c:forEach>
		<!-- <input type="hidden" name="cartList" value="${cartList}"> -->
		<button type="submit">배송지 선택</button>
	</form>
</body>
</html>