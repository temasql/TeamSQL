var question = $("#question").val();
var answer = $("#answer").val();
var explain = $("#explain").val();
var quiz_right = $("#quiz_right").val();

$(function(){
	$(document).ready(function(){
		$.ajax({
			url : "/insertOX",
			method : "get",
			success : function(){
				
			}
		})
		
		var insertOX = {
			question : question,
			answer : answer,
			explain : explain,
			quiz_right : quiz_right
		}
		
		//퀴즈 등록 버튼
		$("#insertOXBtn").on("click", function(){
			$("#insertOXDB").submit();
		})
		$("#insertBtn").on("click", function(){
			$("#insertOXDB").submit();
		})
		
		// 퀴즈리스트 클릭 시 해당 퀴즈 조회 화면 이동 
		$("#tableQuizList").on("click", ".table-light", function(){
			var quiz_id = $(this).find(":first-child").text();
			$("#quizId").val(quiz_id);
			$("#readOXFrm").submit();
		});
		
		// OX퀴즈 수정화면으로 이동
		$("#updateOXBtn").on("click", function(){
			$("#updateOXFrm").submit();
			$("#updateQuizFrm").submit();
		})
		
		// OX 퀴즈 DB 수정
		$("#updateOXBtnDB").on("click", function(){
			$("#updateOXDB").submit();
		})
		
		// 퀴즈 DB 수정
		$("#updateBtnDB").on("click", function(){
			$("#updateDB").submit();
		})
		
		// OX퀴즈삭제 버튼 클릭 시 해당 퀴즈 삭제
		$("#deleteOXBtn").on("click", function(){
			$("#deleteQuizFrm").submit();
		})
		
		//  선택한 퀴즈 삭제
		$("#deleteOXBtn").on("click", function(){
			$("#deleteQuizFrm").submit();
		})
		
		$(".custom-radio").on("click", function(){
			var text = $(this).child().next();
			alert(text);
		})
		
		$(function (){
			var quiz_answer = "${quizAndAnswerVO.quiz_answer}";
			
			if(quiz_answer == "O"){
				$("#oRadio").attr("checked", "checked");	
			}else{
				$("#xRadio").attr("checked", "checked");
			}
		})
		
		function numberMaxLength(e){
	        if(e.value.length > e.maxLength){
	            e.value = e.value.slice(0, e.maxLength);
	        }
	    }
	});
	
	quizList(1, 10);
});

function quizList(page, pageSize){
	$.ajax({
		url : "/quizList",
		method : "post",
		data : "page="+page+"&pageSize="+pageSize+"&quiz_right="+$("#quiz_right1").val(),
		success : function(data){
			// html
			var html = data.split("SEPERATORSEPERATOR");
			$("#tableQuizList").html(html[0]);
			$(".pagination").html(html[1]);
		}
	});
}

function cmaTextareaSize(obj, bsize) { // 객체명, 기본사이즈
    var sTextarea = document.getElementById(obj);
    var csize = (sTextarea.scrollHeight >= bsize) ? sTextarea.scrollHeight+"px" : bsize+"px";
    sTextarea.style.height = bsize+"px"; 
    sTextarea.style.height = csize;
}
