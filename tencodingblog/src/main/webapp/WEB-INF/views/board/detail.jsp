<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<br><br>
<div class="container">
	<button class="btn bg-secondary" onclick="history.back();">돌아가기</button>
	<c:if test="${board.user.id == principal.user.id}">
		<a class="btn btn-warning" id="" href="/board/${board.id}/update_form">수정하기</a>
		<button class="btn btn-danger" id="btn--delete">삭제하기</button>
	</c:if>
	<br> <br> <br>

	<div>
		<input type="hidden" id="board-id" value="${board.id}"> 글 번호 : <span><i>${board.id + 100}</i></span>
	</div>
	<div>
		글 작성자 : <span>${board.user.username}</span>
	</div>
	<div class="">
		<h3>${board.title}</h3>
	</div>
	<div>${board.content}</div>
	<br> <br>
	<div class="card">
		<div class="card-body">
			<textarea rows="1" class="form-control" id="reply--content"></textarea>
		</div>
		<div class="card-footer">
			<button class="btn btn-primary" id="btn-reply-save">등록</button>
		</div>
	</div>
	<br>
	<div class="card">
		<div class="card-header">댓글 목록</div>
	</div>
	<ul class="list-group" id="reply--box">
		<c:forEach var="reply" items="${board.replys}">
			<li class="list-group-item d-flex justify-content-between" id="reply--${reply.id}">
				<div>${reply.content}</div>
				<div class="d-flex">
					<div>작성자 :&nbsp;${reply.user.username}&nbsp;&nbsp;&nbsp;</div>

					<c:if test="${reply.user.id eq principal.user.id}">
						<button class="btn btn-danger badge" onclick="index.replyDelete(${board.id},${reply.id});">삭제</button>
					</c:if>

				</div>
			</li>
		</c:forEach>
	</ul>
</div>

<script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>

