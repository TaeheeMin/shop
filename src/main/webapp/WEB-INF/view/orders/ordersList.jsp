<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
	//관리자용
	$(document).ready(function() {
		$('#orderState').change(function() {
           $('#orderForm').submit();
        })
     });		

	</script>
</head>
<body>
	<h1>주문목록</h1>
		<table border="1">
			<thead>
			  	<tr>
			  		<th>주문번호</th>
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
						<td>${o.orderState}
						<form id="orderForm" method="post" action="${pageContext.request.contextPath}/orders/ordersModify">		     			
		     			<input type="hidden" name="orderCode" value="${o.orderCode}">		     		     			
		     			<select name="orderState" id="orderState">			
							<c:if test="${o.orderState eq '결제' || o.orderState eq '배송중' || o.orderState eq '배송완료'}">
								<option value="결제" selected="selected">결제</option>
								<option value="취소">취소</option>
								<option value="구매확정">구매확정</option>							
							</c:if>
							<c:if test="${o.orderState eq '취소'}">
								<option value="결제">결제</option>
								<option value="취소" selected="selected" >취소</option>
								<option value="구매확정">구매확정</option>								
							</c:if>		
							<c:if test="${o.orderState eq '구매확정'}">
								<option value="결제">결제</option>
								<option value="취소">취소</option>
								<option value="구매확정" selected="selected">구매확정</option>														
							</c:if>						
						</select>
						</td>						
					</form>

						
						</td>
						<td>${o.orderPrice}</td>
	    			</tr>
				</c:forEach>
		  	</tbody>
		</table>
</body>
</html>