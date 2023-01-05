<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지등록</title>
	</head>
	<body>
		<h1>공지등록</h1>
		<a href="${pageContext.request.contextPath}/Home">홈으로</a>
		<a href="${pageContext.request.contextPath}/NoticeList">공지목록</a>
		<form method="post" action="${pageContext.request.contextPath}/NoticeAdd">
			<div> 
				제목 : <input type="text" name="noticeTitle">
			</div>
			<div>
				내용 : <textarea rows="10" cols="20" name="noticeContent"></textarea>
			</div>
			<div> 
				직원 아이디 : <input type="text" name="empId">
			</div>
			<button type="submit">등록</button>
		</form>
	</body>
</html>