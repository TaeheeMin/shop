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
					if($('#goodsCategory').val() == '') {
						$('#categoryMsg').text('장르를 선택해주세요');
						return;
					} else {
						$('#categoryMsg').text('');
					}
					console.log(isNaN($('#goodsPrice').val())); // 숫자 입력F), 그외(T)
					if($('#goodsPrice').val('')) {
						$('#priceMsg').text('가격을 입력해주세요');
						$('#goodsPrice').focus();
						
					} else {
						
					}
						
					if(isNaN($('#goodsPrice').val())) {
						$('#priceMsg').text('숫자만 입력해주세요');
						$('#goodsPrice').focus();
						return;
					} else {
						$('#priceMsg').text('');
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
	            <h2>Goods</h2>
	        </div>
	    </section>
	    <!-- ##### Breadcumb Area End ##### -->
		<section class="album-catagory section-padding-100-0">
		    <section class="contact-area section-padding-0-100">
		        <div class="container">
		            <div class="row">
		                <div class="col-12">
		                    <div class="contact-form-area">
		                        <form enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath}/GoodsAdd" id="addGoodsForm">
		                            <div class="row">
		                                <div class="col-md-6 col-lg-4">
		                                    <div class="form-group">
			                                    <select name="goodsCategory" id="goodsCategory" class="form-control" >
													<option value="">Category</option>
													<option value="발라드">발라드</option>
													<option value="댄스/팝">댄스/팝</option>
													<option value="랩/힙합">랩/힙합</option>
													<option value="재즈">재즈</option>
													<option value="해외팝">해외팝</option>
													<option value="인디">인디</option>
												</select>
		                                        <small id="categoryMsg" class="form-text text-danger"></small>
		                                    </div>
		                                </div>
		                                <div class="col-md-6 col-12">
		                                    <div class="form-group">
												<input type="text" name="goodsPrice" id="goodsPrice" placeholder="Price" class="form-control">
		                                        <small id="priceMsg" class="form-text text-danger"></small>
		                                   	</div>
		                                </div>
		                                
		                                <div class="col-md-6 col-12">
		                                    <div class="form-group">
												<input type="text" name="goodsArtist" id="goodsArtist" placeholder="Artist" class="form-control">
		                                        <small id="artistMsg" class="form-text text-danger"></small>
		                                   	</div>
		                                </div>
		                                <div class="col-md-6 col-lg-4">
		                                    <div class="form-group">
												<input type="file" name="goodsFile" id="goodsFile"  class="form-control">
		                                        <small id="fileMsg" class="form-text text-danger"></small>
		                                   	</div>
		                                </div>
		                                <div class="col-12">
		                                    <div class="form-group">
		                                    	<input type="text" name="goodsTitle" id="goodsTitle" placeholder="Title" class="form-control">
		                                        <small id="titleMsg" class="form-text text-danger"></small>
		                                        <textarea name="goodsContent" class="form-control" id="goodsContent" cols="30" rows="10" placeholder="Content"></textarea>
		                                        <small id="contentMsg" class="form-text text-danger"></small>
		                                    </div>
		                                </div>
		                                <div class="col-12 text-center">
		                                    <button class="btn oneMusic-btn mt-30" type="button" id="addGoodsBtn">Send <i class="fa fa-angle-double-right"></i></button>
		                                </div>
		                            </div>
		                        </form>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </section>
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