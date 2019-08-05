<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="${cp}/resources/sqlEditor/css/rightClickFucntionPackage.css" rel="stylesheet">
<link href="${cp}/resources/sqlEditor/css/rightClickFunction.css" rel="stylesheet">

<!-- 함수 패키지 우클릭 -->
<ul class="functionPackageMenu">
  	<li><span id="createFunctionSpan">함수 생성</span></li>
</ul>

<!-- 함수 우클릭 -->
<ul class="functionMenu">
  	<li><span id="readFunctionSpan">함수 조회</span></li>
  	<li><span id="deleteFunctionSpan">함수 삭제</span></li>
</ul>

<!-- 함수 패키지 우클릭 모달 -->
<div id="fucntionPackageModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
  	<span class="close">&times;</span>
  	<br><br>
	<form id="createFunctionFrm">
		<fieldset>
			<legend>함수 생성</legend>
			<input type="hidden" id="functionAcc" name="account_id"/>
			<br><br>
			<label for="exampleInputEmail1">스키마</label>
			<input type="text" id="functionSchema" class="form-control" readonly>
			<br><br>
			<label for="exampleInputEmail1">이름</label>
			<input type="text" class="form-control" id="function_name" name="function_name" placeholder="함수 이름">
			<br><br>
			<label for="exampleInputEmail1">반환 유형</label>
		    <select class="form-control" name="returnType">
		    	<option>VARCHAR2</option>
		        <option>NUMBER</option>
		        <option>DATE</option>
		        <option>CLOB</option>
		        <option>BLOB</option>
		    </select>
			<br><br>
			<label for="exampleInputEmail1">매개변수</label>
			<img class="imgBtn" id="addImg" src="${cp}/resources/img/add.png">&nbsp;&nbsp;
			<img class="deleteBtn" id="deleteImg" src="${cp}/resources/img/delete.png">
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
				<tbody id="tableBody"></tbody>
			</table>
			<br><br>
			<input type="hidden" id="deleteHidden"/>
			<button type="button" id="createFunctionBtn" class="btn btn-secondary">생성</button>
		</fieldset>
	</form>
  </div>
</div>

<div id="readFunctionModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
  	<span class="close">&times;</span>
  	<br><br>
  	<input type="hidden" id="functionName">
  	<input type="hidden" id="functionId">
  	<select id="readFunctionSelect" class="form-control">
  		<option id="selectCode2">코드</option>
		<option>세부정보</option>
  	</select>
  	<div id="readFunctionDiv"></div>
  </div>
</div>

<script src="${cp}/resources/sqlEditor/js/rightClickFunctionPackage.js"></script>
<script src="${cp}/resources/sqlEditor/js/rightClickFunction.js"></script>
<script src="${cp}/resources/sqlEditor/js/functionManager.js"></script>