<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
	//고객용 
		$(document).ready(function() {
			$('#orderState').change(function() {
               $('#orderForm').submit();
            })
         });
    </script>
    
<title>Insert title here</title>
</head>
<body>
	<h1>주문상세보기</h1>
		<table border="1">
			<thead>
			  	<tr>
			  		<th>주문번호</th>
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
			      	<th>리뷰작성</th>
			      	<th>삭제</th>			      	
			    </tr>
		  	</thead>
		  	<tbody>
		  		<c:forEach var="od" items="${list}">
		     		<tr>
		     			<td>${od.orderCode}</td>
		     			<td>${od.orderQuantity}</td>
		     			<td>${od.orderPrice}</td>
		     			<td>${od.orderState}</td>
		     			<td>${od.createdate}</td>
		     			<td>${od.goodsTitle}</td>
		     			<td>${od.goodsPrice}</td>
		     			<td>${od.customerName}</td>
		     			<td>${od.customerPhone}</td>
		     			<td>${od.address}</td>
						<td>
							<img src="${pageContext.request.contextPath}/goodsimg/${od.filename}" width="200" height="200">
						</td>
						
						<td>
							<c:if test="${od.orderState eq '구매확정'}">						
							<a href="${pageContext.request.contextPath}/review/reviewAdd?orderCode=${od.orderCode}">
							리뷰작성하기
							</a>
							</c:if>
						</td>
						<td>
							<!-- 로그인 세션적용되면 커스터머아이디받아야 삭제가능 -->
							<a href="${pageContext.request.contextPath}/orders/ordersRemove?orderCode=${od.orderCode}">
							삭제
							</a>
						</td>	
	    			</tr>
				</c:forEach>
		  	</tbody>
		</table>
</body>
</html>