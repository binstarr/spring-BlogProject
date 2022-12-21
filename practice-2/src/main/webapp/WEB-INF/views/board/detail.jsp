<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>

<div class="container">
	<button class="btn bg-secondary" onclick="history.back();">돌아가기</button>
	<a class="btn btn-warning" id="btn--update" href="/board/${board.id}/update_form">수정하기</a>
	<button class="btn btn-danger" id="btn--delete">삭제하기</button>
	<br><br><br>
	
	<div>글 번호 : <span id="board-id">${board.id}</span></div>
	<div>작성자 : <span>${board.user.username}</span></div>
	<div class="">
		<h3>${board.title}</h3>
	</div>
	<div>
		${board.content}
	</div>
	<br><br><br>	
</div>
<script type="text/javascript" src="/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>
