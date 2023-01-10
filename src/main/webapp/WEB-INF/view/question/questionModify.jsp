<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>문의사항 수정</title>
	</head>
	<body>
		<h1>문의사항 수정</h1>
		<a href="${pageContext.request.contextPath}/Home">홈으로</a>
		<a href="${pageContext.request.contextPath}/QuestionList">문의사항 목록</a>
		<form action="${pageContext.request.contextPath}/QuestionModify" method="post">
			<table border="1">
				<c:forEach var="list" items="${list}">
					<tr>
						<th>문의번호</th>
						<td>
							<input type="text" name="questionCode" value="${list.questionCode}" readonly="readonly">
						</td>
					</tr>
					<tr>
						<th>주문번호</th>
						<td>
							<input type="text" name="odersCode" value="${list.odersCode}" readonly="readonly">
						</td>
					</tr>
					<tr>
						<th>카테고리</th>
						<td>
							<input type="text" name="category" value="${list.category}">
						</td>
					</tr>
					<tr>
						<th>문의제목</th>
						<td>
							<input type="text" name="questionTitle" value="${list.questionTitle}">
						</td>
					</tr>
					<tr>
						<th>문의내용</th>
						<td>
						<textarea rows="10" cols="25" name="qusetionMemo">${list.qusetionMemo}</textarea>
						</td>
					</tr>
					<tr>
						<th>등록일</th>
						<td><c:out value="${list.createdate}"></c:out></td>
					</tr>
				</c:forEach>
			</table>
			<button type="submit">수정</button>
		</form>
	</body>
</html>