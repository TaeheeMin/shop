<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<c:forEach var="o" items="${list}">
	<script>
	//관리자용
	$(document).ready(function() {
		$('.orderState${o.orderCode}').change(function() {
           $('#orderFormByAdmin${o.orderCode}').submit();
        })
     });	

	</script>
	</c:forEach>
	<script>
	$(document).ready(function() {
		$('#rowPerPage').change(function() {
			$('#listForm').submit();
		});
		$('#searchBtn').click(function() {
			$('#listForm').submit();
		});
	});
	</script>
</head>
<body>
	<h1>주문목록</h1>
	
	<!-- #### search #### -->
		<form action="${pageContext.request.contextPath}/orders/ordersListByAdmin" method="get" id="listForm">
			<div>
				<input type="radio" name="category" id="category" value="">전체
				<input type="radio" name="category" id="category" value="결제">결제
				<input type="radio" name="category" id="category" value="취소">취소
				<input type="radio" name="category" id="category" value="구매확정">구매확정
				<input type="radio" name="category" id="category" value="배송중">배송중
				<input type="radio" name="category" id="category" value="배송완료">배송완료
			</div>
			<select name="rowPerPage" id="rowPerPage">
				<c:if test="${rowPerPage == 10}">
					<option value="10" selected="selected">10</option>
					<option value="20">20</option>
					<option value="30">30</option>
				</c:if>
				<c:if test="${rowPerPage == 20}">
					<option value="10">10</option>
					<option value="20" selected="selected">20</option>
					<option value="30">30</option>
				</c:if>
				<c:if test="${rowPerPage == 30}">
					<option value="10">10</option>
					<option value="20">20</option>
					<option value="30" selected="selected">30</option>
				</c:if>
			</select>
			<select name="search" id="search">
				<option value="gd.goods_title">앨범</option>				
			</select>
			
			<input type="search" placeholder="Search" name="word" id="word" value="${word}">
     		<button type="button" id="searchBtn">검색</button>
		</form>
		
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
		     			<td><a href='${pageContext.request.contextPath}/orders/ordersOne?orderCode=${o.orderCode}'>${o.goodsTitle}</a></td>
						<td>
							<img src="${pageContext.request.contextPath}/goodsimg/${o.filename}" width="200" height="200">
						</td>
						<td>${o.createdate}</td>
						<td>${o.orderState}
								<!-- 관리자용 -->
						<form id="orderFormByAdmin${o.orderCode}" method="post" action="${pageContext.request.contextPath}/orders/ordersModifyByAdmin">		     			
		     			<input type="hidden" name="orderCode" value="${o.orderCode}">		     		     			
		     			<select name="orderState" class="orderState${o.orderCode}">			
							<c:if test="${o.orderState eq '결제' || o.orderState eq '구매확정'}">
								<option value="결제" selected="selected">결제</option>
								<option value="취소">취소</option>
								<option value="배송중">배송중</option>
								<option value="배송완료">배송완료</option>								
							</c:if>
							<c:if test="${o.orderState eq '취소'}">								
								<option value="취소" selected="selected" >취소</option>																
							</c:if>		
							<c:if test="${o.orderState eq '배송중'}">
								<option value="결제">결제</option>
								<option value="취소">취소</option>
								<option value="배송중" selected="selected">배송중</option>
								<option value="배송완료">배송완료</option>							
							</c:if>
							<c:if test="${o.orderState eq '배송완료'}">							
								<option value="배송완료" selected="selected">배송완료</option>								
							</c:if>
						</select>
						</form>
						</td>						
					
						<td>${o.orderPrice}</td>
			
	    			</tr>
				</c:forEach>
		  	</tbody>
		</table>
			<!-- 페이징 -->
		<div>
			<a href="${pageContext.request.contextPath}/orders/ordersListByAdmin?rowPerPage=${rowPerPage}&currentPage=1">처음</a>
			
			<c:if test="${currentPage > 1}">
				<a href="${pageContext.request.contextPath}/orders/ordersListByAdmin?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}">이전</a>
			</c:if>
			
			<c:forEach var="x" begin="${beginPage}" end="${endPage}" step="1">
				<a href="${pageContext.request.contextPath}/orders/ordersListByAdmin?rowPerPage=${rowPerPage}&currentPage=${x}">${x}</a>
			</c:forEach>
			<c:if test="${currentPage < lastPage}">
				<a href="${pageContext.request.contextPath}/orders/ordersListByAdmin?rowPerPage=${rowPerPage}&currentPage=${currentPage +1}">다음</a>
			</c:if>
			
			<a href="${pageContext.request.contextPath}/orders/ordersListByAdmin?rowPerPage=${rowPerPage}&currentPage=${lastPage}">마지막</a>
		</div>
</body>
</html>