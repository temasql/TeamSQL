<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/resources/quiz/css/quizMainCss.css" rel="stylesheet">
<script src="/resources/quiz/js/quizList.js"></script>

<form id="insertOXDB" action="/insertQuiz" method="post" >
	<p id="quizImg">단답식 퀴즈 추가</p>
	<div class="card border-dark mb-3"
		style="width:986px; margin-left: 14%; margin-top: 3%;">
		<div class="card-header">
			<div id="question">문제 :&nbsp;&nbsp;<input class="form-control" id="question" name="quiz_question" type="text"></div>
		</div>
		<div class="card-body">
			<div id="answer"style="margin-left: 2%;">답 : &nbsp;&nbsp;<input class="form-control" type="text" name="quiz_answer"></div>
			<div id="explain">해설 : <textarea class="form-control" id="disabledInput" name="quiz_explain"style="padding-top: 4%;"></textarea></div>
		</div>
	</div>
		
	<input type="hidden" id="quiz_right" name="quiz_right" value="${quiz_right}">
	<input type="button" id="insertBtn" value="퀴즈 추가" class="btn btn-secondary" style="background: black; color: white;margin-left: 83%;">
</form>
