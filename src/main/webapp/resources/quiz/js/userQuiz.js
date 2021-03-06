$(function(){
	$(document).ready(function(){
		var quiz_id = $("#quiz_id").val();
		var quiz_answer = $("#answerOX").val();
		var quiz_right = $("#quiz_right").val();
		
//		if(quiz_answer == "O"){
//			$("#oRadio").attr("checked", "checked");	
//		}else{
//			$("#xRadio").attr("checked", "checked");
//		}
		
		//정답버튼 클릭시 이벤트
		$("#answerBtn").on("click", function(){
			if($("input:radio[name=quiz_answer]").is(":checked") == false){
				
				alert("정답을 체크해 주세요");
				
				return;
			}
			
			if($("#hiddenAnswer").val() == $("input[name=quiz_answer]:checked").val()){
				alert("정답");
				
				$("#answerBtn").css("display", "none");
				
				$("#nextBtn").css("display", "inline");
			}else{
				alert("땡!!!!! 다시 체크해 주세요.!");
			}
		})
		
		//다음문제 버튼 클릭 시 이벤트
		$("#nextBtn").on("click", function(){
			$.ajax({
				url : "/userQuizRead",
				method : "post",
				data : "quiz_id="+quiz_id+"&quiz_right="+quiz_right,
				success : function(data){
					if(data.msg == "마지막 문제입니다."){
					alert("축하합니다! 모든 문제를 푸셨습니다.");
					return;
				}
					
					var html = data.split();
					
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