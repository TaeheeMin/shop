<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>직원 로그인</h1>
	<form action="${pageContext.request.contextPath}/LoginEmp" method="post">
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="empId"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="empPw"></td>
			</tr>
		</table>
		<div>
			<button type="submit">로그인</button>
		</div>		
	</form>
</body>
</html>