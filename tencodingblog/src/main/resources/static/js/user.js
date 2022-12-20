
let index = {
	init : function(){
		$('#btn--save').bind('click', () =>{
			this.save();
		});
		
		$('#btn--login').bind('click', () => {
			this.login();
		});
		
		$('#btn--update').bind('click', () => {
			this.update();
		})
		
	},
	save : function(){
		
		// attr은 속성을 가져오는 메서드!!!!!
		let token = $("meta[name='_csrf']").attr("content")
		let csrfHeader = $("meta[name='_csrf_header']").attr("content")
		console.log("토큰 확인 : " + token)
		console.log("헤더 확인 : " + csrfHeader)
		
		// form 태그 사용자가 입력한 값을 가지고 오기 --> 자바스크립트 변수로
		let data = {
			username: $('#username').val(),
			password: $('#password').val(),
			email : $('#email').val()
		}
		//console.log(data);
		// todo -> ajax 로 통신 (data -> json 변환 자바 서버로 전송)
		
		// Ajax 통신 구현
		//$.ajax().done(function(){}).fail(); done => 성공시, fail => 실패시
		$.ajax({
			beforeSend: function(xhr){
				xhr.setRequestHeader(csrfHeader, token)
			},
			//회원가입 요청
			type : 'POST',
			url : '/auth/joinProc',
			data : JSON.stringify(data), // http 메세지 body 영역에 들어감
			contentType : 'application/json; charset=utf-8', // 보낼 때 데이터 타입 
			dataType : 'json' // 응답이 왔을 때 MIME TYPE 지정, JSON --> javascript Object로 자동 변환
		}).done(function(data, textStatus, xhr){
			console.log(data);
			if(data.status == "OK"){
				alert("회원가입 완료");
				location.href = "/"
			}
		}).fail(function(error){
			console.log(error);
			console.log(error.responseJSON.message);
			alert("회원가입 실패");	
		});
	},
	update: function(){
		let token = $("meta[name='_csrf']").attr("content")
		let csrfHeader = $("meta[name='_csrf_header']").attr("content")
		let data = {
			id : $('#id').val(),
			username: $('#username').val(),
			password : $('#password').val(),
			email : $('#email').val()
		};
		// 방어적 코드 적어야함 : password or email 을 입력 안 했을 때, 
		$.ajax({
			beforeSend: function(xhr){
				xhr.setRequestHeader(csrfHeader, token)
			},
			type:'PUT',
			url:'/api/user',
			data:JSON.stringify(data),
			contentType:'application/json; charset=utf-8',
			dataType:'json'
		}).done(function(data, textStatus, xhr){
			if(data.status == "OK"){
				alert("회원 정보 수정을 완료 하였습니다.")
				location.href = "/"
			}
		}).fail(function(error){
			alert("회원 정보 수정을 실패 하였습니다.")
			
		});
	}
	
	
};

// 위에는 선언

index.init();


