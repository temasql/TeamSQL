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
			
			alert($("input[name=quiz_answer]:checked").val());
			
			if($("#hiddenAnswer").val() == $("input[name=quiz_answer]:checked").val()){
				alert("성공");
				
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
//					$("#question").remove();
//					$("#answer").remove();
//					$("#answerOX").remove();
//					$("#explain").remove();
//					
//					$("#quiz_id").remove();
//					$("#quiz_right").remove();
					
					var html = data.split();
					
					$("#gruopDiv").html(data);
				}
			})
		})
	})
})