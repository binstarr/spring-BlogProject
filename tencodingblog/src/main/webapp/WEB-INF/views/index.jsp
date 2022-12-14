<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="layout/header.jsp"%>

<c:forEach var="board" items="${boards.content}">
	<div class="card m-2">
		<div class="card-body">
			<h4>${board.title}</h4>
			<p>${board.content}</p>
			<a href="#" class="btn btn-primary">상세보기</a>
		</div>
	</div>
	<br>
</c:forEach>

<ul class="pagination justify-content-center">
  <li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>

  <!-- 총 페이지가 몇개인지 알아야 한다. -->
  <!-- 10개가 있으면 페이지당 3개로 지정했으니 페이지가 4개가 나와야 한다. -->
  
  <li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
</ul>



<%@include file="layout/footer.jsp"%>

