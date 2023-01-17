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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		// 직위 변경
		$('#authCodeBtn').click(function(){
			if($('#authCode').val() == '') {
				alert('변경할 직위를 선택바랍니다');
				return;
			}
			$('#authCodeForm').submit();
		});
	});
</script>
<body>
	<!-- 메뉴 partial jsp 구성 -->
	<div>
		<jsp:include page="/inc/menu.jsp"></jsp:include>
	</div>
	
   	<!-- ##### Breadcumb Area Start ##### -->
    <section class="breadcumb-area bg-img bg-overlay" style="background-image: url(${pageContext.request.contextPath}/resources/img/bg-img/breadcumb3.jpg);">
        <div class="bradcumbContent">
            <h2>Employee List</h2>
        </div>
    </section>
    <!-- ##### Breadcumb Area End ##### -->
    
    <!-- ##### Album Catagory Area Start ##### -->
	<section class="album-catagory section-padding-100-0">
		<div class="container">
			<div class="col-12">
				<div style="float:right; padding-bottom:10px;">
					<form action="${pageContext.request.contextPath}/EmpList" method="get">
						<div class="input-group">
							<!-- n명씩보기 (10,20,30) rowPerPage -->
							<select name="rowPerPage">
								<c:if test="${rowPerPage == 5}">
									<option value="5" selected="selected">5</option>
									<option value="10">10</option>
									<option value="20">20</option>
								</c:if>
								<c:if test="${rowPerPage == 10}">
									<option value="5">5</option>
									<option value="10" selected="selected">10</option>
									<option value="20">20</option>
								</c:if>
								<c:if test="${rowPerPage == 20}">
									<option value="5">5</option>
									<option value="10">10</option>
									<option value="20" selected="selected">20</option>
								</c:if>
							</select>
							<!-- 사원검색하기 기능 -->
							<input class="form-control" style="width: 200px; height:30px;" type="text" name="word" placeholder="사원이름을 입력하세요">
							<button class="btn btn-sm" type="submit"><i class="icon-loupe"></i></button>
						</div>
					</form>
				</div>
	
				<!-- n명씩보기 (10,20,30) rowPerPage 넘겨주기 추가예정 -->
				
				<table class = "table table-hover w-100 rounded" style="table-layout: auto; width: 100%; table-layout: fixed;">
					<thead>
						<tr>
							<th>
								<a href="${pageContext.request.contextPath}/EmpList?col=e.emp_name&sort=${paramSort}&word=${word}&rowPerPage=${rowPerPage}">이름</a>
							</th>
							<th>
								<a href="${pageContext.request.contextPath}/EmpList?col=e.emp_id&sort=${paramSort}&word=${word}&rowPerPage=${rowPerPage}">ID</a>
							</th>
							<th>
								<a href="${pageContext.request.contextPath}/EmpList?col=a.auth_memo&sort=${paramSort}&word=${word}&rowPerPage=${rowPerPage}">직위</a>
							</th>
							<th>
								<a href="${pageContext.request.contextPath}/EmpList?col=e.active&sort=${paramSort}&word=${word}&rowPerPage=${rowPerPage}">재직현황</a>
							</th>
							<th>
								<a href="${pageContext.request.contextPath}/EmpList?col=e.createdate&sort=${paramSort}&word=${word}&rowPerPage=${rowPerPage}">입사날짜</a>
							</th>
							<!-- 직원 직위 및 재직상태 변경 (매니저 직위만 가능) -->
							<c:if test="${loginEmp.authCode == 99}">
								<th>직위변경</th>
								<th>재직상태변경</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<!-- 사원목록 출력 -->
						<c:forEach var="e" items="${list}">
								<tr>
									<td>${e.empName}</td>
									<td>${e.empId}</td>
									<td>${e.authMemo}</td>
									<td>${e.active}</td>
									<td>${e.createdate}</td>
									<!-- 직원 직위 및 재직상태 변경 (매니저 직위만 가능) -->
									<c:if test="${loginEmp.authCode == 99}">
										<td><!-- 직위변경 -->
											<form id="authCodeForm" action="${pageContext.request.contextPath}/EmpAuthCode" method="post">
												<select name="authCode" id="authCode">
													<c:if test="${e.authMemo eq '인턴'}">
														<option value="0" selected="selected">인턴</option>
														<option value="1">사원</option>
														<option value="99">매니저</option>
													</c:if>
													<c:if test="${e.authMemo eq '사원'}">
														<option value="0">인턴</option>
														<option value="1" selected="selected">사원</option>
														<option value="99">매니저</option>
													</c:if>
													<c:if test="${e.authMemo eq '매니저'}">
														<option value="0">인턴</option>
														<option value="1">사원</option>
														<option value="99" selected="selected">매니저</option>
													</c:if>
												</select>
												<input type="hidden" name="empId" value="${e.empId}">
												<input type="hidden" name="word" value="${word}">
												<input type="hidden" name="rowPerPage" value="${rowPerPage}">
												<input type="hidden" name="sort" value="${paramSort}">
												&nbsp;&nbsp;
												<button type="submit" class="btn btn-sm btn-secondary" id="authCodeBtn">변경</button>
											</form>
										</td>
										<td><!-- 재직상태 변경 -->
											<form action="${pageContext.request.contextPath}/EmpActive" method="post">
												<select name="active">
													<c:if test="${e.active eq '재직'}">
														<option value="재직" selected="selected">재직</option>
														<option value="휴직">휴직</option>
														<option value="사직">사직</option>
													</c:if>
													<c:if test="${e.active eq '휴직'}">
														<option value="재직">재직</option>
														<option value="휴직" selected="selected">휴직</option>
														<option value="사직">사직</option>
													</c:if>
													<c:if test="${e.active eq '사직'}">
														<option value="재직">재직</option>
														<option value="휴직">휴직</option>
														<option value="사직" selected="selected">사직</option>
													</c:if>
												</select>
												<input type="hidden" name="word" value="${word}">
												<input type="hidden" name="rowPerPage" value="${rowPerPage}">
												<input type="hidden" name="sort" value="${paramSort}">
												<input type="hidden" name="empId" value="${e.empId}">
												&nbsp;&nbsp;
												<button class="btn btn-sm btn-secondary" type="submit">수정</button>
											</form>
										</td>
									</c:if>
								</tr>
						</c:forEach>
					</tbody>
				</table>
	
				<!-- 페이징처리 -->
				<div class="oneMusic-pagination-area wow fadeInUp" data-wow-delay="300ms">
					<nav>
						<ul class="pagination">
							<li class="page-item"><!-- 첫페이지 -->
								<a class="page-link" href="${pageContext.request.contextPath}/EmpList?currentPage=1&word=${word}&rowPerPage=${rowPerPage}&sort=${paramSort}">HOME</a>
							</li>
							<c:if test="${currentPage > 1}">
								<li class="page-item">
									<a class="page-link" href="${pageContext.request.contextPath}/EmpList?currentPage=${currentPage-1}&word=${word}&rowPerPage=${rowPerPage}&sort=${paramSort}">◀</a>
								</li>
							</c:if>
							<!-- 페이지목록 -->
							<c:forEach var="i" begin="${beginPage}" end="${endPage}" step="1">
								<c:choose>
									<c:when test="${currentPage == i}">
										<li class="page-item">
											<a class="page-link" style="color:orange">${i}</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="page-item">
											<a class="page-link" href="${pageContext.request.contextPath}/EmpList?currentPage=${i}&word=${word}&rowPerPage=${rowPerPage}&sort=${paramSort}">${i}</a>
										</li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:if test="${currentPage < lastPage}">
								<li class="page-item"><!-- 다음 -->
									<a class="page-link" href="${pageContext.request.contextPath}/EmpList?currentPage=${currentPage+1}&word=${word}&rowPerPage=${rowPerPage}&sort=${paramSort}">▶</a>
								</li>
							</c:if>
							<li class="page-item"><!-- 끝페이지 -->
								<a class="page-link" href="${pageContext.request.contextPath}/EmpList?currentPage=${lastPage}&word=${word}&rowPerPage=${rowPerPage}&sort=${paramSort}">END</a>
							</li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</section>
	<br><br>
	<!-- 메뉴 partial jsp 구성 -->
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