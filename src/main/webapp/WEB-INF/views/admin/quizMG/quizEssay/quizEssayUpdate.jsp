<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/resources/quiz/css/quizMainCss.css" rel="stylesheet">
<script src="/resources/quiz/js/quizList.js"></script>

<form id="updateDB" action="/updateEssay" method="post" >
	<p id="quizImg">${quizName} 수정</p>
	<div class="card border-dark mb-3" style="width:986px; margin-left: 14%; margin-top: 3%;">
		<div class="card-header">
			<div id="question">문제 :&nbsp;&nbsp;<input class="form-control" id="question" 
			name="quiz_question" type="text" value="${quizAndAnswerVO.quiz_question}"></div>
		</div>
		<div class="card-body">
			<div id="answer">답 : &nbsp;&nbsp;
			</div>
				<textarea class="form-control" id="disabledInput" name="quiz_answer">${quizAndAnswerVO.quiz_answer}</textarea>
			<div id="explain">해설 : &nbsp;&nbsp;<textarea class="form-control" id="disabledInput" name="quiz_explain">${quizAndAnswerVO.quiz_explain}</textarea></div>
		</div>
	</div>
	
<%-- 	<div id="question">문제 :<input class="form-control" id="question" name="quiz_question" type="text" value="${quizAndAnswerVO.quiz_question}"></div> --%>
<%-- 	<div id="answer">답 : <textarea class="form-control" id="disabledInput" name="quiz_answer">${quizAndAnswerVO.quiz_answer}</textarea></div> --%>
<%-- 	<div id="explain">해설 : <textarea class="form-control" id="disabledInput" name="quiz_explain">${quizAndAnswerVO.quiz_explain}</textarea></div> --%>
	
	<input type="hidden" id="quiz_id" name="quiz_id" value="${quizAndAnswerVO.quiz_id}">
	<input type="hidden" id="quiz_right" name="quiz_right" value="${quiz_right}">
</form>
<input type="button" id="updateBtnDB" value="퀴즈 수정" class="btn btn-secondary" style="background: black; color: white;">
