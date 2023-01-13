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
			   let total = $('#totalPrice').val();
			   $('#usePoint').blur(function() {
				  console.log($('#usePoint').val()); 
				   $('#totalPrice').attr('value', total-$('#usePoint').val());
				   $('#point').attr('value',${loginCustomer.point}-$('#usePoint').val())
				});
			   
			   $('#pointAll').change(function() {
				   if($("#pointAll").is(":checked")){
					   if(total-${loginCustomer.point} < 0){
						   $('#totalPrice').attr('value', 0);
						   $('#point').attr('value',${loginCustomer.point}-total)
					   }else{
						   $('#totalPrice').attr('value', total-${loginCustomer.point});
						   $('#point').attr('value', 0)
					   }
			        } else {
					   $('#totalPrice').attr('value', total);
					   $('#point').attr('value', ${loginCustomer.point})
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
					<td>선택</td>
					<td>앨범이름</td>
					<td>앨범</td>
					<td>가격</td>
					<td>주문수량</td>
					<td>주문가격</td>
				</tr>
				<c:forEach var="c" items="${cartList}">
					<tr>
						<td><input type="checkbox" name="pointCk" value="${c.goodsCode }"></td>
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
			<a href="${pageContext.request.contextPath}/AddressList">배송지선택</a>
			<table border="1">
				 <tr>
					<th>ID</th>
					<td><input type="text" name="customerId" value="${loginCustomer.customerId}" readonly="readonly"></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" name="customerName" value="${loginCustomer.customerName}" readonly="readonly"></td>
				</tr>
				<tr>	
					<th>연락처</th>		
					<td><input type="text" name="customerPhone" value="${loginCustomer.customerPhone}" readonly="readonly"></td>
				</tr>
				<tr>		
					<th>포인트</th>
					<td><input type="text" name="point" id="point" value="${loginCustomer.point}" readonly="readonly"></td>				
				</tr>
				<tr>
					<th>배송주소</th>
					<td>
						<c:if test="${myAddress != null}">			
							<div>
								<input type="text" name="address" value="${myAddress.address}" readonly="readonly">
								<input type="hidden" name="addressCode" value="${myAddress.addressCode}">
							</div>
						</c:if>
					</td>
				</tr> 			
			</table>
			
			<br>
			<br>
			
			<!-- 총 주문가격 -->
			총주문 가격 :
			<c:set var = "total" value = "0" />
			<c:forEach var="result" items="${cartList}" varStatus="status">			
				<c:set var= "total" value="${total + result.goodsPrice*result.cartQuantity}"/>
			</c:forEach>
			<input type="text" name="orderPrice" id="totalPrice" value="${total }">
				<p>포인트 사용 : <input type="text" name="usePoint" id="usePoint"></p>
				<p>전액사용 <input type="checkbox" id="pointAll" name="pointAll" value=""></p>
			 
			
			<button type="submit">결제</button>
		</form>		
	</body>
</html>