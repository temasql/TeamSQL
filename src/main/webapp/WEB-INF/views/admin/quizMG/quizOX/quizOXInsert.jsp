<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="/resources/quiz/css/quizMainCss.css" rel="stylesheet">
<script src="/resources/quiz/js/quizList.js"></script>

<!-- <div id="gruopDiv"> -->
	<form id="insertOXDB" action="/insertQuiz" method="post">
		<p id="quizImg">OX퀴즈 문제 추가</p>
		<div class="card border-dark mb-3"
			style="width:986px; margin-left: 14%; margin-top: 3%;">
			<div class="card-header">
				<div id="question">문제 :&nbsp;&nbsp;<input class="form-control" id="question" name="quiz_question" type="text"></div>
			</div>
			<div class="card-body">
				<div id="answer"style="margin-left: 2%;">답 : &nbsp;&nbsp;
					<input type="radio" name="quiz_answer" value="O" checked style="width: 17px;height: 17px;"/>&nbsp;O &nbsp;&nbsp;
					<input type="radio" name="quiz_answer" value="X" style="width: 17px;height: 17px;"/>&nbsp;X
				</div>
				<div id="explain">해설 : <textarea class="form-control" id="disabledInput" name="quiz_explain"style="padding-top: 4%;"></textarea></div>
			</div>
		</div>
		<!-- 		<div id="question">문제 :<input class="form-control" id="question" name="quiz_question" type="text"></div> -->
		<!-- 		<div id="answer">답 : <input type="radio" name="quiz_answer" value="O" checked/>&nbsp;O &nbsp;&nbsp;<input type="radio" name="quiz_answer" value="X"/>&nbsp;X</div> -->
		<!-- 		<div id="explain">해설 : <textarea class="form-control" id="disabledInput" name="quiz_explain"></textarea></div> -->

		<input type="hidden" id="quiz_right" name="quiz_right" value="${quiz_right}">
	</form>
<!-- </div> -->
<div id="btnGroup">
	<input type="button" id="insertBtn" value="퀴즈 추가" class="btn btn-secondary" style="margin-left: 82%;background: black; color: white;">
</div>