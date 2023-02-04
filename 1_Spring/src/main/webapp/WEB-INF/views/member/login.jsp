<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<style>
	html, body {height: 100%;}
	body {display: flex; align-items: center; padding-top: 40px; padding-bottom: 40px; background-color: #f5f5f5;}
	.form-signin {max-width: 330px; padding: 15px;}
	.form-signin .form-floating:focus-within {z-index: 2;}
	.form-signin input[type="email"] {margin-bottom: -1px; border-bottom-right-radius: 0; border-bottom-left-radius: 0;}
	.form-signin input[type="password"] {margin-bottom: 10px; border-top-left-radius: 0; border-top-right-radius: 0;}
	.form-signin a>label{color: black;}
	.form-signin a>label:hover{text-decoration: underline; }
</style>
</head>
<body class="text-center">
	<main class="form-signin w-100 m-auto">
		<form action="${contextPath }/login.me" method="post">
			<a href="${ contextPath }/home.do"><img class="mb-4" src="${ contextPath }/resources/image/spring.png" width="150"></a>
			<h1 class="h3 mb-3 fw-normal">Please sign in</h1>

			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInput" name="id" placeholder="input your id">
				<label for="floatingInput">ID</label>
			</div>
			<div class="form-floating">
				<input type="password" class="form-control" id="floatingPassword" name="pwd" placeholder="Password">
				<label for="floatingPassword">Password</label>
			</div>

			<button class="w-100 btn btn-lg btn-success" type="submit">Sign in</button><br><br>
			<div class="mb-3">
				<a href="${ contextPath }/enroll.me"><label>Sign up</label></a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a><label>Find ID/PWD</label></a>
			</div>
			<input type="hidden" name="beforeURL">
			
		</form>
	</main>
	<script>
		window.onlaod=()=>{
			const beforeURL = document.referrer;
			const msg = '${msg}';
			
			if(msg!=''){
			alert(msg);
			document.getElementsByName('beforeURL')[0].value=beforeURL;
			}
		}
	</script>
	<% session.removeAttribute("msg"); %>	
	
	
	
	
	
</body>
</html>