<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>td:hover{cursor: pointer;}</style>
</head>
<body>
	<jsp:include page="../common/navbar.jsp"/>
	<div class="container py-4">
		<div class="bd-example-snippet bd-code-snippet">
			<div class="p-5 mb-4 bg-light rounded-3">
				<div class="container-fluid py-5">
					<h1 class="display-5 fw-bold">General Board</h1>
				</div>
			</div>
			<div class="bd-example">
				<table class="table table-hover">
					<thead>
						<tr>
							<th width="80px">글 번호</th>
							<th>글 제목</th>
							<th width="130px">작성자</th>
							<th width="130px">작성일자</th>
							<th width="80px">조회수</th>
	          			</tr>
	          		</thead>
	         		<tbody> 
	          			<c:forEach var="list" items="${list}">
	          			<tr>
	            			<td>${list.boardId }</td>
	            			<td>${list.boardTitle }</td>
	            			<td>${list.nickName}</td>
	            			<td>${list.modifyDate }</td>
	            			<td>${list.boardCount }</td>
	          			</tr>
	          			</c:forEach>	
	          		</tbody>
	        	</table>
	       	</div>
		</div>
        <nav aria-label="Standard pagination example" style="float: right;">
        	<ul class="pagination">
	            <li class="page-item">
	            <c:url var="goBack" value="${loc }">
	            	<c:param name="page" value="${pi.currentPage-1 }"></c:param>
	            </c:url>
	            	<a class="page-link" href="${goBack}" aria-label="Previous">
	            		<span aria-hidden="true">&laquo;</span>
	              	</a>
	            </li>
				<c:forEach begin="${pi.startPage }" end="${pi.endPage}" var="p">
	            	<c:url var="goNum" value="${loc}">
	            		<c:param name="page" value="${p}"></c:param>
	            	</c:url>
	            <li class="page-item"><a class="page-link" href="${goNum}">${p}</a></li>
	            </c:forEach>
	            <li class="page-item">
				<c:url var="goNext" value="${loc }">
					<c:param name="page" value="${pi.currentPage+1}"></c:param>
				</c:url>
	            	<a class="page-link" href="${goNext}" aria-label="Next">
	            		<span aria-hidden="true">&raquo;</span>
	            	</a>
	            </li>
	    	</ul>
        </nav>
	<c:if test="${ !empty loginUser }">
        	<button class="btn btn-outline-success" type="button" onclick="location.href='${ contextPath }/writeBoard.bo'">WRITE BOARD</button>
        </c:if>
	</div>
	<script>
		window.onload=()=>{
			const tbody=document.querySelector('tbody');
			const tds=tbody.querySelectorAll('td');
			
			for(const td of tds){
				td.addEventListener('click', function(){
				const trTds = this.parentElement.querySelectorAll('td');
				const boardId =trTds[0].innerText;
				const writer=trTds[2].innerText;
				
				location.href='${contextPath}/selectBoard.bo?bId='+boardId+'&writer='+writer+'&page='+${pi.currentPage};
				
				/* 
				코드 해석 
				const trTds = this.parrentElement(tr을 의미).querySelelctorAll('td')(tr안에 있는 각각의 td들을 의미);
				*/
				
				})
			}
		}
	</script>
	
</body>
</html>