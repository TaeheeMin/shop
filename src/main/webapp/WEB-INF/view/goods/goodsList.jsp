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
				$('#searchBtn').click(function() {
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
			<div>
				<input type="radio" name="category" id="category" value="">전체
				<input type="radio" name="category" id="category" value="발라드">발라드
				<input type="radio" name="category" id="category" value="댄스/팝">댄스/팝
				<input type="radio" name="category" id="category" value="랩/힙합">랩/힙합
				<input type="radio" name="category" id="category" value="재즈">재즈
				<input type="radio" name="category" id="category" value="해외팝">해외팝
				<input type="radio" name="category" id="category" value="인디">인디
			</div>
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
			<select name="search" id="search">
				<option value="gd.goods_title">앨범</option>
				<option value="gd.goods_artist">가수</option>
			</select>
			
			<input type="search" placeholder="Search" name="word" id="word" value="${word}">
     		<button type="button" id="searchBtn">검색</button>
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
						<div>
							<img src="${pageContext.request.contextPath}/goodsimg/${m.filename}" width="200" height="200">
						</div>
						<div>
							view : ${m.view}
						</div>
						<div>
							<c:choose> 
								<c:when test="${m.soldout eq 'N'}">
									<a href="${pageContext.request.contextPath}/CartAdd?goodsCode=${m.goodsCode}&cartQuantity=1&filename=${m.filename}&goodsPrice=${m.goodsPrice}&goodsTitle=${m.goodsTitle}">cart</a>
								</c:when> 
								<c:otherwise>
									품절
								</c:otherwise> 
							</c:choose> 
						</div>
						<c:if test="${loginEmp != null}">
							<div>
								<a href="${pageContext.request.contextPath}/GoodsModify?goodsCode=${m.goodsCode}">수정</a>
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
			
			<c:forEach var="x" begin="${beginPage}" end="${endPage}" step="1">
				<a href="${pageContext.request.contextPath}/GoodsList?rowPerPage=${rowPerPage}&currentPage=${x}">${x}</a>
			</c:forEach>
			<c:if test="${currentPage < lastPage}">
				<a href="${pageContext.request.contextPath}/GoodsList?rowPerPage=${rowPerPage}&currentPage=${currentPage +1}">다음</a>
			</c:if>
			
			<a href="${pageContext.request.contextPath}/GoodsList?rowPerPage=${rowPerPage}&currentPage=${lastPage}">마지막</a>
		</div>
		
		
	</body>
</html>