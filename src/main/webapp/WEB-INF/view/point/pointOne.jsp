<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
		<h1>현재포인트</h1>	
		<div>
		<c:forEach var="p" items="${list}">
		${p.importPoint-p.exportPoint} 점
		</c:forEach>
		</div>
</body>
</html>