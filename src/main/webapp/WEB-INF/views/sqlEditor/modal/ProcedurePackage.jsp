<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="${cp}/resources/sqlEditor/css/rightClickProcedurePackage.css" rel="stylesheet">
<link href="${cp}/resources/sqlEditor/css/rightClickProcedure.css" rel="stylesheet">

<!-- 함수 패키지 우클릭 -->
<ul class="procedurePackageMenu">
  	<li><span id="createProcedureSpan">프로시저 생성</span></li>
</ul>

<!-- 함수 우클릭 -->
<ul class="procedureMenu">
  	<li><span id="readProcedureSpan">프로시저 조회</span></li>
  	<li><span id="deleteProcedureSpan">프로시저 삭제</span></li>
</ul>

<!-- 함수 패키지 우클릭 모달 -->
<div id="procedurePackageModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
  	<span class="close">&times;</span>
  	<br><br>
	<form id="createProcedureFrm">
		<fieldset>
			<legend>프로시저 생성</legend>
			<input type="hidden" id="procedureAcc" name="account_id"/>
			<br><br>
			<label for="exampleInputEmail1">스키마</label>
			<input type="text" id="procedureSchema" class="form-control" readonly>
			<br><br>
			<label for="exampleInputEmail1">이름</label>
			<input type="text" class="form-control" id="procedure_name" name="procedure_name" placeholder="프로시저 이름">
			<br><br>
			<label for="exampleInputEmail1">매개변수</label>
			<img class="imgBtn" id="addImg2" src="${cp}/resources/img/add.png">&nbsp;&nbsp;
			<img class="deleteBtn" id="deleteImg2" src="${cp}/resources/img/delete.png">
			<table class="table table-hover">
				<colgroup>
					<col width="25%">
					<col width="25%">
					<col width="25%">
					<col width="25%">
				</colgroup>
				<thead>
					<tr>
						<th scope="col">이름</th>
						<th scope="col">모드</th>
						<th scope="col">데이터 유형</th>
						<th scope="col">기본값</th>
					</tr>
				</thead>
				<tbody id="tableBody2"></tbody>
			</table>
			<br><br>
			<input type="hidden" id="procedureHidden"/>
			<button type="button" id="createProcedureBtn" class="btn" style="background: black; color: white;">생성</button>
		</fieldset>
	</form>
  </div>
</div>

<div id="readProcedureModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
  	<span class="close">&times;</span>
  	<br><br>
  	<input type="hidden" id="procedureName">
  	<input type="hidden" id="procedureId">
  	<select id="readProcedureSelect" class="form-control">
  		<option id="selectCode3">코드</option>
		<option>세부정보</option>
  	</select>
  	<div id="readProcedureDiv"></div>
  </div>
</div>

<script src="${cp}/resources/sqlEditor/js/rightClickProcedurePackage.js"></script>
<script src="${cp}/resources/sqlEditor/js/rightClickProcedure.js"></script>
<script src="${cp}/resources/sqlEditor/js/procedureManager.js"></script>