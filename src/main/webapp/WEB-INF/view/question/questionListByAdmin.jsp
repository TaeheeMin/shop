<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>문의사항</title>
	</head>
	<body>
		<h1>문의사항-관리자용</h1>
		<a href="${pageContext.request.contextPath}/Home">홈으로</a>
		<table border="1">
			<tr>
				<th>문의사항 번호</th>
				<th>주문번호</th>
				<th>카테고리</th>
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
						<a href="${pageContext.request.contextPath}/QuestionOne?questionCode=${q.questionCode}">${q.questionTitle}</a>
					</td>
					<td>${q.createdate}</td>
					<td>
						<a href="${pageContext.request.contextPath}/QuestionOne?questionCode=${q.questionCode}">답변</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>