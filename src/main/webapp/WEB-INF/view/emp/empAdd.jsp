<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>사원등록</h1>
	<form id="signinForm" action="${pageContext.request.contextPath}/AddEmp" method="post">
		<table>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="empId" id="empId">
					<span class="msg"></span>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="empPw" id="empPw">
					<span class="msg"></span>
				</td>
			</tr>
			<tr>
				<th>비밀번호확인</th>
				<td>
					<input type="password" name="empPwCk" id="empPwCk">
					<span class="msg"></span>
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
					<input type="text" name="empName" id="empName">
					<span class="msg"></span>
				</td>
			</tr>
		</table>
		<div>
			<button type="submit" id="signinBtn">사원등록</button>
		</div>
	</form>
</body>
</html>