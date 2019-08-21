<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${cp}/resources/quiz/css/quizMainCss.css"  rel="stylesheet">
<link href="${cp}/resources/quiz/css/userQuizCss.css" rel="stylesheet">
<link href="${cp}/resources/quiz/css/userMultipleQuizCss.css" rel="stylesheet">
<script src="${cp}/resources/quiz/js/quizList.js"></script>
<script src="${cp}/resources/quiz/js/userMultipleQuiz.js"></script>

	<p id="quizImg">객관식 퀴즈 문제</p>
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
								<label class="example_content" for="customRadio1">${quizExampleList[0].example_content}</label>
								<input type="hidden" value="${quizExampleList[0].example_num}">
								<input type="hidden" name="example_content"
									value="${quizExampleList[0].example_content}">
							</div>
						</li>
						<li class="multiList">
							<div class="custom-control custom-radio"
								style="display: contents;">
								<label class="example_content" for="customRadio2">${quizExampleList[1].example_content}</label>
								<input type="hidden" value="${quizExampleList[1].example_num}">
								<input type="hidden" name="example_content"
									value="${quizExampleList[1].example_content}">
							</div>
						</li>
						<li class="multiList">
							<div class="custom-control custom-radio"
								style="display: contents;">
								<label class="example_content">${quizExampleList[2].example_content}</label>
								<input type="hidden" value="${quizExampleList[2].example_num}">
								<input type="hidden" name="example_content"
									value="${quizExampleList[2].example_content}">
							</div>
						</li>
						<li class="multiList">
							<div class="custom-control custom-radio"
								style="display: contents;">
								<label class="example_content" for="customRadio4">${quizExampleList[3].example_content}</label>
								<input type="hidden" value="${quizExampleList[3].example_num}">
								<input type="hidden" name="example_content"
									value="${quizExampleList[3].example_content}">
							</div>
						</li>
<!-- 						<li class="multiList"> -->
<!-- 							<div class="custom-control custom-radio" -->
<!-- 								style="display: contents;"> -->
<%-- 								<label class="example_content" for="customRadio5">${quizExampleList[4].example_content}</label> --%>
<%-- 								<input type="hidden" value="${quizExampleList[4].example_num}"> --%>
<!-- 								<input type="hidden" name="example_content" -->
<%-- 									value="${quizExampleList[4].example_content}"> --%>
<!-- 							</div> -->
<!-- 						</li> -->
					</ol>
				</div>
				<div id="answer"><div class="card-text"><input type="hidden" id="quiz_answer"  name="quiz_answer" 
							class="form-control" maxlength="1" oninput="numberMaxLength(this);"
							onKeyup="this.value=this.value.replace(/[^1-5]/g,'');">
							
							<input id="hiddenAnswer" type="hidden" value="${quizAndAnswerVO.quiz_answer}"></div></div>
				<div id="explain"><p class="card-text">해설 : ${quizAndAnswerVO.quiz_explain}</p></div>
				<input type="hidden" id="quiz_right" name="quiz_right" value="${quizAndAnswerVO.quiz_right}">
				<input type="hidden" id="quiz_id" value="${quizAndAnswerVO.quiz_id}">
			</div>
		</div>

<div id="BtnDiv">
	<button id="answerBtn" class="btn btn-secondary" style="background: black; color: white;">정답 확인</button>
	<button id="nextBtn" class="btn btn-secondary" style="background: black; color: white;">다음 문제</button>
	<button id="explainBtn" class="btn btn-secondary" style="background: black; color: white;">해설보기</button>
</div>
