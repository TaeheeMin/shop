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
	//관리자용
		$(document).ready(function() {
			$('#orderState').change(function() {
               $('#orderFormByAdmin').submit();
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
		     			<td>${od.orderState}
		     			<form id="orderForm" method="get" action="${pageContext.request.contextPath}/orders/ordersModify">
		     			<input type="hidden" name="orderCode" value="${od.orderCode}">
		     			<input type="hidden" name="customerId" value="${od.customerId}">						
						<select name="orderState" id="orderState">
						<!-- 구매자만 볼수있게 -->
						<c:if test="${od.customerId eq loginCustomer.customerId}">					
							<c:if test="${od.orderState eq '취소'}">
								<option value="결제">결제</option>
								<option value="취소" selected="selected" >취소</option>
								<option value="배송중">배송중</option>
								<option value="배송완료">배송완료</option>
								<option value="구매확정">구매확정</option>
							</c:if>
							<c:if test="${od.orderState eq '구매확정'}">
							<option value="결제">결제</option>
								<option value="취소">취소</option>
								<option value="배송중">배송중</option>
								<option value="배송완료">배송완료</option>
								<option value="구매확정" selected="selected">구매확정</option>
							</c:if>
						</c:if>
						</form>
							
						<!-- 관리자용 -->
						<form id="orderFormByAdmin" method="get" action="${pageContext.request.contextPath}/orders/ordersModifyByAdmin">
		     			<input type="hidden" name="orderCode" value="${od.orderCode}">
		     			<input type="hidden" name="customerId" value="${od.customerId}">			
						<c:if test="${loginEmp.auth_code} > 1">
							<c:if test="${od.orderState eq '취소'}">
								<option value="결제">결제</option>
								<option value="취소" selected="selected" >취소</option>
								<option value="배송중">배송중</option>
								<option value="배송완료">배송완료</option>
								<option value="구매확정">구매확정</option>
							</c:if>		
							<c:if test="${od.orderState eq '배송중'}">
								<option value="결제">결제</option>
								<option value="취소">취소</option>
								<option value="배송중" selected="selected">배송중</option>
								<option value="배송완료">배송완료</option>
								<option value="구매확정">구매확정</option>
							</c:if>
							<c:if test="${od.orderState eq '배송완료'}">
								<option value="결제">결제</option>
								<option value="취소">취소</option>
								<option value="배송중">배송중</option>
								<option value="배송완료" selected="selected">배송완료</option>
								<option value="구매확정">구매확정</option>
							</c:if>
						</c:if>
						</select>
					</form>
		     			</td>
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
						
							<a href="${pageContext.request.contextPath}/review/reviewAdd?orderCode=${od.orderCode}">
							리뷰작성하기
							</a>
							
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