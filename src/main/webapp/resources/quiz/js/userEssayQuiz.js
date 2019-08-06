$(function(){
	$(document).ready(function() {
		
		$("#answerBtn").on("click", function() {
			var userAnswer = $("#userAnswer").val();
			var quiz_id = $("#quiz_id").val();
			var quiz_right = $("#quiz_right").val();
			
			console.log(userAnswer);
			
			var answerData = {
				quiz_id : quiz_id,
				quiz_right : quiz_right,
				userAnswer : userAnswer
			};
			
			$.ajax({
				method : "post",
				url : "/userEssayAnswer",
				data : JSON.stringify(answerData),
				contentType: "application/json",
	        	dataType : "json",
				success : function(response){
					console.log(response);
					console.log(response.msg);
					
					if(response.msg == "정답"){
						alert("정답 입니다.");
					}else {
						alert("정답이 아닙니다.");
					}
				},
				error:function(request,status,error){
			        console.log("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
			       },
			});
		})
	})
})