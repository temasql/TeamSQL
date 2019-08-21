<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="/resources/quiz/css/quizMainCss.css" rel="stylesheet">
<script src="/resources/quiz/js/quizList.js"></script>
<script>
	$(document).ready(function(){
		$("[name='quiz_question']").css("display", "");
	})
</script>

<!-- 객관식 문제는 화면이 달라야해서 다른 메서드로 호출 -->
<form id="insertMultipleDB" action="/insertMultiple" method="post">
	<p id="quizImg">객관식 문제 등록</p>
		<div class="card border-dark mb-3" style="width:986px; margin-left: 14%; margin-top: 3%;">
			<div class="card-header">
				<div id="question" style="display:inline">
					<h4 class="card-title question" style="padding-top: 0%;">문제 :<input class="form-control" id="multipleQuestion" name="quiz_question"
					style="display:inline; margin-left: 1%;" type="text" value="${quizAndAnswerVO.quiz_question}" autofocus="autofocus"></h4>
				</div>
			</div>
			<div class="card-body">
				<div id="multiple">
					객관식
					<ol>
						<li>&nbsp;&nbsp;
							<div id="multiple1" class="custom-control custom-radio">
						      <input id="quiz_multiple1" name="example_content" class="custom-control-label form-control"/>
						    </div>
						</li>
						<li>&nbsp;&nbsp;
							<div id="multiple2" class="custom-control custom-radio">
					      		<input id="quiz_multiple2" name="example_content" class="custom-control-label form-control"/>
						    </div>
						</li>
						<li>&nbsp;&nbsp;
							 <div id="multiple3" class="custom-control custom-radio">
					     		<input id="quiz_multiple3" name="example_content" class="custom-control-label form-control"/>
						    </div>
						</li>
						<li>&nbsp;&nbsp;
							<div id="multiple4" class="custom-control custom-radio">
					      		<input id="quiz_multiple4" name="example_content" class="custom-control-label form-control"/>
						    </div>
						</li> 
						<li>&nbsp;&nbsp;
							<div id="multiple5" class="custom-control custom-radio">
					      	  <input id="quiz_multiple5" name="example_content" class="custom-control-label form-control"/>
						    </div>
						</li>
					</ol>
				</div>
			</div>
			<div id="answer" style="width: 50px;margin-left: 4%;">답 :<input type="text" id="quiz_answer"  name="quiz_answer" 
					class="form-control" maxlength="1" oninput="numberMaxLength(this);"
					onKeyup="this.value=this.value.replace(/[^1-5]/g,'');" value="${quizAndAnswerVO.quiz_answer}"/>
			</div>
			<div id="explain" style="margin-left: 2%;">
				해설 : <textarea class="form-control explanMultiple" id="disabledInput" name="quiz_explain" style="padding-top: 37px; margin-bottom: 10px; margin-left: 10px;">${quizAndAnswerVO.quiz_explain}</textarea>
			</div>
		</div>
	<input type="hidden" id="quiz_right" name="quiz_right" value="${quiz_right}">
	<input type="button" id="multiInsertBtn" value="퀴즈 추가" class="btn btn-secondary"style="background: black; color: white;">
</form>
