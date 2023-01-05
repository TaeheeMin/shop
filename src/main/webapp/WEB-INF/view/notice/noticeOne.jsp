<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지상세보기</title>
	</head>
	<body>
		<h1>공지 상세보기</h1>
		<a href="${pageContext.request.contextPath}/NoticeList">공지목록</a>
		<!-- #### list #### -->
		<table border="1">
			<tr>
				<th>NO</th>
				<td>${noticeOne.noticeCode}</td>
			</tr>
			<tr>
				<th>TITLE</th>
				<td>${noticeOne.noticeTitle}</td>
			</tr>
			<tr>
				<th>CONTENT</th>
				<td>${noticeOne.noticeContent}</td>
			</tr>
			<tr>
				<th>DATE</th>
				<td>${noticeOne.createdate}</td>
			</tr>
			<tr>
				<th>ACTION</th>
				<td>
					<a href="${pageContext.request.contextPath}/NoticeModify?noticeCode=${noticeOne.noticeCode}">
						수정
					</a>
					<a href="${pageContext.request.contextPath}/NoticeRemove?noticeCode=${noticeOne.noticeCode}">
						삭제
					</a>
				</td>
			</tr>
		</table>
	</body>
</html>