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
					<th>CATEGORY</th>
					<td><c:out value="${goodsOne.goodsCategory}"></c:out></td>
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
				<c:if test="${goodsOne.soldout eq 'N'}">
					<tr>
						<th>ACTION</th>
						<td>
							<form action="${pageContext.request.contextPath}/CartAdd" method="get">
								<input type="number" name="cartQuantity">
								<input type="hidden" name="goodsCode" value="${goodsOne.goodsCode}">
								<button type="submit">cart</button>
							</form>
						</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
		
		<!-- #### review #### -->
		<h3>리뷰</h3>
		<c:if test="${review != null}">
		<table border="1">
			<c:forEach var="review" items="${review}">
				<tr>
					<th>CUSTOMERID</th>
					<td><c:out value="${review.customerId}"></c:out></td>
				</tr>
				<tr>
					<th>REVIEW</th>
					<td><c:out value="${review.reviewMemo}"></c:out></td>
				</tr>
				<tr>
					<th>CREATEDATE</th>
					<td><c:out value="${review.createdate}"></c:out></td>
				</tr>
			</c:forEach>
		</table>
		</c:if>
	</body>
</html>