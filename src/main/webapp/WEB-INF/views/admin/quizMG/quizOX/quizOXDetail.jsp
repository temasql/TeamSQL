<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${cp}/resources/quiz/css/quizMainCss.css"  rel="stylesheet">
<script src="/resources/quiz/js/quizList.js"></script>

<div id="quizHeader">${quizName} 문제</div>
<div id="gruopDiv">
	<form id="insertOXDB" action="/updateOX" method="post" >
		<div id="question">문제 : ${quizAndAnswerVO.quiz_question}</div>
		<div id="answer">답 : ${quizAndAnswerVO.quiz_answer}</div>
		<div id="explain">해설 : ${quizAndAnswerVO.quiz_explain}</div>
		
		<input type="hidden" id="quiz_right" name="quiz_right" value="${quiz_right}">
	</form>

	<div>
		<input type="button" id="updateOXBtn" value="퀴즈 수정" class="btn btn-secondary" style="margin-left: 84%;">
		<input type="button" id="deleteOXBtn" value="삭제" class="btn btn-secondary" style="display: inline-block; margin-top: 2%;">
		
		<!-- 퀴즈 수정 버튼 클릭 시 -->
		<c:if test="${quiz_right==01}">
			<form action="/updateMultipleQuiz" id="updateOXFrm">
				<input type="hidden" id="quiz_id" name="quiz_id" value="${quizAndAnswerVO.quiz_id}">
				<input type="hidden" id="quiz_right" name="quiz_right" value="${quiz_right}">
			</form>
		</c:if>
		<c:if test="${quiz_right==02}">
			<form action="/updateOX" id="updateQuizFrm">
				<input type="hidden" id="quiz_id" name="quiz_id" value="${quizAndAnswerVO.quiz_id}">
				<input type="hidden" id="quiz_right" name="quiz_right" value="${quiz_right}">
			</form>
		</c:if>
		<c:if test="${quiz_right==03}">
			<form action="/updateQuiz" id="updateQuizFrm">
				<input type="hidden" id="quiz_id" name="quiz_id" value="${quizAndAnswerVO.quiz_id}">
				<input type="hidden" id="quiz_right" name="quiz_right" value="${quiz_right}">
			</form>
		</c:if>
		<c:if test="${quiz_right==04}">
			<form action="/updateQuiz" id="updateQuizFrm">
				<input type="hidden" id="quiz_id" name="quiz_id" value="${quizAndAnswerVO.quiz_id}">
				<input type="hidden" id="quiz_right" name="quiz_right" value="${quiz_right}">
			</form>
		</c:if>
		
		<!-- 퀴즈 삭제 버튼 클릭 시 -->
		<form id = "deleteQuizFrm" action="/deleteQuiz" method="post" style="display:inline-block;">
			<input type="hidden" id="quiz_id" name="quiz_id" value="${quizAndAnswerVO.quiz_id}">
			<input type="hidden" id="quiz_right" name="quiz_right" value="${quiz_right}">
		</form>
	</div>	
</div>
