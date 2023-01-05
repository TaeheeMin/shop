<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>사원목록</h1>
	
	<table border="1">
		<thead>
			<tr>
				<th>이름</th>
				<th>ID</th>
				<th>직위</th>
				<th>직위변경</th>
				<th>활성화</th>
				<th>입사날짜</th>
			</tr>
		</thead>
		<tbody>
			<!-- 사원목록 출력 -->
			<c:forEach var="e" items="${list}">
				<!-- 비활성화일 경우, 활성화버튼 -->
				<c:if test="${e.active eq 'Y'}">
					<tr>
						<td>${e.empName}</td>
						<td>${e.empId}</td>
						<td>${e.authMemo}</td>
						<td>
							<form>
								<select name="authCode">
									<option value="">==선택==</option>
									<option value="0">인턴</option>
									<option value="1">사원</option>
									<option value="99">매니저</option>
								</select>
							</form>
						</td>
						<td>
							${e.active}
							<form action="${pageContext.request.contextPath}/EmpList" method="get">
								<input type="hidden" name="empId" value="${e.empId}">
								<input type="hidden" name="active" value="Y">
								<button type="submit">활성화</button>
							</form>
						</td>
						<td>${e.createdate}</td>
					</tr>
				</c:if>
				<!-- 활성화일경우, 비활성화 버튼 -->
				<tr>
					<td>${e.empName}</td>
					<td>${e.empId}</td>
					<td>${e.authMemo}</td>
					<td>${e.active}</td>
					<td>${e.createdate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>