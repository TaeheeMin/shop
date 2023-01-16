<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="description" content="">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    	
    	<!-- Title -->
		<title>Recoder Musice - World Best Music</title>
		
		<!-- Favicon -->
	    <link rel="icon" href="${pageContext.request.contextPath}/resources/img/core-img/favicon.ico">
	
	    <!-- Stylesheet -->
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css">
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
				<script>
			$(document).ready(function() {
			   let total = $('#totalPrice').val(); // 주문금액 
			   let orderPriceCount = ${fn:length(cartList)}; // 상품 개수 
			   let orderPriceList = document.querySelectorAll('.orderPrice'); // 상품별 가격
			   let cartOrderPriceList = document.querySelectorAll('.cartOrderPrice'); // 상품별 원래 가격
			   let customerPoint = $('#point').val(); // 고객 포인트
			   
			   // 포인트 일부사용
			   $('#usePoint').blur(function() {
				   let usePoint = $('#usePoint').val(); // 사용 포인트
				   if(usePoint != 0) {
					   let sharePoint = Math.round(usePoint / orderPriceCount) // 상품별 사용 포인트
					   //console.log('나머지 : ' + usePoint % orderPriceCount);
					   console.log('sharePoint : ' + sharePoint);
					   for(let i = 0; i<orderPriceCount; i++) { // 상품 개수만큼 반복
							let orderPrice = $(orderPriceList[i]).val()-sharePoint; // 상품별 가격에 사용 포인트 차감
							//console.log(orderPrice)
						   $(orderPriceList[i]).val(orderPrice);// 가격수정
					   }
					   $('#totalPrice').attr('value', total-$('#usePoint').val()); // 주문금액 - 사용포인트 
					   $('#point').attr('value',${loginCustomer.point}-$('#usePoint').val()) // 잔여 포인트
					   $('#sharePoint').attr('value', sharePoint);
				   } else{ // 원래 값으로 변경
					   for(let i = 0; i<orderPriceCount; i++) {
							let cartOrderPrice = $(cartOrderPriceList[i]).val();
							// console.log(cartOrderPrice)
						   $(orderPriceList[i]).val(cartOrderPrice);
					   }
					   $('#totalPrice').attr('value', total);
					   $('#point').attr('value',customerPoint);
					   $('#sharePoint').attr('value', 0);
				   }
				});
			   
			   // 포인트 전액사용
			   $('#pointAll').change(function() {
				   let sharePoint = Math.floor(customerPoint / orderPriceCount) // 상품별 사용 포인트
				   if($("#pointAll").is(":checked")){ // 전액사용
					   if(total-${loginCustomer.point} < 0){ // 주문 금액 < 잔여포인트
						   for(let i = 0; i<orderPriceCount; i++) { // 상품 개수만큼 반복
							   $(orderPriceList[i]).val(0);// 가격수정
						   }
						   $('#totalPrice').attr('value', 0); // 주문금액 0
						   $('#point').attr('value',${loginCustomer.point}-total) // 잔여포인트 계산
						   $('#sharePoint').attr('value', sharePoint);
					   }else{ // 주문금액 > 포인트 
						   let totalPrice = total-${loginCustomer.point};
						   $('#totalPrice').attr('value', totalPrice); // 주문금액
						   for(let i = 0; i<orderPriceCount; i++) {
							   $(orderPriceList[i]).val(Math.round(totalPrice/orderPriceCount));
						   }
						   $('#point').attr('value', 0) // 잔여포인트 0
						   $('#sharePoint').attr('value', sharePoint);
					   }
				   
			        } else { // 전액 사용 X	
			        	for(let i = 0; i<orderPriceCount; i++) {
							let cartOrderPrice = $(cartOrderPriceList[i]).val();
							// console.log(cartOrderPrice)
						   $(orderPriceList[i]).val(cartOrderPrice);
					   }
					   $('#totalPrice').attr('value', total);
					   $('#point').attr('value',customerPoint);
					   $('#sharePoint').attr('value', 0);
			        }
				});
			});
		</script>
	</head>
	<body>
		<!-- 메뉴 partial jsp 구성 -->
		<div>
			<jsp:include page="/inc/menu.jsp"></jsp:include>
		</div>
		
    	<!-- ##### Breadcumb Area Start ##### -->
	    <section class="breadcumb-area bg-img bg-overlay" style="background-image: url(${pageContext.request.contextPath}/resources/img/bg-img/breadcumb3.jpg);">
	        <div class="bradcumbContent">
	            
	            <h2>Order</h2>
	        </div>
	    </section>
	    <!-- ##### Breadcumb Area End ##### -->
	    
	    <!-- ##### Album Catagory Area Start ##### -->
	    <section class="album-catagory section-padding-100-0">
    		
    		
	        <div class="container">
	         <div class="col-12">	        
					
			<form method="post" action="${pageContext.request.contextPath}/orders/ordersAdd">
			<!-- 주문할 상품 목록 -->
			<h2>상품정보</h2>
			<table class = "table w-100 rounded" style="table-layout: auto; width: 100%; table-layout: fixed;">
				<tr>
					<td>포인트 사용</td>
					<td>앨범이름</td>
					<td>앨범</td>
					<td>가격</td>
					<td>주문수량</td>
					<td>주문가격</td>
				</tr>
				<c:forEach var="c" items="${cartList}">
					<tr>
						<td>
							<input type="checkbox" name="pointCk" id="pointCk" class="pointCk" value="${c.goodsCode}" checked="checked">
						</td>
						<td>${c.goodsTitle}</td>
						<td><img src="${pageContext.request.contextPath}/goodsimg/${c.filename}" width="200" height="200"></td>
						<td>${c.goodsPrice}</td>
						<td>${c.cartQuantity }</td>
						<td>
							<input type="text" id="orderPrice" class="orderPrice" name="orderPrice" value="${c.cartQuantity*c.goodsPrice}" >
							<input type="hidden" name="goodsCode" value="${c.goodsCode}">
							<input type="hidden" name="cartQuantity" value="${c.cartQuantity}" >
							<input type="hidden" id="cartOrderPrice" class="cartOrderPrice" value="${c.cartQuantity*c.goodsPrice}" >
						</td>
					</tr>
				</c:forEach>
			</table>		
					
			<!-- 고객 정보 -->
			<h2>주문자정보</h2>
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
			<c:set var = "total" value = "0" />
			<c:forEach var="result" items="${cartList}">			
				<c:set var= "total" value="${total + result.goodsPrice*result.cartQuantity}"/>
			</c:forEach>
			총주문 가격 : <input type="text" name="totalPrice" id="totalPrice" value="${total}">
			<p>포인트 사용 : <input type="text" name="usePoint" id="usePoint"></p>
			<p>전액사용 <input type="checkbox" id="pointAll" name="pointAll" value=""></p>
			<input type="hidden" id="sharePoint" name="sharePoint" value="">
			 
			<button type="submit">결제</button>
		</form>		

			        
			</div>
		</div>
	    </section>
	    <!-- ##### Album Catagory Area End ##### -->

    <!-- ##### Add Area Start ##### -->
    <div class="add-area mb-100">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="adds">
                        <a href="#"><img src="img/bg-img/add3.gif" alt=""></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ##### Add Area End ##### -->

  

		
		
		
		
	<!-- 메뉴 partial jsp 구성 -->
	<div>
		<jsp:include page="/inc/footer.jsp"></jsp:include>
	</div>
		
	<!-- ##### All Javascript Script ##### -->
    <!-- jQuery-2.2.4 js -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery/jquery-2.2.4.min.js"></script>
    <!-- Popper js -->
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap/popper.min.js"></script>
    <!-- Bootstrap js -->
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap/bootstrap.min.js"></script>
    <!-- All Plugins js -->
    <script src="${pageContext.request.contextPath}/resources/js/plugins/plugins.js"></script>
    <!-- Active js -->
    <script src="${pageContext.request.contextPath}/resources/js/active.js"></script>
		
	</body>
</html>