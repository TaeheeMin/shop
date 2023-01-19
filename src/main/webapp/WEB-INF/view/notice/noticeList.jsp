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
	</head>
	<body>
		<!-- 메뉴 partial jsp 구성 -->
		<div>
			<jsp:include page="/inc/menu.jsp"></jsp:include>
		</div>
	
		<!-- 사원이상의 재직자만 공지등록 가능 -->
		<c:if test="${limitNotice != null}">
			<script>
				alert('사원이상의 재직자만 접근 가능합니다');
			</script>
		</c:if>
		
	    <!-- ##### Breadcumb Area Start ##### -->
	    <section class="breadcumb-area bg-img bg-overlay" style="background-image: url(${pageContext.request.contextPath}/resources/img/bg-img/breadcumb3.jpg);">
	        <div class="bradcumbContent">
	            <p>See what’s new</p>
	            <h2>News</h2>
	        </div>
	    </section>
	    <!-- ##### Breadcumb Area End ##### -->
	
	    <!-- ##### Blog Area Start ##### -->
	    <div class="blog-area section-padding-100">
	        <div class="container">
	            <div class="row">
	                <div class="col-12 col-lg-9">
	                	<!-- 직원만 공지등록 버튼 조회 가능 -->
						<c:if test="${loginEmp != null}">
							<div style="float: right; margin-bottom: 10px;">
	                        	<a href="${pageContext.request.contextPath}/NoticeAdd">공지등록</a>
							</div>
						</c:if>

	                    <!-- Single Post Start -->
						<c:forEach var="n" items="${notice}">
		                    <div class="single-blog-post mb-100 wow fadeInUp panel single-accordion" data-wow-delay="100ms">
		                        <!-- Post Thumb -->
		                        <div class="blog-post-thumb mt-30">
		                            <a href="${pageContext.request.contextPath}/NoticeOne?noticeCode=${n.noticeCode}"><img src="${pageContext.request.contextPath}/resources/img/bg-img/blog2.jpg" alt=""></a>
		                            <!-- Post Date -->
		                            <div class="post-date">
		                                <span>${n.createdate.substring(8,10)}</span>
		                                <span>${n.createdate.substring(5,7)}‘${n.createdate.substring(2,4)}</span>
		                            </div>
		                        </div>
		
		                        <!-- Blog Content -->
		                        <div class="blog-content">
		                        	<!-- Post Meta -->
		                            <div class="post-meta d-flex">
		                                <p class="tags"><a href="#"> Notice</a></p>
		                                <p class="post-author">By Admin</p>
		                                <p class="tags"><a href="#"> ${n.createdate.substring(0,10)}</a></p>
		                            </div>
		                            <!-- Post Excerpt -->
		                        
		                            <!-- Post Title -->
		                            <div class="accordions mb-30" id="accordion" role="tablist" aria-multiselectable="true">
				                        <!-- single accordian area -->
				                            <h6><a role="button" class="post-title" aria-expanded="false" aria-controls="collapse${n.noticeCode }" data-toggle="collapse" data-parent="#accordion" href="#collapse${n.noticeCode }">${n.noticeTitle}
				                                    <span class="accor-open"><i class="fa fa-plus" aria-hidden="true"></i></span>
				                                    <span class="accor-close"><i class="fa fa-minus" aria-hidden="true"></i></span>
				                                </a>
				                            </h6>
				                            <div id="collapse${n.noticeCode}" class="accordion-content collapse">
				                                <p>${n.noticeContent}</p>
				                            	<c:if test="${loginEmp != null}">
					                            	<P>
					                            		<a href="${pageContext.request.contextPath}/NoticeModify?noticeCode=${n.noticeCode}">수정</a>
					                            		<a href="${pageContext.request.contextPath}/NoticeRemove?noticeCode=${n.noticeCode}">삭제</a>
					                            	</P>
												</c:if>
				                            	
				                            </div>
				                     </div>
		                        </div>
		                    </div>
						</c:forEach>
						<!-- Pagination -->
			            <div class="oneMusic-pagination-area wow fadeInUp mb-50" data-wow-delay="300ms">
			                <nav>
			                    <ul class="pagination">
			                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/NoticeList?rowPerPage=${rowPerPage}&currentPage=1">HOME</a></li>
			                        <c:if test="${currentPage > 1}">
										<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/NoticeList?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}"><<</a></li>
									</c:if>
									<c:forEach var="x" begin="${beginPage}" end="${endPage}" step="1">
										<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/NoticeList?rowPerPage=${rowPerPage}&currentPage=${x}">${x}</a></li>
									</c:forEach>
									<c:if test="${currentPage < lastPage}">
										<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/NoticeList?rowPerPage=${rowPerPage}&currentPage=${currentPage +1}">>></a></li>
									</c:if>
									<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/NoticeList?rowPerPage=${rowPerPage}&currentPage=${lastPage}">END</a></li>
			                    </ul>
			                </nav>
			            </div>
	                    <!-- Pagination -->
	                    <div class="oneMusic-pagination-area wow fadeInUp" data-wow-delay="300ms">
	                        <nav>
	                            <ul class="pagination">
	                                <li class="page-item active"><a class="page-link" href="#">01</a></li>
	                                <li class="page-item"><a class="page-link" href="#">02</a></li>
	                                <li class="page-item"><a class="page-link" href="#">03</a></li>
	                            </ul>
	                        </nav>
	                    </div>
	                </div>
					
					
	                <div class="col-12 col-lg-3">
	                    <div class="blog-sidebar-area">
	                        <!-- Widget Area -->
	                        <div class="single-widget-area mb-30">
	                            <div class="widget-title">
	                                <h5>Categories</h5>
	                            </div>
	                            <div class="widget-content">
	                                <ul>
	                                    <li><a href="#">Music</a></li>
	                                    <li><a href="#">Events &amp; Press</a></li>
	                                    <li><a href="#">Festivals</a></li>
	                                    <li><a href="#">Lyfestyle</a></li>
	                                    <li><a href="#">Uncategorized</a></li>
	                                </ul>
	                            </div>
	                        </div>
							
	                        <!-- Widget Area -->
	                        <div class="single-widget-area mb-30">
	                            <div class="widget-title">
	                                <h5>Archive</h5>
	                            </div>
	                            <div class="widget-content">
	                                <ul>
	                                    <li><a href="#">February 2018</a></li>
	                                    <li><a href="#">March 2018</a></li>
	                                    <li><a href="#">April 2018</a></li>
	                                    <li><a href="#">May 2018</a></li>
	                                    <li><a href="#">June 2018</a></li>
	                                </ul>
	                            </div>
	                        </div>
	
	                        <!-- Widget Area -->
	                        <div class="single-widget-area mb-30">
	                            <div class="widget-title">
	                                <h5>Tags</h5>
	                            </div>
	                            <div class="widget-content">
	                                <ul class="tags">
	                                    <li><a href="#">music</a></li>
	                                    <li><a href="#">events</a></li>
	                                    <li><a href="#">artists</a></li>
	                                    <li><a href="#">press</a></li>
	                                    <li><a href="#">mp3</a></li>
	                                    <li><a href="#">videos</a></li>
	                                    <li><a href="#">concerts</a></li>
	                                    <li><a href="#">performers</a></li>
	                                </ul>
	                            </div>
	                        </div>
	
	                        <!-- Widget Area -->
	                        <div class="single-widget-area mb-30">
	                            <a href="#"><img src="${pageContext.request.contextPath}/resources/img/bg-img/add.gif" alt=""></a>
	                        </div>
	
	                        <!-- Widget Area -->
	                        <div class="single-widget-area mb-30">
	                            <a href="#"><img src="${pageContext.request.contextPath}/resources/img/bg-img/add2.gif" alt=""></a>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <!-- ##### Blog Area End ##### -->
		
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