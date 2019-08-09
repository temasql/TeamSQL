$(function(){
	$(document).ready(function() {
		$("#answerBtn").on("click", function() {
			var userAnswer = $("#userAnswer").val();
			var quiz_id = $("#quiz_id").val();
			var quiz_right = $("#quiz_right").val();
			var adminAnswer = $("#answerValue").val();
			
			var answerData = {
				quiz_id : quiz_id,
				quiz_right : quiz_right,
				quiz_answer : userAnswer,
				adminAnswer : adminAnswer
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
						
						$("#answerBtn").css("display", "none");
						
						$("#nextBtn").css("display", "inline");
					}else {
						alert("정답이 아닙니다.");
					}
				},
				error:function(request,status,error){
			        console.log("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
			       },
			});
		})
		
		//다음문제 버튼 클릭 시 이벤트
		$("#nextBtn").on("click", function(){
			var quiz_id = $("#quiz_id").val();
			var quiz_right = $("#quiz_right").val();
			alert("다음 문제 버튼 클릭 시 " + quiz_id);
			
			$.ajax({
				url : "/userQuizRead",
				method : "post",
				data : "quiz_id="+quiz_id+"&quiz_right="+quiz_right,
				success : function(data){
					
					$("#gruopDiv").html(data);
				}
			})
		})
		
		//해설 보이기
		$("#explainBtn").on("click", function(){
			
			$("#explain").css("display", "block");
		})
	})
})