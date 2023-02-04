<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
</head>
<body class="bg-light">
	<jsp:include page="../common/navbar.jsp"/>
	<div class="container">
		<main>
			<div class="py-5 text-center">
				<a href="${ contextPath }/home.do"><img class="d-block mx-auto mb-4" src="${ contextPath }/resources/image/spring.png" width="130"></a>
				<h2>UPDATE ATTACHMENT BOARD</h2>
			</div>

			<div style="margin-left: 350px; margin-right: 350px;">
				<form class="needs-validation" action="${ contextPath }/updateAttm.at" method="POST" enctype="multipart/form-data" id="attmForm">
					<div class="row g-3">
						<div class="col-12">
							<label for="boardTitle" class="form-label">TITLE</label>
							<input type="hidden" name="boardId" value="${b.boardId }">
							<input type="hidden" name="page" value="${page }">
							<input type="text" class="form-control" id="boardTitle" name="boardTitle" value="${b.boardTitle }">
						</div>

						<div class="col-12">
							<div class="input-group">
					          <span class="input-group-text">CONTENT</span>
					          <textarea class="form-control" rows="10" name="boardContent" style="resize: none;">${b.boardContent }</textarea>
					        </div>
						</div>
						
						<br><br><br><br><br>
						<div>
							<c:forEach items="${list}" var="a">
							<h5>
								<a href="${contextPath }/resources/uploadFiles/${a.renameName }"download="${a.originalName }">
									${a.originalName }
								</a>
							<button type="button" class="btn btn-outline-dark btn-sm deleteAttm" id="delete-${a.renameName }/${a.attmLevel}">삭제 OFF</button>
							<input type="hidden" name="deleteAttm">
							</h5>
							<br>
							</c:forEach>
							
							<br>
								<button type="button" class="w-25 btn btn-outline-success" id="addFile">+파일 추가</button>
								<br><br>
								<div id="fileArea">
									<div class="mb-3">
									 <input type="file" class="form-control form-control-lg" name="file">
									</div>
								</div>
						
						</div>
						<button class="w-100 btn btn-success btn-lg" type="button" id="submitAttm">SUBMIT</button>
						<button class="w-100 btn btn-dark btn-lg" type="button" onclick="javascript:history.back();">BACK</button>
					</div>
				</form>
			</div>
		</main>

		<footer class="my-5 pt-5 text-muted text-center text-small"></footer>
		<div class="modal fade" tabindex="-1" role="dialog" id="modalChoice">
		<div class="modal-dialog" role="document">
    		<div class="modal-content rounded-3 shadow">
      			<div class="modal-body p-4 text-center">
        			<h3 class="mb-0">첨부파일이 첨부되지 않았습니다.</h3>
        			<p class="mb-0">작성된 글은 일반게시판으로 옮겨집니다.</p>
      			</div>
      			<div class="modal-footer flex-nowrap p-0">
        			<button type="button" class="btn btn-lg btn-link fs-6 text-decoration-none col-6 m-0 rounded-0 border-end" id="moveBoard">
        				<strong>네</strong>
        			</button>
        			<button type="button" class="btn btn-lg btn-link fs-6 text-decoration-none col-6 m-0 rounded-0" data-bs-dismiss="modal">아니오</button>
      			</div>
    		</div>
  		</div>
	</div>
	</div>
	<script>
	
		window.onload=()=>{
			const fileArea=document.querySelector('#fileArea');
			document.getElementById('addFile').addEventListener('click',()=>{
				const newDiv = document.createElement('div');
				newDiv.classList.add('mb-3');
				newDiv.innerHTML='<input type="file" class="form-control form-control-lg" name="file">';
				
				fileArea.append(newDiv);
			});
		}
	
		const delBtns= document.getElementsByClassName('deleteAttm');/* 삭제를 누르는 버튼을 의미한다. */
		for(const btn of delBtns){
			btn.addEventListener('click',function(){
				const nextHidden=this.nextElementSibling;
				
				if(nextHidden.value==''){//삭제 버튼을 누르지 않았다면(삭제 off)
					this.style.background='black';
					this.style.color='white';
					this.innerText='삭제ON';
					nextHidden.value=this.id.split("-")[1];
				
				
				}else{//삭제 버튼을 눌렀다면 (삭제 on)
					this.style.background='none';
					this.style.color='black';
					this.innerText='삭제OFF';
					nextHidden.removeAttribute('value');
				
				
				}
				
			});
		}
		
		
		const form = document.getElementById('attmForm'); /* form변수에 form요소찾는 코드를 넣음 */
			document.getElementById('submitAttm').addEventListener('click',()=>{ /* submit 버튼을 누를 때  */
			const files= document.getElementsByName('file'); /* 파일의 이름을 가진 파일들을 files 안에 넣는다.  */
			
			let isEmpty=true; /* 비어있는지 확인하는 boolean 변수를 만들어 준다 */
			
			for(const f of files){ /* 파일안에 있는 내용을 f안에 넣어 줄꺼야!!   */
				if(f.value!=''){ /*  어 근데 파일 안에 값이 없네? */
					isEmpty=false;/* 그러면 isEmpty값을 바꿔줄꺼야 */
			
				}
			}
			/* 파일 수정 시 모두 삭제 체크 되면 일반게시판으로 보내지는 모달창이 뜬다  */
			
			let isAllRemove=true;/* 모든 값을 삭제했는지 확인하는 변수를 boolean값으로 넣어 준다. */
			
			for(const btn of delBtns){ /*  delBtns에서 btn에 있는 innerText값이 삭제off가 있어? */
				if(btn.innerText=='삭제off'){
					isAllRemove=false;/*  그러면 모두 삭제가 아니므로 모달창을 안띄워도 돼!! */
				}
			}
			if(isEmpty && isAllRemove){/*값이 있는지를 체크하는boolean값들이 파일이 없고 모두 삭제하겠다고 하네? */
				
				$('#modalChoice').modal('show'); /*그럼 첨부게시판에 있을 필요가 없으니 일반게시판으로 보내는 모달창 띄워서 보내버려!  */
			}else{
				
				form.submit();
				
			}
		
		});
		document.getElementById('moveBoard').addEventListener('click',()=>{
			form.submit();
		});
		
		
	
	</script>
	
</body>
</html>