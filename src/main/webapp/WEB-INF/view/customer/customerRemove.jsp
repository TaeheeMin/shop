<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원탈퇴</h1>
	<div>
		<ul>
			<li>사용하고 계신 아이디는 탈퇴할 경우 <span style="font-weight:bold; color:red;">재사용 및 복구가 불가능</span>합니다.</li>
			<br>
			<li>탈퇴 후 회원정보 및 개인형 서비스 이용기록은 모두 삭제됩니다.</li>
			<br>
			<li>모든 내용을 확인하셨으며 탈퇴를 진행하고자 하시면 비밀번호를 입력해주세요.</li>
		</ul>
	</div>
	<form action="${pageContext.request.contextPath}/CustomerRemove" method="post" id="">
		<div><input type="hidden" name="customerId" value="${loginCustomer.customerId}"></div>
		<div><input type="password" name="customerPw" placeholder="비밀번호를 입력하세요"></div>
		<div><button type="submit">탈퇴</button></div>
		<div><a href="${pageContext.request.contextPath}/CustomerOne">취소</a></div>
	</form>
</body>
</html>