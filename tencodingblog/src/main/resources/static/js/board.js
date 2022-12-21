
let index = {
	init: function() {
		$('#btn--save').bind('click', () => {
			this.save();
		});
		$('#btn--delete').bind('click', () => {
			this.deleteById();
		});
		$('#btn--update').bind('click', () => {
			this.update();
		});
		$('#btn-reply-save').bind('click', () => {
			this.replySave();
		});
	},
	save: function() {

		let token = $("meta[name='_csrf']").attr("content")
		let csrfHeader = $("meta[name='_csrf_header']").attr("content")

		let xcheckTitle = XSSCheck($('#title').val());
		console.log(xcheckTitle)
		let data = {
			title: xcheckTitle,
			content: $('#content').val(),
		};
		// ajax 통신 요청
		$.ajax({
			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader, token);
			},
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

	deleteById: function() {
		let id = $("#board-id").val();
		let token = $("meta[name='_csrf']").attr("content")
		let csrfHeader = $("meta[name='_csrf_header']").attr("content")

		// 통신 ---> ajax
		$.ajax({
			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader, token)
			},
			type: 'DELETE',
			url: '/api/board/' + id,
		}).done(function(data, textStatus, xhr) {
			if (data.status == "OK") {
				alert("글 삭제가 완료 되었습니다.");
				location.href = "/";
			}
		}).fail(function(error) {
			alert("글 삭제하기에 실패 하였습니다.");
		});
	},

	update: function() {

		let token = $("meta[name='_csrf']").attr("content");
		let csrfHeader = $("meta[name='_csrf_header']").attr("content");
		// HTML 태그에 직접 속성을 정의할 수 있다. 규칙은 data-*
		// data-*의 값을 가지고 오기 위해서 Jquery --> (선택자).attr("data-id")
		let boardId = $("#board-id").attr("data-id");
		console.log(">>>>>>>>>>>> : " + boardId);
		console.log(">>>>token>>>>>>> : " + token);
		console.log(">>>>>csrfHeader>>>>>> : " + csrfHeader);

		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}

		$.ajax({
			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader, token);
			},
			type: "PUT",
			url: "/api/board/" + boardId,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(data, textStatus, xhr) {
			if (data.status) {
				alert("글 수정이 완료 되었습니다.");
				location.href = "/";
			}
		}).fail(function(error) {
			alert("글 수정에 실패 하였습니다.");
		});
	},
	replySave: function() {
		let token = $("meta[name='_csrf']").attr("content")
		let csrfHeader = $("meta[name='_csrf_header']").attr("content")
		let replyData = {
			boardId: $('#board-id').val(), // fk(board에 해당 리플)
			content: $('#reply--content').val()
		};
		// ajax 통신 요청
		$.ajax({
			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader, token)
			},
			type: 'POST',
			url: `/api/board/${replyData.boardId}/reply`,
			data: JSON.stringify(replyData),
			contentType: 'application/json; charset=utf-8',
			dataType: 'json'
		}).done(function(data, textStatus, xhr) {
			if (data.status == 'OK') {
				addReplyElement(data.body);
			}
		}).fail(function(error) {
			console.log(error);
			alert("댓글 작성에 실패 하였습니다")

		});
	},
	replyDelete: function(boardId, replyId) {
		let token = $("meta[name='_csrf']").attr("content")
		let csrfHeader = $("meta[name='_csrf_header']").attr("content")
		//alert(boardId + "," + replyId)

		$.ajax({
			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader, token)
			},
			type: 'DELETE',
			url: `/api/board/${boardId}/reply/${replyId}`,
			// delete에는 body가 없으니까 contentType, data가 필요가 없다.
			dataType: 'json'
		}).done(function(resData) {
			if (resData.status == "OK") {
				alert("댓글 삭제 성공")
				location.href = `/board/${boardId}`
			}
		}).fail(function(error) {
			alert("댓글 삭제 실패")
		});
	},

}

function addReplyElement(reply) {
	let childElement = `<li class="list-group-item d-flex justify-content-between" id="reply--${reply.id}">
				<div>${reply.content}</div>
				<div class="d-flex">
					<div>작성자 :&nbsp;${reply.user.username}&nbsp;&nbsp;&nbsp;</div>
					<button class="btn btn-danger badge" onclick="index.replyDelete(${reply.board.id},${reply.id});">삭제</button>
				</div>
			</li>`;

	$("#reply--box").prepend(childElement);
	$("#reply--content").val("");
}

function XSSCheck(str, level) {
	if (level == undefined || level == 0) {
		str = str.replace(/\<|\>|\"|\'|\%|\;|\(|\)|\&|\+|\-/g, "");
	} else if (level != undefined && level == 1) {
		str = str.replace(/\</g, "&lt;");
		str = str.replace(/\>/g, "&gt;");
	}
	return str;
}

index.init();
