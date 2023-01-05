<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>주문상세보기</h1>
		<table border="1">
			<thead>
			  	<tr>
			  		<th>주문번호<th>
			      	<th>주문수량</th>
			      	<th>주문가격</th>
			      	<th>주문상태</th>
			      	<th>주문일자</th>
			      	<th>상품이름</th>
			      	<th>상품가격</th>
			      	<th>주문자이름</th>
			      	<th>주문자연락처</th>
			      	<th>배송지</th>
			      	<th>상품이미지</th>			      	
			    </tr>
		  	</thead>
		  	<tbody>
		  		<c:forEach var="o" items="${list}">
		     		<tr>
		     			<td>${o.orderCode}</td>
		     			<td>${o.orderQuantity}</td>
		     			<td>${o.orderPrice}</td>
		     			<td>${o.orderState}</td>
		     			<td>${o.createdate}</td>
		     			<td>${o.goodsTitle}</td>
		     			<td>${o.goodsPrice}</td>
		     			<td>${o.customerName}</td>
		     			<td>${o.customerPhone}</td>
		     			<td>${o.address}</td>
						<td>
							<img src="${pageContext.request.contextPath}/goodsimg/${o.filename}" width="200" height="200">
						</td>	
	    			</tr>
				</c:forEach>
		  	</tbody>
		</table>
</body>
</html>