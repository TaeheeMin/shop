<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				// 유효성검사
				$('#addGoodsBtn').click(function() {
					// 카테고리
					if($('#goodsCategory').val() == '') {
						$('#categoryMsg').text('장르를 선택해주세요');
						return;
					} else {
						$('#categoryMsg').text('');
					}
					$('#addGoodsForm').submit();
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
	            <p>See what’s new</p>
	            <h2>ALBUMS</h2>
	        </div>
	    </section>
	    <!-- ##### Breadcumb Area End ##### -->
		
		<!-- #### list #### -->
		<section class="album-catagory section-padding-100-0">
			<c:forEach var="goodsOne" items="${goodsOne}">
	    		<!-- ##### 상세보 ##### -->
			    <div class="oneMusic-buy-now-area mb-100">
			        <div class="container">
			            <div class="row">
							
							<!-- img Content -->
	                        <div class="blog-content" style="padding-right: 50px;">
								<div class="col-12">
										<img src="${pageContext.request.contextPath}/goodsimg/${goodsOne.filename}" width="500" height="500">
	                        		<div class="album-info">
		                                ${goodsOne.goodsTitle}
		                            	
	                        		</div>
	                        	</div>
	                       	</div>
                       		
                       		<!-- 앨범 정보 -->
                        	<div class="col-md-6 col-lg-4">
                        		<h1>${goodsOne.goodsTitle}</h1>
                        		<p>${goodsOne.goodsArtist}</p>
                        		<h3>${goodsOne.goodsPrice}</h3>
                        		<form action="${pageContext.request.contextPath}/CartAdd" method="get" id="cartQuantityForm">
                        			<div class="contact-form-area">
	                        			<div class="form-group">
											<input type="hidden" name="goodsCode" value="${goodsOne.goodsCode}">
											<input type="hidden" name="goodsTitle" value="${goodsOne.goodsTitle}">
											<input type="hidden" name="filename" value="${goodsOne.filename}">
											<input type="hidden" name="goodsPrice" value="${goodsOne.goodsPrice}">
											<input type="hidden" name="soldout" id="soldout"value="${goodsOne.soldout}">
											<c:choose> 
												<c:when test="${goodsOne.soldout eq 'N'}">
													<select name="cartQuantity" id="cartQuantity" class="form-control" >
														<option value="1">1</option>
														<option value="2">2</option>
														<option value="3">3<option>
														<option value="4">4</option>
														<option value="5">5</option>
													</select>
													<button class="btn oneMusic-btn mt-30" type="button" id="cartQuantityBtn">Cart <i class="fa fa-angle-double-right"></i></button>
												</c:when> 
												<c:otherwise>
													품절
												</c:otherwise> 
											</c:choose> 
		                        		</div>
	                        		</div>
                        		</form>
                            </div>
                            <div class="col-md-3 col-lg">
                            	<br>
                            	<span class="badge badge-light">HIT ${goodsOne.view}</span>
                            </div>
                            
                        	<!-- content Area -->
                        	<div class="row" style="width: 100%">
                				<div class="col-12">
				                    <div class="contact-form-area">
		                                <div class="col-12">
		                                    <div class="form-group mt-100 mb-100">
		                                        <textarea class="form-control" cols="100" rows="10" readonly="readonly">${goodsOne.goodsContent}</textarea>
		                                    </div>
		                                </div>
				                    </div>
			                    </div>
		                    </div>
	                    </div>
                    </div>
                 </div>
			</c:forEach>
		
			<!-- #### review #### -->
			<div class="container mb-100">
            	<div class="row">
					<h3>Review</h3>
	                <div class="col-12">
	                    <div class="contact-form-area">
	                        <div class="row">
		                        <c:forEach var="review" items="${review}">
		                            <div class="col-md-6 col-lg-2">
		                                <div class="form-group">
		                                    <input type="text" class="form-control" id="name" value="${review.customerId}" readonly="readonly">
		                                </div>
		                            </div>
		                            <div class="col-md-6 col-lg-6">
		                                <div class="form-group">
		                                    <input type="text" class="form-control" id="reviewMemo" value="${review.reviewMemo}" readonly="readonly">
		                                </div>
		                            </div>
		                            ${review.createdate}
	                            </c:forEach>
	                        </div>
	                    </div>
	                </div>
	            </div>
            </div>
		</section>
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