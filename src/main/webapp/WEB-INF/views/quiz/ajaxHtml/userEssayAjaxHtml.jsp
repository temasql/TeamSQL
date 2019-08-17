<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${cp}/resources/quiz/css/quizMainCss.css"  rel="stylesheet">
<link href="${cp}/resources/quiz/css/userQuizCss.css"  rel="stylesheet">
<script src="/resources/quiz/js/quizList.js"></script>
<script src="/resources/quiz/js/userEssayQuiz.js"></script>

<div id="quizHeader">주관식 퀴즈</div>
<div id="gruopDiv">
	<div id="question">문제 : ${quizAndAnswerVO.quiz_question}</div>
	<div id="answer">답 :&nbsp;<textarea id="userAnswer" name="userAnswer" class="form-control" ></textarea></div>
	<div id="explain">해설 :&nbsp;<div id="explainAppend">${quizAndAnswerVO.quiz_explain}</div></div>
	
	
	<input type="hidden" id="answerValue" value="${quizAndAnswerVO.quiz_answer}">
	<input type="hidden" id="quiz_right" name="quiz_right" value="${quizAndAnswerVO.quiz_right}">
	<input type="hidden" id="quiz_id" name="quiz_id" value="${quizAndAnswerVO.quiz_id}">
	
</div>
<div id="BtnDiv">
	<button id="answerBtn" class="btn btn-secondary">정답 확인</button>
	<button id="nextBtn" class="btn btn-secondary">다음 문제</button>
	<button id="explainBtn" class="btn btn-secondary">해설보기</button>
</div>
