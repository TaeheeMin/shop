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
				// 유효성검사
				$('#addAnswerBtn').click(function() {				}
					if($('#commentMemo').val() == '') {
						$('#contentMsg').text('내용을 입력해주세요');
						$('#commentMemo').focus();
						return;
					} else {
						$('#contentMsg').text('');
					}
					$('#addAnswerBtn').submit();
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
	            
	            <h2>Q&A</h2>
	        </div>
	    </section>
	   
	    
	    <section class="album-catagory section-padding-100-0">
    		
    		<form action="${pageContext.request.contextPath}/QuestionCommentAdd" method="post">
	        <div class="container">
	         <div class="col-12">       	
       		<h3>Question</h3>
			   <table class = "table w-100 rounded" style="table-layout: auto; width: 100%; table-layout: fixed;">     
			  				<c:forEach var="list" items="${list}">
					<tr>
						<th>문의사항 번호</th>
						<td>
							<input type="hidden" name="questionCode" value="${list.questionCode}">
							<c:out value="${list.questionCode}"></c:out>
						</td>
					</tr>
					<tr>
						<th>주문상품</th>
						<td>
							<img src="${pageContext.request.contextPath}/goodsimg/${list.filename}" width="100" height="100">
							<br>
							<c:out value="${list.goodsTitle}"></c:out>
						</td>
					</tr>
					<tr>
						<th>카테고리</th>
						<td><c:out value="${list.category}"></c:out></td>
					</tr>
					<tr>
						<th>문의제목</th>
						<td><c:out value="${list.questionTitle}"></c:out></td>
					</tr>
					<tr>
						<th>문의내용</th>
						<td><c:out value="${list.qusetionMemo}"></c:out></td>
					</tr>
					<tr>
						<th>등록일</th>
						<td><c:out value="${list.createdate}"></c:out></td>
					</tr>
				</c:forEach>
			</table>	
			
			</div>
			<br>
		<h3>Answer</h3>
		<c:forEach var="comment" items="${comment}">
			<table class = "table w-100 rounded" style="table-layout: auto; width: 100%; table-layout: fixed;">
				<tr>
					<th>번호</th>
					<td>${comment.questionCode}</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>${comment.createdate}</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${comment.commentMemo}</td>
				</tr>
			</table>
			</c:forEach>
			<br>
			<c:if test="${loginEmp != null && commentCk == false}">
				<h3>Add Answer</h3>				
				<div class="col-12">
			    	<div class="form-group">
						<textarea name="commentMemo" class="form-control" id="commentMemo" cols="30" rows="10" placeholder="content"></textarea>
			            <small id="contentMsg" class="form-text text-danger"></small>
					</div>
	 				<div class="col-12 text-center">
	 					<button class="btn oneMusic-btn mt-30" type="Submit" id="addAnswerBtn">답변완료<i class="fa fa-angle-double-right"></i></button>
			        </div>
				</div>
			</c:if>
		                                		
		</div>
		</form>		
	
		
		
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