<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="${cp}/resources/quiz/css/quizMainCss.css" rel="stylesheet">
<link href="${cp}/resources/quiz/css/userQuizCss.css" rel="stylesheet">
<link href="${cp}/resources/quiz/css/quizOX.css" rel="stylesheet">
<script src="${cp}/resources/quiz/js/userQuiz.js"></script>

<div id="quizHeader">OX 퀴즈</div>
<div id="gruopDiv">
	<div id="question">문제 : <div style="display:inline-block">${quizAndAnswerVO.quiz_question}</div></div>
	<div id="answer">답 : <input id="oRadio" type="radio" name="quiz_answer" value="O"/>O<input id="xRadio" type="radio" name="quiz_answer" value="X"/>X</div>
	<div id="explain">해설 : ${quizAndAnswerVO.quiz_explain}</div>
	
	<input id="quiz_id" type="hidden" value="${quizAndAnswerVO.quiz_id}">
	<input id="quiz_right" type="hidden" value="${quizAndAnswerVO.quiz_right}">
	<input id="hiddenAnswer" type="hidden" value="${quizAndAnswerVO.quiz_answer}">
	<div id="BtnDiv">
		<button id="answerBtn" class="btn" style="background: black; color: white;">정답 확인</button>
		<button id="nextBtn" class="btn" style="background: black; color: white;">다음 문제</button>
		<button id="explainBtn" class="btn" style="background: black; color: white;">해설보기</button>
	</div>
</div>
