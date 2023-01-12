<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>문의사항</title>
	</head>
	<body>
		<h1>문의사항-회원용</h1>
		<a href="${pageContext.request.contextPath}/Home">홈으로</a>
		<a href="${pageContext.request.contextPath}/QuestionAdd">문의사항 등록</a>
		<table border="1">
			<tr>
				<th>문의사항 번호</th>
				<th>주문번호</th>
				<th>카테고리</th>
				<th>상품</th>
				<th>문의제목</th>
				<th>등록일</th>
				<th>수정삭제</th>
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
					<td>${q.createdate}</td>
					<td>
						<c:forEach var="c" items="${comment}">
							<c:choose> 
								<c:when test="${c.questionCode eq q.questionCode}">
									<a href="${pageContext.request.contextPath}/QuestionOne?questionCode=${q.questionCode}">답변보기</a>
								</c:when> 
								<c:otherwise>
									<a href="${pageContext.request.contextPath}/QuestionModify?questionCode=${q.questionCode}">수정</a>
									<a href="${pageContext.request.contextPath}/QuestionRemove?questionCode=${q.questionCode}">삭제</a>
								</c:otherwise> 
							</c:choose> 
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>