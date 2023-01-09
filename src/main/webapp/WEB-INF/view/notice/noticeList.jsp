<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지목록</title>
	</head>
	<body>
		<h1>공지목록</h1>
		<a href="${pageContext.request.contextPath}/Home">홈으로</a>
		<c:if test="${loginEmp != null}">
		<a href="${pageContext.request.contextPath}/NoticeAdd">공지등록</a>
		</c:if>
		<!-- #### list #### -->
		<table border="1">
			<thead>
			  	<tr>
			      	<th>NO</th>
			      	<th>TITLE</th>
			      	<th>DATE</th>
			    </tr>
		  	</thead>
		  	<tbody>
		  		<c:forEach var="n" items="${notice}">
		     		<tr>
		     			<th>${n.noticeCode}</th>
		      			<td>
		      				<a href="${pageContext.request.contextPath}/NoticeOne?noticeCode=${n.noticeCode}">
		      					${n.noticeTitle}
		      				</a>
		      			</td>
				      	<td>${n.createdate}</td>
	    			</tr>
				</c:forEach>
		  	</tbody>
		</table>
	</body>
</html>