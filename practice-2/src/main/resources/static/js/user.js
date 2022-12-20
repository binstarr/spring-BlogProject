let index = {
	init: function() {
		$('#btn--save').bind('click', () => {
			console.log("클릭")
			this.save();
		});
		$('#btn--login').bind('click', () => {
			console.log("클릭")
			this.login();
		})
	},

	save: function() {
		console.log("save 시작")
		let data = {
			username: $('#username').val(),
			password: $('#password').val(),
			email: $('#email').val(),
		}

		$.ajax({
			type: 'POST',
			url: '/auth/joinProc',
			data: JSON.stringify(data),
			contentType: 'application/json; charset=utf-8',
			dataType: 'json'
		}).done(function(data, textStatus, xhr) {
			if (data.status == "OK") {
				alert("회원가입 성공")
				location.href = '/'
			}
		}).fail(function(error) {
			alert("회원가입 실패")
			console.log(error);
		});
	},
	
}
index.init();

