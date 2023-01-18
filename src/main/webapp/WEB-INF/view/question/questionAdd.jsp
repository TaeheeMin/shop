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
				$('#addQuestionBtn').click(function() {
					if($('#questionTitle').val() == '') {
						$('#titleMsg').text('제목을 입력해주세요');
						$('#questionTitle').focus();
						return;
					} else {
						$('#titleMsg').text('');
					}					
					
					if($('#questionMemo').val() == '') {
						$('#contentMsg').text('내용을 입력해주세요');
						$('#questionMemo').focus();
						return;
					} else {
						$('#contentMsg').text('');
					}
					$('#questionForm').submit();
				});
			});
		</script>
	</head>
	<body>
		 <!-- 리뷰 중복값 있을 경우 -->
	<c:if test="${overlap != null}">
		<script>
			alert('리뷰 작성을 실패하였습니다.');
		</script>
	</c:if>
		<!-- 메뉴 partial jsp 구성 -->
		<div>
			<jsp:include page="/inc/menu.jsp"></jsp:include>
		</div>
		
		<!-- ##### Breadcumb Area Start ##### -->
	    <section class="breadcumb-area bg-img bg-overlay" style="background-image: url(${pageContext.request.contextPath}/resources/img/bg-img/breadcumb3.jpg);">
	        <div class="bradcumbContent">
	            <h2>Question</h2>
	        </div>
	    </section>
	    <!-- ##### Breadcumb Area End ##### -->
	    
		<section class="album-catagory section-padding-100-0">
		    <section class="contact-area section-padding-0-100">
		        <div class="container">
		            <div class="row">
		                <div class="col-12">
		                    <div class="contact-form-area">
		                        <form action="${pageContext.request.contextPath}/QuestionAdd" method="post" id=questionForm>
		                            <div class="row">
		                                <div class="col-md-6 col-lg-4">
		                                <div class="form-group">
											Order List :
											<select name="ordersCode">
												<c:forEach var="list" items="${list}">
													<option value="${list.orderCode}">${list.orderCode} ${list.goodsTitle}</option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group">
											Category : 
											<select name="category">
												<option value="배송">배송</option>
												<option value="반품">반품</option>
												<option value="교환">교환</option>
												<option value="기타">기타</option>
											</select>
										</div>
									                                
		                                
		                                    <div class="form-group">		                                    	
												<input type="text" name="questionTitle" id="questionTitle" class="form-control"  placeholder="title">
												<small id="titleMsg" class="form-text text-danger"></small> 												                            
		                                    </div>
		                                </div>
		                                <div class="col-12">
		                                    <div class="form-group">
		                                        <textarea name="questionMemo" class="form-control" id="questionMemo" cols="30" rows="10" placeholder="content"></textarea>
		                                        <small id="contentMsg" class="form-text text-danger"></small>
		                                    </div>
		                                </div>
		                                <div class="col-12 text-center">
		                                    <button class="btn oneMusic-btn mt-30" type="button" id="addQuestionBtn">등록<i class="fa fa-angle-double-right"></i></button>
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