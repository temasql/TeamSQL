$(document).ready(function(){
	//input type text 초기화
	$("#sendBtn").on("click", function(){
		var msg = $("#message").val();
		socket.send(msg);
		$("#message").val("");
	});
	
	$("#account").change(function(){
		$("#userId").text($(this).val());
	});
	
	$("#data").scrollTop($("#data")[0].scrollHeight);
})
