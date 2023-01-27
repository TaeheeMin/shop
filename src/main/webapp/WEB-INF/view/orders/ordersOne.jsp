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
		<!-- 리뷰 중복값 있을 경우 -->
		<c:if test="${overlap != null}">
 			<script>
				alert('이번 주문 관련하여 이전에 리뷰를 작성하셨습니다');
			</script>
		</c:if>
    	<!-- ##### Breadcumb Area Start ##### -->
	    <section class="breadcumb-area bg-img bg-overlay" style="background-image: url(${pageContext.request.contextPath}/resources/img/bg-img/breadcumb3.jpg);">
	        <div class="bradcumbContent">
	            <h2>Order Page</h2>
	        </div>
	    </section>
	   
	    <section class="album-catagory section-padding-100-0">
	        <div class="container">
	         <div class="col-12">
	          <!-- #### search #### -->	
	          <c:forEach var="od" items="${list}">	        		
			   <table class = "table w-100 rounded" style="table-layout: auto; width: 100%; table-layout: fixed;">     
			  	<tr>
			  		<th>앨범</th>
			  		<td>
						<img src="${pageContext.request.contextPath}/goodsimg/${od.filename}" width="300" height="300">
					</td>
				</tr>
				<tr>		
			  		<th>주문번호</th>
			      	<td>${od.orderCode}</td>
			    </tr>
			    <tr> 	
			      	<th>앨범제목</th>
			    	<td>${od.goodsTitle}</td>
			    </tr>
			    <tr>  
			      	<th>수량</th>
			      	<td>${od.orderQuantity}</td>
			    </tr>  	
			    <tr>
			      	<th>앨범가격</th>
			    	<td>${od.goodsPrice}</td>			    
			    </tr>
			    <tr>
			    	<th>이름</th>
			    	<td>${od.customerName}</td>
			    </tr>
			    <tr>
			    	<th>연락처</th>
			    	<td>${od.customerPhone}</td>
			    </tr>
			    <tr>
			    	<th>주소</th>
			    	<td>${od.address}</td>
			    </tr>
			    <tr>
			    	<th>주문일자</th>
			    	<td>${od.createdate}</td>
			    </tr>
			    <tr>
			    	<th>리뷰작성</th>
					<td>
						<c:if test="${od.orderState eq '구매확정'}">						
							<a href="${pageContext.request.contextPath}/review/reviewAdd?orderCode=${od.orderCode}">
							<i class="icon-edit">리뷰작성</i>
							</a>
						</c:if>
					</td>
			    </tr>			
		     	</table>
		     	</c:forEach> 	
			</div>
		</div>
	    </section>
	    <!-- ##### Album Catagory Area End ##### -->

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