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
				$('#listFormBtn').click(function() {
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
	            <h2>Q&A</h2>
	        </div>
	    </section>
	    <!-- ##### Breadcumb Area End ##### -->
		
		<section class="album-catagory section-padding-100-0">
	        <div class="container">
		        <div class="col-12">	         
			        <!-- #### search #### -->
					<form action="${pageContext.request.contextPath}/QuestionListByAdmin" method="get" id="listForm">
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
						<input type="radio" name="category" id ="category" value="" checked="checked">전체
						<input type="radio" name="category" id ="category" value="배송">배송
						<input type="radio" name="category" id ="category" value="반품">반품
						<input type="radio" name="category" id ="category" value="상품">상품
						<input type="radio" name="category" id ="category" value="교환">교환
						<input type="radio" name="category" id ="category" value="기타">기타
						<button type="button" id="listFormBtn">검색</button>
					</form>
				   <table class = "table table-hover w-100 rounded" style="table-layout: auto; width: 100%; table-layout: fixed;">
						<tr>
							<th>문의사항 번호</th>
							<th>카테고리</th>
							<th>상품</th>
							<th>문의제목</th>
							<th>작성자</th>
							<th>등록일</th>
							<th>답변</th>
						</tr>
						<c:forEach var="q" items="${list}">
							<tr>
								<td>${q.questionCode}</td>
								<td>${q.category}</td>
								<td>
								<div class="single-album-area">
									<div class="album-thumb">
				                            <img src="${pageContext.request.contextPath}/goodsimg/${q.filename}" width="150" height="150" alt="">
				                            <!-- Play Icon -->
				                            <div class="play-icon">
				                                <a href="${pageContext.request.contextPath}/GoodsOne?goodsCode=${q.goodsCode}" class="video--play--btn"><span class="icon-music"></span></a>
				                            </div>
				                        </div>
				                        
				                        <div class="album-info">
				                                ${q.goodsTitle}
				                        </div>
			                        </div>
									
										
								</td>
								<td>
									<a href="${pageContext.request.contextPath}/QuestionOne?questionCode=${q.questionCode}">${q.questionTitle}</a>
								</td>
								<td>${q.customerId}</td>
								<td>${q.createdate.substring(0,10)}</td>
								<td>
									<c:forEach var="c" items="${comment}">
										<c:choose> 
											<c:when test="${c.questionCode eq q.questionCode}">
												<p>답변완료</p>
											</c:when> 
											<c:otherwise>
												<a href="${pageContext.request.contextPath}/QuestionOne?questionCode=${q.questionCode}"> <p class="text-danger">미답변</p></a>
											</c:otherwise> 
										</c:choose> 
									</c:forEach>
								</td>
							</tr>
						</c:forEach>
					</table>
			
		            <!-- Pagination -->
		            <div class="oneMusic-pagination-area wow fadeInUp mb-50" data-wow-delay="300ms">
		                <nav>
		                    <ul class="pagination">
		                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/QuestionListByAdmin?rowPerPage=${rowPerPage}&currentPage=1">HOME</a></li>
		                        <c:if test="${currentPage > 1}">
									<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/QuestionListByAdmin?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}"><<</a></li>
								</c:if>
								<c:forEach var="x" begin="${beginPage}" end="${endPage}" step="1">
									<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/QuestionListByAdmin?rowPerPage=${rowPerPage}&currentPage=${x}">${x}</a></li>
								</c:forEach>
								<c:if test="${currentPage < lastPage}">
									<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/QuestionListByAdmin?rowPerPage=${rowPerPage}&currentPage=${currentPage +1}">>></a></li>
								</c:if>
								<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/QuestionListByAdmin?rowPerPage=${rowPerPage}&currentPage=${lastPage}">END</a></li>
		                    </ul>
		                </nav>
		            </div>
				</div>
			</div>
	    </section>
		
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