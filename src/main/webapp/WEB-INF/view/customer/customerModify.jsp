<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원정보수정</h1>
	<p>비밀번호, 이름, 전화번호 변경</p>
	<form id="" action="${pageContext.request.contextPath}/CustomerModify" method="post">
		<table>
			<tr>
				<td>
					<input type="password" name="customerPw" placeholder="기존 비밀번호">
				</td>
			</tr>
			<tr>
				<td>
					<input type="password" name="customerNewPw" placeholder="새 비밀번호">
				</td>
			</tr>
			<tr>
				<td>
					<input type="password" name="customerNewPwCk" placeholder="새 비밀번호 확인">
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" name="customerName" value="${loginCustomer.customerName}" placeholder="이름">
				</td>
			</tr>
			<tr>
				<td>
					<input type="number" name="customerPhone" value="${loginCustomer.customerPhone}" placeholder="전화번호">
				</td>
			</tr>
		</table>
		<div><button type="submit">수정</button></div>
		<div><a href="${pageContext.request.contextPath}/CustomerOne">취소</a></div>
	</form>
</body>
</html>