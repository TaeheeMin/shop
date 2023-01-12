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
			<table>
				<tr>
					<th>장르</th>
					<td>
						<select name="goodsCategory">
							<option value="발라드">발라드</option>
							<option value="댄스/팝">댄스/팝</option>
							<option value="랩/힙합">랩/힙합</option>
							<option value="재즈">재즈</option>
							<option value="해외팝">해외팝</option>
							<option value="인디">인디</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>
						<input type="text" name="goodsTitle">
					</td>
				</tr>
				<tr>
					<th>가수</th>
					<td>
						<input type="text" name="goodsArtist">
					</td>
				</tr>
				<tr>
					<th>설명</th>
					<td>
						<textarea rows="10" cols="50" name="goodsContent"></textarea>
					</td>
				</tr>
				<tr>
					<th>가격</th>
					<td>
						<input type="text" name="goodsPrice">
					</td>
				</tr>
				<tr>
					<th>사진</th>
					<td>
						 <input type="file" name="goodsFile">
					</td>
				</tr>
			</table>
			<button type="submit" id="addBtn">등록</button>
		</form>
	</body>
</html>