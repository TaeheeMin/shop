<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>문의사항</title>
	</head>
	<body>
		<h1>문의사항-관리자용</h1>
		<a href="${pageContext.request.contextPath}/Home">홈으로</a>
		<table border="1">
			<tr>
				<c:forEach var="m" items="${list}" varStatus="s">
					<td>
						<div>${m.goodsCode}</div>
						<div>${m.goodsTitle}</div>
						<div>${m.goodsArtist}</div>
						<div>${m.goodsPrice}</div>
						<div>${m.soldout}</div>
						<div>
							<img src="${pageContext.request.contextPath}/goodsimg/${m.filename}" width="200" height="200">
						</div>
						<div>
							<a href="${pageContext.request.contextPath}/CartAdd?goodsCode=${m.goodsCode}&cartQuantity=1&filename=${m.filename}&goodsPrice=${m.goodsPrice}&goodsTitle=${m.goodsTitle}">cart</a>
							<a href="${pageContext.request.contextPath}/orders/ordersAdd?goodsCode=${m.goodsCode}&cartQuantity=1&goodsArtist=${m.goodsArtist}&filename=${m.filename}&goodsPrice=${m.goodsPrice}&goodsTitle=${m.goodsTitle}">구매하기</a>
						</div>
					</td>
				</c:forEach>
			</tr>
		</table>
	</body>
</html>