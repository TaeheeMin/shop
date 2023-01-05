<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>주문목록</h1>
		<table border="1">
			<thead>
			  	<tr>
			  		<th>주문번호<th>
			      	<th>상품이름</th>
			      	<th>상품이미지</th>
			      	<th>주문일자</th>
			      	<th>주문상태</th>
			      	<th>주문가격</th>
			    </tr>
		  	</thead>
		  	<tbody>
		  		<c:forEach var="o" items="${list}">
		     		<tr>
		     			<td>${o.orderCode}</td>
		     			<td><a href='${pageContext.request.contextPath}/orders/ordersOne?orderCode=${o.orderCode}'>${o.goodsTitle}</td>
						<td>
							<img src="${pageContext.request.contextPath}/goodsimg/${o.filename}" width="200" height="200">
						</td>
						<td>${o.createdate}</td>
						<td>${o.orderState}</td>
						<td>${o.orderPrice}</td>
	    			</tr>
				</c:forEach>
		  	</tbody>
		</table>
</body>
</html>