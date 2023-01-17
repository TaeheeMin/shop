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
		<script>
			$(document).ready(function() {
				
			});
		</script>
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
	            <h2>ALBUMS</h2>
	        </div>
	    </section>
	    <!-- ##### Breadcumb Area End ##### -->
		
		
		<!-- #### list #### -->
		<table border="1">
			<c:forEach var="goodsOne" items="${goodsOne}">
				<tr>
					<th>NO</th>
					<td><c:out value="${goodsOne.goodsCode}"></c:out></td>
				</tr>
				<tr>
					<th>CATEGORY</th>
					<td><c:out value="${goodsOne.goodsCategory}"></c:out></td>
				</tr>
				<tr>
					<th>TITLE</th>
					<td><c:out value="${goodsOne.goodsTitle}"></c:out></td>
				</tr>
				<tr>
					<th>IMG</th>
					<td>
						<img src="${pageContext.request.contextPath}/goodsimg/${goodsOne.filename}" width="200" height="200">
					</td>
				</tr>
				<tr>
					<th>ARTIST</th>
					<td><c:out value="${goodsOne.goodsArtist}"></c:out></td>
				</tr>
				<tr>
					<th>CONTENT</th>
					<td><c:out value="${goodsOne.goodsContent}"></c:out></td>
				</tr>
				<tr>
					<th>PRICE</th>
					<td><c:out value="${goodsOne.goodsPrice}"></c:out></td>
				</tr>
				<tr>
					<th>VIEW</th>
					<td><c:out value="${goodsOne.view}"></c:out></td>
				</tr>
				<c:if test="${goodsOne.soldout eq 'N'}">
					<tr>
						<th>ACTION</th>
						<td>
							<form action="${pageContext.request.contextPath}/CartAdd" method="get">
								<input type="number" name="cartQuantity">
								<input type="hidden" name="goodsCode" value="${goodsOne.goodsCode}">
								<input type="hidden" name="goodsTitle" value="${goodsOne.goodsTitle}">
								<input type="hidden" name="filename" value="${goodsOne.filename}">
								<input type="hidden" name="goodsPrice" value="${goodsOne.goodsPrice}">
								<button type="submit">cart</button>
							</form>
						</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
		
		<!-- #### review #### -->
		<h3>리뷰</h3>
		<c:if test="${review != null}">
		<table border="1">
			<c:forEach var="review" items="${review}">
				<tr>
					<th>CUSTOMERID</th>
					<td><c:out value="${review.customerId}"></c:out></td>
				</tr>
				<tr>
					<th>REVIEW</th>
					<td><c:out value="${review.reviewMemo}"></c:out></td>
				</tr>
				<tr>
					<th>CREATEDATE</th>
					<td><c:out value="${review.createdate}"></c:out></td>
				</tr>
			</c:forEach>
		</table>
		</c:if>
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