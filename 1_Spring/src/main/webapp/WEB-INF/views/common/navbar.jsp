<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
</head>
<body>
	<c:set value="${ pageContext.servletContext.contextPath }" var="contextPath" scope="application"/>
	<nav class="navbar navbar-expand navbar-dark bg-dark" aria-label="Second navbar example">
		<div class="container-fluid">
			<a class="navbar-brand" href="${ contextPath }/home.do">Spring project</a>
			<div class="collapse navbar-collapse" id="navbarsExample02">
				<ul class="navbar-nav me-auto">
					<li class="nav-item"><a class="nav-link active" aria-current="page" href="${ contextPath }.home.do">Home</a></li>
					<li class="nav-item"><a class="nav-link active" href="${contextPath}/list.bo">Board</a></li>
					<li class="nav-item"><a class="nav-link active" href="${contextPath}/list.at">Photo</a></li>
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>