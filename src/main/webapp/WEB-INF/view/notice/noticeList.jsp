<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지</title>
	</head>
	<body>
		<!-- #### list #### -->
		<table border="1">
			<thead>
			  	<tr>
			      	<th>TITLE</th>
			      	<th>DATE</th>
			    </tr>
		  	</thead>
		  	<tbody>
		  		<c:forEach var="n" items="${notice}">
		     		<tr>
		      			<th>${n.noticeTitle}</th>
				      	<td>${n.createdate}</td>
	    			</tr>
				</c:forEach>
		  	</tbody>
		</table>
	</body>
</html>