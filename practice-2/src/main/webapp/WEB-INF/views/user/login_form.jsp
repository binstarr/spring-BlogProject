<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form action="/auth/loginProc" method="POST">

		<div class="form-group">
			<label for="username">Username</label> <input type="username" class="form-control" placeholder="Enter Username" id="username" name="username" value="teco">
		</div>

		<div class="form-group">
			<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password" name="password" value="asd123">
		</div>

		<button type="submit" class="btn btn-primary">LOGIN입니다</button>
	</form>
</div>
<br>

<%@ include file="../layout/footer.jsp"%>

