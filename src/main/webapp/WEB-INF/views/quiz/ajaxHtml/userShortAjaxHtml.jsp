<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${cp}/resources/quiz/css/quizMainCss.css"  rel="stylesheet">
<link href="${cp}/resources/quiz/css/userQuizCss.css" rel="stylesheet">
<link href="${cp}/resources/quiz/css/userShortQuizCss.css" rel="stylesheet">
<script src="/resources/quiz/js/quizList.js"></script>
<script src="${cp}/resources/quiz/js/userShortQuiz.js"></script>

<p id="quizImg">단답식 퀴즈</p>

<div class="card border-dark mb-3" style="width:986px; margin-left: 14%; margin-top: 3%;">
  <div class="card-header">
  	<div id="question">
  		<h4 class="card-title question">문제 : ${quizAndAnswerVO.quiz_question}</h4>
  	</div>
  </div>
  <div class="card-body">
    <div id="answer">
    	<p class="card-text">답 : <input id="quiz_answer" type="text" class="form-control" autofocus="autofocus"></p>
    </div>
    <div id="explain">
    	<p class="card-text">해설 : ${quizAndAnswerVO.quiz_explain}</p>
    </div>
  </div>
</div>

<input type="hidden" id="hiddenAnswer" value="${quizAndAnswerVO.quiz_answer}">
<input type="hidden" id="quiz_right" name="quiz_right" value="${quizAndAnswerVO.quiz_right}">
<input type="hidden" id="quiz_id" value="${quizAndAnswerVO.quiz_id}">

<div id="BtnDiv">
	<button id="answerBtn" class="btn btn-secondary" style="background: black; color: white;">정답 확인</button>
	<button id="nextBtn" class="btn btn-secondary" style="background: black; color: white;">다음 문제</button>
	<button id="explainBtn" class="btn btn-secondary" style="background: black; color: white;">해설보기</button>
</div>