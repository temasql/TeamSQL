<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="${cp}/resources/quiz/css/quizMainCss.css" rel="stylesheet">
<link href="${cp}/resources/quiz/css/userQuizCss.css" rel="stylesheet">
<script src="${cp}/resources/quiz/js/userQuiz.js"></script>


	<div class="card border-dark mb-3" style="width:986px; margin-left: 14%; margin-top: 3%;">
	  <div class="card-header">
	  	<div id="question">
	  		<h4 class="card-title question">문제 : ${quizAndAnswerVO.quiz_question}</h4>
	  	</div>
	  </div>
	  <div class="card-body">
	    <div id="answer">
	    	<p class="card-text">답 : <input id="oRadio" type="radio" name="quiz_answer" value="O"/>O<input id="xRadio" type="radio" name="quiz_answer" value="X"/>X</p>
	    </div>
	    <div id="explain">
	    	<p class="card-text">해설 : ${quizAndAnswerVO.quiz_explain}</p>
	    </div>
	  </div>
	</div>
	
	<input id="quiz_id" type="hidden" value="${quizAndAnswerVO.quiz_id}">
	<input id="quiz_right" type="hidden" value="${quizAndAnswerVO.quiz_right}">
	<input id="hiddenAnswer" type="hidden" value="${quizAndAnswerVO.quiz_answer}">

<div id="BtnDiv">
	<button id="answerBtn" class="btn btn-secondary" style="background: black; color: white;">정답 확인</button>
	<button id="nextBtn" class="btn btn-secondary" style="background: black; color: white;">다음 문제</button>
	<button id="explainBtn" class="btn btn-secondary" style="background: black; color: white;">해설보기</button>
</div>