<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>상품 상세보기</title>
		
	</head>
	<body>
		<h1>상품 상세보기</h1>
		<a href="${pageContext.request.contextPath}/Home">홈으로</a>
		<a href="${pageContext.request.contextPath}/GoodsList">상품목록</a>
		<a href="${pageContext.request.contextPath}/CartList">장바구니</a>
		<!-- #### list #### -->
		<table border="1">
			<tr>
				<th>NO</th>
				<td>${goodsOne.goodsCode}</td>
			</tr>
			<tr>
				<th>TITLE</th>
				<td>${goodsOne.goodsTitle}</td>
			</tr>
			<tr>
				<th>ARTIST</th>
				<td>${goodsOne.goodsArtist}</td>
			</tr>
			<tr>
				<th>CONTENT</th>
				<td>${goodsOne.goodsContent}</td>
			</tr>
			<tr>
				<th>PRICE</th>
				<td>${goodsOne.goodsPrice}</td>
			</tr>
			<tr>
				<th>ACTION</th>
				<td>
					<a href="${pageContext.request.contextPath}/CartAdd?goodsCode=${goodsOne.goodsCode}&cartQuantity=1&filename=${goodsOne.filename}&goodsPrice=${goodsOne.goodsPrice}&goodsTitle=${goodsOne.goodsTitle}">cart</a>
					<a href="${pageContext.request.contextPath}/orders/ordersAdd?goodsCode=${goodsOne.goodsCode}&cartQuantity=1&goodsArtist=${goodsOne.goodsArtist}&filename=${goodsOne.filename}&goodsPrice=${goodsOne.goodsPrice}&goodsTitle=${goodsOne.goodsTitle}">구매하기</a>
				</td>
			</tr>
		</table>
	</body>
</html>