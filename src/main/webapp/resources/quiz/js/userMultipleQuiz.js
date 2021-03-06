$(function(){
	$(document).ready(function(){
		var quiz_id = $("#quiz_id").val();
		var quiz_right = $("#quiz_right").val();
		
		
		//정답버튼 클릭시 이벤트
		$("#answerBtn").on("click", function(){
			if($("#quiz_answer").val() == 0){
				
				alert("답을 입력해 주세요.");
				
				return;
			}
			
			if($("#hiddenAnswer").val() == $("#quiz_answer").val()){
				alert("정답");
				
				$("#answerBtn").css("display", "none");
				
				$("#nextBtn").css("display", "inline");
			}else{
				alert("땡!!!!! 다시 선택해 주세요!");
			}
		})
		
		// li태그 클릭시 이벤트
		$(".multiList").on("click",function(){
			var temp = $(this).children().children().next().val();
			$(".multiList").css("color", "");
			$(".multiList").css("font-weight", "");
			
			$(this).css("color", "red");
			$(this).css("font-weight", "bolder");
			$("#quiz_answer").val(temp);
		})
		
		//다음문제 버튼 클릭 시 이벤트
		$("#nextBtn").on("click", function(){
			
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
