$(function(){
	$(document).ready(function(){
		$.ajax({
			url : "/insertOX",
			method : "get",
			success : function(){
				
			}
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