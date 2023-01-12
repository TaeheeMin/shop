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
	</head>
	<body>
	<!-- ##### Header Area Start ##### -->
    <header class="header-area">
        <!-- Navbar Area -->
        <div class="oneMusic-main-menu">
            <div class="classy-nav-container breakpoint-off">
                <div class="container">
                    <!-- Menu -->
                    <nav class="classy-navbar justify-content-between" id="oneMusicNav">

                        <!-- Nav brand -->
                        <a href="index.html" class="nav-brand"><img src="${pageContext.request.contextPath}/resources/img/core-img/logo.png" alt=""></a>

                        <!-- Navbar Toggler -->
                        <div class="classy-navbar-toggler">
                            <span class="navbarToggler"><span></span><span></span><span></span></span>
                        </div>

                        <!-- Menu -->
                        <div class="classy-menu">

                            <!-- Close Button -->
                            <div class="classycloseIcon">
                                <div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
                            </div>

                            <!-- Nav Start -->
                            <div class="classynav">
                                <ul>
                                    <li><a href="index.html">Home</a></li>
                                    <li><a href="albums-store.html">Albums</a></li>
                                    <li><a href="#">Pages</a>
                                        <ul class="dropdown">
                                            <li><a href="index.html">Home</a></li>
                                            <li><a href="albums-store.html">Albums</a></li>
                                            <li><a href="event.html">Events</a></li>
                                            <li><a href="blog.html">News</a></li>
                                            <li><a href="contact.html">Contact</a></li>
                                            <li><a href="elements.html">Elements</a></li>
                                            <li><a href="login.html">Login</a></li>
                                            <li><a href="#">Dropdown</a>
                                                <ul class="dropdown">
                                                    <li><a href="#">Even Dropdown</a></li>
                                                    <li><a href="#">Even Dropdown</a></li>
                                                    <li><a href="#">Even Dropdown</a></li>
                                                    <li><a href="#">Even Dropdown</a>
                                                        <ul class="dropdown">
                                                            <li><a href="#">Deeply Dropdown</a></li>
                                                            <li><a href="#">Deeply Dropdown</a></li>
                                                            <li><a href="#">Deeply Dropdown</a></li>
                                                            <li><a href="#">Deeply Dropdown</a></li>
                                                            <li><a href="#">Deeply Dropdown</a></li>
                                                        </ul>
                                                    </li>
                                                    <li><a href="#">Even Dropdown</a></li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </li>
                                    <li><a href="event.html">Events</a></li>
                                    <li><a href="blog.html">News</a></li>
                                    <li><a href="contact.html">Contact</a></li>
                                </ul>

                                <!-- Login/Register & Cart Button -->
                                <div class="login-register-cart-button d-flex align-items-center">
                                    <!-- Login/Register -->
                                    <div class="login-register-btn mr-50">
                                        <a href="login.html" id="loginBtn">Login / Register</a>
                                    </div>

                                    <!-- Cart Button -->
                                    <div class="cart-btn">
                                        <p><span class="icon-shopping-cart"></span> <span class="quantity">1</span></p>
                                    </div>
                                </div>
                            </div>
                            <!-- Nav End -->

                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </header>
    <!-- ##### Header Area End ##### -->
    
    <!-- ##### Hero Area Start ##### -->
    <section class="hero-area">
        <div class="hero-slides owl-carousel">
            <!-- Single Hero Slide -->
            <div class="single-hero-slide d-flex align-items-center justify-content-center">
                <!-- Slide Img -->
                <div class="slide-img bg-img" style="background-image: url(${pageContext.request.contextPath}/resources/img/bg-img/newjeans2.jpg);"></div>
                <!-- Slide Content -->
                <div class="container">
                    <div class="row">
                        <div class="col-12">
                            <div class="hero-slides-content text-center">
                                <h6 data-animation="fadeInUp" data-delay="100ms">Latest album</h6>
                                <h2 data-animation="fadeInUp" data-delay="300ms">NewJeans <span>NewJeans</span></h2>
                                <a data-animation="fadeInUp" data-delay="500ms" href="#" class="btn oneMusic-btn mt-50">Discover <i class="fa fa-angle-double-right"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Single Hero Slide -->
            <div class="single-hero-slide d-flex align-items-center justify-content-center">
                <!-- Slide Img -->
                <div class="slide-img bg-img" style="background-image: url(${pageContext.request.contextPath}/resources/img/bg-img/newjeans.jpg);"></div>
                <!-- Slide Content -->
                <div class="container">
                    <div class="row">
                        <div class="col-12">
                            <div class="hero-slides-content text-center">
                                <h6 data-animation="fadeInUp" data-delay="100ms">Latest album</h6>
                                <h2 data-animation="fadeInUp" data-delay="300ms">Colorlib Music <span>Colorlib Music</span></h2>
                                <a data-animation="fadeInUp" data-delay="500ms" href="#" class="btn oneMusic-btn mt-50">Discover <i class="fa fa-angle-double-right"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Hero Area End ##### -->
    
    <!-- ##### 최신앨범 홍보란 ##### -->
    <section class="latest-albums-area section-padding-100">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="section-heading style-2">
                        <p>See what’s new</p>
                        <h2>Latest Albums</h2>
                    </div>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-12 col-lg-9">
                    <div class="ablums-text text-center mb-70">
                        <p>방금 막 공개된 따끈따끈한 최신앨범들을 구경해보세요!!</p>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-12">
                    <div class="albums-slideshow owl-carousel">
                    
                    	<c:forEach var="m" items="${list}" begin="1" end="8" step="1">
                    	<div class="single-album">
	                    	<img src="${pageContext.request.contextPath}/goodsimg/${m.filename}" alt="">
                            <div class="album-info">
                                <a href="#">
                                    <h5>${m.goodsTitle}</h5>
                                </a>
                                <p>${m.goodsArtist}</p>
                            </div>
                    	</div>
                    	</c:forEach>                      
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Latest Albums Area End ##### -->
		<div>
			<h4>[접속자수]</h4>
			<span>현재 접속자 수 : ${currentCnt} /</span>
			<span>Today 접속자 수 : ${todayCount} /</span>
			<span>Total 접속자 수 : ${totalCount}</span>
		</div>
		<hr>
		<div>
			<h4>[TH]공지, 상품, 장바구니, 문의사항</h4>
			<a href="${pageContext.request.contextPath}/NoticeList">공지</a>
			<a href="${pageContext.request.contextPath}/GoodsList">상품</a>
			<a href="${pageContext.request.contextPath}/CartList">장바구니</a>
			<a href="${pageContext.request.contextPath}/QuestionListByAdmin">문의사항(관리자)</a>
			<a href="${pageContext.request.contextPath}/QuestionListByCustomer">문의사항(회원)</a>
		</div>
		<hr>
		<div>
			<h4>[SG]회원,직원 관련 기능구현 中</h4>
			<!-- 직원 로그인일 경우 보일 항목들 -->
			<c:if test="${loginEmp != null}">
				<a href="${pageContext.request.contextPath}/AddEmp">직원등록(직원만 가능)</a>
				<a href="${pageContext.request.contextPath}/EmpList">직원목록(사원이상만 조회가능)</a>
				<a href="${pageContext.request.contextPath}/Logout">로그아웃</a>
			</c:if>
			<!-- 직원 비로그인일 경우 보일 항목들 -->
			<c:if test="${loginEmp == null}">
				<a href="${pageContext.request.contextPath}/LoginEmp">직원로그인</a>
			</c:if>

			<!-- 회원 로그인일 경우 보일 항목들 -->
			<c:if test="${loginCustomer != null}">
				<a href="${pageContext.request.contextPath}/CustomerOne">회원정보</a>
				<a href="${pageContext.request.contextPath}/Logout">로그아웃</a>
			</c:if>
			
			<!-- 회원 비로그인일 경우 보일 항목들 -->
			<c:if test="${loginCustomer == null}">
				<a href="${pageContext.request.contextPath}/LoginCustomer">회원로그인</a>
			<a href="${pageContext.request.contextPath}/AddCustomer">회원가입</a>
			</c:if>
		</div>
		<hr>
		<div>
			<h4>[DG]주문, 리뷰, 포인트</h4>
			<a href="${pageContext.request.contextPath}/orders/ordersList">주문(고객용)</a>
			<a href="${pageContext.request.contextPath}/orders/ordersListByAdmin">주문(관리자용)</a>
			<a href="${pageContext.request.contextPath}/review/reviewList">리뷰</a>
			<a href="${pageContext.request.contextPath}/point/pintOne">포인트(구현중)</a>
		</div>
		<hr>
		
		
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