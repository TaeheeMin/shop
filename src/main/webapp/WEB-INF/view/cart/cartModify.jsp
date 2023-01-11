<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>수량 수정</title>
	</head>
	<body>
		<h1>장바구니 수정</h1>
		<a href="${pageContext.request.contextPath}/Home">홈으로</a>
		<a href="${pageContext.request.contextPath}/GoodsList">상품목록</a>
		<table border="1">
			<tr>
				<th>상품정보</th>
				<th>상품금액</th>
				<th>수량</th>
				<th>삭제</th>
			</tr>
			<c:forEach var="c" items="${list}">
				<tr>
					<td>
						<input type="hidden" name="goodsCode" value="${c.goodsCode}"> 
						<img src="${pageContext.request.contextPath}/goodsimg/${c.filename}" width="200" height="200">
						<input type="text" name="goodsTitle" value="${c.goodsTitle}" readonly="readonly"> 
					</td>
					<td>
						<input type="text" name="goodsPrice" value="${c.goodsPrice}" readonly="readonly">
						
					</td>
					
					<td>
						<form action="${pageContext.request.contextPath}/orders/ordersAdd" method="get">
							<select name="cartQuantity" id="cartQuantity">
								<c:forEach var="x" begin="1" end="10" step="1">
									<c:choose> 
										<c:when test="${x eq c.cartQuantity}">
											<option value="${x}" selected="selected">${x}</option>
										</c:when> 
										<c:otherwise>
											<option value="${x}">${x}</option>
										</c:otherwise> 
									</c:choose> 
								</c:forEach>
							</select>
						</form>
						<button id="cartQuantityBtn" type="button">수정</button>
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/CartRemove?goodsCode=${c.goodsCode}">삭제</a>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td>총 주문금액</td>
				<td colspan="3">
					<c:set var = "cartPrice" value = "0" />
						<c:forEach var="c" items="${list}">
							<c:set var= "cartPrice" value="${cartPrice + (c.goodsPrice * c.cartQuantity)}"/>
						</c:forEach>
					<input type="text" name="cartPrice" value="${cartPrice}" readonly="readonly">
				</td>
			</tr>
		</table>
	</body>
</html>