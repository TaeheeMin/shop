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
			<c:forEach var="o" items="${list}">
				<script>
				//관리자용
				$(document).ready(function() {
					$('.orderState${o.orderCode}').change(function() {
			           $('#orderFormByAdmin${o.orderCode}').submit();
			        })
			     });	
			
				</script>
			</c:forEach>
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
		<!-- 메뉴 partial jsp 구성 -->
		<div>
			<jsp:include page="/inc/menu.jsp"></jsp:include>
		</div>
		
    	<!-- ##### Breadcumb Area Start ##### -->
	    <section class="breadcumb-area bg-img bg-overlay" style="background-image: url(${pageContext.request.contextPath}/resources/img/bg-img/breadcumb3.jpg);">
	        <div class="bradcumbContent">
	            
	            <h2>Order List</h2>
	        </div>
	    </section>
	   
	    
	    <section class="album-catagory section-padding-100-0">
    		
    		
	        <div class="container">
	         <div class="col-12">
	          <!-- #### search #### -->
				<div>
				<form action="${pageContext.request.contextPath}/orders/ordersListByAdmin" method="get" id="listForm">
					
						<input type="radio" name="category" id="category" value="">전체
						<input type="radio" name="category" id="category" value="결제">결제
						<input type="radio" name="category" id="category" value="취소">취소
						<input type="radio" name="category" id="category" value="구매확정">구매확정
						<input type="radio" name="category" id="category" value="배송중">배송중
						<input type="radio" name="category" id="category" value="배송완료">배송완료
						<input type="radio" name="category" id="category" value="반품신청">반품신청
						<input type="radio" name="category" id="category" value="반품완료">반품완료
					
					<div style="float: right; padding-bottom: 10px;">
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
						</select>
						
						<input type="search" placeholder="Search" name="word" id="word" value="${word}">
			     		<button type="button" id="searchBtn">검색</button>
		     		</div>	
				</form>
			    </div>
	         	         
			   <table class = "table table-hover w-100 rounded" style="table-layout: auto; width: 100%; table-layout: fixed;">
		         	<thead>
		              <tr>
		                  <th>주문번호</th>
		                  <th>상품이름</th>
		                  <th>상품이미지</th>
		                  <th>주문일자</th>
		                  <th>주문상태</th>
		                  <th>주문가격</th>
		             </tr>
		           </thead>
		           <tbody>
		              <c:forEach var="o" items="${list}">
		                 <tr>
		                    <td>${o.orderCode}</td>
		                    <td><a href='${pageContext.request.contextPath}/orders/ordersOne?orderCode=${o.orderCode}'>${o.goodsTitle}</a></td>
		                  <td>
		                     <img src="${pageContext.request.contextPath}/goodsimg/${o.filename}" width="200" height="200">
		                  </td>
		                  <td>${o.createdate}</td>
		                  <td>${o.orderState}
								<!-- 관리자용 -->
						<form id="orderFormByAdmin${o.orderCode}" method="post" action="${pageContext.request.contextPath}/orders/ordersModifyByAdmin">		     			
		     			<input type="hidden" name="orderCode" value="${o.orderCode}">		     		     			
		     			<select name="orderState" class="orderState${o.orderCode}">			
							<c:if test="${o.orderState eq '결제'}">
								<option value="결제" selected="selected">결제</option>
								<option value="취소">취소</option>
								<option value="배송중">배송중</option>
								<option value="배송완료">배송완료</option>								
							</c:if>
							<c:if test="${o.orderState eq '취소'}">								
								<option value="취소" selected="selected" >취소</option>																
							</c:if>		
							<c:if test="${o.orderState eq '배송중'}">
								<option value="결제">결제</option>
								<option value="취소">취소</option>
								<option value="배송중" selected="selected">배송중</option>
								<option value="배송완료">배송완료</option>							
							</c:if>
							<c:if test="${o.orderState eq '배송완료'}">							
								<option value="배송완료" selected="selected">배송완료</option>								
							</c:if>
							<c:if test="${o.orderState eq '구매확정'}">							
								<option value="구매확정" selected="selected">구매확정</option>								
							</c:if>
							<c:if test="${o.orderState eq '반품신청'}">
								<option value="반품신청" selected="selected">반품신청</option>								
								<option value="반품완료" >반품완료</option>								
							</c:if>
							<c:if test="${o.orderState eq '반품완료'}">							
								<option value="구매확정" selected="selected">반품완료</option>								
							</c:if>
						</select>
						</form>                   
		                  </td>                  
		                  <td>${o.orderPrice}</td>
		                </tr>
		            </c:forEach>
		           </tbody>
		      </table>
	            <!-- Pagination -->
	            <div class="oneMusic-pagination-area wow fadeInUp" data-wow-delay="300ms">
	                <nav>
	                    <ul class="pagination">
	                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/orders/ordersListByAdmin?rowPerPage=${rowPerPage}&currentPage=1">HOME</a></li>
	                        <c:if test="${currentPage > 1}">
								<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/orders/ordersListByAdmin?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}"><<</a></li>
							</c:if>
							<c:forEach var="x" begin="${beginPage}" end="${endPage}" step="1">
								<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/orders/ordersListByAdmin?rowPerPage=${rowPerPage}&currentPage=${x}">${x}</a></li>
							</c:forEach>
							<c:if test="${currentPage < lastPage}">
								<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/orders/ordersListByAdmin?rowPerPage=${rowPerPage}&currentPage=${currentPage +1}">>></a></li>
							</c:if>
							<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/orders/ordersListByAdmin?rowPerPage=${rowPerPage}&currentPage=${lastPage}">END</a></li>
	                    </ul>
	                </nav>
	            </div>
			        
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