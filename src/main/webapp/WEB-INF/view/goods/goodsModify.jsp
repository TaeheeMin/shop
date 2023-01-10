<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>상품 수정</title>
		
	</head>
	<body>
		<h1>상품 수정</h1>
		<a href="${pageContext.request.contextPath}/Home">홈으로</a>
		<a href="${pageContext.request.contextPath}/GoodsList">상품목록</a>

		<form action="${pageContext.request.contextPath}/GoodsModify" method="post" id="">
			<table border="1">
				<c:forEach var="list" items="${list}">
					<tr>
						<th>NO</th>
						<td>
							<input type="text" name="goodsCode" value="${list.goodsCode}">
						</td>
					</tr>
					<tr>
						<th>CATEGORY</th>
						<td>
							<select name="goodsCategory">
								<option value="발라드">발라드</option>
								<option value="댄스/팝">댄스/팝</option>
								<option value="랩/힙합">랩/힙합</option>
								<option value="재즈">재즈</option>
								<option value="해외팝">해외팝</option>
								<option value="인디">인디</option>
							</select>
							<input type="text" name="" value="${list.goodsCategory}">
						</td>
					</tr>
					<tr>
						<th>TITLE</th>
						<td>
							<input type="text" name="goodsTitle" value="${list.goodsTitle}">
						</td>
					</tr>
					<tr>
						<th>IMG</th>
						<td>
							<img src="${pageContext.request.contextPath}/goodsimg/${list.filename}" width="200" height="200">
						</td>
					</tr>
					<tr>
						<th>ARTIST</th>
						<td>
							<input type="text" name="goodsArtist" value="${list.goodsArtist}">
						</td>
					</tr>
					<tr>
						<th>CONTENT</th>
						<td>
							<input type="text" name="goodsContent" value="${list.goodsContent}">
						</td>
					</tr>
					<tr>
						<th>PRICE</th>
						<td>
							<input type="text" name="goodsPrice" value="${list.goodsPrice}">
						</td>
					</tr>
					<tr>
						<th>SOLDOUT</th>
						<td>
							<c:choose> 
								<c:when test="${list.soldout eq 'N'}">
									<input type="radio" name="soldout" value="Y">재고없음
									<input type="radio" name="soldout" value="N" checked="checked">재고있음
								</c:when> 
								<c:otherwise>
									<input type="radio" name="soldout" value="Y" checked="checked">재고없음
									<input type="radio" name="soldout" value="N" >재고있음
								</c:otherwise> 
							</c:choose> 
						</td>
					</tr>
				</c:forEach>
			</table>
		<button type="submit">수정</button>
		</form>
	</body>
</html>