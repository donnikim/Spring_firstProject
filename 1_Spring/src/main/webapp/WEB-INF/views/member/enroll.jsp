<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
</head>
<body class="bg-light">
	<div class="container">
		<main>
			<div class="py-5 text-center">
				<a href="${ contextPath }/home.do"><img class="d-block mx-auto mb-4" src="${ contextPath }/resources/image/spring.png" alt="" width="130"></a>
				<h2>Sign Up</h2>
			</div>

			<div style="margin-left: 350px; margin-right: 350px;">
				<form class="needs-validation" action="${ contextPath }/insertMember.me" method="POST">
					<div class="row g-3">
						<div class="col-12">
							<label for="id" class="form-label">ID</label>
							<input type="text" class="form-control" id="id" name="id" required>
							<label id="idCheckResult">Please check your ID.</label>
						</div>
						
						<div class="col-12">
							<label for="pwd" class="form-label">PWD</label>
							<input type="password" class="form-control" id="pwd" name="pwd" required>
						</div>
						
						<div class="col-12">
							<label for="pwdConfirm" class="form-label">PWD Confirm</label>
							<input type="password" class="form-control" id="pwdConfirm" required>
						</div>
						
						<div class="col-12">
							<label for="name" class="form-label">NAME</label>
							<input type="text" class="form-control" id="name" name="name" required>
						</div>
						
						<div class="col-12">
							<label for="nickName" class="form-label">NICKNAME</label>
							<input type="text" class="form-control" id="nickName" name="nickName" required>
							<label id="nickNameCheckResult">Please check your NickName.</label>
						</div>

						<div class="col-12">
							<label for="emailId" class="form-label">EMAIL</label>
							<div class="input-group">
								<input type="text" class="form-control" id="emailId" name="emailId">
								<span class="input-group-text">@</span>
								<select name="emailDomain" style="width: 280px;">
									<option>naver.com</option>
									<option>gmail.com</option>
									<option>nate.com</option>
									<option>hanmail.net</option>
								</select>
							</div>
						</div>
						
						<div class="col-12">
							<label class="form-label">GENDER</label><br>
							<input type="radio" id="man" name="gender" value="M" checked> MAN &nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" id="woman" name="gender" value="F"> WOMAN
						</div>
						
						<div class="col-12">
							<label for="age" class="form-label">AGE</label>
							<input type="number" class="form-control" id="age" name="age" min="0" max="100" value="0">
						</div>

						<div class="col-12">
							<label for="phone" class="form-label">PHONE</label>
							<input type="text" class="form-control" id="phone" name="phone">
						</div>
						
						<div class="col-12">
							<label for="address" class="form-label">ADDRESS</label>
							<input type="text" class="form-control" id="address" name="address">
						</div>
						
						<br><br><br><br><br>
						
						<button class="w-100 btn btn-primary btn-lg">SIGN UP</button>
					</div>
				</form>
			</div>
		</main>

		<footer class="my-5 pt-5 text-muted text-center text-small"></footer>
	</div>
	
	<script>
		window.onload=()=>{
			document.getElementById("id").addEventListener('change',function(){/* change : ????????? ???????????? ???????????? ????????????  */
				const idResult=document.getElementById('idCheckResult');
				if(this.value.trim()==''){
					idResult.innerText='Please check your ID.';
					idResult.style.color='black';
				}else{
					$.ajax({
						url:'${contextPath}/checkId.me',
						data:{id:this.value},
						success:(data)=>{
							console.log(data);
							if(data.trim()=='yes'){
								idResult.innerText='You can use Id';
								idResult.style.color='green';
							}else if(data.trim()=='no'){
								idResult.innerText='You can not use Id';
								idResult.style.color='red';
							}
						},
						error:(data)=>{
							console.log(data);
						}
					})
				}
				
			});
			document.getElementById("nickName").addEventListener('change',function(){/* change : ????????? ???????????? ???????????? ????????????  */
				const nickNameResult=document.getElementById('nickNameCheckResult');
				if(this.value.trim()==''){
					nickNameResult.innerText='Please check your nickName.';
					nickNameResult.style.color='black';
				}else{
					$.ajax({
						url:'${contextPath}/checkNickName.me',
						data:{nickName:this.value},
						success:(data)=>{
							console.log(data);
							if(data.trim()=='yes'){
								nickNameResult.innerText='You can use nickName';
								nickNameResult.style.color='green';
							}else if(data.trim()=='no'){
								nickNameResult.innerText='You can not use nickName';
								nickNameResult.style.color='red';
							}
						},
						error:(data)=>{
							console.log(data);
						}
					})
				}
				
			});
		}
	
	</script>
</body>
</html>