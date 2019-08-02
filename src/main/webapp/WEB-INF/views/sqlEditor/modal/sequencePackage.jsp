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
		    <input type="text" class="form-control" id="seqName" name="seqName" placeholder="시퀀스명">
		    <small class="form-text text-muted" id="tableNameHint">영문으로 시작하여 특수문자(#,$,_)포함 3~9글자 사이입니다.</small>
		   	</div>
			<label id="lblOption">속성</label>
			<input type="text" class="form-control setSeq" id="seqStart" name="seqStart" placeholder="다음으로 시작">
			<input type="text" class="form-control setSeq" id="seqIncrement" name="seqIncrement" placeholder="증분">
			<input type="text" class="form-control setSeq" id="seqMin" name="seqMin" placeholder="최소값">
			<input type="text" class="form-control setSeq" id="seqMax" name="seqMax" placeholder="최대값">
			
			<label for="from-control" id="lblCache">캐시</label>
		    <select class="form-control" id="seqCache" name="seqCache" >
		    	<option value="">지정되지 않음</option>
		    	<option value="CACHE">캐시</option>
		        <option value="NOCACHE">캐시없음</option>
		    </select>
		    <input type="number" class="form-control" id="inputCache" name="inputCache"  placeholder="캐시값" readOnly/>
		    
		    <label for="">주기</label>
		    <select class="form-control" id="seqCycle" name="seqCycle" >
		    	<option value="">지정되지 않음</option>
		    	<option value="CYCLE">주기</option>
		        <option value="">주기없음</option>
		    </select>
		    
		    <label for="from-control">정렬</label>
		    <select class="form-control" id="seqOrder" name="seqOrder" >
		    	<option value="">지정되지 않음</option>
		    	<option value="ORDER">정렬</option>
		        <option value="">정렬없음</option>
		    </select>
		    <input type ="hidden" class="hiddenSequence" name="hiddenSequence" value="CREATE SEQUENCE "/>
		    <input type = "hidden" class="hiddenSequence" name="hiddenStart">
		    <input type = "hidden" class="hiddenSequence" name="hiddenIncrement">
		    <input type = "hidden" class="hiddenSequence" name="hiddenMin">
		    <input type = "hidden" class="hiddenSequence" name="hiddenMax">
		    <input type = "hidden" class="hiddenSequence" name="hiddenCache">
		    <input type = "hidden" class="hiddenSequence" name="hiddenCycle">
		    <input type = "hidden" class="hiddenSequence" name="hiddenOrder">
		    
			<br>
			<button type="button" class="btn btn-secondary" id="createSeqBtn">확인</button>
			
		</fieldset>
	</form>
  </div>
</div>

<script src="${cp}/resources/sqlEditor/js/rightClickSequence.js"></script>
<script src="${cp}/resources/sqlEditor/js/rightClickSequencePackage.js"></script>
<script src="${cp}/resources/sqlEditor/js/sequenceManager.js"></script>