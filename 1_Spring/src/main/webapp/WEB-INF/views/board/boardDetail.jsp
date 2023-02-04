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
				<h2>BOARD DETAIL</h2>
			</div>

			<div style="margin-left: 350px; margin-right: 350px;">
				<form class="needs-validation" method="POST" id="detailForm">
					<div class="row g-3">
						<div class="col-12">
							<label for="boardTitle" class="form-label">TITLE</label>
							<input type="hidden" value="${b.boardId }" name="boardId">
							<input type="hidden" value="${page}" name="page">
							<br>${b.boardTitle}
		
						</div>
						<div class="col-12">
							<div class="input-group">
					          <span class="input-group-text">CONTENT</span>
					          <textarea class="form-control" rows="10" name="boardContent" style="resize: none;" readonly>${b.boardContent}</textarea>
					        </div>
						</div>
						
						<br><br><br><br><br>
						<c:if test="${loginUser.id eq b.boardWriter }">
						<button class="w-100 btn btn-success btn-lg" type="button" id="updateForm" >UPDATE</button>
						<button class="w-100 btn btn-success btn-lg" type="button" id="deleteModal" >DELETE</button>
						</c:if>
						<button class="w-100 btn btn-dark btn-lg" type="button" onclick="location.href='${contextPath}/list.bo?page='+${page }">GO TO LIST</button>
					</div>
					<br>

					<div class="input-group">
						<span class="input-group-text">REPLY<br>CONTENT</span>
						<textarea class="form-control" rows="3" id="replyContent" style="resize: none;"></textarea>
						<button class="btn btn-outline-success btn-lg" id="replySubmit" type="button" <c:if test="${ empty loginUser }">disabled</c:if>>SUBMIT</button>
					</div>
					<br>
					<table class="table">
						<thead>
							<tr>
								<th width="130px">작성자</th>
								<th>작성내용</th>
								<th width="130px">작성일자</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list }" var="r">
								<tr>
									<td>${r.nickName }</td>
									<td>${r.replyContent }</td>
									<td>${r.replyModifyDate }</td>
								</tr>
							</c:forEach>
						
						</tbody>
					</table>
				</form>
			</div>
		</main>
	<div class="modal fade" tabindex="-1" role="dialog" id="modalChoice">
		<div class="modal-dialog" role="document">
    		<div class="modal-content rounded-3 shadow">
      			<div class="modal-body p-4 text-center">
        			<h3 class="mb-0">정말로 삭제하시겠습니까?</h3>
        			<p class="mb-0">삭제 후 게시글은 복구할 수 없습니다.</p>
      			</div>
      			<div class="modal-footer flex-nowrap p-0">
        			<button type="button" class="btn btn-lg btn-link fs-6 text-decoration-none col-6 m-0 rounded-0 border-end" id="delete">
        				<strong>네</strong>
        			</button>
        			<button type="button" class="btn btn-lg btn-link fs-6 text-decoration-none col-6 m-0 rounded-0" data-bs-dismiss="modal">아니오</button>
      			</div>
    		</div>
  		</div>
	</div>
		<footer class="my-5 pt-5 text-muted text-center text-small"></footer>
	</div>
	
	<script>
		window.onload=()=>{
			const upd=document.getElementById('updateForm');
			const form=document.getElementById('detailForm');
			
			if(upd!=null){
				upd.addEventListener('click',()=>{
					form.action='${contextPath}/updateForm.bo';
					form.submit();
				});
			}
			const deleteModal=document.getElementById('deleteModal');
			if(deleteModal!=null){
				document.getElementById('deleteModal').addEventListener('click',()=>{
					$('#modalChoice').modal('show');
				});
			}
			
			document.getElementById('delete').addEventListener('click',()=>{
				form.action='${contextPath}/delete.bo';
				form.submit();
			});
			document.getElementById('replySubmit').addEventListener('click',()=>{
				$.ajax({
					url:'${contextPath}/insertReply.bo',
					data:{replyContent:document.getElementById('replyContent').value,
						refBoardId:${b.boardId},replyWriter:'${loginUser.id}'},
					success:(data)=>{
						console.log(data);
						const tbody=document.querySelector('tbody');
						tbody.innerHTML='';
						
						for(const r of data){
							const tr = document.createElement('tr');
							const writerTd = document.createElement('td');
							writerTd.innerText=r.nickName;
							const contentTd=document.createElement('td');
							contentTd.innerText=r.replyContent;
							const dateTd=document.createElement('td');
							dateTd.innerText=r.replyModifyDate;
							
							tr.append(writerTd);
							tr.append(contentTd);
							tr.append(dateTd);
							tbody.append(tr);
						}
						document.getElementById('replyContent').value='';
					},
					error:(data)=>{
						console.log(data);
					}
				})
			})
		}
	</script>
</body>
</html>