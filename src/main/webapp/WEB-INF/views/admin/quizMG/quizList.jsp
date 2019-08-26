<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${cp}/resources/quiz/css/quizAdminList.css"  rel="stylesheet">
<script src="${cp}/resources/quiz/js/quizList.js"></script>


<div id="quizAdminHeader">${quizName} 리스트</div>

<table class="table table-hover" style="width: 100%; margin:0px auto; margin-top:3%; font-size: 20px;">
  <thead>
    <tr>
      <th scope="col" style="width:5%;">No.</th>
      <th scope="col" style="width:50%; text-align: center">문제</th>
      <th scope="col" style="width:7%; text-align: center">퀴즈 작성자</th>
      <th scope="col" style="width:9%; text-align: center">작성 날짜</th>
    </tr>
  </thead>
  <tbody id="tableQuizList">
  </tbody>
</table>
 
<!-- 퀴즈 조회 화면으로 이동 -->
<form id="readOXFrm" action="/readOX">
  <input type="hidden" id="quizId" name="quiz_id">
  <input type="hidden" id="quiz_right" name="quiz_right" value="${quiz_right}">
</form>

<div>
	<ul style="width:50%; margin:0px auto;" class="pagination">
	
	</ul>
</div>
 
<!-- 퀴즈 추가 화면 이동 -->
<div id="addBtnDiv">
	<c:if test="${quiz_right==01}">
		<form id="insertFrm" action="/insertMultiple" method="get">
			<button id="insertBtn" type="submit" class="btn btn-secondary" style="background: black; color: white;">객관식 퀴즈 추가</button>
			<input type="hidden" name="quiz_right" value="${quiz_right}">
		</form>
	</c:if>
	<c:if test="${quiz_right==02}">
		<form id="insertFrm" action="/insertOX" method="get">
			<button id="insertBtn" type="submit" class="btn btn-secondary" style="background: black; color: white;">OX 퀴즈 추가</button>
			<input type="hidden" name="quiz_right" value="${quiz_right}">
		</form>
	</c:if>
	<c:if test="${quiz_right==03}">
		<form action="/insertShort">
			<button id="insertBtn" type="submit" class="btn btn-secondary" style="background: black; color: white;">단답식 퀴즈 추가</button>
			<input type="hidden" name="quiz_right" value="${quiz_right}">
		</form>
	</c:if>
	<c:if test="${quiz_right==04}">
		<form action="/insertEssay">
			<button id="insertBtn" type="submit" class="btn btn-secondary" style="background: black; color: white;">주관식 퀴즈 추가</button>
			<input type="hidden" name="quiz_right" value="${quiz_right}">
		</form>
	</c:if>
</div>

<!-- 퀴즈 구분자 -->
<input type="hidden" id="quiz_right1" name="quiz_right" value="${quiz_right}"/>

<!-- 리스트에서 해당 퀴즈 클릭 시 조회화면으로 이동 -->
<form id="readFrm" action="/readOX" >
	<input type="hidden" id="quiz_id" name="quiz_id">
</form>