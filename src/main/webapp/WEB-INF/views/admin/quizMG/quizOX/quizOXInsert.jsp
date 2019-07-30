<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/resources/quiz/css/quizMainCss.css" rel="stylesheet">
<script src="/resources/quiz/js/quizList.js"></script>


<div id="quizHeader">OX퀴즈 추가</div>
<div id="gruopDiv">
	<form id="insertOXDB" action="/insertQuiz" method="post" >
		<div id="question">문제 :<input class="form-control" id="question" name="quiz_question" type="text"></div>
		<div id="answer">답 : <input type="radio" name="quiz_answer" value="O" checked/>&nbsp;O &nbsp;&nbsp;<input type="radio" name="quiz_answer" value="X"/>&nbsp;X</div>
		<div id="explain">해설 : <textarea class="form-control" id="disabledInput" name="quiz_explain"></textarea></div>
		
		<input type="hidden" id="quiz_right" name="quiz_right" value="${quiz_right}">
		<input type="button" id="insertOXBtn" value="퀴즈 추가" class="btn btn-secondary">
	</form>
</div>
