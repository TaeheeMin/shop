<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="description" content="">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    	
    	<!-- Title -->
		<title>Recoder Musice - CartList</title>
		
		<!-- Favicon -->
	    <link rel="icon" href="${pageContext.request.contextPath}/resources/img/core-img/favicon.ico">
	
	    <!-- Stylesheet -->
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css">
	    
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<c:forEach var="c" items="${list}">
			<script>
				$(document).ready(function(){
					// 상품 수량 수정 js
					$('#cartQuantityBtn${c.goodsCode}').click(function(){
						let cartQuantity = $('#cartQuantity${c.goodsCode}');
						console.log('(1)'+cartQuantity.val());
						
						let cartQtt = $('#cartQtt${c.goodsCode}');
						cartQtt = cartQuantity
						console.log('(2)'+cartQtt.val())
						$('input[name=cartQuantity]').attr('value',cartQtt.val());
						$('#modifyQttForm${c.goodsCode}').submit();
					});
				});
			</script>
		</c:forEach>
	</head>
	<body>
		<!-- 메뉴 partial jsp 구성 -->
		<div>
			<jsp:include page="../../../inc/menu.jsp"></jsp:include>
		</div>
		
		<!-- ##### Breadcumb Area Start ##### -->
	    <section class="breadcumb-area bg-img bg-overlay" style="background-image: url(${pageContext.request.contextPath}/resources/img/bg-img/breadcumb3.jpg);">
	        <div class="bradcumbContent">
	            <p>장바구니</p>
	            <h2>Cart</h2>
	        </div>
	    </section>
	    <!-- ##### Breadcumb Area End ##### -->
	    
	     <!-- ##### Album Catagory Area Start ##### -->
		<section class="album-catagory section-padding-100-0">
			<div class="container">
				<div class="col-12">	        
				
					<form action="${pageContext.request.contextPath}/orders/ordersAdd" method="get">
						<table class="table table-hover w-100 rounded" style="table-layout: auto; width: 100%; table-layout: fixed;">
							<tr>
								<th>상품이름</th>
								<th>상품이미지</th>
								<th>수량</th>
								<th>상품금액</th>
								<th>삭제</th>
							</tr>
							<c:forEach var="c" items="${list}">
								<tr>
									<th><!-- 상품이름 -->
										<input type="hidden" name="goodsCode" value="${c.goodsCode}"> 
										${c.goodsTitle}
										<input type="hidden" name="goodsTitle" value="${c.goodsTitle}" readonly="readonly"> 
									</th>
									<td><!-- 상품이미지 -->
										<div class="single-album-area">
										<div class="album-thumb">
											<img src="${pageContext.request.contextPath}/goodsimg/${c.filename}" width="200" height="200">
											<div class="album-price">
												<p>${c.goodsPrice}</p>
											</div>
										</div>
										</div>
									</td>
									<!-- 수량수정 -->
									<td>
										<select name="cartQuantity" id="cartQuantity${c.goodsCode}">
											<c:forEach var="x" begin="1" end="10" step="1">
												<c:choose> 
													<c:when test="${x == c.cartQuantity}">
														<option value="${x}" selected="selected">${x}</option>
													</c:when> 
													<c:otherwise>
														<option value="${x}">${x}</option>
													</c:otherwise> 
												</c:choose> 
											</c:forEach>
										</select>
										<button id="cartQuantityBtn${c.goodsCode}" class="btn btn-sm btn-outline-dark" type="button">수정</button>
									</td>
									<td><!-- 상품가격 -->
										${c.goodsPrice*c.cartQuantity}
										<input type="hidden" name="goodsPrice" value="${c.goodsPrice}" readonly="readonly">
									</td>
									<td>
										<a class="btn btn-sm btn-danger" href="${pageContext.request.contextPath}/CartRemove?goodsCode=${c.goodsCode}">X</a>
									</td>
								</tr>
							</c:forEach>
							<tr>
								<th colspan="3"><span style="font-size:24px">Total Order Price</span></th>
								<th>
									<c:set var = "cartPrice" value = "0" />
										<c:forEach var="c" items="${list}">
											<c:set var= "cartPrice" value="${cartPrice + (c.goodsPrice*c.cartQuantity)}"/>
										</c:forEach>
									<span style="font-size:23px; color:red;">${cartPrice}</span>
									<input type="hidden" name="cartPrice" value="${cartPrice}" readonly="readonly">
								</th>
								<td>
									<button class="btn btn-sm" style="background-color:#E4F7BA" type="submit">Order</button>
								</td>
							</tr>
						</table>
						<br>
						
					</form>
					<!-- 수량변경 히든폼 -->
					<c:forEach var="c" items="${list}">
						<form action="${pageContext.request.contextPath}/CartModify" method="post" id="modifyQttForm${c.goodsCode}">
							<input type="hidden" name="goodsCode" value="${c.goodsCode}">
							<input type="hidden" name="cartQuantity" value="" id="cartQtt${c.goodsCode}">
						</form>
					</c:forEach>
				</div>
			</div>
		</section>
		
		<!-- 하단 footer Bar 구성 -->
		<div>
			<jsp:include page="../../../inc/footer.jsp"></jsp:include>
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