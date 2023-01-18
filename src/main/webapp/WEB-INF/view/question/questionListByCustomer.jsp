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
	            
	            <h2>Q&A</h2>
	        </div>
	    </section>
	    <!-- ##### Breadcumb Area End ##### -->
	    
	    <!-- ##### Album Catagory Area Start ##### -->
	    <section class="album-catagory section-padding-100-0">
    		
    		
	        <div class="container">
	         <div class="col-12">
	         	<div style="float:right;"><button type="button" class="btn btn-sm btn-outline-dark mt-3"><a href="${pageContext.request.contextPath}/QuestionAdd">문의사항 등록</a></button></div>         
			   <table class = "table table-hover w-100 rounded" style="table-layout: auto; width: 100%; table-layout: fixed;">
		         	<thead>
		              <tr>
		          		<th>문의사항 번호</th>
						<th>주문번호</th>
						<th>카테고리</th>
						<th>상품</th>
						<th>문의제목</th>
						<th>등록일</th>
						<th>수정삭제</th>
		             </tr>
		           </thead>
		           <tbody>
		             <c:forEach var="q" items="${list}">
						<tr>
							<td>${q.questionCode}</td>
							<td>${q.ordersCode}</td>
							<td>${q.category}</td>
							<td>
								<div>
									<img src="${pageContext.request.contextPath}/goodsimg/${q.filename}" width="100" height="100">
								</div>
								${q.goodsTitle}
							</td>
							<td>
								<a href="${pageContext.request.contextPath}/QuestionOne?questionCode=${q.questionCode}">${q.questionTitle}</a>
							</td>
							<td>${q.createdate}</td>
							<td>
								<c:forEach var="c" items="${comment}">
									<c:choose> 
										<c:when test="${c.questionCode eq q.questionCode}">
											<a href="${pageContext.request.contextPath}/QuestionOne?questionCode=${q.questionCode}">답변보기</a>
										</c:when> 
										<c:otherwise>
										<a href="${pageContext.request.contextPath}/QuestionModify?questionCode=${q.questionCode}">수정</a>
										<a href="${pageContext.request.contextPath}/QuestionRemove?questionCode=${q.questionCode}">삭제</a>
										</c:otherwise> 
									</c:choose> 
								</c:forEach>
							</td>
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