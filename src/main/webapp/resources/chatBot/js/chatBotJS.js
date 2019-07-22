$(document).ready(function() {
	$("#questionBtn").on('click',function(){
		var question= $("#userInput").val();
		alert(question);
		$(".userLi").append("<span>유저</span><div class='userQuestion'>"+question+"</div>").val();
		$("#userInput").val("");
	});
	
	var html = "";
	
	
	
//	   <li class="userLi">
//	    <span>유저</span>
//	    <div class="userQuestion">
//	    	<p class="userMSG" id="idUserMSG">테이블 생성하는 방법</p>
//	     </div>
//	   </li>
	
});