let index = {
	
	init : function(){
		$("#btn--save").bind('click', () =>{
			this.save();
		});
		$("#btn--delete").bind('click', () =>{
			this.delete();
		});
		$("#btn--update").bind('click', () =>{
			this.update();
		});
	},
	save:function(){
		let data ={
			title: $("#title").val(),
			content: $("#content").val()
		};
		$.ajax({
			type:'POST',
			url:'/api/board',
			data: JSON.stringify(data),
			contentType: 'application/json; charset=utf-8',
			dataType: 'json'
		}).done(function(data, textStatus, xhr){
			console.log("저장 버튼 눌러짐")
			if(data.status == "OK"){
				console.log("저장 버튼 눌러짐")
				alert("글 작성 완료");
				location.href="/";
			}
		}).fail(function(error){
			console.log(error)
		});
	},
	delete:function(){
		let id = $("#board-id").text();
		console.log(id)
		$.ajax({
			type:'DELETE',
			url:'/api/board/' + id
		}).done(function(data, textStatus, xhr){
			if(data.status == "OK"){
				console.log("삭제버튼!")
				alert("삭제 완료");
				location.href="/";
			}
		}).fail(function(error){
			console.log(error)
		});
	},
	update:function(){
		let data ={
			title: $("#title").val(),
			content: $("#content").val()
		};
		$.ajax({
			type:'POST',
			url:'/api/board',
			data: JSON.stringify(data),
			contentType: 'application/json; charset=utf-8',
			dataType: 'json'
		}).done(function(data, textStatus, xhr){
			console.log("저장 버튼 눌러짐")
			if(data.status == "OK"){
				console.log("저장 버튼 눌러짐")
				alert("글 작성 완료");
				location.href="/";
			}
		}).fail(function(error){
			console.log(error)
		});
	},
}

index.init();
