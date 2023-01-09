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
			<div>
				상품정보: 
						<br>앨범이름 <input type="text" name="goodsTitle" value="${goodsTitle}">
						<br>아티스트 <input type="text" name="goodsArtist" value="${goodsArtist}">
						<br>가격 <input type="text" name="goodsPrice" value="${goodsPrice}"> 
			</div>
			<br>		
			<div> 
				주문자 정보:  
				<br>고객ID<input type="text" name="customerId" value="${loginCustomer.customerId}">
				<br>고객이름<input type="text" name="customerName" value="${loginCustomer.customerName}">
				<br>고객주소<input type="text" name="address">
				<br>고객연락처<input type="text" name="customerPhone" value="${loginCustomer.customerPhone}">
			</div>
			<br>					
			<div> 
				주문수량 :  <input type="text" name="orderQuantity">
			</div>			
			<div> 
				주문가격 :  <input type="text" name="orderPrice" value="${orderPrice}">
			</div>			
			<div> 
				<input type="hidden" name="goodsCode" value="${goodsCode}">
			</div>
			<!-- 셀렉트로 선택할수있게 변경해야함 -->
			<div>	
				주소코드 :<input type="text" name="addressCode" value="${addressCode}">
			</div>						
			<button type="submit">결제</button>
		</form>
	
	
</body>
</html>