<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	</head>
	<style>
		.msg {color : #FF0000;}
	</style>
<body>
	<h1>회원가입</h1>
	<form id="signinForm" action="${pageContext.request.contextPath}/AddCustomer" method="post">
		<table>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="customerId" id="customerId">
					<span class="msg"></span>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="customerPw" id="customerPw">
					<span class="msg"></span>
				</td>
			</tr>
			<tr>
				<th>비밀번호확인</th>
				<td>
					<input type="password" name="customerPwCk" id="customerPwCk">
					<span class="msg"></span>
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
					<input type="text" name="customerName" id="customerName">
					<span class="msg"></span>
				</td>
			</tr>
			<tr>
				<th>휴대폰번호</th>
				<td>
					<input type="text" name="customerPhone" id="customerPhone">
					<span class="msg"></span>
				</td>
			</tr>
		</table>
		<div>
			<button type="submit" id="signinBtn">회원가입</button>
		</div>
	</form>
</body>
</html>