<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Review List</h1>
	<div>
		<table border="1">
			<tr>
				<th>orderCode</th>
				<th>reviewMemo</th>
				<th>createdate</th>
				<th>삭제</th>
			</tr>
			<c:forEach var="r" items="${reviewList}">
			<tr>
				<td>${r.orderCode}</td>
				<td>${r.reviewMemo}</td>  <!-- 상세보기 추가예정 -->
				<td>${r.createdate}</td>
				<td>
					<a href="${pageContext.request.contextPath}/review/reviewRemoveByAdmin?orderCode=${r.orderCode}">
					삭제
					</a>
				</td>
			</tr>
			</c:forEach>
		</table>
		<div>
			<a href="${pageContext.request.contextPath}/review/reviewList?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}">이전</a>
			<a href="${pageContext.request.contextPath}/review/reviewList?rowPerPage=${rowPerPage}&currentPage=${currentPage+1}">다음</a>
		</div>	
	</div>
	
    
</body>
</html>