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
	            
	            <h2>Statistics Page</h2>
	        </div>
	    </section>
	    <!-- ##### Breadcumb Area End ##### -->
	    
	    <!-- ##### Album Catagory Area Start ##### -->
	    <section class="album-catagory section-padding-100-0">
    		
    		
	        <div class="container">
	         <div class="col-12">
	         <h3>월별 매출</h3>	         
			   <table class = "table table-hover w-100 rounded" style="table-layout: auto; width: 100%; table-layout: fixed;">
		         	<thead>
		              <tr>
		              	<th>년</th>
		              	<th>월</th>
		              	<th>주문금액</th>
		              	<th>취소금액</th>
		              	<th>수익</th>
		              </tr>
		           </thead>
		           <tbody>
		              <c:forEach var="o" items="${orderMonth}">
		              <tr>
		              		<td>${o.year}</td>		                 
		                    <td>${o.month}</td>	                   
		                   	<td>${o.orderPrice}</td>
		                   	<td>${o.cancelPrice}</td>
		                   	<td>${o.orderPrice-o.cancelPrice}</td>
		              </tr>     			            
		            </c:forEach>	
		           </tbody>
		      </table>
		      <br>
		      <br>
		      <h3>월별 주문/취소 건</h3>
			   <table class = "table table-hover w-100 rounded" style="table-layout: auto; width: 100%; table-layout: fixed;">
		         	<thead>
		              <tr>
		              	<th>년</th>
		              	<th>월</th>  
		              	<th>주문건</th>
		              	<th>취소건</th>
		              </tr>
		           </thead>
		           <tbody>
		              <c:forEach var="oc" items="${orderCnt}">
			          <tr>
			          	<td>${oc.year}</td>
		              	<td>${oc.month}</td>      
		                <td>${oc.orderCnt}</td>
		                <td>${oc.cancelCnt}</td>		                
		              </tr>    			            
		            </c:forEach>	
		           </tbody>
		      </table>
		      <br>
		      <br>
		      <h3>년별 매출</h3>
			   <table class = "table table-hover w-100 rounded" style="table-layout: auto; width: 100%; table-layout: fixed;">
		         	<thead>
		              <tr>
		              	<th>년</th>
		              	<th>주문금액</th>
		              	<th>취소금액</th>
		              	<th>수익</th>
		              </tr>
		           </thead>
		           <tbody>
		           <c:forEach var="oy" items="${orderYear}">
		              <tr>		                 
		                    <td>${oy.year}</td>	                   
		                   	<td>${oy.orderPrice}</td>
		                   	<td>${oy.cancelPrice}</td>
		                   	<td>${oy.orderPrice-o.cancelPrice}</td>
		              </tr>
		           </c:forEach>     			            	
		           </tbody>
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