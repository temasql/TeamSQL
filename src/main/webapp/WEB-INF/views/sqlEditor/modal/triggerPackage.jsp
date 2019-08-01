<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="${cp}/resources/sqlEditor/css/rightClickTriggerPackage.css" rel="stylesheet">

<!-- 트리거 패키지 우클릭 모달 -->
<div id="triggerPackageModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
  	<span class="close">&times;</span>
  	<br><br>
	<form action="#" method="post" id="createTriggerFrm">
		<input type="hidden" id="schema_id"/>
		<fieldset>
			<legend>트리거 생성</legend>
			<br><br>
			<label for="exampleInputEmail1">스키마</label>
			<input type="text" id="schemaName" class="form-control" readonly>
			<br><br>
			<label for="exampleInputEmail1">이름</label>
			<input type="text" class="form-control" placeholder="트리거 이름">
			<br><br>
			<label for="exampleInputEmail1">기본 유형</label>
			<input type="text" class="form-control" value="TABLE" readonly>
			<br><br>
			<label for="exampleInputEmail1">기본 객체</label>
		    <select class="form-control" id="tableSelect"></select>
			<br><br>
			<label for="exampleInputEmail1">타이밍</label>
		    <select class="form-control" id="exampleSelect2">
		    	<option>BEFORE</option>
		        <option>AFTER</option>
		    </select>
			<br><br>
			<label for="exampleInputEmail1">이벤트</label>
			 <select multiple class="form-control" id="eventSelect">
		        <option>DELETE</option>
		        <option>INSERT</option>
		        <option>UPDATE</option>
		   	</select>
			<br><br>
			<label for="exampleInputEmail1">열</label>
			<select multiple class="form-control" id="columnSelect" disabled="disabled"></select>
			<br><br>
			<button type="button" class="btn btn-secondary">확인</button>
		</fieldset>
	</form>
  </div>
</div>

<script src="${cp}/resources/sqlEditor/js/rightClickTriggerPackage.js"></script>
<script src="${cp}/resources/sqlEditor/js/triggerManager.js"></script>