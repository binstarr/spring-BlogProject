<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file= "../layout/header.jsp" %>

<script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>

<div class="container">
	<form action="">
	<input type="hidden" id="id" value="principal.user.id">
	
		<div class="form-group">
			<label for="title" id="board-id" data-id="${board.id}">글 번호 : ${board.id}</label> 
		</div>
		
		<div class="form-group">
			<label for="title">username:</label> 
			<input type="text" name="username" id="username" class="form-control" value="${principal.user.username}" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="title">Title</label> <input type="text" name="title" id="title" class="form-control" value="${board.title}" >
		</div>
		<div class="form-group">
			<label for="content">Content</label>
			<textarea name="content" id="content" rows="5" class="form-control content">${board.content}</textarea>
		</div>
	</form>
	<button type="button" class="btn btn-primary" id="btn--update">글 수정하기</button>
</div>


<script>
	$('.content').summernote({
		placeholder : '내용을 입력해주세요',
		tabsize : 2,
		height : 300
	});
</script>
<script type="text/javascript" src="/js/board.js"></script>




<%@include file= "../layout/footer.jsp" %>
