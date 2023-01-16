<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="description" content="">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    	
    	<!-- Title -->
		<title>Recoder Musice - World Best Music</title>
		
		<!-- Favicon -->
	    <link rel="icon" href="${pageContext.request.contextPath}/resources/img/core-img/favicon.ico">
	
	    <!-- Stylesheet -->
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css">
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	</head>
	<body>
		<!-- 메뉴 partial jsp 구성 -->
		<div>
			<jsp:include page="/inc/menu.jsp"></jsp:include>
		</div>

	    <!-- ##### Breadcumb Area Start ##### -->
	    <section class="breadcumb-area bg-img bg-overlay" style="background-image: url(${pageContext.request.contextPath}/resources/img/bg-img/breadcumb3.jpg);">
	        <div class="bradcumbContent">
	            <p>See what’s new</p>
	            <h2>Goods</h2>
	        </div>
	    </section>
	    
	    <!-- ##### Breadcumb Area End ##### -->
		<h1>상품등록</h1>
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
		
		<!-- 메뉴 partial jsp 구성 -->
		<div>
			<jsp:include page="/inc/footer.jsp"></jsp:include>
		</div>
			
		<!-- ##### All Javascript Script ##### -->
	    <!-- jQuery-2.2.4 js -->
	    <script src="${pageContext.request.contextPath}/resources/js/jquery/jquery-2.2.4.min.js"></script>
	    <!-- Popper js -->
	    <script src="${pageContext.request.contextPath}/resources/js/bootstrap/popper.min.js"></script>
	    <!-- Bootstrap js -->
	    <script src="${pageContext.request.contextPath}/resources/js/bootstrap/bootstrap.min.js"></script>
	    <!-- All Plugins js -->
	    <script src="${pageContext.request.contextPath}/resources/js/plugins/plugins.js"></script>
	    <!-- Active js -->
	    <script src="${pageContext.request.contextPath}/resources/js/active.js"></script>
	</body>
</html>