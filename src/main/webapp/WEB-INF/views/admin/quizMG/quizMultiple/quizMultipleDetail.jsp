<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${cp}/resources/quiz/css/quizMainCss.css" rel="stylesheet">
<script src="/resources/quiz/js/quizList.js"></script>

	<form id="insertOXDB" action="/updateOX" method="post">
		<p id="quizImg">${quizName}문제</p>
		<div class="card border-dark mb-3" style="width:986px; margin-left: 14%; margin-top: 3%;">
			<div class="card-header">
				<div id="question">
					<h4 class="card-title question">문제 : ${quizAndAnswerVO.quiz_question}</h4>
				</div>
			</div>
			<div class="card-body">
				<div id="example">
					<ol>
						<li class="multiList">
							<div class="custom-control custom-radio"
								style="display: contents;">
								<label class="example_content" for="customRadio1">${exampleList[0].example_content}</label>
								<input type="hidden" value="${exampleList[0].example_num}">
								<input type="hidden" name="example_content"
									value="${exampleList[0].example_content}">
							</div>
						</li>
						<li class="multiList">
							<div class="custom-control custom-radio"
								style="display: contents;">
								<label class="example_content" for="customRadio2">${exampleList[1].example_content}</label>
								<input type="hidden" value="${exampleList[1].example_num}">
								<input type="hidden" name="example_content"
									value="${exampleList[1].example_content}">
							</div>
						</li>
						<li class="multiList">
							<div class="custom-control custom-radio"
								style="display: contents;">
								<label class="example_content">${exampleList[2].example_content}</label>
								<input type="hidden" value="${exampleList[2].example_num}">
								<input type="hidden" name="example_content"
									value="${exampleList[2].example_content}">
							</div>
						</li>
						<li class="multiList">
							<div class="custom-control custom-radio"
								style="display: contents;">
								<label class="example_content" for="customRadio4">${exampleList[3].example_content}</label>
								<input type="hidden" value="${exampleList[3].example_num}">
								<input type="hidden" name="example_content"
									value="${exampleList[3].example_content}">
							</div>
						</li>
						<li class="multiList">
							<div class="custom-control custom-radio"
								style="display: contents;">
								<label class="example_content" for="customRadio5">${exampleList[4].example_content}</label>
								<input type="hidden" value="${exampleList[4].example_num}">
								<input type="hidden" name="example_content"
									value="${exampleList[4].example_content}">
							</div>
						</li>
					</ol>
				</div>
				<div id="answer"><div class="card-text"> 답 : ${quizAndAnswerVO.quiz_answer}</div></div>
				<div id="explain"><p class="card-text">해설 : ${quizAndAnswerVO.quiz_explain}</p></div>
			</div>
		</div>

		<input type="hidden" id="quiz_right" name="quiz_right"
			value="${quiz_right}">
	</form>

<div class="btnGroup">
	<input type="button" id="updateOXBtn" value="퀴즈 수정"
		class="btn btn-secondary" style="background: black; color: white;">
	<input type="button" id="deleteOXBtn" value="삭제"
		class="btn btn-secondary"
		style="display: inline-block; background: black; color: white;">

	<!-- 퀴즈 수정 버튼 클릭 시 -->
	<c:if test="${quiz_right==01}">
		<form action="/updateMultipleQuiz" id="updateOXFrm">
			<input type="hidden" id="quiz_id" name="quiz_id"
				value="${quizAndAnswerVO.quiz_id}"
				style="background: black; color: white;"> <input
				type="hidden" id="quiz_right" name="quiz_right"
				value="${quiz_right}" style="background: black; color: white;">
		</form>
	</c:if>

	<!-- 퀴즈 삭제 버튼 클릭 시 -->
	<form id="deleteQuizFrm" action="/deleteQuiz" method="post"
		style="display: inline-block;">
		<input type="hidden" id="quiz_id" name="quiz_id"
			value="${quizAndAnswerVO.quiz_id}"
			style="background: black; color: white;"> <input
			type="hidden" id="quiz_right" name="quiz_right" value="${quiz_right}"
			style="background: black; color: white;">
	</form>
</div>