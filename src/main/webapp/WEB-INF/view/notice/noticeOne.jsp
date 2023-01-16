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
	            <h2>News</h2>
	        </div>
	    </section>
	    <!-- ##### Breadcumb Area End ##### -->
	    <!-- ##### Blog Area Start ##### -->
	    <div class="blog-area section-padding-100">
	        <div class="container">
	            <div class="row">
		<!-- #### list #### -->
		<table border="1">
			<tr>
				<th>NO</th>
				<td>${noticeOne.noticeCode}</td>
			</tr>
			<tr>
				<th>TITLE</th>
				<td>${noticeOne.noticeTitle}</td>
			</tr>
			<tr>
				<th>CONTENT</th>
				<td>${noticeOne.noticeContent}</td>
			</tr>
			<tr>
				<th>DATE</th>
				<td>${noticeOne.createdate}</td>
			</tr>
			<c:if test="${loginEmp != null}">
				<tr>
					<th>ACTION</th>
					<td>
						<a href="${pageContext.request.contextPath}/NoticeModify?noticeCode=${noticeOne.noticeCode}">
							수정
						</a>
						<a href="${pageContext.request.contextPath}/NoticeRemove?noticeCode=${noticeOne.noticeCode}">
							삭제
						</a>
					</td>
				</tr>
			</c:if>
		</table>
		</div>
		</div>
		</div>
	
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