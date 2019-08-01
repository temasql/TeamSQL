<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="${cp}/resources/sqlEditor/css/rightClickSequence.css" rel="stylesheet">
<link href="${cp}/resources/sqlEditor/css/rightClickSequencePackage.css" rel="stylesheet">
<!-- createSequence modal -->
<div id="craeteSequenceModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
    <span class="close">&times;</span>   
    <br><br>                  
	<form action="${cp}/sqlEditor/createSequence" method="post" id="createSequenceFrm">
	  <fieldset>
	    <legend>시퀀스 생성</legend>
			<br><br>
			
			<div class="form-group">
		    <input type="text" class="form-control" id="sequenceName" name="sequence_Name" placeholder="시퀀스명">
		    <small class="form-text text-muted" id="tableNameHint">영문으로 시작하여 특수문자(#,$,_)포함 3~9글자 사이입니다.</small>
		   	</div>
			<br><br>
			<input type="text" class="form-control" placeholder="시퀀스 이름">
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

<script src="${cp}/resources/sqlEditor/js/rightClickSequence.js"></script>
<script src="${cp}/resources/sqlEditor/js/rightClickSequencePackage.js"></script>
<script src="${cp}/resources/sqlEditor/js/createSequence.js"></script>