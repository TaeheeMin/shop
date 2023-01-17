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
			$('#empBtn').click(function(){
				// 아이디 유효성 체크
				if($('#empId').val() == '') {
					$('#empIdMsg').text('아이디를 입력해주세요');
					$('#privacy').text('');
					$('#empId').focus();
					return;
				} else {
					$('#empIdMsg').text('');
				}
				// 패스워드 유효성 체크
				if($('#empPw').val() == '') {
					$('#empPwMsg').text('패스워드를 입력해주세요');
					$('#empPw').focus();
					return;
				} else {
					$('#empPwMsg').text('');
				}
				
				$('#empForm').submit();
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
            <p>※관계자 외 출입금지※</p>
            <h2>Manager</h2>
        </div>
    </section>
    <!-- ##### Breadcumb Area End ##### -->
    	
	<!-- ##### Login Area Start ##### -->
    <section class="login-area section-padding-100">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-lg-8">
                    <div class="login-content">
                        <h3>Manager Login</h3>
                        
                        <!-- 사직처리된 직원아이디 로그인할 경우 alert 출력 -->
                        <c:if test="${outEmp != null}">
                        	<script>
                        		alert('사직처리된 직원 아이디로 사용 불가합니다.');
                        	</script>
                        </c:if>
                        
                        <!-- 재직자가 아닌 직원이 orderListByAdimin에 접근할 경우 alert 출력 -->
                        <c:if test="${orderEmp != null}">
                        	<script>
                        		alert('재직중인 직원만 접근 가능합니다.');
                        	</script>
                        </c:if>
                        
                        <!-- 아이디나 비밀번호 잘못입력한 경우 -->
                        <c:if test="${wrongEmp != null}">
                        	<span class="text-danger">아이디나 비밀번호를 잘못입력하였습니다.</span>
						</c:if>
						
                        <!-- Login Form -->
                        <div class="login-form">
                            <form id="empForm" action="${pageContext.request.contextPath}/LoginEmp" method="post">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Id</label>
                                    <input type="text" name="empId" class="form-control" id="empId" placeholder="아이디를 입력하세요">
                                    <small id="privacy" class="form-text text-muted"><i class="fa fa-lock mr-2"></i>관리자 계정으로 로그인하세요</small>
                                    <small id="empIdMsg" class="form-text text-danger"></small>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1">Password</label>
                                    <input type="password" id="empPw" name="empPw" class="form-control" placeholder="비밀번호를 입력하세요">
                                    <small id="empPwMsg" class="form-text text-danger"></small>
                                </div>
                                <button id="empBtn" type="button" class="btn oneMusic-btn mt-30">Login</button>
                            </form>
                            <span style="float:left"><a href="${pageContext.request.contextPath}/LoginCustomer"><i class="icon-user"></i></a></span>
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