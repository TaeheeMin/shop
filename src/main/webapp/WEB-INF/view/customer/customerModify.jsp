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
    <title>Recorder Music Modify MyInformation</title>
    
    <!-- Favicon -->
    <link rel="icon" href="${pageContext.request.contextPath}/resources/img/core-img/favicon.ico">

    <!-- Stylesheet -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css">
    
    <!-- J-Query -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script>
	    $(document).ready(function() {
	    	
	    	// 유효성검사 정규식
			let reg_pw = /(?=.*[a-zA-ZS])(?=.*?[0-9])(?=.*?[~!@#$%^&*_-]).{8,16}$/;
			let reg_name = /^[가-힣a-zA-Z]{1,15}$/;
			let reg_phone = /^010[0-9]{8}$/;  

			// 유효성검사
			$('#modifyBtn').click(function(){	
				// 기존 패스워드 유효성 체크
				if($('#customerPw').val() == '') {
					$('#customerPwMsg').text('기존 패스워드를 입력바랍니다');
					$('#customerPw').focus();
					return;
				} else {
					$('#customerPwMsg').text('');
				}
			
				// 새 패스워드 유효성 체크
				if(!reg_pw.test($('#customerNewPw').val())) {
					$('#customerNewPwMsg').text('8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.');
					return;
				} else {
					$('#customerNewPwMsg').text('');
				}
				
				// 새 패스워드 확인 유효성 체크
				if($('#customerNewPw').val() != $('#customerNewPwCk').val()) {
					$('#customerNewPwCkMsg').text('비밀번호가 일치하지 않습니다.');
					return;
				} else {
					$('#customerPwCkMsg').text('');
				}
				
				// 이름 유효성 체크
				if(!reg_name.test($('#customerName').val())) {
					console.log($('#customerName').val());
					$('#customerNameMsg').text('이름은 한글과 영어대소문자 15자리 이하로 입력하세요');
					return;
				} else {
					$('#customerNameMsg').text('');
				}
				
				// 휴대폰번호 유효성 체크
				if(!reg_phone.test($('#customerPhone').val())) {
					console.log($('#customerPhone').val());
					$('#customerPhoneMsg').text('휴대폰번호 11자리를 입력하세요');
					return;
				} else {
					$('#customerPhoneMsg').text('');
				}
				$('#modifyForm').submit();
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
            <p>비밀번호 및 이름 및 휴대폰번호 변경</p>
            <h2>Modify My Info</h2>
        </div>
    </section>
    <!-- ##### Breadcumb Area End ##### -->
    
     <!-- ##### Login Area Start ##### -->
    <section class="login-area section-padding-100">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-lg-8">
                    <div class="login-content">
                        <h3>Update</h3>
                        
                        <!-- 비밀번호 최근이력 3개와 중복될 경우 -->
                        <c:if test="${overlapPw != null}">
                        	<div class="text-danger">최근 변경한 비밀번호와 중복됩니다. 다른 비밀번호를 사용하세요.</div>
                        </c:if>
                        
                        <!-- 기존비밀번호 불일치 시 -->
                        <c:if test="${noPwMsg != null}">
                        	<div class="text-danger">기존 비밀번호가 일치하지 않습니다.</div>
                        </c:if>
                        
						<form id="modifyForm" action="${pageContext.request.contextPath}/CustomerModify" method="post">
							<table class="table">
								<tr>
									<td>
										<input type="password" id="customerPw" name="customerPw" placeholder="기존 비밀번호">
										<input type="hidden" id="oldPw" value="${loginCustomer.customerPw}">
										<small id="customerPwMsg" class="form-text text-danger"></small>
									</td>
								</tr>
								<tr>
									<td>
										<input type="password" id="customerNewPw" name="customerNewPw" placeholder="새 비밀번호">
										<small id="customerNewPwMsg" class="form-text text-danger"></small>
									</td>
								</tr>
								<tr>
									<td>
										<input type="password" id="customerNewPwCk" name="customerNewPwCk" placeholder="새 비밀번호 확인">
										<small id="customerNewPwCkMsg" class="form-text text-danger"></small>
									</td>
								</tr>
								<tr>
									<td>
										<input type="text" id="customerName" name="customerName" value="${loginCustomer.customerName}" placeholder="이름">
										<small id="customerNameMsg" class="form-text text-danger"></small>
									</td>
								</tr>
								<tr>
									<td>
										<input type="number" id="customerPhone" name="customerPhone" value="${loginCustomer.customerPhone}" placeholder="전화번호">
										<small id="customerPhoneMsg" class="form-text text-danger"></small>
									</td>
								</tr>
							</table>
							<div><button type="button" id="modifyBtn" class="btn btn-sm btn-outline-dark">수정</button>
							<a class="btn btn-sm btn-outline-danger" href="${pageContext.request.contextPath}/CustomerOne">취소</a></div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	
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