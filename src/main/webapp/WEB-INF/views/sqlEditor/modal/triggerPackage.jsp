<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="${cp}/resources/sqlEditor/css/rightClickTriggerPackage.css" rel="stylesheet">
<link href="${cp}/resources/sqlEditor/css/rightClickTrigger.css" rel="stylesheet">

<!-- 트리거 패키지 우클릭 모달 -->
<div id="triggerPackageModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
  	<span class="close">&times;</span>
  	<br><br>
	<form id="createTriggerFrm">
		<fieldset>
			<input type="hidden" id="schema_id" name="schema_id"/>
			<legend>트리거 생성</legend>
			<br><br>
			<label for="exampleInputEmail1">스키마</label>
			<input type="text" id="schemaName" class="form-control" readonly>
			<br><br>
			<label for="exampleInputEmail1">이름</label>
			<input type="text" id="triggerName" name="trigger_name" class="form-control" placeholder="트리거 이름">
			<br><br>
			<label for="exampleInputEmail1">기본 유형</label>
			<input type="text" class="form-control" value="TABLE" readonly>
			<br><br>
			<label for="exampleInputEmail1">기본 객체</label>
		    <select class="form-control" id="tableSelect" name="table_name"></select>
			<br><br>
			<label for="exampleInputEmail1">타이밍</label>
		    <select class="form-control" id="timingSelect" name="timing">
		    	<option>BEFORE</option>
		        <option>AFTER</option>
		    </select>
			<br><br>
			<label for="exampleInputEmail1">이벤트</label>
			 <select multiple class="form-control" id="eventSelect" name="event">
		        <option class="events">DELETE</option>
		        <option class="events">INSERT</option>
		        <option class="events">UPDATE</option>
		   	</select>
			<br><br>
			<label for="exampleInputEmail1">열</label>
			<select multiple class="form-control" id="columnSelect" name="column" disabled="disabled"></select>
			<br><br>
			<button type="button" id="createTriggerBtn" class="btn btn-secondary">생성</button>
		</fieldset>
	</form>
  </div>
</div>

<!-- 트리거 조회 우클릭 모달 -->
<div id="readTriggerModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
  	<span class="close">&times;</span>
  	<br><br>
  	<input type="hidden" id="triggerName">
  	<input type="hidden" id="triggerId">
  	<select id="readTriggerSelect" class="form-control">
  		<option id="selectCode">코드</option>
		<option>세부정보</option>
  	</select>
  	<div id="readTriggerDiv"></div>
  </div>
</div>

<!-- 트리거 우클릭 -->
<ul class="triggerMenu">
  	<li><span id="readTriggerSpan">트리거 조회</span></li>
  	<li><span id="deleteTriggerSpan">트리거 삭제</span></li>
</ul>

<script src="${cp}/resources/sqlEditor/js/rightClickTriggerPackage.js"></script>
<script src="${cp}/resources/sqlEditor/js/rightClickTrigger.js"></script>
<script src="${cp}/resources/sqlEditor/js/triggerManager.js"></script>