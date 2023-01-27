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
		<!-- 인턴, 휴직, 사직 직원들은 직원등록 접근불가 -->
		<c:if test="${empAdd != null}">
			<script>
				alert('사원 이상의 재직자만 접근 가능합니다.');
			</script>
		</c:if>
		
		<!-- 휴직, 사직 직원들은 리뷰관리 접근불가 -->
		<c:if test="${reviewLimit != null}">
			<script>
				alert('재직중인 직원만 접근 가능합니다.');
			</script>
		</c:if>
		
		<!-- ##### Header Area Start ##### -->
		<!-- 상단 메뉴 nav bar 구성 -->
		<jsp:include page="../../inc/menu.jsp"></jsp:include>
    
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
	                                <h2 data-animation="fadeInUp" data-delay="300ms">New Jeans<span>New Jeans</span></h2>
	                                <a data-animation="fadeInUp" data-delay="500ms" href="${pageContext.request.contextPath}/GoodsList" class="btn oneMusic-btn mt-50">Discover <i class="fa fa-angle-double-right"></i></a>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	
	            <!-- Single Hero Slide -->
	            <div class="single-hero-slide d-flex align-items-center justify-content-center">
	                <!-- Slide Img -->
	                <div class="slide-img bg-img" style="background-image: url(${pageContext.request.contextPath}/resources/img/bg-img/bg-2.jpg);"></div>
	                <!-- Slide Content -->
	                <div class="container">
	                    <div class="row">
	                        <div class="col-12">
	                            <div class="hero-slides-content text-center">
	                                <h6 data-animation="fadeInUp" data-delay="100ms">Latest album</h6>
	                                <h2 data-animation="fadeInUp" data-delay="300ms">Colorlib Music <span>Colorlib Music</span></h2>
	                                <a data-animation="fadeInUp" data-delay="500ms" href="${pageContext.request.contextPath}/GoodsList" class="btn oneMusic-btn mt-50">Discover <i class="fa fa-angle-double-right"></i></a>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </section>
	    <!-- ##### Hero Area End ##### -->
	  
	     <!-- ##### 최신앨범 홍보란 (최근추가된 앨범 8개) ##### -->
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
	                        <p>방금 막 공개된 따끈따끈한 최신앨범들을 구경해보세요!! (좀더 장문으로 이쁘고 멋진소개글 쓰기)</p>
	                    </div>
	                </div>
	            </div>
	
	            <div class="row">
	                <div class="col-12">
	                    <div class="albums-slideshow owl-carousel">
	                    
	                    	<c:forEach var="m" items="${rlist}" begin="1" end="8" step="1">
		                    	<div class="single-album">
			                    	<img src="${pageContext.request.contextPath}/goodsimg/${m.filename}" alt="">
		                            <div class="album-info">
		                                <a href="${pageContext.request.contextPath}/GoodsOne?goodsCode=${m.goodsCode}">
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
    
    <!-- 베스트셀러앨범 (hit상품 12개나열하기) -->
	<!-- ##### Buy Now Area Start ##### -->
    <section class="oneMusic-buy-now-area has-fluid bg-gray section-padding-100">
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <div class="section-heading style-2">
                        <p>See what’s best seller album</p>
                        <h2>Buy What’s Best</h2>
                    </div>
                </div>
            </div>

            <div class="row">
				<c:forEach var="h" items="${hlist}" begin="1" end="12" step="1">
				<div class="col-12 col-sm-6 col-md-4 col-lg-2">
                    <div class="single-album-area wow fadeInUp" data-wow-delay="100ms">
                        <div class="album-thumb">
                            <img src="${pageContext.request.contextPath}/goodsimg/${h.filename}" alt="">
                        </div>
                        <div class="album-info">
                            <a href="${pageContext.request.contextPath}/GoodsOne?goodsCode=${h.goodsCode}">
                                <h5>${h.goodsTitle}</h5>
                            </a>
                            <p>${h.goodsArtist}</p>
                        </div>
                    </div>
                </div>
				</c:forEach>
            </div>

            <div class="row">
                <div class="col-12">
                    <div class="load-more-btn text-center wow fadeInUp" data-wow-delay="300ms">
                        <a href="${pageContext.request.contextPath}/GoodsList" class="btn oneMusic-btn">Load More <i class="fa fa-angle-double-right"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Buy Now Area End ##### -->
    
    <!-- 추천 가수 및 앨범 명반 멋드러진 가수랑 앨범 하나 가져오기 (하나 고정값) -->
	<!-- ##### Featured Artist Area Start ##### -->
    <section class="featured-artist-area section-padding-100 bg-img bg-overlay bg-fixed" style="background-image: url(img/bg-img/bg-4.jpg);">
        <div class="container">
            <div class="row align-items-end">
                <div class="col-12 col-md-5 col-lg-4">
                    <div class="featured-artist-thumb">
                        <img src="${pageContext.request.contextPath}/goodsimg/10cm_4.0.jpg" width="500px" alt="">
                    </div>
                </div>
                <div class="col-12 col-md-7 col-lg-8">
                    <div class="featured-artist-content">
                        <!-- Section Heading -->
                        <div class="section-heading white text-left mb-30">
                            <p>10CM</p>
                            <h2>4.0</h2>
                        </div>
                        <p>[4.0]은 '십센치(10cm)'의 통산 네 번째 정규앨범이다. 여덟 곡의 새로운 노래들과 인스트루멘탈 하나, 이렇게 총 아홉 트랙을 수록하고 있는 앨범은 무엇보다도 최근 십센치가 듀오에서 '권정열' 1인 체제로의 큰 변화를 이행한 이후 처음으로 공개하는 작품이라는 점에서 남다른 의미가 있다.
                        	'나'를 "너의 강아지처럼 길들여줘"라 짓궂게 고백하는 'pet'과 같은 곡들에서 여전히 건재한 십센치 고유의 감성과 센스를 만날 수 있다.
                        </p>
                        <div class="song-play-area">
                            <div class="song-name">
                                <p>02. pet</p>
                            </div>
                            <audio preload="auto" controls>
                                <source src="${pageContext.request.contextPath}/resources/audio/10CM-Pet.mp3">
                            </audio>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Featured Artist Area End ##### -->
    
    <!-- 장르별, 가수별, 추천곡 나열하기 -->
    <!-- ##### Miscellaneous Area Start ##### -->
    <section class="miscellaneous-area section-padding-100-0">
        <div class="container">
            <div class="row">
            
                <!-- ***** Weeks Top ***** -->
                <div class="col-12 col-lg-4">
                    <div class="weeks-top-area mb-100">
                        <div class="section-heading text-left mb-50 wow fadeInUp" data-wow-delay="50ms">
                            <p>See what’s new</p>
                            <h2>This week’s top</h2>
                        </div>

                        <!-- Single Top Item -->
                        <c:forEach var="r" items="${rlist}" begin="1" end="6" step="1">
	                        <div class="single-top-item d-flex wow fadeInUp" data-wow-delay="100ms">
	                            <div class="thumbnail">
	                                 <img src="${pageContext.request.contextPath}/goodsimg/${r.filename}" alt="">
	                            </div>
	                            <div class="content-">
	                                <h5>${r.goodsTitle}</h5>
	                                <p>${r.goodsArtist}</p>
	                            </div>
	                        </div>
                        </c:forEach>
                    </div>
                </div>

                <!-- ***** New Hits Songs ***** -->
                <div class="col-12 col-lg-4">
                    <div class="new-hits-area mb-100">
                        <div class="section-heading text-left mb-50 wow fadeInUp" data-wow-delay="50ms">
                            <p>See what’s new</p>
                            <h2>New Hits</h2>
                        </div>
                        <!-- Single Top Item -->
	                    <c:forEach var="h" items="${hlist}" begin="1" end="6" step="1">
	                        <div class="single-new-item d-flex align-items-center justify-content-between wow fadeInUp" data-wow-delay="100ms">
	                            <div class="first-part d-flex align-items-center">
	                                <div class="thumbnail">
	                                    <img src="${pageContext.request.contextPath}/goodsimg/${h.filename}" alt="">
	                                </div>
	                                <div class="content-">
	                                  	<h6>${h.goodsTitle}</h6>
	                            		<p>${h.goodsArtist}</p>
	                                </div>
	                            </div>
	                            <audio preload="auto" controls>
	                                <source src="audio/dummy-audio.mp3">
	                            </audio>
	                        </div>
	                    </c:forEach>
                    </div>
                </div>


                <!-- ***** Popular Artists ***** -->
                <div class="col-12 col-lg-4">
                    <div class="popular-artists-area mb-100">
                        <div class="section-heading text-left mb-50 wow fadeInUp" data-wow-delay="50ms">
                            <p>See what’s new</p>
                            <h2>Popular Artist</h2>
                        </div>

                        <!-- Single Artist -->
                        <c:forEach var="a" items="${alist}" begin="1" end="6" step="1">
	                        <div class="single-artists d-flex align-items-center wow fadeInUp" data-wow-delay="100ms">
	                            <div class="thumbnail">
	                                <img src="${pageContext.request.contextPath}/resources/img/bg-img/pa1.jpg" alt="">
	                            </div>
	                            <div class="content-">
	                                <p>${a.goodsArtist}</p>
	                            </div>
	                        </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Miscellaneous Area End ##### -->
    
     <!-- ##### Contact Area Start ##### -->
    <section class="contact-area section-padding-100 bg-img bg-overlay bg-fixed has-bg-img" style="background-image: url(img/bg-img/bg-2.jpg);">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="section-heading white wow fadeInUp" data-wow-delay="100ms">
                        <p>See what’s new</p>
                        <h2>Get In Touch</h2>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-12">
                    <!-- Contact Form Area -->
                    <div class="contact-form-area">
                        <form action="#" method="post">
                            <div class="row">
                                <div class="col-md-6 col-lg-4">
                                    <div class="form-group wow fadeInUp" data-wow-delay="100ms">
                                        <input type="text" class="form-control" id="name" placeholder="Name">
                                    </div>
                                </div>
                                <div class="col-md-6 col-lg-4">
                                    <div class="form-group wow fadeInUp" data-wow-delay="200ms">
                                        <input type="email" class="form-control" id="email" placeholder="E-mail">
                                    </div>
                                </div>
                                <div class="col-lg-4">
                                    <div class="form-group wow fadeInUp" data-wow-delay="300ms">
                                        <input type="text" class="form-control" id="subject" placeholder="Subject">
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-group wow fadeInUp" data-wow-delay="400ms">
                                        <textarea name="message" class="form-control" id="message" cols="30" rows="10" placeholder="Message"></textarea>
                                    </div>
                                </div>
                                <div class="col-12 text-center wow fadeInUp" data-wow-delay="500ms">
                                    <button class="btn oneMusic-btn mt-30" type="submit">Send <i class="fa fa-angle-double-right"></i></button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Contact Area End ##### -->
    
    	<!-- 하단 footer navbar 구성 -->
		<div>
			<jsp:include page="../../inc/footer.jsp"></jsp:include>
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