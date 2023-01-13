<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>문의사항</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				$('#rowPerPage').change(function() {
					$('#listForm').submit();
				});
			});
		</script>
	</head>
	<body>
		<h1>문의사항-관리자용</h1>
		<a href="${pageContext.request.contextPath}/Home">홈으로</a>
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
			<input type="radio" name="comment" value="">전체
			<input type="radio" name="category" value="배송">배송
			<input type="radio" name="category" value="반품">반품
			<input type="radio" name="category" value="상품">상품
			<input type="radio" name="category" value="교환">교환
			<input type="radio" name="category" value="기타">기타
		</form>
		<table border="1">
			<tr>
				<th>문의사항 번호</th>
				<th>주문번호</th>
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
					<td>${q.ordersCode}</td>
					<td>${q.category}</td>
					<td>
						<div>
							<img src="${pageContext.request.contextPath}/goodsimg/${q.filename}" width="100" height="100">
						</div>
						${q.goodsTitle}
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
									답변완료
								</c:when> 
								<c:otherwise>
									<a href="${pageContext.request.contextPath}/QuestionOne?questionCode=${q.questionCode}">미답변</a>
								</c:otherwise> 
							</c:choose> 
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div>
			<a href="${pageContext.request.contextPath}/QuestionListByAdmin?rowPerPage=${rowPerPage}&currentPage=1">처음</a>
			
			<c:if test="${currentPage > 1}">
				<a href="${pageContext.request.contextPath}/QuestionListByAdmin?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}">이전</a>
			</c:if>
			
			<c:forEach var="x" begin="${beginPage}" end="${endPage}" step="1">
				<a href="${pageContext.request.contextPath}/QuestionListByAdmin?rowPerPage=${rowPerPage}&currentPage=${x}">${x}</a>
			</c:forEach>
			<c:if test="${currentPage < lastPage}">
				<a href="${pageContext.request.contextPath}/QuestionListByAdmin?rowPerPage=${rowPerPage}&currentPage=${currentPage +1}">다음</a>
			</c:if>
			
			<a href="${pageContext.request.contextPath}/QuestionListByAdmin?rowPerPage=${rowPerPage}&currentPage=${lastPage}">마지막</a>
		</div>
	</body>
</html>