<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="/resources/quiz/css/quizMainCss.css" rel="stylesheet">
<script src="/resources/quiz/js/quizList.js"></script>

<section>
	<!-- 객관식 문제는 화면이 달라야해서 다른 메서드로 호출 -->
	<form id="multipleFrm" action="/updateMultipleQuiz" method="post">
		<p id="quizImg">객관식 퀴즈 수정</p>
		<div class="card border-dark mb-3" style="width:986px; margin-left: 14%; margin-top: 3%;">
			<div class="card-header">
				<div id="question" style="display:block; margin-left: 2%">
					<h4 class="card-title question" >문제 :<input class="form-control" id="question" name="quiz_question" type="text" value="${quizAndAnswerVO.quiz_question}" autofocus="autofocus"  style="display:inline;"></h4>
				</div>
			</div>
			<div class="card-body">
				<div id="multiple">
					객관식
					<ol>
						<li>&nbsp;&nbsp;
							<div id="quiz_multiple1" class="custom-control custom-radio">
						      <input name="example_content" class="custom-control-label form-control" value="${exampleList[0].example_content}"/>
						    </div>
						</li>
						<li>&nbsp;&nbsp;
							<div id="quiz_multiple2" class="custom-control custom-radio">
					      		<input name="example_content" class="custom-control-label form-control" value="${exampleList[1].example_content}"/>
						    </div>
						</li>
						<li>&nbsp;&nbsp;
							 <div id="quiz_multiple3" class="custom-control custom-radio">
					     		<input name="example_content" class="custom-control-label form-control" value="${exampleList[2].example_content}"/>
						    </div>
						</li>
						<li>&nbsp;&nbsp;
							<div id="quiz_multiple4" class="custom-control custom-radio">
					      		<input name="example_content" class="custom-control-label form-control" value="${exampleList[3].example_content}"/>
						    </div>
						</li> 
<!-- 						<li>&nbsp;&nbsp; -->
<!-- 							<div id="quiz_multiple1" class="custom-control custom-radio"> -->
<%-- 					      	  <input name="example_content" class="custom-control-label form-control" value="${exampleList[4].example_content}"/> --%>
<!-- 						    </div> -->
<!-- 						</li> -->
					</ol>
				</div>
			</div>
			<div id="answer" style="width: 50px; margin-left: 2%;">답 :<input type="text" id="quiz_answer"  name="quiz_answer" 
					class="form-control" maxlength="1" oninput="numberMaxLength(this);"
					onKeyup="this.value=this.value.replace(/[^1-5]/g,'');" value="${quizAndAnswerVO.quiz_answer}"/>
			</div>
			<div id="explain">해설 : <textarea class="form-control explanMultiple" id="disabledInput" name="quiz_explain" style="margin-bottom: 10px; margin-left: 5px; padding-top: 4%;">${quizAndAnswerVO.quiz_explain}</textarea>
			</div>
			<input type="hidden" id="quiz_right" name="quiz_right" value="${quiz_right}">
			<input type="hidden" id="quiz_id" name="quiz_id" value="${quizAndAnswerVO.quiz_id}">
		</div>
		
	</form>
	<div id="multipleBtn">
		<input type="button" id="multipleUpdateBtn" value="퀴즈 수정" class="btn btn-secondary" style="background: black; color: white;">
	</div>
</section>
