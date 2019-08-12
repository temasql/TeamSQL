$(document).ready(function(){	
	
	//챗봇클릭이벤트
	$("#chatBotPopup").click(function(){
		 window.open("/chatBot/chatBot", "_blank","scrollbar=no, resizeable=no, top=100,left=1015,width=600,height=651");
	});
	
	//채팅클릭 이벤트
	$("#groupChat").on("click",function(){
		window.open("/teamChat/ChattingView", "_blank","scrollbar=no, resizeable=no, top=100,left=1015,width=600,height=651");
	})
	
});