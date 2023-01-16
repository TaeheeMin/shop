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
    <title>Recorder Music MyPage</title>
    
    <!-- Favicon -->
    <link rel="icon" href="${pageContext.request.contextPath}/resources/img/core-img/favicon.ico">

    <!-- Stylesheet -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css">
</head>
<body>
	<!-- ##### Header Area Start ##### -->
	<!-- 상단 메뉴 nav bar 구성 -->
	<jsp:include page="../../../inc/menu.jsp"></jsp:include>
	
	<!-- ##### Breadcumb Area Start ##### -->
    <section class="breadcumb-area bg-img bg-overlay" style="background-image: url(${pageContext.request.contextPath}/resources/img/bg-img/breadcumb3.jpg);">
        <div class="bradcumbContent">
            <p>내 정보 조회 및 수정</p>
            <h2>My Account</h2>
        </div>
    </section>
    <!-- ##### Breadcumb Area End ##### -->
    
    <!-- ##### Login Area Start ##### -->
    <section class="login-area section-padding-100">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-lg-8">
                    <div class="login-content">
                        <h3>Customer Information</h3>
						
						<div class="mb-5">
							${customerOne.customerName}(${customerOne.customerId})님 어서오세요
						</div>
					    <div class="row">
					    	<div class="col">
					    		<div>
									보유 포인트 : ${customerOne.point}
								</div>
								<div class="mt-3">
									가입날짜 : ${customerOne.createdate}
								</div>
								
					    	</div>
					    	<div class="col">
					    		<div>
									<a href="${pageContext.request.contextPath}/CustomerModify">회원정보수정</a>
								</div>
								<div class="mt-3">
									<a href="${pageContext.request.contextPath}/CustomerRemove">회원탈퇴</a>
								</div>
					    	</div>
					    </div>          
						<hr>
						<div>
							주소목록
						</div>
						<c:forEach var="a" items="${list}">
							<p>
								${a.address}
								<span><a href="${pageContext.request.contextPath}/AddressRemove?customerId=${a.customerId}&address=${a.address}">삭제</a></span>
							</p>
						</c:forEach>
						
						
						<form action="${pageContext.request.contextPath}/AddressAdd" method="post">
							<div>
								주소추가
							</div>
							<div>
								<textarea rows="4" cols="40" name="address"></textarea>
							</div>
							<input type="hidden" name="customerId" value="${customerOne.customerId}">
							<div><button type="submit">추가</button></div>
						</form>	
						<hr>
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