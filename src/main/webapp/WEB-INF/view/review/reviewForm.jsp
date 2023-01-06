<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>리뷰등록</h1>	
		<form method="post" action="${pageContext.request.contextPath}/review/reviewAdd">
			<div> 
				주문번호 : <input type="text" name="orderCode" value="orderCode" readonly="readonly"> <!-- orderCode는 리스트에서 받아오는값으로 readonly예정 -->
			</div>
			<div>
				리뷰내용 : <textarea rows="10" cols="20" name="reviewMemo"></textarea>
			</div>			
			<button type="submit">등록</button>
		</form>
</body>
</html>