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
				$('#modifyNoticeBtn').click(function() {
					if($('#title').val() == '') {
						$('#titleMsg').text('제목을 입력해주세요');
						$('#title').focus();
						return;
					} else {
						$('#titleMsg').text('');
					}
					if($('#content').val() == '') {
						$('#contentMsg').text('내용을 입력해주세요');
						$('#content').focus();
						return;
					} else {
						$('#contentMsg').text('');
					}
					$('#modifyNoticeForm').submit();
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
	            
	            <h2>NOTICE</h2>
	        </div>
	    </section>
	    <!-- ##### Breadcumb Area End ##### -->
		<section class="album-catagory section-padding-100-0">
			<!-- ##### Contact Area Start ##### -->
		    <section class="contact-area section-padding-0-100">
		        <div class="container">
		            <div class="row">
		                <div class="col-12">
		                    <div class="contact-form-area">
		                        <form action="${pageContext.request.contextPath}/NoticeModify?noticeCode=${noticeOne.noticeCode}" method="post" id="modifyNoticeForm">
		                            <div class="row">
		                                <div class="col-md-6 col-lg-4">
		                                    <div class="form-group">
		                                         <input type="hidden" name="noticeCode" readonly="readonly" value="${noticeOne.noticeCode}" class="form-control">
		                                         <input type="text" name="title" id="title" value="${noticeOne.noticeTitle}" class="form-control">
		                                         <small id="titleMsg" class="form-text text-danger"></small>
		                                    </div>
		                                </div>
		                                <div class="col-lg-4">
		                                    <div class="form-group">
		                                    <input type="text" name="createdate" id="createdate" value="${noticeOne.createdate}" readonly="readonly" class="form-control">
		                                    </div>
		                                </div>
		                                <div class="col-12">
		                                    <div class="form-group">
		                                        <textarea name="content" class="form-control" id="content" cols="30" rows="10">${noticeOne.noticeContent}</textarea>
		                                        <small id="contentMsg" class="form-text text-danger"></small>
		                                    </div>
		                                </div>
		                                <div class="col-12 text-center">
		                                    <button class="btn oneMusic-btn mt-30" type="button" id="modifyNoticeBtn">Send <i class="fa fa-angle-double-right"></i></button>
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