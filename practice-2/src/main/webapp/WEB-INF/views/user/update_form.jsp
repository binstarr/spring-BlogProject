<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>

<div class="container">
	<form action="">
		<input type="hidden" name="id" id="id" value="${principal.user.id}">
		<div class="form-group">
			<label for=" username"> username:</label> 
			<input type=" text" class="form-control" readonly="readonly" name="username" placeholder="Enter username" id="username" value="${principal.user.username}">
		</div>
		
		<div class="form-group">
			<label for="password">password:</label> 
			<input type="password" class="form-control" placeholder="Enter password" id="password" value="">
		</div>
		
		<div class="form-group">
			<label for="email">email:</label> 
			<input type="email" class="form-control" placeholder="Enter email" id="email" value="${principal.user.email}">
		</div>
		
		<button type="button" id="btn--update" class="btn btn-primary">회원정보수정완료</button>
	</form>

</div>
<script type="text/javascript" src="/js/user.js"></script>
<%@include file="../layout/footer.jsp"%>
