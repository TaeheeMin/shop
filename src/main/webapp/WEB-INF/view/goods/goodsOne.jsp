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
			<c:forEach var="goodsOne" items="${goodsOne}">
				<tr>
					<th>NO</th>
					<td><c:out value="${goodsOne.goodsCode}"></c:out></td>
				</tr>
				<tr>
					<th>TITLE</th>
					<td><c:out value="${goodsOne.goodsTitle}"></c:out></td>
				</tr>
				<tr>
					<th>IMG</th>
					<td>
						<img src="${pageContext.request.contextPath}/goodsimg/${goodsOne.filename}" width="200" height="200">
					</td>
				</tr>
				<tr>
					<th>ARTIST</th>
					<td><c:out value="${goodsOne.goodsArtist}"></c:out></td>
				</tr>
				<tr>
					<th>CONTENT</th>
					<td><c:out value="${goodsOne.goodsContent}"></c:out></td>
				</tr>
				<tr>
					<th>PRICE</th>
					<td><c:out value="${goodsOne.goodsPrice}"></c:out></td>
				</tr>
				<tr>
					<th>VIEW</th>
					<td><c:out value="${goodsOne.view}"></c:out></td>
				</tr>
				<tr>
					<th>ACTION</th>
					<td>
						<a href="${pageContext.request.contextPath}/CartAdd?goodsCode=${goodsOne.goodsCode}&cartQuantity=1&filename=${goodsOne.filename}&goodsPrice=${goodsOne.goodsPrice}&goodsTitle=${goodsOne.goodsTitle}">cart</a>
						<a href="${pageContext.request.contextPath}/orders/ordersAdd?goodsCode=${goodsOne.goodsCode}&cartQuantity=1&goodsArtist=${goodsOne.goodsArtist}&filename=${goodsOne.filename}&goodsPrice=${goodsOne.goodsPrice}&goodsTitle=${goodsOne.goodsTitle}">구매하기</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>