<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta charset="utf-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>

<style>
	abbr>a{color: black; text-decoration: none;}
	#delete{color: lightgray;}
	#delete:hover{color: gray; cursor: pointer;}
</style>
</head>
<body>
	<main>
		<jsp:include page="../common/navbar.jsp"/>
		<div class="container py-4">
			<div class="p-5 mb-4 bg-light rounded-3">
				<div class="container-fluid py-5">
					<h1 class="display-5 fw-bold">
						<abbr title="if you click this, you can go main page(home.do).">
							<a href="${ contextPath }/home.do">${ loginUser.nickName }'s Information</a>
						</abbr>
					</h1>
					<label id="delete">> 탈퇴하기</label>
				</div>
			</div>

			<div class="row align-items-md-stretch">
				<div class="col-md-6">
					<div class="h-100 p-5 text-bg-success rounded-3">
						<h2>Basic Information</h2>
						<table>
							<tr><td width="150px">ID</td><td>${ loginUser.id }</td></tr>
							<tr><td>NAME</td><td>${ loginUser.name }</td></tr>
							<tr><td>NICKNAME</td><td>${ loginUser.nickName }</td></tr>
							<tr><td>EMAIL</td><td>${ empty loginUser.email ? '-' : loginUser.email }</td></tr>
							<tr><td>GENDER</td><td>${ loginUser.gender == 'M' ? '남자' : '여자' }</td></tr>
							<tr><td>AGE</td><td>${ loginUser.age == 0 ? '-' : loginUser.age }</td></tr>
							<tr><td>PHONE</td><td>${ empty loginUser.phone ? '-' : loginUser.phone }</td></tr>
							<tr><td>ADDRESS</td><td>${ empty loginUser.address ? '-' : loginUser.address }</td></tr>
							<tr><td>ENROLL</td><td>${ loginUser.enrollDate }</td></tr>
							<tr><td>UPDATE</td><td>${ loginUser.updateDate }</td></tr>
						</table>
						<br>
						<button class="btn btn-outline-light" type="button" onclick="location.href='${contextPath}/myEdit.me'">EDIT MY INFO</button>
					</div>
				</div>
				<div class="col-md-6">
					<div class="h-100 p-5 bg-light border rounded-3">
						<form action="${ contextPath }/updatePassword.me" method="post">
							<h2>Update your Password</h2>
							<div class="col-12">
								<label for="currentPwd" class="form-label">Current PWD</label>
								<input type="password" class="form-control" id="currentPwd" name="currentPwd" required>
							</div>
							<br>
							<div class="col-12">
								<label for="newPwd" class="form-label">PWD to Change</label>
								<input type="password" class="form-control" id="newPwd" name="newPwd" required>
							</div>
							<br>
							<div class="col-12">
								<label for="newPwdConfirm" class="form-label">PWD to Change Confirm</label>
								<input type="password" class="form-control" id="newPwdConfirm" name="newPwdConfirm" required>
							</div>
							<br>
							<button class="btn btn-outline-secondary">UPDATE MY PWD</button>
						</form>
					</div>
				</div>
				<div class="col-md-6">
					<div class="h-100 p-5 bg-light border rounded-3">
						<h2>My Boards Top 5</h2>
						<table style="text-align: center; width: 100%;">
							<tr>
								<th>No.</th>
								<th>Title</th>
								<th>Date</th>
								<th>Views</th>
							</tr>
							<c:forEach var="list" items="${list}">
								<c:if test="${list.BI_BT>0 }">
								<tr>
									<th>${list.BI_BT}</th>
									<th>${list.BOARD_TITLE}</th>
									<th>${fn:split(list.MD,' ')[0]}</th>
									<th>${list.BC_RC}</th>
								</tr>
								</c:if>
							</c:forEach>
						</table>
					</div>
				</div>
				<div class="col-md-6">
					<div class="h-100 p-5 text-bg-dark rounded-3">
						<h2>My Replies Top 5</h2>
						<table style="text-align: center; width: 100%;">
							<tr>
								<th>Type</th>
								<th>Title</th>
								<th>Content</th>
								<th>Date</th>
							</tr>
							<c:forEach var="r" items="${list}">
								<c:if test="${r.BI_BT<0 }">
								<tr>
									<th>${r.BI_BT==-2?'Board':'Photo'}</th>
									<th>${r.BOARD_TITLE}</th>
									<th>${r.BC_RC}</th>
									<th>${fn:split(r.MD,' ')[0]}</th>
								</tr>
								</c:if>
							</c:forEach>
							
						</table>
					</div>
				</div>
			</div>

			<footer class="pt-3 mt-4 text-muted border-top"> &copy; 2022 </footer>
		</div>
	</main>
	
	<div class="modal fade" tabindex="-1" role="dialog" id="modalChoice">
		<div class="modal-dialog" role="document">
    		<div class="modal-content rounded-3 shadow">
      			<div class="modal-body p-4 text-center">
        			<h3 class="mb-0">정말로 탈퇴하시겠습니까?</h3>
        			<p class="mb-0">탈퇴 후 계정은 복구할 수 없습니다.</p>
      			</div>
      			<div class="modal-footer flex-nowrap p-0">
        			<button type="button" class="btn btn-lg btn-link fs-6 text-decoration-none col-6 m-0 rounded-0 border-end" onclick="location.href='${contextPath}/deleteMember.me'">
        				<strong>네</strong>
        			</button>
        			<button type="button" class="btn btn-lg btn-link fs-6 text-decoration-none col-6 m-0 rounded-0" data-bs-dismiss="modal">아니오</button>
      			</div>
    		</div>
  		</div>
	</div>
	
	<script>
		window.onload = () => {
			const loginUser = '${loginUser}';
			if(loginUser == ''){
				alert('로그인 후 이용해주세요.');
				location.href='${contextPath}/loginView.me';
			}
			
			document.getElementById('delete').addEventListener('click', ()=>{
				$('#modalChoice').modal('show');	
			});
		}
	</script>
</body>
</html>
