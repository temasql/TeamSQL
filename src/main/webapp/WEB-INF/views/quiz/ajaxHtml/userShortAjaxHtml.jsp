<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${cp}/resources/quiz/css/quizMainCss.css"  rel="stylesheet">
<link href="${cp}/resources/quiz/css/userQuizCss.css" rel="stylesheet">
<link href="${cp}/resources/quiz/css/userShortQuizCss.css" rel="stylesheet">
<script src="/resources/quiz/js/quizList.js"></script>
<script src="${cp}/resources/quiz/js/userMultipleQuiz.js"></script>

<div id="quizHeader">단답식 퀴즈</div>
<div id="gruopDiv">
	<div id="question">문제 : ${quizAndAnswerVO.quiz_question}</div>
	<div id="answer">답 : <input id="quiz_answer" type="text" class="form-control" autofocus="autofocus">
		<input id="hiddenAnswer" type="hidden" value="${quizAndAnswerVO.quiz_answer}">
	</div>
	<div id="explain">해설 : ${quizAndAnswerVO.quiz_explain}</div>
	<input type="hidden" id="explainValue" value="${quizAndAnswerVO.quiz_explain}">
	
	<input type="hidden" id="quiz_right" name="quiz_right" value="${quizAndAnswerVO.quiz_right}">
	<input type="hidden" id="quiz_id" value="${quizAndAnswerVO.quiz_id}">
</div>

<div id="BtnDiv">
	<button id="answerBtn" class="btn btn-secondary">정답 확인</button>
	<button id="nextBtn" class="btn btn-secondary">다음 문제</button>
	<button id="explainBtn" class="btn btn-secondary">해설보기</button>
</div>