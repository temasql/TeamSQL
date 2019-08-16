<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${cp}/resources/quiz/css/quizMainCss.css"  rel="stylesheet">
<link href="${cp}/resources/quiz/css/userQuizCss.css" rel="stylesheet">
<link href="${cp}/resources/quiz/css/userMultipleQuizCss.css" rel="stylesheet">
<script src="${cp}/resources/quiz/js/quizList.js"></script>
<script src="${cp}/resources/quiz/js/userMultipleQuiz.js"></script>

<div id="question">문제 : ${quizAndAnswerVO.quiz_question}</div>
<div id="example">
	<ol>
		<c:forEach items="${quizExampleList}" var="exampleVO">
			<li>
				<div class="custom-control custom-radio" style="display:contents;">
			      <label class="example_content" for="customRadio1">${exampleVO.example_content}</label>
			      <input type="hidden" value="${exampleVO.example_num}">
			      <input type="hidden" name="example_content" value="${exampleVO.example_content}">
			    </div>
		    </li>
		</c:forEach>
	</ol>
</div>
<div id="answer">답 : <input type="text" id="quiz_answer"  name="quiz_answer" 
					class="form-control" maxlength="1" oninput="numberMaxLength(this);"
					onKeyup="this.value=this.value.replace(/[^1-5]/g,'');">
					
					<input id="hiddenAnswer" type="text" value="${quizAndAnswerVO.quiz_answer}">
</div>
<div id="explain">해설 : ${quizAndAnswerVO.quiz_explain}</div>

<input type="hidden" id="quiz_right" name="quiz_right" value="${quizAndAnswerVO.quiz_right}">
<input type="hidden" id="quiz_id" value="${quizAndAnswerVO.quiz_id}">
<input type="hidden" id="msg"	value="${msg}">

<div id="BtnDiv">
	<button id="answerBtn" class="btn btn-secondary">정답 확인</button>
	<button id="nextBtn" class="btn btn-secondary">다음 문제</button>
	<button id="explainBtn" class="btn btn-secondary">해설보기</button>
</div>
