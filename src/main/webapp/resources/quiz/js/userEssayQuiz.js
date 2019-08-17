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
					}else if(response.msg == "잘못된 쿼리문 입니다."){
						alert(response.msg);
						return;
					}else if(response.msg=="세미콜론이 없습니다."){
						alert(response.msg);
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
			
			$.ajax({
				url : "/userQuizRead",
				method : "post",
				data : "quiz_id="+quiz_id+"&quiz_right="+quiz_right,
				success : function(data){
					
					if(data.msg == "마지막 문제입니다."){
						alert(data.msg);
						return;
					}
					
					$("#overrite").html(data);
				}
			})
		})
		
		//해설 보이기
		$("#explainBtn").on("click", function(){
			
			$("#explain").css("display", "block");
		})
	})
})