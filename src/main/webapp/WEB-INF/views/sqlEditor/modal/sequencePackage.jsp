<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="${cp}/resources/sqlEditor/css/rightClickSequence.css" rel="stylesheet">
<link href="${cp}/resources/sqlEditor/css/rightClickSequencePackage.css" rel="stylesheet">
<!-- createSequence modal -->
<div id="craeteSequenceModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content" id="seqModal">
    <span class="close">&times;</span>   
	<form action="${cp}/sqlEditor/createSequence" method="post" id="createSequenceFrm">
	  <fieldset>
	    <legend id="seqLegend">시퀀스 생성</legend>
			<div class="form-group">
			<label id="seqSchema"></label>
		    <input type="text" class="form-control" id="sequenceName" name="sequence_Name" placeholder="시퀀스명">
		    <small class="form-text text-muted" id="tableNameHint">영문으로 시작하여 특수문자(#,$,_)포함 3~9글자 사이입니다.</small>
		   	</div>
			<input type="text" class="form-control"  placeholder="시퀀스 이름">
			<label id="seqSchema">속성</label>
			<input type="number" class="form-control" placeholder="다음으로 시작">
			<input type="number" class="form-control" placeholder="증분">
			<input type="number" class="form-control" placeholder="최소값">
			<input type="number" class="form-control" placeholder="최대값">
			
			<label for="from-control">캐시</label>
		    <select class="form-control" id="selectCache" >
		    	<option>지정되지 않음</option>
		    	<option>캐시</option>
		        <option>캐시없음</option>
		    </select>
		    <input type="text" class="form-control" placeholder="캐시값">
		    
		    <label for="">주기</label>
		    <select class="form-control" id="selectCycle" >
		    	<option>지정되지 않음</option>
		    	<option>주기</option>
		        <option>주기없음</option>
		    </select>
		    
		    <label for="from-control">정렬</label>
		    <select class="form-control" id="selectOrder" >
		    	<option>지정되지 않음</option>
		    	<option>정렬</option>
		        <option>정렬없음</option>
		    </select>
		    
			<br>
			<button type="button" class="btn btn-secondary" id="createSeqBtn">확인</button>
			
		</fieldset>
	</form>
  </div>
</div>

<script src="${cp}/resources/sqlEditor/js/rightClickSequence.js"></script>
<script src="${cp}/resources/sqlEditor/js/rightClickSequencePackage.js"></script>
<script src="${cp}/resources/sqlEditor/js/sequenceManager.js"></script>