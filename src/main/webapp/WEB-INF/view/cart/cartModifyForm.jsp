<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	<form id="cartQuantityForm" action="${pageContext.request.contextPath}/CartModify?cartQuantity=${c.cartQuantity}&goodsCode=${goodsCode}" method="get">
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
	</body>
</html>