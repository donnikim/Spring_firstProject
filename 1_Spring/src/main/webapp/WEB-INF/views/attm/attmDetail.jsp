<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<title>Masonry example · Bootstrap v5.2</title>
<style>.bd-placeholder-img {font-size: 1.125rem; text-anchor: middle;}</style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
</head>
<body>
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
							<input type="hidden" value='${ b.boardId }' name="boardId">
							<input type="hidden" value='${ page }' name="page">
							<br>${ b.boardTitle }
					
						</div>
						
						<div class="col-12">
							<div class="input-group">
					          <span class="input-group-text">CONTENT</span>
					          <textarea class="form-control" rows="10" name="boardContent" style="resize: none;" readonly>${ b.boardContent }</textarea>
					        </div>
						</div>
						
						<main class="container py-5">
							<div class="row">
							<c:forEach items="${list }" var="a">
								<c:if test="${fn:containsIgnoreCase(a.renameName,'jpg') or fn:containsIgnoreCase(a.renameName,'png')}">
									<!-- 이미지 파일일 때 -->
									<div class="mb-4">
										<div class="card">
											<img src="${contextPath }/resources/uploadFiles/${a.renameName}" width="100%" height="200">
											<div class="card-body">
												<h5 class="card-title">
													<a href="${contextPath }/resources/uploadFiles/${a.renameName}" download="${a.originalName}">
													${a.originalName}
													</a>
												</h5>
											</div>
										</div>
									</div>
									</c:if>
							</c:forEach>
								<c:if test="${!(fn:containsIgnoreCase(a.renameName,'jpg') or fn:containsIgnoreCase(a.renameName,'png'))}">
								<!-- 이미지 파일이 아닐 때 -->
									<div class="mb-4">
										<div class="card">
											<div class="card-body"><h5 class="card-title">
											<a href="${contextPath}/resources/uploadFiles/${a.renameName}" download="${a.originalName }">${a.originalName }</a>
											</h5></div>
										</div>
									</div>
								</c:if>
							</div>
						</main>
						<c:if test="${ loginUser.id eq b.boardWriter }">
							<button class="w-100 btn btn-success btn-lg" type="button" id="updateForm">UPDATE</button>
							<button class="w-100 btn btn-secondary btn-lg" type="button" id="deleteModal">DELETE</button>
						</c:if>
						<button class="w-100 btn btn-dark btn-lg" type="button" onclick="location.href='${contextPath}/list.at?page='+${page}">GO TO LIST</button>
					</div>
				</form>
			</div>
		</main>

		<footer class="my-5 pt-5 text-muted text-center text-small"></footer>
		
		<div class="modal fade" tabindex="-1" role="dialog" id="modalChoice">
			<div class="modal-dialog" role="document">
	    		<div class="modal-content rounded-3 shadow">
	      			<div class="modal-body p-4 text-center">
	        			<h3 class="mb-0">정말로 삭제하시겠습니까?</h3>
	        			<p class="mb-0">삭제 후 게시글은 되돌릴 수 없습니다.</p>
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
	</div>
	<script>
		window.onload = () =>{
			const upd = document.getElementById("updateForm");
			const form = document.getElementById('detailForm');
			if(upd != null){
				upd.addEventListener('click', ()=>{
					form.action = '${contextPath}/updateForm.at';
					form.submit();
				});
			}
			
			document.getElementById('deleteModal').addEventListener('click', ()=>{
				$('#modalChoice').modal('show');	
			});
			
			document.getElementById("delete").addEventListener('click', ()=>{
				form.action = '${contextPath}/delete.at';
				form.submit();
			});
		}
	</script>
</body>
</html>