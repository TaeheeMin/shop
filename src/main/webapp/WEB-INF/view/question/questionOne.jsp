<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>문의사항 상세보기</title>
	</head>
	<body>
		<h1>문의사항-상세보기</h1>
		<a href="${pageContext.request.contextPath}/Home">홈으로</a>
		<c:if test="${loginEmp != null}">
		<a href="${pageContext.request.contextPath}/QuestionListByAdmin">관리자 문의사항 목록</a>
		</c:if>
		<c:if test="${loginCustomer != null}">
		<a href="${pageContext.request.contextPath}/QuestionListByCustomer">문의사항 목록</a>
		</c:if>
		<form action="${pageContext.request.contextPath}/QuestionCommentAdd" method="post">
			<table border="1">
				<c:forEach var="list" items="${list}">
					<tr>
						<th>문의사항 번호</th>
						<td>
							<input type="hidden" name="questionCode" value="${list.questionCode}">
							<c:out value="${list.questionCode}"></c:out>
						</td>
					</tr>
					<tr>
						<th>주문번호</th>
						<td><c:out value="${list.odersCode}"></c:out></td>
					</tr>
					<tr>
						<th>카테고리</th>
						<td><c:out value="${list.category}"></c:out></td>
					</tr>
					<tr>
						<th>문의제목</th>
						<td><c:out value="${list.questionTitle}"></c:out></td>
					</tr>
					<tr>
						<th>문의내용</th>
						<td><c:out value="${list.qusetionMemo}"></c:out></td>
					</tr>
					<tr>
						<th>등록일</th>
						<td><c:out value="${list.createdate}"></c:out></td>
					</tr>
				</c:forEach>
			</table>
			
			<c:if test="${loginEmp != null}">
			<h3>답변 등록</h3>
			<textarea rows="5" cols="30" name="commentMemo"></textarea>
			</c:if>
		</form>
		<h3>답변보기</h3>
		<c:forEach var="comment" items="${comment}">
			<table border="1">
				<tr>
					<th>작성일</th>
					<td>${comment.createdate}</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${comment.commentMemo}</td>
				</tr>
			</table>		
		</c:forEach>
	</body>
</html>