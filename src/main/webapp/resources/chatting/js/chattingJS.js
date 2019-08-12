$(document).ready(function(){
	//input type text 초기화
	$("#sendBtn").on("click", function(){
		var msg = $("#message").val();
		socket.send(msg);
		$("#message").val("");
	});
})
