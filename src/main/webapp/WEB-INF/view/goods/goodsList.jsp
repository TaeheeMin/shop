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
		<table border="1">
			<tr>
				<c:forEach var="m" items="${list}" varStatus="s">
					<c:if test="${s.index != 0 && s.index % 5 == 0 }">
						</tr><tr>
					</c:if>
					
					<td>
						<div>${m.goodsName}</div>
						<div>${m.goodsPrice}</div>
						<div>${m.soldout}</div>
						<div>
							<img src="${pageContext.request.contextPath}/goodsimg/${m.filename}" width="200" height="200">
						</div>
						<div>수정 삭제</div>
					</td>
				</c:forEach>
			</tr>
		</table>
	</body>
</html>