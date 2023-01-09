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
		<a href="${pageContext.request.contextPath}/QuestionList">문의사항 목록</a>
		<table border="1">
			<c:forEach var="list" items="${list}">
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
					<tr>	
						<th>수정삭제</th>
						<td>
							수정
							삭제
						</td>
					</tr>	
			</c:forEach>
		</table>
	</body>
</html>