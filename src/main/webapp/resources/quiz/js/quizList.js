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
		
		// 퀴즈리스트 클릭 시 해당 퀴즈 조회 화면 이동 
		$(".table-light").on("click", function(){
			alert($(this).find(":first-child").text());
//			$("#quiz_id").val($(this).val());
		})
	})
	
	quizList(1, 10);
})

function quizList(page, pageSize){
	$.ajax({
		url : "/quizOX",
		method : "post",
		data : "page="+page+"&pageSize="+pageSize+"&quiz_right="+$("#quiz_right").val(),
		success : function(data){
			// html
			var html = data.split("SEPERATORSEPERATOR");
			$("#tableQuizList").html(html[0]);
			$(".pagination").html(html[1]);
		}
	})
}

function cmaTextareaSize(obj, bsize) { // 객체명, 기본사이즈
    var sTextarea = document.getElementById(obj);
    var csize = (sTextarea.scrollHeight >= bsize) ? sTextarea.scrollHeight+"px" : bsize+"px";
    sTextarea.style.height = bsize+"px"; 
    sTextarea.style.height = csize;
}
