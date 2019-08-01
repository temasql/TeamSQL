<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="${cp}/resources/sqlEditor/css/rightClickTriggerPackage.css" rel="stylesheet">
<!-- 트리거 패키지 우클릭 모달 -->
<div id="triggerPackageModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
  	<span class="close">&times;</span>
  	<br><br>
	<form action="#" method="post" id="createTriggerFrm">
		<fieldset>
			<legend>트리거 생성</legend>
			<br><br>
			<label for="exampleInputEmail1">스키마</label>
			<input type="text" class="form-control" value="계정아이디(슬라이스)" readonly>
			<br><br>
			<label for="exampleInputEmail1">이름</label>
			<input type="text" class="form-control" placeholder="트리거 이름">
			<br><br>
			<label for="exampleInputEmail1">기본 유형</label>
			<input type="text" class="form-control" value="TABLE" readonly>
			<br><br>
			<label for="exampleInputEmail1">기본 객체</label>
		    <select class="form-control" id="exampleSelect1">
		    	<option>테이블명1</option>
		        <option>테이블명2</option>
		        <option>테이블명3</option>
		        <option>테이블명4</option>
		        <option>테이블명5</option>
		    </select>
			<br><br>
			<label for="exampleInputEmail1">타이밍</label>
		    <select class="form-control" id="exampleSelect2">
		    	<option>BEFORE</option>
		        <option>AFTER</option>
		    </select>
			<br><br>
			<label for="exampleInputEmail1">이벤트</label>
			 <select multiple class="form-control" id="exampleSelect3">
		        <option>DELETE</option>
		        <option>INSERT</option>
		        <option>UPDATE</option>
		   	</select>
			<br><br>
			<label for="exampleInputEmail1">이벤트</label>
			 <select multiple class="form-control" id="exampleSelect4">
		        <option>컬럼명1</option>
		        <option>컬럼명2</option>
		        <option>컬럼명3</option>
		   	</select>
			<br><br>
			<button type="button" class="btn btn-secondary">확인</button>
		</fieldset>
	</form>
  </div>
</div>

<script src="${cp}/resources/sqlEditor/js/rightClickTriggerPackage.js"></script>