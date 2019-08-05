<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/resources/quiz/css/quizMainCss.css" rel="stylesheet">
<script src="/resources/quiz/js/quizList.js"></script>

<div id="quizHeader">주관식 퀴즈</div>
<div id="gruopDiv">
	<form id="insertOXDB" action="/insertEssay" method="post" >
		<div id="question">문제 :<input class="form-control" id="question" name="quiz_question" type="text"></div>
		<div id="answer">답 : <textarea class="form-control" id="disabledInput" name="quiz_answer"></textarea></div>
		<div id="explain">해설 : <textarea class="form-control" id="disabledInput" name="quiz_explain"></textarea></div>
			
		<input type="hidden" id="quiz_right" name="quiz_right" value="${quiz_right}">
		<input type="button" id="insertBtn" value="퀴즈 추가" class="btn btn-secondary">
	</form>
</div>
