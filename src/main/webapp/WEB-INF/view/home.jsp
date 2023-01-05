<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<div>
			<a href="${pageContext.request.contextPath}/NoticeList">공지목록</a>
			<a href="${pageContext.request.contextPath}/GoodList">상품목록</a>
			
		</div>
		<div>
			<h4>-회원,직원 관련 기능구현 中-</h4>
			<!-- 직원 로그인일 경우 보일 항목들 -->
			<c:if test="${loginEmp != null}">
				<a href="${pageContext.request.contextPath}/AddEmp">직원등록(직원이상만 가능)</a>
				<a href="${pageContext.request.contextPath}/Logout">로그아웃</a>
			</c:if>
			<!-- 직원 비로그인일 경우 보일 항목들 -->
			<c:if test="${loginEmp == null}">
				<a href="${pageContext.request.contextPath}/LoginEmp">직원로그인</a>
			</c:if>

			<!-- 회원 로그인일 경우 보일 항목들 -->
			<c:if test="${loginCustomer != null}">
				<a href="${pageContext.request.contextPath}/CustomerOne">회원정보</a>
				<a href="${pageContext.request.contextPath}/Logout">로그아웃</a>
			</c:if>
			
			<!-- 회원 비로그인일 경우 보일 항목들 -->
			<c:if test="${loginCustomer == null}">
				<a href="${pageContext.request.contextPath}/LoginCustomer">회원로그인</a>
			<a href="${pageContext.request.contextPath}/AddCustomer">회원가입</a>
			</c:if>
		</div>
	</body>
</html>