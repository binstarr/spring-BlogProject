<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>

<div class="container">

	<form action="">
		<input type="hidden" id="" value="${principal.user.id}">

		<div class="form-group">
			<label for="content" id="board-id" data-id="${board.id}"> 글번호 : ${board.id}</label>
		</div>

		<div class="form-group">
			<label for="content">작성자 : </label> <input type="text" name="username" id="username" 
			class="form-control" value="${principal.user.username}"
				readonly>
		</div>

		<div class="form-group">
			<label for="content">Title : </label> <input type="text" name="title" id="title" 
			class="form-control" value="${board.title}">
		</div>

		<div class="form-group">
			<label for="content">Content : </label>
			<textarea name="content" id="content" rows="5" class="form-control content">
			 ${board.content}</textarea>
		</div>
		<script>
			$('.content').summernote({
				//placeholder : '내용을 입력해주세요!',
				tabsize : 2,
				height : 300
			});
		</script>

	</form>
	<button type="button" id="btn--update" class="btn btn-outline-secondary ">글 수정하기</button>
</div>
<br>

<script type="text/javascript" src="/js/board.js"></script>

<%@ include file="../layout/footer.jsp"%>
