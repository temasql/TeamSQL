<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<link href="${cp}/resources/quiz/css/quizAdminList.css"  rel="stylesheet">
<script src="${cp}/resources/quiz/js/quizList.js"></script>

<div id="quizAdminHeader">${quizName} 리스트</div>

<table class="table table-hover" style="width:50%; margin:0px auto; margin-top:3%">
  <thead>
    <tr>
      <th scope="col" style="width:5%;">No.</th>
      <th scope="col" style="width:50%; text-align: center">문제</th>
    </tr>
  </thead>
  <tbody id="tableQuizList">
    
  </tbody>
</table> 

<div>
	<ul style="width:50%; margin:0px auto;" class="pagination">
	
	</ul>
</div>

<div id="addBtnDiv">
	<form id="insertFrm" action="/insertOX" method="get">
		<button id="insertOXBtn" type="submit" class="btn btn-secondary" value="${quiz_right}">퀴즈 문제 추가</button>
	</form>
</div>

<input type="hidden" id="quiz_right" value="${quiz_right}"/>