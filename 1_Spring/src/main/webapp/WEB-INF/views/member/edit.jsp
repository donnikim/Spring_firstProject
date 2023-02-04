<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body class="bg-light">
	<jsp:include page="../common/navbar.jsp"/>
	<div class="container">
		<main>
			<div class="py-5 text-center">
				<a href="${ contextPath }/home.do"><img class="d-block mx-auto mb-4" src="${ contextPath }/resources/image/spring.png" alt="" width="130"></a>
				<h2>EDIT MY INFORMATION</h2>
			</div>

			<div style="margin-left: 350px; margin-right: 350px;">
				<form class="needs-validation" action="${ contextPath }/updateMember.me" method="POST">
					<div class="row g-3">
						<div class="col-12">
							<label for="id" class="form-label">ID</label>
							<input type="text" class="form-control" id="id" name="id" value="${loginUser.id }" required readonly >
						</div>
						
						<div class="col-12">
							<label for="name" class="form-label">NAME</label>
							<input type="text" class="form-control" id="name" name="name" value="${loginUser.name}" required>
						</div>
						
						<div class="col-12">
							<label for="nickName" class="form-label">NICKNAME</label>
							<input type="text" class="form-control" id="nickName" name="nickName" value="${loginUser.nickName }" required>
							<label id="nickNameCheckResult">Please check your NickName.</label>
						</div>

						<div class="col-12">
							<label for="emailId" class="form-label">EMAIL</label>
							<c:set var="emailId" value="${fn:split(loginUser.email, '@')[0] }"/>
							<c:set var="emailDomain" value="${fn:split(loginUser.email, '@')[1] }"/>
							<div class="input-group">
								<input type="text" class="form-control" id="emailId" name="emailId" value="${emailId }">
								<span class="input-group-text">@</span>
								<select name="emailDomain" style="width: 280px;">
									<option <c:if test="${emailDomain eq 'naver.com' }">selected</c:if>>naver.com</option>
									<option <c:if test="${emailDomain eq 'gmail.com' }">selected</c:if>>gmail.com</option>
									<option <c:if test="${emailDomain eq 'nate.com' }">selected</c:if>>nate.com</option>
									<option<c:if test="${emailDomain eq 'hanmail.com' }">selected</c:if>>hanmail.net</option>
								</select>
							</div>
						</div>
						
						<div class="col-12">
							<label class="form-label">GENDER</label><br>
							<input type="radio" id="man" name="gender" value="M" <c:if test="${loginUser.gender=='M' }"> checked</c:if>> MAN &nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" id="woman" name="gender" value="F"<c:if test="${loginUser.gender=='F' }"> checked</c:if>> WOMAN
						
						</div>
						
						<div class="col-12">
							<label for="age" class="form-label">AGE</label>
							<input type="number" class="form-control" id="age" name="age" min="0" max="100" value="${loginUser.age}">
						</div>

						<div class="col-12">
							<label for="phone" class="form-label">PHONE</label>
							<input type="text" class="form-control" id="phone" name="phone" value="${loginUser.phone }">
						</div>
						
						<div class="col-12">
							<label for="address" class="form-label">ADDRESS</label>
							<input type="text" class="form-control" id="address" name="address" value="${loginUser.address }">
						</div>
						
						<br><br><br><br><br>
						
						<button class="w-100 btn btn-primary btn-lg" onclick="location.href='${contextPath}/updateMember.me'">UPDATE</button>
					</div>
				</form>
			</div>
		</main>

		<footer class="my-5 pt-5 text-muted text-center text-small"></footer>
	</div>
	
		<script>
		window.onload = () => {
			const loginUser = '${loginUser}';
			if(loginUser == ''){
				alert('로그인 후 이용해주세요.');
				location.href='${contextPath}/loginView.me';
			}
			
		}
	</script>
</body>
</html>