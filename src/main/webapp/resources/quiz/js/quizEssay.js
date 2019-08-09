$(document).ready(function(){
	$(function(){
//		문제 답
		var quizAnswer = $("#answerValue").val();
		answerReplace = quizAnswer.replace(/\n/gi,"\n");
		answerReplace = answerReplace.replace(/\t/gi,"&#9;");
		
		answerReplace = answerReplace.substring(0, answerReplace.lastIndexOf("&#9;"));
		
		$("#answerAppend").append(answerReplace);
		
//		문제 해설
		var quizExplain = $("#explainValue").val();
		var explainReplace = quizExplain.replace(/\n/gi,"<br>");
		
		$("#explainAppend").append(explainReplace);
		
	});
})
	
