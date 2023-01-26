<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <!-- Title -->
    <title>Recorder Music Log-in Customer</title>
    
    <!-- Favicon -->
    <link rel="icon" href="${pageContext.request.contextPath}/resources/img/core-img/favicon.ico">

    <!-- Stylesheet -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css">
    
    <!-- J-Query -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script>
	    $(document).ready(function() {
			// 유효성검사
			$('#logInBtn').click(function(){
				// 아이디 유효성 체크
				if($('#customerId').val() == '') {
					$('#customerIdMsg').text('아이디를 입력해주세요');
					$('#privacy').text('');
					$('#customerId').focus();
					return;
				} else {
					$('#customerIdMsg').text('');
				}
				// 패스워드 유효성 체크
				if($('#customerPw').val() == '') {
					$('#customerPwMsg').text('패스워드를 입력해주세요');
					$('#customerPw').focus();
					return;
				} else {
					$('#customerPwMsg').text('');
				}
				
				$('#loginForm').submit();
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
            <p>회원 로그인</p>
            <h2>Log in</h2>
        </div>
    </section>
    <!-- ##### Breadcumb Area End ##### -->
    
    <!-- ##### Login Area Start ##### -->
    <section class="login-area section-padding-100">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-lg-8">
                    <div class="login-content">
                        <h3>Customer Login</h3>
                        
                        <!-- 아이디나 비밀번호 잘못입력한 경우 -->
                        <c:if test="${wrong != null}">
                        	<span class="text-danger">아이디나 비밀번호를 잘못입력하였습니다.</span>
						</c:if>
						
                        <!-- Login Form -->
                        <div class="login-form">
                            <form id="loginForm" action="${pageContext.request.contextPath}/LoginCustomer" method="post">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Id</label>
                                    <input type="text" name="customerId" class="form-control" id="customerId" placeholder="아이디를 입력하세요" value="test">
                                    <small id="privacy" class="form-text text-muted"><i class="fa fa-lock mr-2"></i>모든 계정은 철저히 감시되고 있습니다</small>
                                    <small id="customerIdMsg" class="form-text text-danger"></small>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1">Password</label>
                                    <input type="password" id="customerPw" name="customerPw" class="form-control" placeholder="비밀번호를 입력하세요" value="1234">
                                    <small id="customerPwMsg" class="form-text text-danger"></small>
                                </div>
                                <button id="logInBtn" type="button" class="btn oneMusic-btn mt-30">Login</button>
                            </form>
                            <span style="float:left"><a href="${pageContext.request.contextPath}/LoginEmp"><i class="icon-key"></i></a></span>
                            <span style="float:right"><a class="btn" href="${pageContext.request.contextPath}/AddCustomer">Join</a></span>
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