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
		$("#account_id").val(account);
		
		$("#frm").submit();
	});
	
	$("#data").scrollTop($("#data")[0].scrollHeight);
	
	function getTimeStamp() {
		  var d = new Date();
		  var s =
		    leadingZeros(d.getFullYear(), 4) + '-' +
		    leadingZeros(d.getMonth() + 1, 2) + '-' +
		    leadingZeros(d.getDate(), 2) + ' ' +

		    leadingZeros(d.getHours(), 2) + ':' +
		    leadingZeros(d.getMinutes(), 2) + ':' +
		    leadingZeros(d.getSeconds(), 2);

		  return s;
		}

	function leadingZeros(n, digits) {
	  var zero = '';
	  n = n.toString();

	  if (n.length < digits) {
	    for (i = 0; i < digits - n.length; i++)
	      zero += '0';
	  }
	  return zero + n;
	}
})
