<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>상품목록</title>
	</head>
	<body>
		<h1>상품목록</h1>
		<a href="${pageContext.request.contextPath}/Home">홈으로</a>
		<a href="${pageContext.request.contextPath}/CartList">장바구니</a>
		<table border="1">
			<tr>
				<c:forEach var="m" items="${list}" varStatus="s">
					<c:if test="${s.index != 0 && s.index % 5 == 0 }">
						</tr><tr>
					</c:if>
					
					<td>
						<div>${m.goodsCode}</div>
						<div>${m.goodsName}</div>
						<div>${m.goodsPrice}</div>
						<div>${m.soldout}</div>
						<div>
							<img src="${pageContext.request.contextPath}/goodsimg/${m.filename}" width="200" height="200">
						</div>
						<div>
							<a href="${pageContext.request.contextPath}/CartAdd?goodsCode=${m.goodsCode}">cart</a>
						</div>
					</td>
				</c:forEach>
			</tr>
		</table>
	</body>
</html>