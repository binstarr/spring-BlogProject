<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp" %>

<!-- /auth/loginProc 는 SecurityConfig에 있다. -->
<div class="container">
<form action="/auth/loginProc" method="POST">
	<div class="form-group">
		<label for=" username"> username:</label> 
		<input type=" text" class="form-control" placeholder="Enter username" id="username" name="username" value="teco">
	</div>
	<div class="form-group">
		<label for="password">password:</label> 
		<input type="password" class="form-control" placeholder="Enter password" id="password" name="password" value="1q2w3e4r">
	</div>
	<button type="submit" id="btn--login" class="btn btn-primary">signIn</button>
</form>

</div>
<%@include file="../layout/footer.jsp" %>