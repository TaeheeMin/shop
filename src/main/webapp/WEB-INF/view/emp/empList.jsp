<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		// 직위 변경
		$('#authCodeBtn').click(function(){
			if($('#authCode').val() == '') {
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
	<div>
	사원검색 : 
		<input type="text" placeholder="사원이름을 입력하세요">
	</div>
	
	<!-- 이름별, 직위별, 활성화별, 입사날짜별, 내림차순 오름차순 정렬기능 추가예정 -->
	
	<!-- n명씩보기 (10,20,30) rowPerPage 넘겨주기 추가예정 -->
	
	<!-- authCodeSelect에서 =선택= (value="") 선택시, 반환하는 방법 -->
	
	<table border="1">
		<thead>
			<tr>
				<th>이름</th>
				<th>ID</th>
				<th>직위</th>
				<th>직위변경</th>
				<th>재직현황</th>
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
								<button type="button" id="authCodeBtn">변경</button>
							</form>
						</td>
						<td>
							${e.active}
							<form action="${pageContext.request.contextPath}/EmpActive" method="post">
								<input type="hidden" name="empId" value="${e.empId}">
								<!-- 비활성화일경우, 활성화 -->
								<select name="active">
									<c:if test="${e.active eq '재직'}">
										<option value="재직" selected="selected">재직</option>
										<option value="휴직">휴직</option>
										<option value="사직">사직</option>
									</c:if>
									<c:if test="${e.active eq '휴직'}">
										<option value="재직">재직</option>
										<option value="휴직" selected="selected">휴직</option>
										<option value="사직">사직</option>
									</c:if>
									<c:if test="${e.active eq '사직'}">
										<option value="재직">재직</option>
										<option value="휴직">휴직</option>
										<option value="사직" selected="selected">사직</option>
									</c:if>
								</select>
								<button type="submit">수정</button>
							</form>
						</td>
						<td>${e.createdate}</td>
					</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 페이징처리 -->
	
</body>
</html>