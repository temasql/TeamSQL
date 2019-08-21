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
			var questionText = $("[name='quiz_question']").val();
			var answerText = $("[name='quiz_answer']").val();
			var explainText = $("[name='quiz_explain']").val();
			
			//문제가 비었을때
			if(questionText==""){
				alert("문제를 입력해 주세요.");
				return;
			}
			if(answerText==""){
				alert("정답을 입력해 주세요.");
				return;
			}
			if(explainText==""){
				alert("해설을 입력해 주세요.");
				return;
			}
			
			$("#insertOXDB").submit();
		})
		
		//객관식 퀴즈 추가 버튼 클릭시 이벤트
		$("#multiInsertBtn").on("click", function(){
			var questionText = $("#multipleQuestion").val();
			var answerText = $("#quiz_answer").val();
			var explainText = $("[name='quiz_explain']").val();
			
			//객관식 값들 가져오기
			var multiple1 = $("#quiz_multiple1").val();
			var multiple2 = $("#quiz_multiple2").val();
			var multiple3 = $("#quiz_multiple3").val();
			var multiple4 = $("#quiz_multiple4").val();
			var multiple5 = $("#quiz_multiple5").val();
			
			console.log(multiple4);
			
			//문제가 비었을때
			if(questionText==""){
				alert("문제를 입력해 주세요.");
				$("#multipleQuestion").focus();
				return;
			}
			
			//객관식이 비었을때
			if(multiple1==""){
				alert("1번째 객관식을 입력해주세요.");
				return;
			}
			if(multiple2==""){
				alert("2번째 객관식을 입력해주세요.");
				return;
			}
			if(multiple3==""){
				alert("3번째 객관식을 입력해주세요.");
				return;
			}
			if(multiple4==""){
				alert("4번째 객관식을 입력해주세요.");
				return;
			}
			if(multiple5==""){
				alert("5번째 객관식을 입력해주세요.");
				return;
			}
			
			//정답이 비었을때
			if(answerText==""){
				alert("정답을 입력해 주세요.");
				$("#quiz_answer").focus();
				return;
			}
			//해설이 비었을때
			if(explainText==""){
				$("[name='quiz_explain']").focus();
				alert("해설을 입력해 주세요.");
				return;
			}
			
			
			$("#insertMultipleDB").submit();
		});
		
		$("#multipleUpdateBtn").on("click", function(){
			$("#multipleFrm").submit();
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
