<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>장바구니</title>
	</head>
	<body>
		<h1>장바구니 목록</h1>
		<a href="${pageContext.request.contextPath}/Home">홈으로</a>
		<form action="" method="get">
			<table border="1">
				<c:forEach var="c" items="${list}" varStatus="s">
					<tr>
						<td>
							<img src="${pageContext.request.contextPath}/goodsimg/${c.filename}" width="200" height="200">
						</td>
						<td>${c.goodsTitle}</td>
						<td>${c.goodsPrice}</td>
						<td>
							<select name="cartQuantity">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
							</select>
							${c.cartQuantity}
						</td>
						<td>
							<a href="${pageContext.request.contextPath}/CartRemove?goodsCode=${c.goodsCode}">삭제</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<button type="submit">주문</button>
		</form>
	</body>
</html>