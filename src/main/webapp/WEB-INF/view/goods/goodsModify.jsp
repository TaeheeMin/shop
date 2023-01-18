<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
				$('#modifyGoodsBtn').click(function() {
					// 제목
					if($('#goodsTitle').val() == '') {
						$('#titleMsg').text('제목을 입력해주세요');
						$('#title').focus();
						return;
					} else {
						$('#titleMsg').text('');
					}
					
					// goodsArtist
					if($('#goodsArtist').val() == '') {
						$('#artistMsg').text('가수를 입력해주세요');
						return;
					} else {
						$('#artistMsg').text('');
					}
					
					// goodsPrice
					if($('#goodsPrice').val() == '' || isNaN($('#goodsPrice').val())) {
						$('#priceMsg').text('가격을 입력해주세요');
						$('#goodsPrice').focus();
						return
					} else {
						$('#priceMsg').text('');
					}
					
					// goodsCategory
					if($('#goodsCategory').val() == '') {
						$('categoryMsg').text('내용을 입력해주세요');
						return;
					} else {
						$('#categoryMsg').text('');
					}
					
					// goodsContent
					if($('#goodsContent').val() == '') {
						$('contenttMsg').text('내용을 입력해주세요');
						$('#goodsContent').focus();
						return;
					} else {
						$('#contenttMsg').text('');
					}
					$('#goodsModifyForm').submit();
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
	            <h2>LATEST ALBUMS</h2>
	        </div>
	    </section>
	    <!-- ##### Breadcumb Area End ##### -->
	    
	     <!-- ##### Breadcumb Area End ##### -->
		<section class="album-catagory section-padding-100-0">
			<!-- ##### Contact Area Start ##### -->
		    <section class="contact-area section-padding-0-100">
		        <div class="container">
		            <div class="row">
		                <div class="col-12">
		                    <div class="contact-form-area">
		                        <form action="${pageContext.request.contextPath}/GoodsModify" method="post" id="goodsModifyForm">
		                            <div class="row">
		                            	<c:forEach var="list" items="${list}">
			                                <div class="col-md-6 col-lg-4">
			                                    <img src="${pageContext.request.contextPath}/goodsimg/${list.filename}" width="500" height="500">
		                                	</div>  
			                                <div class="col-lg-4">
			                                    <div class="form-group">
			                                        <input type="text" name="goodsTitle" id="goodsTitle" value="${list.goodsTitle}" class="form-control" placeholder="Title">
			                                        <small id="titleMsg" class="form-text text-danger"></small>
			                                    </div>
			                                    <div class="form-group"> 
				                                    <input type="text" name="goodsArtist" id="goodsArtist" value="${list.goodsArtist}" class="form-control" placeholder="Artist">
			                                        <small id="artistMsg" class="form-text text-danger"></small>
		                                        </div>
			                                    <div class="form-group">
				                                    <input type="text" name="goodsPrice" id="goodsPrice" value="${list.goodsPrice}" class="form-control" placeholder="Price">
			                                        <small id="priceMsg" class="form-text text-danger"></small>
		                                        </div>
			                                    <div class="form-group">
			                                        <select name="goodsCategory" id="goodsCategory" class="form-control">
														<option value="발라드">발라드</option>
														<option value="댄스/팝">댄스/팝</option>
														<option value="랩/힙합">랩/힙합</option>
														<option value="재즈">재즈</option>
														<option value="해외팝">해외팝</option>
														<option value="인디">인디</option>
													</select>
			                                        <small id="categoryMsg" class="form-text text-danger"></small>
		                                       </div>
			                                    <div class="form-group"> 
													<div>
														<c:choose> 
															<c:when test="${list.hit eq 0}">
																<input type="radio" name="hit" value="1">추천상품
																<input type="radio" name="hit" value="0" checked="checked">일반상품
															</c:when> 
															<c:otherwise>
																<input type="radio" name="hit" value="1" checked="checked">추천상품
																<input type="radio" name="hit" value="0">일반상품
															</c:otherwise> 
														</c:choose> 
													</div>
												</div>
			                                    <div class="form-group">
													<div>
														<c:choose> 
															<c:when test="${list.soldout eq 'N'}">
																<input type="radio" name="soldout" value="Y">재고없음
																<input type="radio" name="soldout" value="N" checked="checked">재고있음
															</c:when> 
															<c:otherwise>
																<input type="radio" name="soldout" value="Y" checked="checked">재고없음
																<input type="radio" name="soldout" value="N" >재고있음
															</c:otherwise> 
														</c:choose> 
													</div>
													<input type="hidden" name="originalCategory" id="originalCategory" value="${list.goodsCategory}" >
													<input type="hidden" name="goodsCode" readonly="readonly" value="${list.goodsCode}" class="form-control">
			                                    </div>
			                                </div>
			                                <div class="col-12">
			                                    <div class="form-group">
			                                        <textarea name="goodsContent" class="form-control" id="goodsContent" cols="30" rows="10" placeholder="Content">${list.goodsContent}</textarea>
			                                        <small id="contentMsg" class="form-text text-danger"></small>
			                                    </div>
			                                </div>
			                                <div class="col-12 text-center">
			                                    <button class="btn oneMusic-btn mt-30" type="button" id="modifyGoodsBtn">Send <i class="fa fa-angle-double-right"></i></button>
			                                </div>
			                            </c:forEach>
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