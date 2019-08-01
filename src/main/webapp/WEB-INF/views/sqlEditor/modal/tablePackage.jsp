<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${cp}/resources/sqlEditor/css/rightClickTable.css" rel="stylesheet">
<link href="${cp}/resources/sqlEditor/css/rightClickTablePackage.css" rel="stylesheet">


<input type="hidden" id="deleteRowData"/>
<input type="hidden" id="tableNm"/>
<!-- craeteTable Modal -->
<div id="craeteTableModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
    <span class="close">&times;</span>   
    <br><br>                  
	<form action="${cp}/sqlEditor/createTable" method="post" id="createTableFrm">
	  <fieldset>
	    <legend>테이블 생성</legend>
	    <div class="form-group">
	      <input type="text" class="form-control" id="tableName" name="table_name" placeholder="테이블명">
	      <small class="form-text text-muted" id="tableNameHint">영문자, 숫자 포함 3~6자이며 첫 글자는 영문자로 시작</small>
	    </div>
	    <br><br>
	    <a href="#" id="appendData">플러스 이미지</a>
	    <a href="#" id="removeData">마이너스 이미지</a>
	    <table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">PK</th>
					<th scope="col">이름</th>
					<th scope="col">데이터 유형</th>
					<th scope="col">크기</th>
					<th scope="col">널이 아님</th>
					<th scope="col">기본 값</th>
					<th scope="col">설명</th>
				</tr>
			</thead>
			<tbody id="tableDataTbody">
			</tbody>
		</table>
	    <button type="button" id="createTableBtn" class="btn btn-secondary">생성</button>
	  </fieldset>
	</form>
  </div>
</div>
<!-- Read Modal -->
<div id="readTableModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
    <span class="close">&times;</span>   
    <br><br>                  
	<form action="${cp}/sqlEditor/selectTable" method="post" id="SelcetTableFrm">
	  <fieldset>
	    <legend id="readTableTitle"></legend>
	    <select id="tableSelectChoice">
	    	<option value="column">column</option>
	    	<option value="data">data</option>
	    	<option value="constraint">constraint</option>
	    	<option value="trigger">trigger</option>
	    	<option value="detail">detail</option>
	    	<option value="index">index</option>
	    	<option value="DDL">DDL</option>
	    </select>
	    <br><br>
	    <table class="table table-hover">
			<thead id="tableReadThead">
			</thead>
			<tbody id="tableReadTbody">
			</tbody>
		</table>
	  </fieldset>
	</form>
  </div>
</div>

<script src="${cp}/resources/sqlEditor/js/rightClickTable.js"></script>
<script src="${cp}/resources/sqlEditor/js/rightClickTablePackage.js"></script>
<script src="${cp}/resources/sqlEditor/js/tableManager.js"></script>