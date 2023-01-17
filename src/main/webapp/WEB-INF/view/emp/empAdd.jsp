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
    <title>Recorder Music Add Employee</title>
    
    <!-- Favicon -->
    <link rel="icon" href="${pageContext.request.contextPath}/resources/img/core-img/favicon.ico">

    <!-- Stylesheet -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css">
    
    <!-- J-Query -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script>
	    $(document).ready(function() {
	    	// 유효성검사 정규식
			let reg_id = /^[a-z0-9]{4,12}$/;
			let reg_name = /^[가-힣a-zA-Z]{1,15}$/;
			
			// 유효성검사
			$('#addEmpBtn').click(function(){
				// 아이디 유효성 체크
				if(!reg_id.test($('#empId').val())) {
					$('#empIdMsg').text('아이디는 영문소문자와 숫자 4~12자리로 입력하세요');
					$('#empId').focus();
					return;
				} else {
					$('#empIdMsg').text('');
				}
				// 패스워드 유효성 체크
				if($('#empPw').val().length < 4) {
					$('#empPwMsg').text('패스워드를 4자리 이상 입력하세요');
					$('#empPw').focus();
					return;
				} else {
					$('#empPwMsg').text('');
				}
				
				// 패스워드 확인 유효성 체크
				if($('#empPwCk').val() != $('#empPw').val()) {
					$('#empPwCkMsg').text('비밀번호가 일치하지 않습니다.');
					return;
				} else {
					$('#empPwCkMsg').text('');
				}
				
				// 이름 유효성 체크
				if(!reg_name.test($('#empName').val())) {
					$('#empNameMsg').text('이름은 한글과 영어대소문자 15자리 이하로 입력하세요');
					return;
				} else {
					$('#empNameMsg').text('');
				}
				
				$('#addEmpForm').submit();
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
            <p>직원 등록</p>
            <h2>Add Employee</h2>
        </div>
    </section>
    <!-- ##### Breadcumb Area End ##### -->
    
    <!-- ##### Login Area Start ##### -->
    <section class="login-area section-padding-100">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-lg-8">
                    <div class="login-content">
                        <h3>Add Employee</h3>

						<!-- 아이디 중복값 있을 경우 -->
                        <c:if test="${overlapEmp != null}">
                        	<script>
								alert('이미 존재하는 직원 아이디입니다.');
							</script>
						</c:if>

						<form id="addEmpForm" action="${pageContext.request.contextPath}/AddEmp" method="post">
							<table class="table">
								<tr>
									<th>아이디</th>
									<td>
										<input type="text" name="empId" id="empId">
										 <small id="empIdMsg" class="form-text text-danger"></small>
									</td>
								</tr>
								<tr>
									<th>비밀번호</th>
									<td>
										<input type="password" name="empPw" id="empPw">
										<small id="empPwMsg" class="form-text text-danger"></small>
									</td>
								</tr>
								<tr>
									<th>비밀번호확인</th>
									<td>
										<input type="password" name="empPwCk" id="empPwCk">
										<small id="empPwCkMsg" class="form-text text-danger"></small>
									</td>
								</tr>
								<tr>
									<th>이름</th>
									<td>
										<input type="text" name="empName" id="empName">
										<small id="empNameMsg" class="form-text text-danger"></small>
									</td>
								</tr>
							</table>
							<div>
								<button class="btn btn-sm btn-outline-secondary" type="Button" id="addEmpBtn">사원등록</button>
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