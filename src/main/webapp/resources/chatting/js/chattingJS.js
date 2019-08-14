$(document).ready(function(){
	//input type text 초기화
	function sendClick(){
		var msg = $("#message").val();
		socket.send(msg);
		$("#message").val("");
		
		$("#message").focus();
	}
	
	$("#sendBtn").on("click", function(){
		sendClick();
	});

	$("#message").keypress(function(event){
		if(event.keyCode == 13){
			$("#sendBtn").click();
			return false;
		}
	})
	
	
	//채팅창에서 계정 변경시 이벤트
	$("#account").change(function(){
		$("#userId").text($(this).val());
		var account = $(this).val();
//		alert(account);
//		
//		$.ajax({
//			method : "post",
//			url : "/changeAccount",
//			data : "account="+account,
//			success : function(data){
//				console.log(data);
//			},
//			error : function(error){
//				alert(error);
//			}
//		});
	});
	
	$("#data").scrollTop($("#data")[0].scrollHeight);
})
