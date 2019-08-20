<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="/resources/quiz/css/quizMainCss.css" rel="stylesheet">
<script src="/resources/quiz/js/quizList.js"></script>

<form id="insertOXDB" action="/insertEssay" method="post">
	<p id="quizImg">주관식 퀴즈 추가</p>
	<div class="card border-dark mb-3"
		style="width:986px; margin-left: 14%; margin-top: 3%;">
		<div class="card-header">
			<div id="question">
				<h4 class="card-title question">문제 : </h4><input class="form-control" id="question" name="quiz_question" type="text">
			</div>
		</div>
		<div class="card-body">
			<div id="answer">
				<p class="card-text">답 : </p><textarea class="form-control" id="disabledInput" name="quiz_answer"></textarea>
			</div>
			<div id="explain" style="display:inline;">
				<p class="card-text">해설 : </p><textarea class="form-control" id="disabledInput" name="quiz_explain"></textarea>
			</div>
		</div>
	</div>

<!-- 		<div id="question"> -->
<!-- 			문제 :<input class="form-control" id="question" name="quiz_question" type="text"> -->
<!-- 		</div> -->
<!-- 		<div id="answer"> -->
<!-- 			답 : <textarea class="form-control" id="disabledInput" name="quiz_answer"></textarea> -->
<!-- 		</div> -->
<!-- 		<div id="explain"> -->
<!-- 			해설 : <textarea class="form-control" id="disabledInput" name="quiz_explain"></textarea> -->
<!-- 		</div> -->

	<input type="hidden" id="quiz_right" name="quiz_right"value="${quiz_right}">
	<input type="button" id="insertBtn" value="퀴즈 추가"
	class="btn btn-secondary"style="background: black; color: white; margin-left: 83%;">
</form>
