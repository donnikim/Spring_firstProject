<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta charset="utf-8">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
</head>
<body>
	<main>
		<jsp:include page="common/navbar.jsp"/>
		<div class="container py-4">
			<div class="p-5 mb-4 bg-light rounded-3">
				<div class="container-fluid py-5">
					<h1 class="display-5 fw-bold">Spring Framework</h1>
					<p class="col-md-8 fs-4">
						The Spring Framework provides a comprehensive programming and configuration model 
						for modern Java-based enterprise applications - on any kind of deployment platform.
					</p>
					
					
					<c:if test="${ empty loginUser }">
					<button class="btn btn-primary btn-lg" type="button" onclick="location.href='${contextPath}/loginView.me'">SIGN IN</button>
					<button class="btn btn-success btn-lg" type="button" onclick="location.href='${contextPath}/enroll.me'">SIGN UP</button>
					<button class="btn btn-warning btn-lg" type="button" >FIND ID/PWD</button>
					</c:if>
					<c:if test="${ !empty loginUser }">
						<br>
						<p class="col-md-8 fs-4 fw-blod">
						${loginUser.nickName }님 환영합니다.
						<button class="btn btn-success btn-lg" type="button" onclick="location.href='${contextPath}/myInfo.me'">MY PAGE</button>
						<button class="btn btn-dark btn-lg" type="button" onclick="location.href='${contextPath}/logout.me'">LOGOUT</button>
						</p>
					</c:if>
				</div>
			</div>

			<div class="row align-items-md-stretch">
				<div class="col-md-6">
					<div class="h-100 p-5 text-bg-dark rounded-3">
						<h2>Standard Board</h2>
						<p>
							This is the general bulletin board. 
							You can view the entire post, view the details, write, modify and delete the post, and even implement the comment function in the post.
						</p>
						<button class="btn btn-outline-light" type="button" onclick="location.href='${contextPath}/list.bo'">GO TO BOARD</button>
					</div>
				</div>
				<div class="col-md-6">
					<div class="h-100 p-5 bg-light border rounded-3">
						<h2>Photo Board</h2>
						<p>
							This bulletin board allows you to add attachments such as photos to your post. 
							As you implement attachment functions, bulletin board functions become more colorful.
						</p>
						<button class="btn btn-outline-secondary" type="button" onclick="location.href='${contextPath}/list.at'">GO TO PHOTO</button>
																														<!--at : attachment  -->
					</div>
				</div>
			</div>
			<footer class="pt-3 mt-4 text-muted border-top"> &copy; 2022 </footer>
		</div>
	</main>
</body>
</html>
