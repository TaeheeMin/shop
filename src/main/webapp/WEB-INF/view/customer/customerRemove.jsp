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
    <title>Recorder Music Remove MyAccount</title>
    
    <!-- Favicon -->
    <link rel="icon" href="${pageContext.request.contextPath}/resources/img/core-img/favicon.ico">

    <!-- Stylesheet -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css">
	
	<!-- J-Query -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script>
    	$(document).ready(function() {
    		$('#removeBtn').click(function(){
    			if($('.removeCk:checked').length == 0){
    				alert('약관에 동의 바랍니다.');
    				return;
    			}
    			
    			if($('#customerPw').val() == '') {
					$('#customerPwMsg').text('기존 패스워드를 입력바랍니다');
					$('#customerPw').focus();
					return;
				} else {
					$('#customerPwMsg').text('');
				}
    			
    			$('#removeForm').submit();
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
            <p>회원탈퇴</p>
            <h2>Drop Out <i class="icon-user"></i></h2>
        </div>
    </section>
    <!-- ##### Breadcumb Area End ##### -->
    
    <!-- ##### Modify Area Start ##### -->
    <section class="login-area section-padding-100">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-lg-8">
                    <div class="login-content">
                        <h3>Drop Out</h3>
                        
						<div class="text-left mb-5">
								<div>사용하고 계신 아이디는 탈퇴할 경우 <span style="font-weight:bold; color:red;">재사용 및 복구가 불가능</span>합니다.</div>
								<br>
								<div>탈퇴 후 회원정보 및 개인형 서비스 이용기록은 모두 삭제됩니다.</div>
								<br>
								<div>모든 내용을 확인하셨으면 동의에 체크 후 비밀번호를 입력해주세요.</div>
								<hr>
								<small>
									<input type="checkbox" class="removeCk">&nbsp;&nbsp;안내사항을 모두 확인하였으며, 이에 동의합니다
								</small>
						</div>
						<form action="${pageContext.request.contextPath}/CustomerRemove" method="post" id="removeForm">
							<div class="row">
							<input type="hidden" name="customerId" value="${loginCustomer.customerId}">
								<div class="col-8">
									<input style="width:200px" type="password" id="customerPw" name="customerPw" placeholder="비밀번호를 입력하세요">
									<small id="customerPwMsg" class="form-text text-danger"></small>
									<!-- 패스워드 불일치시, 문구출력 -->
									<c:if test="${removeMsg != null}">
										<small class="form-text text-danger">패스워드가 일치하지 않습니다.</small>
									</c:if>
								</div>
								<div class="col-4">
									<button id="removeBtn" class="btn btn-sm btn-outline-danger mx-3" type="button">탈퇴</button>
									<a class="btn btn-sm btn-outline-dark" href="${pageContext.request.contextPath}/CustomerOne">취소</a>
								</div>
							</div>
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