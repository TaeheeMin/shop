<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
		<!-- test -->
		<h1>포인트등록</h1>	
		<form method="post" action="${pageContext.request.contextPath}/point/pointAdd">
			<div> 
				주문번호 : <input type="text" name="orderCode">
			</div>				
			<button type="submit">등록</button>
		</form>	
</body>
</html>