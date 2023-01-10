<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<h1>구매페이지</h1>
	<form method="post" action="${pageContext.request.contextPath}/orders/ordersAdd">
		<!-- 주문할 상품 목록 -->
		<c:forEach var="g" items="${goodsList}">
			<div>
				상품정보: 
				<br>앨범이름 <input type="text" name="goodsTitle" value="${g.goodsTitle}">
				<br>아티스트 <input type="text" name="goodsArtist" value="${g.goodsArtist}">
				<br>가격 <input type="text" name="goodsPrice" value="${g.goodsPrice}"> 
			</div>
			<br>		
			
			<br>					
			<div> 
				주문수량 :	<input type="text" name="orderQuantity" value="${orderQuantity}">								
			</div>			
			<div> 
				주문가격 :  <input type="text" name="orderPrice" value="${g.goodsPrice*orderQuantity}">
			</div>			
			<div> 
				<input type="hidden" name="goodsCode" value="${g.goodsCode}">
			</div>
		</c:forEach>
		<!-- 고객 정보 -->
		<div> 
			주문자 정보:  
			<br>고객ID<input type="text" name="customerId" value="${loginCustomer.customerId}">
			<br>고객이름<input type="text" name="customerName" value="${loginCustomer.customerName}">				
			<br>고객연락처<input type="text" name="customerPhone" value="${loginCustomer.customerPhone}">
		</div>

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
		<hr>				
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