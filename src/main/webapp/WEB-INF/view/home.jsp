<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>홈</title>
	</head>
	<body>
		<div>
			<h4>[접속자수]</h4>
			<span>현재 접속자 수 : ${currentCnt} /</span>
			<span>Today 접속자 수 : ${todayCount} /</span>
			<span>Total 접속자 수 : ${totalCount}</span>
		</div>
		<hr>
		<div>
			<h4>[TH]공지, 상품, 장바구니, 문의사항</h4>
			<a href="${pageContext.request.contextPath}/NoticeList">공지</a>
			<a href="${pageContext.request.contextPath}/GoodsList">상품</a>
			<a href="${pageContext.request.contextPath}/CartList">장바구니</a>
			<a href="${pageContext.request.contextPath}/QuestionListByAdmin">문의사항(관리자)</a>
			<a href="${pageContext.request.contextPath}/QuestionListByCustomer">문의사항(회원)</a>
		</div>
		<hr>
		<div>
			<h4>[SG]회원,직원 관련 기능구현 中</h4>
			<!-- 직원 로그인일 경우 보일 항목들 -->
			<c:if test="${loginEmp != null}">
				<a href="${pageContext.request.contextPath}/AddEmp">직원등록(직원만 가능)</a>
				<a href="${pageContext.request.contextPath}/EmpList">직원목록(사원이상만 조회가능)</a>
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
		<hr>
		<div>
			<h4>[DG]주문, 리뷰, 포인트</h4>
			<a href="${pageContext.request.contextPath}/orders/ordersList">주문(고객용)</a>
			<a href="${pageContext.request.contextPath}/orders/ordersListByAdmin">주문(관리자용)</a>
			<a href="${pageContext.request.contextPath}/review/reviewList">리뷰</a>
			<a href="${pageContext.request.contextPath}/">포인트(구현중)</a>
		</div>
		<hr>
	</body>
</html>