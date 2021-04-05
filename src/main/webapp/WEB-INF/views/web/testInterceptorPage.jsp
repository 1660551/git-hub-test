<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>	
<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">
<title>Trang chủ</title>

</head>

<body>

	<!-- Page Content -->
	<div class="container">
		<ul>
			<c:forEach var ="item" items="menu">
			<li>${item.name}</li>
			</c:forEach>
			
		</ul>

	</div>
	<!-- /.container -->

</body>

</html>