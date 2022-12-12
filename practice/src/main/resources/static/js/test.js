
let index = {
	init : function(){
		$('#btn--save').bind('click', () => {
			this.save();
		});
	},
	save : function(){
		let data         = {
			username: $('#username').val(),
			password: $('#password').val(),
			email: $('#email').val()
		}
		$.ajax({
			type: 'POST',
			url : '/api/user',
			data : JSON.stringify(data), // http 메세지 body 영역에 들어감
			contentType : 'application/json; charset=utf-8', // 보낼 때 데이터 타입
			dataType : 'json' // 응답이 왓을 때 MIME TYPE 지정 JSON --> Object로 자동 변환
		}).done(function(data, textStatus, xhr) {
			console.log(data);
			if(data.httpStatus == "OK"){
				console.log(data)
				alert("회원가입 완료")
				location.href = "/"
			}
		}).fail(function(error){
			alert("회원가입 실패")
			console.log(error);
		});
	}
}

index.init();

