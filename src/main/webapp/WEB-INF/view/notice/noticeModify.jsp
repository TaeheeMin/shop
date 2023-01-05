<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지수정</title>
	</head>
	<body>
		<h1>공지수정</h1>
		<a href="${pageContext.request.contextPath}/NoticeList">공지목록</a>
		<!-- #### list #### -->
		<form action="${pageContext.request.contextPath}/NoticeModify?noticeCode=${noticeOne.noticeCode}" method="post">
			<table border="1">
				<tr>
					<th>NO</th>
					<td>
						<input type="text" name="noticeCode" readonly="readonly" value="${noticeOne.noticeCode}">
					</td>
				</tr>
				<tr>
					<th>TITLE</th>
					<td>
						<input type="text" name="title" value="${noticeOne.noticeTitle}">
					</td>
				</tr>
				<tr>
					<th>CONTENT</th>
					<td>
						<textarea rows="10" cols="50" name="content">${noticeOne.noticeContent}</textarea>
					
					</td>
				</tr>
				<tr>
					<th>DATE</th>
					<td>${noticeOne.createdate}</td>
				</tr>
			</table>
			<button type="submit">수정</button>
		</form>
	</body>
</html>