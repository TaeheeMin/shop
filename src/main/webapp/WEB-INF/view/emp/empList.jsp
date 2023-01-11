<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	<h1>사원목록</h1>
	<div><a href="${pageContext.request.contextPath}/Home">홈</a></div>
	<!-- 검색기능 추가예정 -->
	<div>
		<form action="${pageContext.request.contextPath}/EmpList" method="get">
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
			사원검색 : 
				<input type="text" name="word" placeholder="사원이름을 입력하세요">
			<button type="submit">검색</button>
		</form>
	</div>
	
	<!-- n명씩보기 (10,20,30) rowPerPage 넘겨주기 추가예정 -->
	
	<table border="1">
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
									<button type="submit" id="authCodeBtn">변경</button>
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
									<button type="submit">수정</button>
								</form>
							</td>
						</c:if>
					</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 페이징처리 -->
	<div>
		<span><!-- 첫페이지 -->
			<a href="${pageContext.request.contextPath}/EmpList?currentPage=1&word=${word}&rowPerPage=${rowPerPage}&sort=${paramSort}">첫</a>
		</span>
		<c:if test="${currentPage > 1}">
			<span><!-- 이전 -->
				<a href="${pageContext.request.contextPath}/EmpList?currentPage=${currentPage-1}&word=${word}&rowPerPage=${rowPerPage}&sort=${paramSort}">◀</a>
			</span>
		</c:if>
		<!-- 페이지목록 -->
		<c:forEach var="i" begin="${beginPage}" end="${endPage}" step="1">
			<c:choose>
				<c:when test="${currentPage == i}">
					<span>
						<a style="color:orange">${i}</a>
					</span>
				</c:when>
				<c:otherwise>
					<span>
						<a href="${pageContext.request.contextPath}/EmpList?currentPage=${i}&word=${word}&rowPerPage=${rowPerPage}&sort=${paramSort}">${i}</a>
					</span>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${currentPage < lastPage}">
			<span><!-- 다음 -->
				<a href="${pageContext.request.contextPath}/EmpList?currentPage=${currentPage+1}&word=${word}&rowPerPage=${rowPerPage}&sort=${paramSort}">▶</a>
			</span>
		</c:if>
		<span><!-- 끝페이지 -->
			<a href="${pageContext.request.contextPath}/EmpList?currentPage=${lastPage}&word=${word}&rowPerPage=${rowPerPage}&sort=${paramSort}">끝</a>
		</span>
	</div>
</body>
</html>