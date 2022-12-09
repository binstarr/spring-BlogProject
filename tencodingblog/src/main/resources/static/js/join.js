
$('#join--submit').on('click', function() {
	let data = {
		username: $('#username').val(),
		password: $('#password').val(),
		email: $('#email').val(),
	}; // 객체 만드는 문법

	console.log("username : " + data.username);
	console.log("password : " + data.password);
	console.log("email : " + data.email);

	// js도 http 통신 가능, 동기 방식, 비동기 방식
	// 대부분 비동기 통신을 많이 사용한다.
	$.ajax({
		type: 'POST',
		url: '/blog/dummy/signup',
		data: JSON.stringify(data),
		contentType: 'application/json; charset=utf-8',
		dataType: 'json'
	}).done(function(response) {
		console.log(response);
		//alert("회원가입 완료");
	}).fail(function(error) {
		console.log(error);
		//alert("회원가입 실패");
	});


});