<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>상품등록</title>
	</head>
	<body>
		<h1>상품등록</h1>
		<a href="${pageContext.request.contextPath}/Home">홈으로</a>
		<a href="${pageContext.request.contextPath}/GoodsList">상품목록</a>
		<form enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath}/GoodsAdd">
			상품 이름 : <input type="text" name="goodsTitle">
			상품 가수 : <input type="text" name="goodsArtist">
			상품 설명 : <input type="text" name="goodsContent">
			상품 가격 : <input type="text" name="goodsPrice">
			상품 이미지 : <input type="file" name="goodsFile">
			<button type="submit" id="addBtn">등록</button>
		</form>
	</body>
</html>