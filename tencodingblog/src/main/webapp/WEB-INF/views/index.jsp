<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="layout/header.jsp"%>

<br><br>
<div class="container">

	<div class="d-flex justify-content-end m-2">

		<form action="/board/search" method="get" class="form-inline">
			<input class="form-control mr-1" type="text" placeholder="검색어를 입력하세요" name="q" value="${q}">
			<button type="submit" class="btn btn-warning">TITLE</button>
		</form>
	</div>


	<c:forEach var="board" items="${boards.content}">
		<div class="card m-2">
			<div class="card-body">
				<h4>${board.title}</h4>
				<!--  -->
				<a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
			</div>
		</div>
		<br>
	</c:forEach>
	<ul class="pagination justify-content-center">
		<c:set var="isDisabled" value="disabled"></c:set>
		<c:set var="isNotDisabled" value=""></c:set>
		<li class="page-item ${boards.first ? isDisabled : isNotDisabled}">
			<a class="page-link" href="/board/search/?q=${q}&page=${boards.number-1}">Previous</a>
		</li>
		<!-- 총 페이지가 몇개인지 알아야 한다. -->
		<!-- 10개가 있으면 페이지당 3개로 지정했으니 페이지가 4개가 나와야 한다. -->
		<!-- 반복문 처리하기 123, 1234, 12345 -->
		<c:forEach var="num" items="${pageNumbers}">
			<c:choose>
				<c:when test="${nowPage eq num}">
					<li class="page-item active">
					<a class="page-link" href="/board/search?q=${q}&page=${num - 1}">${num}</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item">
					<a class="page-link" href="/board/search?q=${q}&page=${num - 1}">${num}</a>
					</li>
				</c:otherwise>
			</c:choose>

		</c:forEach>

		<li class="page-item ${boards.last ? isDisabled: isNotDisabled}"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
	</ul>
</div>




<%@include file="layout/footer.jsp"%>

