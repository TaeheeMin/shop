<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>리뷰수정</h1>		
		<form action="${pageContext.request.contextPath}/review/reviewModify" method="post">
			<table border="1">
			<div> 
				주문번호 : <input type="text" name="orderCode"> <!-- orderCode는 리스트에서 받아오는값으로 readonly예정 -->
			</div>
			<div>
				리뷰내용 : <textarea rows="10" cols="20" name="reviewMemo"></textarea>
			</div>
			<div>
			 	리뷰작성자 : <input type="text" name="customerId">
			</div>			
			<button type="submit">수정</button>				
		</form>
</body>
</html>