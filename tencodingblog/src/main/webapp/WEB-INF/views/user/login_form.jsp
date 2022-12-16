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
		<input type="password" class="form-control" placeholder="Enter password" id="password" name="password" value="asd123">
	</div>
	<button type="submit" id="btn--login" class="btn btn-primary">signIn</button>
	<a href="https://kauth.kakao.com/oauth/authorize?client_id=1e1f1847ff550347b03a392367de4ad2&redirect_uri=http://localhost:9090/auth/kakao/callback&response_type=code">
	<img alt="카카오로그인" src="/image/kakao_login_medium.png" width="68" height="38"></a>
</form>

</div>
<%@include file="../layout/footer.jsp" %>