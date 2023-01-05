<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>주문목록</h1>
		<table border="1">
			<thead>
			  	<tr>
			      	<th>goodsName</th>
			      	<th>filename</th>
			      	<th>date</th>
			      	<th>state</th>
			      	<th>price</th>
			    </tr>
		  	</thead>
		  	<tbody>
		  		<c:forEach var="o" items="${list}">
		     		<tr>
		     			<td>${o.goodsName}</td>
						<td>${o.filename}</td>
						<td>${o.createdate}</td>
						<td>${o.orderState}</td>
						<td>${o.orderPrice}</td>
	    			</tr>
				</c:forEach>
		  	</tbody>
		</table>
</body>
</html>