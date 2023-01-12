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
				<th>주문코드</th>
				<th>상품이름</th>
				<th>상품</th>
				<th>리뷰내용</th>
				<th>작성자</th>
				<th>작성일</th>				
				<th>삭제</th>
			</tr>
			<c:forEach var="r" items="${list}">
			<tr>
				<td>${r.orderCode}</td>
				<td>${r.goodsTitle}</td>
				<td><img src="${pageContext.request.contextPath}/goodsimg/${r.filename}" width="200" height="200"></td>
				<td>${r.reviewMemo}</td>
				<td>${r.customerId}</td>
				<td>${r.createdate}</td>
				<td>
					<a href="${pageContext.request.contextPath}/review/reviewRemoveByAdmin?orderCode=${r.orderCode}">
					삭제
					</a>
				</td>
			</tr>
			</c:forEach>
		</table>	
	</div>
	<!-- 페이징 -->
		<div>
			<a href="${pageContext.request.contextPath}/review/reviewList?rowPerPage=${rowPerPage}&currentPage=1">처음</a>
			
			<c:if test="${currentPage > 1}">
				<a href="${pageContext.request.contextPath}/review/reviewList?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}">이전</a>
			</c:if>
			
			<c:forEach var="x" begin="${beginPage}" end="${endPage}" step="1">
				<a href="${pageContext.request.contextPath}/review/reviewList?rowPerPage=${rowPerPage}&currentPage=${x}">${x}</a>
			</c:forEach>
			<c:if test="${currentPage < lastPage}">
				<a href="${pageContext.request.contextPath}/review/reviewList?rowPerPage=${rowPerPage}&currentPage=${currentPage +1}">다음</a>
			</c:if>
			
			<a href="${pageContext.request.contextPath}/review/reviewList?rowPerPage=${rowPerPage}&currentPage=${lastPage}">마지막</a>
		</div>
    
</body>
</html>