<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp" %>

<div class="container">
<form action="">
	<div class="form-group">
		<label for=" username"> username:</label> 
		<input type=" text" class="form-control" placeholder="Enter username" id="username" value="빈빈빈">
	</div>
	<div class="form-group">
		<label for="password">password:</label> 
		<input type="password" class="form-control" placeholder="Enter password" id="password" value="asd123">
	</div>
	<div class="form-group">
		<label for="email">email:</label> 
		<input type="email" class="form-control" placeholder="Enter email" id="email" value="abc@naver.com">
	</div>
	<button type="button" id="btn--save" class="btn btn-primary">signUp</button>
</form>

</div>
<script type="text/javascript" src="/js/test.js"></script>

<%@include file="../layout/footer.jsp" %>