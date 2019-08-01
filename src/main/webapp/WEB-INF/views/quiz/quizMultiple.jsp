<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="/resources/quiz/css/quizMainCss.css" rel="stylesheet">
<script src="/resources/quiz/js/quizList.js"></script>
<script src="/resources/quiz/js/quizMultiple.js"></script>


<div id="quizHeader">객관식 퀴즈 추가</div>
<section>
	<div id="gruopDiv">
		<!-- 객관식 문제는 화면이 달라야해서 다른 메서드로 호출 -->
		<form id="insertOXDB" action="/insertMultiple" method="post">
			<div id="question"> 문제 :<input class="form-control" id="question" name="quiz_question" type="text"></div>
			<div id="multiple">
				객관식
				<ul>
					<li>&nbsp;&nbsp;
					<div class="custom-control custom-radio">
				      <input type="radio" id="multiple1" name="customRadio" class="custom-control-input">
				      <label class="custom-control-label" for="multiple1"></label>
				      <input class="custom-control-label form-control"/>
				    </div>
					</li>
					<li>&nbsp;&nbsp;
						<div class="custom-control custom-radio">
					      <input type="radio" id="multiple2" name="customRadio" class="custom-control-input">
					      <label class="custom-control-label" for="multiple2"></label>
				      <input class="custom-control-label form-control"/>
					    </div>
					</li>
					<li>&nbsp;&nbsp;
						 <div class="custom-control custom-radio">
					      <input type="radio" id="multiple3" name="customRadio" class="custom-control-input">
					      <label class="custom-control-label" for="multiple3"></label>
				      <input class="custom-control-label form-control"/>
					    </div>
					</li>
					<li>&nbsp;&nbsp;
						<div class="custom-control custom-radio">
					      <input type="radio" id="multiple4" name="customRadio" class="custom-control-input">
					      <label class="custom-control-label" for="multiple4"></label>
				      <input class="custom-control-label form-control"/>
					    </div>
					</li>
					<li>&nbsp;&nbsp;
						<div class="custom-control custom-radio">
					      <input type="radio" id="multiple5" name="customRadio" class="custom-control-input">
					      <label class="custom-control-label" for="multiple5"></label>
				      	  <input class="custom-control-label form-control"/>
					    </div>
					</li>
				</ul>
			</div>
			
			<div id="answer">
				답 : <input type="text" id="quiz_answer" name="quiz_answer" class="form-control">
			</div>
			<div id="explain">
				해설 : <textarea class="form-control" id="disabledInput" name="quiz_explain"></textarea>
			</div>
	
			<input type="hidden" id="quiz_right" name="quiz_right" value="${quiz_right}">
			<input type="button" id="insertBtn" value="퀴즈 추가" class="btn btn-secondary">
		</form>
	</div>
</section>
