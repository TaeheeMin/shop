<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
	$(document).ready(function() {
		$('#authCodeBtn').click(function(){
			// 선택 유효성 체크
			if($('#authCode').val() == '') {
				console.log(('#authCode').val());
				alert('변경할 직위를 선택바랍니다');
				return;
			}
			
			$('#authCodeForm').submit();
		});
	});
</script>
<body>
	<h1>사원목록</h1>
	
	<!-- 검색기능 추가예정 -->
	검색 : <input type="text" placeholder="검색(기능추가예정 미완)">
	
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
					<tr>
						<td>${e.empName}</td>
						<td>${e.empId}</td>
						<td>${e.authMemo}</td>
						<td>
							<form id="authCodeForm" action="${pageContext.request.contextPath}/EmpAuthCode" method="post">
								<select name="authCode" id="authCode">
									<option value="">=선택=</option>
									<option value="0">인턴</option>
									<option value="1">사원</option>
									<option value="99">매니저</option>
								</select>
								<input type="hidden" name="empId" value="${e.empId}">
								<button type="submit" id="authCodeBtn">변경</button>
							</form>
						</td>
						<td>
							${e.active}
							<form action="${pageContext.request.contextPath}/EmpActive" method="post">
								<input type="hidden" name="empId" value="${e.empId}">
								<!-- 비활성화일경우, 활성화 -->
								<c:if test="${e.active eq 'Y'}">
									<input type="hidden" name="active" value="N">
									<button type="submit">비활성화</button>
								</c:if>
								<c:if test="${e.active ne 'Y'}">
									<input type="hidden" name="active" value="Y">
									<button type="submit">활성화</button>
								</c:if>
							</form>
						</td>
						<td>${e.createdate}</td>
					</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>