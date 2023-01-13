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
				$('#rowPerPage').change(function() {
					$('#listForm').submit();
				});
				$('#searchBtn').click(function() {
					$('#listForm').submit();
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
	            <p>See what’s new</p>
	            <h2>LATEST ALBUMS</h2>
	        </div>
	    </section>
	    <!-- ##### Breadcumb Area End ##### -->
	    
	    <!-- ##### Album Catagory Area Start ##### -->
	    <section class="album-catagory section-padding-100-0">
	        <div class="container">
	            <div class="row">
	                <div class="col-12">
	                	<!-- #### search #### -->
						<form action="${pageContext.request.contextPath}/GoodsList" method="get" id="listForm">
							<div class="browse-by-catagories catagory-menu d-flex flex-wrap align-items-center mb-70">
		                        <a href="#" data-filter="*">Browse All</a>
		                        <a href="#" data-filter=".a">A</a>
		                        <a href="#" data-filter=".b">B</a>
		                        <a href="#" data-filter=".c">C</a>
		                        <a href="#" data-filter=".d">D</a>
		                        <a href="#" data-filter=".e">E</a>
		                        <a href="#" data-filter=".f">F</a>
		                        <a href="#" data-filter=".g">G</a>
		                        <a href="#" data-filter=".h">H</a>
		                        <a href="#" data-filter=".i">I</a>
		                        <a href="#" data-filter=".j">J</a>
		                        <a href="#" data-filter=".k">K</a>
		                        <a href="#" data-filter=".l">L</a>
		                        <a href="#" data-filter=".m">M</a>
		                        <a href="#" data-filter=".n">N</a>
		                        <a href="#" data-filter=".o">O</a>
		                        <a href="#" data-filter=".p">P</a>
		                        <a href="#" data-filter=".q">Q</a>
		                        <a href="#" data-filter=".r">R</a>
		                        <a href="#" data-filter=".s">S</a>
		                        <a href="#" data-filter=".t">T</a>
		                        <a href="#" data-filter=".u">U</a>
		                        <a href="#" data-filter=".v">V</a>
		                        <a href="#" data-filter=".w">W</a>
		                        <a href="#" data-filter=".x">X</a>
		                        <a href="#" data-filter=".y">Y</a>
		                        <a href="#" data-filter=".z">Z</a>
		                        <a href="#" data-filter=".number">0-9</a>
	                    	</div>
							
							<input type="radio" name="category" id="category" value="">전체
							<input type="radio" name="category" id="category" value="발라드">발라드
							<input type="radio" name="category" id="category" value="댄스/팝">댄스/팝
							<input type="radio" name="category" id="category" value="랩/힙합">랩/힙합
							<input type="radio" name="category" id="category" value="재즈">재즈
							<input type="radio" name="category" id="category" value="해외팝">해외팝
							<input type="radio" name="category" id="category" value="인디">인디
							
							<div style="float: right; padding-bottom: 10px;">
								<select name="rowPerPage" id="rowPerPage">
									<c:if test="${rowPerPage == 10}">
										<option value="10" selected="selected">10</option>
										<option value="20">20</option>
										<option value="30">30</option>
									</c:if>
									<c:if test="${rowPerPage == 20}">
										<option value="10">10</option>
										<option value="20" selected="selected">20</option>
										<option value="30">30</option>
									</c:if>
									<c:if test="${rowPerPage == 30}">
										<option value="10">10</option>
										<option value="20">20</option>
										<option value="30" selected="selected">30</option>
									</c:if>
								</select>
								<select name="search" id="search">
									<option value="gd.goods_title">앨범</option>
									<option value="gd.goods_artist">가수</option>
								</select>
								<input type="search" placeholder="Search" name="word" id="word" value="${word}">
					     		<button type="button" id="searchBtn">검색</button>
							</div>
	                    
	                    </form>
	                </div>
	            </div>
	
	            <div class="row oneMusic-albums">
	                <!-- #### list #### -->	
	                <c:forEach var="m" items="${list}" varStatus="s">
		                <c:if test="${s.index != 0 && s.index % 6 == 0 }">
							</div><div>
						</c:if>
		                <div class="col-12 col-sm-4 col-md-3 col-lg-2 single-album-item t c p">
		                    <div class="single-album">
		                        <img src="${pageContext.request.contextPath}/goodsimg/${m.filename}" alt="">
		                        <div class="album-info">
		                            <a href="${pageContext.request.contextPath}/GoodsOne?goodsCode=${m.goodsCode}">
		                                <h5>${m.goodsArtist}</h5>
		                            </a>
		                            <p>${m.goodsTitle}</p>
		                            <p>${m.goodsPrice}</p>
		                            <p>
										<c:choose> 
											<c:when test="${m.soldout eq 'N'}">
												<a href="${pageContext.request.contextPath}/CartAdd?goodsCode=${m.goodsCode}&cartQuantity=1&filename=${m.filename}&goodsPrice=${m.goodsPrice}&goodsTitle=${m.goodsTitle}">cart</a>
											</c:when> 
											<c:otherwise>
												품절
											</c:otherwise> 
										</c:choose> 
									</p>
									<c:if test="${loginEmp != null}">
										<div>
											<a href="${pageContext.request.contextPath}/GoodsModify?goodsCode=${m.goodsCode}">수정</a>
										</div>
									</c:if>
		                            
		                        </div>
		                    </div>
		                </div>
	                </c:forEach>
	            </div>
	            <!-- Pagination -->
	            <div class="oneMusic-pagination-area wow fadeInUp" data-wow-delay="300ms">
	                <nav>
	                    <ul class="pagination">
	                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/GoodsList?rowPerPage=${rowPerPage}&currentPage=1">HOME</a></li>
	                        <c:if test="${currentPage > 1}">
								<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/GoodsList?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}"><<</a></li>
							</c:if>
							<c:forEach var="x" begin="${beginPage}" end="${endPage}" step="1">
								<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/GoodsList?rowPerPage=${rowPerPage}&currentPage=${x}">${x}</a></li>
							</c:forEach>
							<c:if test="${currentPage < lastPage}">
								<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/GoodsList?rowPerPage=${rowPerPage}&currentPage=${currentPage +1}">>></a></li>
							</c:if>
							<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/GoodsList?rowPerPage=${rowPerPage}&currentPage=${lastPage}">END</a></li>
	                    </ul>
	                </nav>
	            </div>
	            <!-- ##### 추천앨범 ##### -->
			    <div class="oneMusic-buy-now-area mt-100">
			        <div class="container">
			            <div class="row">
			                <!-- Single Album Area -->
			                <c:forEach var="m" items="${hlist}" varStatus="s" begin="0" end="3" step="1">
								<div class="col-12 col-sm-6 col-md-3">
				                    <div class="single-album-area">
				                        <div class="album-thumb">
				                            <img src="${pageContext.request.contextPath}/goodsimg/${m.filename}" alt="">
				                            <!-- Album Price -->
				                            <div class="album-price">
				                                <p>${m.goodsPrice}</p>
				                            </div>
				                            <!-- Play Icon -->
				                            <div class="play-icon">
				                                <a href="#" class="video--play--btn"><span class="icon-play-button"></span></a>
				                            </div>
				                        </div>
				                        <div class="album-info">
				                            <a href="${pageContext.request.contextPath}/GoodsOne?goodsCode=${m.goodsCode}">
				                                <h5>${m.goodsTitle}</h5>
				                            </a>
				                            <p>${m.goodsArtist}</p>
				                        </div>
				                    </div>
				                </div>
							</c:forEach>
			            </div>
			        </div>
	    		</div>
	    		<!-- ##### 추천앨범 End ##### -->
	        </div>
	    </section>
	    <!-- ##### Album Catagory Area End ##### -->

    <!-- ##### Add Area Start ##### -->
    <div class="add-area mb-100">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="adds">
                        <a href="#"><img src="img/bg-img/add3.gif" alt=""></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ##### Add Area End ##### -->

    <!-- ##### Song Area Start ##### -->
    <div class="one-music-songs-area mb-70">
        <div class="container">
            <div class="row">

                <!-- Single Song Area -->
                <div class="col-12">
                    <div class="single-song-area mb-30 d-flex flex-wrap align-items-end">
                        <div class="song-thumbnail">
                            <img src="img/bg-img/s1.jpg" alt="">
                        </div>
                        <div class="song-play-area">
                            <div class="song-name">
                                <p>01. Main Hit Song</p>
                            </div>
                            <audio preload="auto" controls>
                                <source src="audio/dummy-audio.mp3">
                            </audio>
                        </div>
                    </div>
                </div>

                <!-- Single Song Area -->
                <div class="col-12">
                    <div class="single-song-area mb-30 d-flex flex-wrap align-items-end">
                        <div class="song-thumbnail">
                            <img src="img/bg-img/s2.jpg" alt="">
                        </div>
                        <div class="song-play-area">
                            <div class="song-name">
                                <p>01. Main Hit Song</p>
                            </div>
                            <audio preload="auto" controls>
                                <source src="audio/dummy-audio.mp3">
                            </audio>
                        </div>
                    </div>
                </div>

                <!-- Single Song Area -->
                <div class="col-12">
                    <div class="single-song-area mb-30 d-flex flex-wrap align-items-end">
                        <div class="song-thumbnail">
                            <img src="img/bg-img/s3.jpg" alt="">
                        </div>
                        <div class="song-play-area">
                            <div class="song-name">
                                <p>01. Main Hit Song</p>
                            </div>
                            <audio preload="auto" controls>
                                <source src="audio/dummy-audio.mp3">
                            </audio>
                        </div>
                    </div>
                </div>

                <!-- Single Song Area -->
                <div class="col-12">
                    <div class="single-song-area mb-30 d-flex flex-wrap align-items-end">
                        <div class="song-thumbnail">
                            <img src="img/bg-img/s4.jpg" alt="">
                        </div>
                        <div class="song-play-area">
                            <div class="song-name">
                                <p>01. Main Hit Song</p>
                            </div>
                            <audio preload="auto" controls>
                                <source src="audio/dummy-audio.mp3">
                            </audio>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <!-- ##### Song Area End ##### -->

    <!-- ##### Contact Area Start ##### -->
    <section class="contact-area section-padding-100 bg-img bg-overlay bg-fixed has-bg-img" style="background-image: url(img/bg-img/bg-2.jpg);">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="section-heading white">
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
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="name" placeholder="Name">
                                    </div>
                                </div>
                                <div class="col-md-6 col-lg-4">
                                    <div class="form-group">
                                        <input type="email" class="form-control" id="email" placeholder="E-mail">
                                    </div>
                                </div>
                                <div class="col-lg-4">
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="subject" placeholder="Subject">
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-group">
                                        <textarea name="message" class="form-control" id="message" cols="30" rows="10" placeholder="Message"></textarea>
                                    </div>
                                </div>
                                <div class="col-12 text-center">
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
		
		
		
		
		<h1>상품목록</h1>
		<a href="${pageContext.request.contextPath}/Home">홈으로</a>
		<a href="${pageContext.request.contextPath}/CartList">장바구니</a>
		<c:if test="${loginEmp != null}">
			<a href="${pageContext.request.contextPath}/GoodsAdd">상품등록</a>
		</c:if>
		
		<!-- #### search #### -->
		<form action="${pageContext.request.contextPath}/GoodsList" method="get" id="listForm">
			<div>
				<input type="radio" name="category" id="category" value="">전체
				<input type="radio" name="category" id="category" value="발라드">발라드
				<input type="radio" name="category" id="category" value="댄스/팝">댄스/팝
				<input type="radio" name="category" id="category" value="랩/힙합">랩/힙합
				<input type="radio" name="category" id="category" value="재즈">재즈
				<input type="radio" name="category" id="category" value="해외팝">해외팝
				<input type="radio" name="category" id="category" value="인디">인디
			</div>
			<select name="rowPerPage" id="rowPerPage">
				<c:if test="${rowPerPage == 10}">
					<option value="10" selected="selected">10</option>
					<option value="20">20</option>
					<option value="30">30</option>
				</c:if>
				<c:if test="${rowPerPage == 20}">
					<option value="10">10</option>
					<option value="20" selected="selected">20</option>
					<option value="30">30</option>
				</c:if>
				<c:if test="${rowPerPage == 30}">
					<option value="10">10</option>
					<option value="20">20</option>
					<option value="30" selected="selected">30</option>
				</c:if>
			</select>
			<select name="search" id="search">
				<option value="gd.goods_title">앨범</option>
				<option value="gd.goods_artist">가수</option>
			</select>
			
			<input type="search" placeholder="Search" name="word" id="word" value="${word}">
     		<button type="button" id="searchBtn">검색</button>
		</form>
		
		<!-- #### list #### -->	
		<table border="1">
			<tr>
				<c:forEach var="m" items="${list}" varStatus="s">
					<c:if test="${s.index != 0 && s.index % 5 == 0 }">
						</tr><tr>
					</c:if>
					
					<td>
						<div>${m.goodsCode}</div>
						<div>
							<a href="${pageContext.request.contextPath}/GoodsOne?goodsCode=${m.goodsCode}">
								${m.goodsTitle}
		      				</a>
						</div>
						<div>${m.goodsArtist}</div>
						<div>${m.goodsPrice}</div>
						<div>
							<img src="${pageContext.request.contextPath}/goodsimg/${m.filename}" width="200" height="200">
						</div>
						<div>
							view : ${m.view}
						</div>
						<div>
							<c:choose> 
								<c:when test="${m.soldout eq 'N'}">
									<a href="${pageContext.request.contextPath}/CartAdd?goodsCode=${m.goodsCode}&cartQuantity=1&filename=${m.filename}&goodsPrice=${m.goodsPrice}&goodsTitle=${m.goodsTitle}">cart</a>
								</c:when> 
								<c:otherwise>
									품절
								</c:otherwise> 
							</c:choose> 
						</div>
						<c:if test="${loginEmp != null}">
							<div>
								<a href="${pageContext.request.contextPath}/GoodsModify?goodsCode=${m.goodsCode}">수정</a>
							</div>
						</c:if>
					</td>
				</c:forEach>
			</tr>
		</table>
		<div>
			<a href="${pageContext.request.contextPath}/GoodsList?rowPerPage=${rowPerPage}&currentPage=1">처음</a>
			
			<c:if test="${currentPage > 1}">
				<a href="${pageContext.request.contextPath}/GoodsList?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}">이전</a>
			</c:if>
			
			<c:forEach var="x" begin="${beginPage}" end="${endPage}" step="1">
				<a href="${pageContext.request.contextPath}/GoodsList?rowPerPage=${rowPerPage}&currentPage=${x}">${x}</a>
			</c:forEach>
			<c:if test="${currentPage < lastPage}">
				<a href="${pageContext.request.contextPath}/GoodsList?rowPerPage=${rowPerPage}&currentPage=${currentPage +1}">다음</a>
			</c:if>
			
			<a href="${pageContext.request.contextPath}/GoodsList?rowPerPage=${rowPerPage}&currentPage=${lastPage}">마지막</a>
		</div>
		
		
		
		
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