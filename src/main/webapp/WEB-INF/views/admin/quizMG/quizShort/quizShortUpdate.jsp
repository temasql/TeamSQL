<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/resources/quiz/css/quizMainCss.css" rel="stylesheet">
<script src="/resources/quiz/js/quizList.js"></script>


<div id="quizHeader">${quizName} 수정</div>
<div id="gruopDiv">
	<form id="updateDB" action="/updateQuiz" method="post" >
		<div id="question">문제 :<input class="form-control" id="question" name="quiz_question" type="text" value="${quizAndAnswerVO.quiz_question}"></div>
		<div id="answer">답 : <input type="text" class="form-control" name="quiz_answer" value="${quizAndAnswerVO.quiz_answer}"/></div>
		<div id="explain">해설 : <textarea class="form-control" id="disabledInput" name="quiz_explain">${quizAndAnswerVO.quiz_explain}</textarea></div>
		
		<input type="hidden" id="quiz_id" name="quiz_id" value="${quizAndAnswerVO.quiz_id}">
		<input type="hidden" id="quiz_right" name="quiz_right" value="${quiz_right}">
		<input type="button" id="updateBtnDB" value="퀴즈 수정" class="btn btn-secondary">
	</form>
</div>
