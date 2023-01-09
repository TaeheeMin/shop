<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>상품목록</title>
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
		<h1>상품목록</h1>
		<a href="${pageContext.request.contextPath}/Home">홈으로</a>
		<a href="${pageContext.request.contextPath}/CartList">장바구니</a>
		<c:if test="${loginEmp != null}">
			<a href="${pageContext.request.contextPath}/GoodsAdd">상품등록</a>
		</c:if>
		
		<!-- #### search #### -->
		<form action="${pageContext.request.contextPath}/GoodsList" method="get" id="listForm">
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
			<select name="category" id="category">
				<option value="gd.goods_title">앨범</option>
				<option value="gd.goods_artist">가수</option>
			</select>
			<input type="search" placeholder="Search" name="word" id="search" value="${word}">
     		<button type="submit">검색</button>
		</form>
		
		<!-- #### list #### -->	
		<table border="1">
			<tr>
				<c:forEach var="m" items="${list}" varStatus="s">
					<c:if test="${s.index != 0 && s.index % 5 == 0 }">
						</tr><tr>
					</c:if>
					
					<td>
						<div>${m.goodsCode}</div>
						<div>
							<a href="${pageContext.request.contextPath}/GoodsOne?goodsCode=${m.goodsCode}">
								${m.goodsTitle}
		      				</a>
						</div>
						<div>${m.goodsArtist}</div>
						<div>${m.goodsPrice}</div>
						<div>${m.soldout}</div>
						<div>
							<img src="${pageContext.request.contextPath}/goodsimg/${m.filename}" width="200" height="200">
						</div>
						<div>
							view : ${m.view}
						</div>
						<div>
							<a href="${pageContext.request.contextPath}/CartAdd?goodsCode=${m.goodsCode}&cartQuantity=1&filename=${m.filename}&goodsPrice=${m.goodsPrice}&goodsTitle=${m.goodsTitle}">cart</a>
							<a href="${pageContext.request.contextPath}/orders/ordersAdd?goodsCode=${m.goodsCode}&cartQuantity=1&goodsArtist=${m.goodsArtist}&filename=${m.filename}&goodsPrice=${m.goodsPrice}&goodsTitle=${m.goodsTitle}">구매하기</a>
						</div>
						<c:if test="${loginEmp != null}">
							<div>
								<a href="${pageContext.request.contextPath}/GoodsModify">수정</a>
								<a href="${pageContext.request.contextPath}/GoodsRemove">삭제</a>
							</div>
						</c:if>
					</td>
				</c:forEach>
			</tr>
		</table>
		<div>
			<a href="${pageContext.request.contextPath}/GoodsList?rowPerPage=${rowPerPage}&currentPage=1">처음</a>
			
			<c:if test="${currentPage > 1}">
				<a href="${pageContext.request.contextPath}/GoodsList?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}">이전</a>
			</c:if>
			
			<c:forEach var="x" begin="${beginPage}" end="${beginPage = endPage}" step="1">
				<a href="${pageContext.request.contextPath}/GoodsList?rowPerPage=${rowPerPage}&currentPage=${x}">${x}</a>
			</c:forEach>
			<c:if test="${currentPage < lastPage}">
				<a href="${pageContext.request.contextPath}/GoodsList?rowPerPage=${rowPerPage}&currentPage=${currentPage +1}">다음</a>
			</c:if>
			
			<a href="${pageContext.request.contextPath}/GoodsList?rowPerPage=${rowPerPage}&currentPage=${lastPage}">마지막</a>
		</div>
		
		
	</body>
</html>