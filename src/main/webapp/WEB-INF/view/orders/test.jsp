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
	            
	            <h2>Order Page</h2>
	        </div>
	    </section>
	   
	    
	    <section class="album-catagory section-padding-100-0">
    		
    		
	        <div class="container">
	         <div class="col-12">
	          <!-- #### search #### -->	
	          <h3>상품정보</h3>			
			   <table class = "table w-100 rounded" style="table-layout: auto; width: 100%; table-layout: fixed;">
		         
			
			  	<tr>
			  		<th>주문번호</th>
			      	<th>앨범제목</th>
			      	<th>앨범</th>
			      	<th>수량</th>
			      	<th>앨범가격</th>
			    </tr>
			    <c:forEach var="od" items="${list}">
		     	<tr>
		     			<td>${od.orderCode}</td>
		     			<td>${od.goodsTitle}</td>
		     			<td>
							<img src="${pageContext.request.contextPath}/goodsimg/${od.filename}" width="200" height="200">
						</td>
		     			<td>${od.orderQuantity}</td>
		     			<td>${od.goodsPrice}</td>
		     	</tr>	

		     	</c:forEach>
		     	</table>
		     	<div>
		     	<h3>주문자정보</h3>
				</div>
		     	 <table class = "table w-100 rounded" style="table-layout: auto; width: 100%; table-layout: fixed;">		         
				    <tr>  	
				      	<th>이름</th>
				      	<th>연락처</th>
				      	<th>배송지</th>
				      	<th>주문일자</th>			      	
				      	<th>리뷰작성</th>			      		      	
				    </tr>			  
			
			  		<c:forEach var="od" items="${list}">		
			     		<tr>		     			
			     			<td>${od.customerName}</td>
			     			<td>${od.customerPhone}</td>
			     			<td>${od.address}</td>
			     			<td>${od.createdate}</td>						
							<td>
								<c:if test="${od.orderState eq '구매확정'}">						
								<a href="${pageContext.request.contextPath}/review/reviewAdd?orderCode=${od.orderCode}">
								리뷰작성하기
								</a>
								</c:if>
							</td>
		    			</tr>
					</c:forEach>		  
				</table>    	    
			        
			</div>
		</div>
	    </section>
	    <!-- ##### Album Catagory Area End ##### -->

    <!-- ##### Add Area Start ##### -->
    <div class="add-area mb-100">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="adds">
                        <a href="#"><img src="img/bg-img/add3.gif" alt=""></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ##### Add Area End ##### -->

  

		
		
		
		
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