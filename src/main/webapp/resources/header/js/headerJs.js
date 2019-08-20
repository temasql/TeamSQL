$(document).ready(function(){	

	as = true;
	$("#thumbnail").on("click", function(){
		if (as) {
			$("#te").animate({opacity: "1" }, 300);
			$("#pname").text($("#hname").val())
			$("#pemail").text($("#hemail").val())
			$("#te").css("display", "block")
			$(".profile").css("box-shadow", "0 2px 5px");
			as = false;
		}else{
			$("#te").animate({opacity: "0"}, 100);
			$("#te").css("display", "none")
			$(".profile").css("box-shadow", "");
			as = true;
		}
	})
	$(".mcl").on("click", function(){
		location.href=$("#cp").val() + "/user/mypage";
	})
//	$("#thumbnail").on("click", function(){
//	})
	//챗봇클릭이벤트
	$("#chatBotPopup").click(function(){
		 window.open("/chatBot/chatBot", "_blank","scrollbar=no, resizeable=no, top=100,left=1015,width=600,height=651");
	});
	
	//채팅클릭 이벤트
	$("#groupChat").on("click",function(){
		window.open("/teamChat/ChattingView", "_blank","scrollbar=no, resizeable=no, top=100,left=1015,width=600,height=700");
	})
	
});