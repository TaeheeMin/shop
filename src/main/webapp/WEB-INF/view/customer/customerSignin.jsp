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
    <title>Recorder Music Sign-in Customer</title>
    
    <!-- Favicon -->
    <link rel="icon" href="${pageContext.request.contextPath}/resources/img/core-img/favicon.ico">

    <!-- Stylesheet -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css">
    
	<style>
		.msg {color : #FF0000;}
	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			
			// 유효성검사 정규식
			let reg_id = /^[a-z0-9]{4,12}$/;
			let reg_pw = /(?=.*[a-zA-ZS])(?=.*?[0-9])(?=.*?[~!@#$%^&*_-]).{8,16}$/;
			let reg_name = /^[가-힣a-zA-Z]{1,15}$/;
			let reg_phone = /^010[0-9]{8}$/;  
			
			let idCk = false;
			let pwCk = false;
			let pwCkCk = false;
			let nameCk = false;
			let phoneCk = false;
			
			$('#customerId').focus();
			
			// 아이디 유효성 검사
			$('#customerId').blur(function() {
				if(!reg_id.test($('#customerId').val())) {
					console.log($('#customerId').val());
					$('#customerIdMsg').text('아이디는 영문소문자와 숫자 4~12자리로 입력하세요.');
				} else {
					$('#customerIdMsg').text('');
					idCk = true;
				}
			});
			
			// 패스워드 유효성 검사
			$('#customerPw').blur(function() {
				if(!reg_pw.test($('#customerPw').val())) {
					$('#customerPwMsg').text('8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.');
				} else {
					$('#customerPwMsg').text('');
					pwCk = true;
				}
			});
			
			// 패스워드확인 유효성 검사
			$('#customerPwCk').blur(function(){
				if($('#customerPwCk').val() != $('#customerPw').val()) {
					$('#customerPwCkMsg').text('비밀번호가 일치하지 않습니다.');
				} else {
					$('#customerPwCkMsg').text('');
					pwCkCk = true;
				}
			});
			
			// 이름 유효성 검사
			$('#customerName').blur(function() {
				if(!reg_name.test($('#customerName').val())) {
					console.log($('#customerName').val());
					$('#customerNameMsg').text('이름은 한글과 영어대소문자 15자리 이하로 입력하세요');
				} else {
					$('#customerNameMsg').text('');
					nameCk = true;
				}
			});
			
			// 휴대폰번호 유효성 검사
			$('#customerPhone').blur(function() {
				if(!reg_phone.test($('#customerPhone').val())) {
					console.log($('#customerPhone').val());
					$('#customerPhoneMsg').text('휴대폰번호 11자리를 입력하세요');
				} else {
					$('#customerPhoneMsg').text('');
					phoneCk = true;
				}
			});
			
			// btn눌러 form action으로 제출하기
			$('#joinBtn').click(function(){
				if(idCk && pwCk && pwCkCk && nameCk && phoneCk) {
					$('#joinForm').submit();
				} else {
					alert('모든 항목을 입력해주세요.');
					return;
				}
			});
		});
	</script>
</head>
<body>
	<!-- ##### Header Area Start ##### -->
	<!-- 상단 메뉴 nav bar 구성 -->
	<jsp:include page="../../../inc/menu.jsp"></jsp:include>
	
	<!-- ##### Breadcumb Area Start ##### -->
    <section class="breadcumb-area bg-img bg-overlay" style="background-image: url(${pageContext.request.contextPath}/resources/img/bg-img/breadcumb3.jpg);">
        <div class="bradcumbContent">
            <p>See what’s new</p>
            <h2>Join</h2>
        </div>
    </section>
    <!-- ##### Breadcumb Area End ##### -->
    
	 <!-- ##### Login Area Start ##### -->
    <section class="login-area section-padding-100">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-lg-8">
                    <div class="login-content">
                        <h3>Welcome to Recorder Music</h3>
                        
                        <!-- 아이디 중복값 있을 경우 -->
                        <c:if test="${overlap != null}">
                        	<script>
								alert('중복된 아이디가 존재합니다.');
							</script>
						</c:if>
						
                        <!-- Login Form -->
                        <div class="login-form">
                            <form id= "joinForm" action="${pageContext.request.contextPath}/AddCustomer" method="post">
                                <div class="form-group"><!-- 아이디 -->
                                    <label for="exampleInputEmail1">Id</label>
                                    <input type="text" id="customerId" name="customerId" class="form-control" id="" placeholder="아이디를 입력하세요">
                                    <small id="customerIdMsg" class="form-text text-danger"></small>
                                </div>
                                <div class="form-group"><!-- 비밀번호 -->
                                    <label for="exampleInputPassword1">Password</label>
                                    <input type="password" id="customerPw" name="customerPw" class="form-control" id="" placeholder="비밀번호를 입력하세요">
                                    <small id="customerPwMsg" class="form-text text-danger"></small>
                                </div>
                                <div class="form-group"><!-- 비밀번호 확인 -->
                                    <label for="exampleInputPassword1">Password Check</label>
                                    <input type="password" id="customerPwCk"name="customerPw" class="form-control" id="" placeholder="비밀번호를 입력하세요">
                                    <small id="customerPwCkMsg" class="form-text text-danger"></small>
                                </div>
                                <div class="form-group"><!-- 이름 -->
                                    <label for="exampleInputPassword1">Name</label>
                                    <input type="text" id="customerName"name="customerName" class="form-control" id="" placeholder="이름을 입력하세요">
                                    <small id="customerNameMsg" class="form-text text-danger"></small>
                                </div>
                                <div class="form-group"><!-- 휴대폰번호 -->
                                    <label for="exampleInputPassword1">P.number</label>
                                    <input type="number" id="customerPhone" name="customerPhone" class="form-control" id="" placeholder="휴대폰번호를 입력하세요">
                                    <small id="customerPhoneMsg" class="form-text text-danger"></small>
                                </div>
                                <button id="joinBtn" type="button" class="btn oneMusic-btn mt-30">Join</button>
                            </form>                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Login Area End ##### -->
	
	<!-- footer 하단 bar -->
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