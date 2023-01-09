<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>문의사항 등록</title>
	</head>
	<body>
		<h1>문의사항 등록</h1>
		<a href="${pageContext.request.contextPath}/Home">홈으로</a>
		<form action="${pageContext.request.contextPath}/QuestionAdd" method="post"> 
			<div>
				주문 목록
				<select name="odersCode">
					<c:forEach var="list" items="${list}">
						<option value="${list.orderCode}">${list.orderCode}</option>
					</c:forEach>
				</select>
			</div>
			<div>
				카테고리
				<select name="category">
					<option value="배송">배송</option>
					<option value="반품">반품</option>
					<option value="교환">교환</option>
					<option value="기타">기타</option>
				</select>
			</div>
			<div>
				제목
				<input type="text" name="questionTitle">
			</div>
			<div>
				내용
				<textarea rows="10" cols="25" name="questionMemo"></textarea>
			</div>
			<button type="submit">등록</button>
		</form>
	</body>
</html>