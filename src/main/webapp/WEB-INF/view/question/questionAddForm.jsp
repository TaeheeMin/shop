<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>문의사항 등록</title>
	</head>
	<body>
		<h1>문의사항 등록</h1>
		<form> 
			<input type="text" name="orderCode">
			<select name="category">
				<option value="배송">배송</option>
				<option value="반품">반품</option>
				<option value="교환">교환</option>
				<option value="기타">기타</option>
			</select>
			<input type="text" name="questionTitle">
			<textarea rows="10" cols="5" name="questionMemo"></textarea>
			<button type="submit">등록</button>
		</form>
	</body>
</html>