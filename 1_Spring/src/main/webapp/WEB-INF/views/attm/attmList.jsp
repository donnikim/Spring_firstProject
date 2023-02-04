<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	.album .col{cursor: pointer;}
	.bd-placeholder-img {font-size: 1.125rem; text-anchor: middle;}
	.card *:hover{cursor: pointer;}
</style>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../common/navbar.jsp"/>
	<main>

		<section class="py-5 text-center container">
			<div class="row py-lg-5"><h1 class="fw-light">Attachment Board</h1></div>
		</section>

		<div class="album py-5 bg-light">
			<div class="container">
				<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
       
						<c:forEach items="${bList }" var="b" >
							
								<div class="col">
								<div class="card shadow-sm">
									<c:forEach items="${aList }" var="a">
										<c:if test="${b.boardId eq a.refBoardId }">
											<c:if test="${fn:containsIgnoreCase(a.renameName,'jpg') or fn:containsIgnoreCase(a.renameName,'png')}">
												<img src="${contextPath}/resources/uploadFiles/${a.renameName}" width="100%" height="225">
											</c:if>
											<c:if test="${!(fn:containsIgnoreCase(a.renameName,'jpg') or fn:containsIgnoreCase(a.renameName,'png'))}">
												<svg class="bd-placeholder-img card-img-top" width="100%" height="225">
													<rect width="100%" height="100%" fill="#55595c"/>
													<text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text>
												</svg>
											</c:if>
										</c:if>									
									</c:forEach>
										<div class="card-body">
											<p class="card-text">
												<label class="bId">No. ${b.boardId}</label> | <label class="writer">${b.nickName }</label>
											</p>
											<div class="d-flex justify-content-between align-items-center">
												${b.boardTitle }
												<small class="text-muted">${b.boardCount }View</small>
											</div>
										</div>
									</div>
								</div>
							
						</c:forEach>
					
					
				</div>
				<br><br><br>
				<nav aria-label="Standard pagination example" style="float: right;">
					${loc}
					
					<ul class="pagination">
	           				<li class="page-item">
	            					<c:url var="goBack" value="${ loc }">
	            						<c:param name="page" value="${ pi.currentPage-1 }"></c:param>
	            					</c:url>
	            					<a class="page-link" href="${ goBack }" aria-label="Previous">
	            						<span aria-hidden="true">&laquo;</span>
	              					</a>
	            				</li>
	            				<c:forEach begin="${ pi.startPage }" end="${ pi.endPage }" var="p">
	           					<c:url var="goNum" value="${ loc }">
	           						<c:param name="page" value="${ p }"></c:param>
	           					</c:url>
	            					<li class="page-item"><a class="page-link" href="${ goNum }">${ p }</a></li>
	            				</c:forEach>
	            				<li class="page-item">
	            					<c:url var="goNext" value="${ loc }">
	            						<c:param name="page" value="${ pi.currentPage+1 }"></c:param>
	            					</c:url>
	            					<a class="page-link" href="${ goNext }" aria-label="Next">
	            						<span aria-hidden="true">&raquo;</span>
	            					</a>
	            				</li>
	    				</ul>
        			</nav>
		        	<c:if test="${ !empty loginUser }">
		        		<button class="btn btn-outline-success" type="button" onclick="location.href='${ contextPath }/writeAttm.at'">WRITE BOARD</button>
		        	</c:if>
			</div>
		</div>
	</main>
	<script>
		window.onload=()=>{
			const dives=document.getElementsByClassName('card');
			
			for(const div of dives ){
				div.addEventListener('click',function(){
					const fullId=this.querySelector('.bId').innerText;
					const boardId=fullId.split(' ')[1];
					const writer=this.querySelector('.writer').innerText;
					location.href='${contextPath}/selectAttm.at?bId='+boardId+'&writer='+writer+'&page='+${pi.currentPage};
				});
			}
			
		}
	</script>
	
</body>
</html>
