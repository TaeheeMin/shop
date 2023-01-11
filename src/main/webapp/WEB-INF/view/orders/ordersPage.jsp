<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
			   var total = $('#totalPrice').val();
			   console.log($('#totalPrice').val());
				
			   $('#point').change(function() {
				   if($("#point").is(":checked")){
					   $('#totalPrice').attr('value', total-${loginCustomer.point});
			        } else {
					   $('#totalPrice').attr('value', total);
			        }
				});
			});
		</script>
	</head>
	<body>
		<h1>구매페이지</h1>
		<form method="post" action="${pageContext.request.contextPath}/orders/ordersAdd">
			<!-- 주문할 상품 목록 -->
			
			<h2>상품정보</h2>
			<table border='1'>
				<tr>
					<td>앨범이름</td>
					<td>앨범</td>
					<td>가격</td>
					<td>주문수량</td>
					<td>주문가격</td>
				</tr>
				<c:forEach var="c" items="${cartList}">
					<tr>
						<td>${c.goodsTitle}</td>
						<td><img src="${pageContext.request.contextPath}/goodsimg/${c.filename}" width="200" height="200"></td>
						<td>${c.goodsPrice}</td>
						<td>${c.cartQuantity }</td>
						<td>${c.cartQuantity*c.goodsPrice}</td>
					</tr>
				</c:forEach>
			</table>		
			
						
			<!-- 고객 정보 -->
			<h2>고객정보</h2>
			<table border="1">
				 <tr>
					<th>ID</th>
					<th>이름</th>
					<th>연락처</th>				
					<th>포인트</th>				
				</tr>
				<tr>
					<td><input type="text" name="customerId" value="${loginCustomer.customerId}" readonly="readonly"></td>
					<td><input type="text" name="customerName" value="${loginCustomer.customerName}" readonly="readonly"></td>
					<td><input type="text" name="customerPhone" value="${loginCustomer.customerPhone}" readonly="readonly"></td>
					<td><input type="text" name="customerPhone" value="${loginCustomer.point}" readonly="readonly"></td>
				</tr> 			
			</table>
	
			<div>
				배송주소
			</div>
			<c:forEach var="a" items="${addressList}">
				<div>
					<span><input type="radio" name="addressCode" class="addressCode" value="${a.addressCode}"></span>
					${a.address}
					<span><a href="${pageContext.request.contextPath}/AddressRemoveOrder?customerId=${a.customerId}&address=${a.address}&goodsCode=${g.goodsCode}">삭제</a></span>
				</div>
			</c:forEach>
			<br>
			<br>
			총주문 가격 :
			<c:set var = "total" value = "0" />
			<c:forEach var="result" items="${cartList}" varStatus="status">			
			<input type="text" name="orderPrice" value="${result.goodsPrice*result.cartQuantity}" readonly="readonly"></td>	
			<c:set var= "total" value="${total + result.goodsPrice*result.cartQuantity}"/>
			</c:forEach>
			
			<input type="checkbox" id="point"> 포인트 사용
			<input type="text" id="totalPrice" value="${total}">
			
			<c:out value="${total}"/>
			<button type="submit">결제</button>
		</form>
		
		<!-- 주소추가 폼 (미완)
		<form action="${pageContext.request.contextPath}/orders/ordersAdd" method="get">
			<div>
				주소추가
			</div>
			<div>
				<input type="hidden" name="orderQuantity" value="${orderQuantity}">
				<input type="hidden" name="goodsCode" value="${g.goodsCode}">
			</div>
			<div>
				<textarea rows="4" cols="40" name="address"></textarea>
			</div>
			<div><button type="submit">추가</button></div>
		</form>
		 -->
		
		
	</body>
</html>