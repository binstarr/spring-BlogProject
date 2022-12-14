
let index = {
	init: function() {
		$('#btn--save').bind('click', () => {
			this.save();
		});
		$('#btn--delete').bind('click', () => {
			this.deleteById();
		});
	},
	save: function() {
		let data = {
			title: $('#title').val(),
			content: $('#content').val()
		};
		// ajax 통신 요청
		$.ajax({
			type: 'POST',
			url: '/api/board',
			data: JSON.stringify(data),
			contentType: 'application/json; charset=utf-8',
			dataType: 'json'
		}).done(function(data, textStatus, xhr) {
			if (data.status == 'OK') {
				alert("글쓰기 성공")
				location.href = "/";
			}
		}).fail(function(error) {
			console.log(error);
			alert(error.responseJSON.error)
		});
	},
	deleteById:function(){
		let id = $("#board-id").text();
		
		// 통신 ---> ajax
		$.ajax({
			type:'DELETE',
			url:'/api/board/'+id,
		}).done(function(data, textStatus, xhr){
			if(data.status == "OK"){
				alert("글 삭제가 완료 되었습니다.");
				location.href = "/";
			}
		}).fail(function(error){
			alert("글 삭제하기에 실패 하였습니다.");
		});
		
	}
}

index.init();
