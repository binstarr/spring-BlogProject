
let index = {
	init : function(){
		$('#btn--save').bind("click", () => {
			this.save();
		});
	},
	
	save : function(){
		let data = {
			username : $("#username").val(),
			password : $("#password").val(),
			email : $("#email").val()
		}
		
		$.ajax({
			type:"POST",
			url:"/api/user",
			data: JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		}).done(function(data, textStatus, xhr){
			alert("딩동댕")
			console.log(data)
			console.log(textStatus)
			console.log(xhr)
			location.href = "/"
		}).fail(function(error){
			alert("실패")
		});
	}
}

index.init();