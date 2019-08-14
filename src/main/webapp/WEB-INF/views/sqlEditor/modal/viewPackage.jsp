<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${cp}/resources/sqlEditor/css/rightClickView.css" rel="stylesheet">
<link href="${cp}/resources/sqlEditor/css/rightClickViewPackage.css" rel="stylesheet">


<input type="hidden" id="viewNm"/>
<!-- craeteView Modal -->
<div id="craeteViewModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
    <span class="close">&times;</span>   
    <br><br>                  
	<form action="${cp}/sqlEditor/createView" method="post" id="createViewFrm">
	  <fieldset>
	    <legend>뷰 생성</legend>
	    <div class="form-group">
	    <label for="exampleInputEmail1">스키마</label>
		<input type="text" id="sName" class="form-control" readonly>
		<br>
		<input type="hidden" id="sc_id" class="sc_id" name="sc_id">
		<label for="exampleInputEmail1">뷰 이름</label>
	      <input type="text" class="form-control" id="viewName" name="view_name" placeholder="뷰이름">
	    </div>
	    <br><br>
		<textarea class="form-control" id="viewText" name="viewQuery" rows="10" cols="229" autofocus required></textarea>
		<br>
	    <button type="button" id="createViewBtn" class="btn" style="background: black; color: white;">생성</button>
	  </fieldset>
	</form>
  </div>
</div>

<!-- updateView Modal -->
<div id="updateViewModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
    <span class="close">&times;</span>   
    <br><br>                  
	<form action="${cp}/sqlEditor/updateView" method="post" id="updateViewFrm">
	  <fieldset>
	    <legend>뷰 수정</legend>
	    <div class="form-group">
	    <label for="exampleInputEmail1">스키마</label>
		<input type="text" id="scName" class="form-control" readonly>
		<input type="hidden" id="sc_id1" class="sc_id" name="sc_id">
		<br>
		<input type="hidden" id="oldVN" name="oldVN">
		<label for="exampleInputEmail1">뷰 이름</label>
	      <input type="text" class="form-control" id="updateViewName" name="view_name" placeholder="뷰이름">
	    </div>
	    <br><br>
		<textarea class="form-control" id="updateViewText" name="viewQuery" rows="10" cols="229" autofocus required></textarea>
		<br>
	    <button type="button" id="updateViewBtn" class="btn" style="background: black; color: white;">수정</button>
	  </fieldset>
	</form>
  </div>
</div>

<!-- Read Modal -->
<div id="readViewModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
    <span class="close">&times;</span>   
    <br><br>                  
	  <fieldset>
	    <legend id="readViewTitle"></legend>
	    <select id="viewSelectChoice" class="form-control" style=" width: 100px;">
	    	<option value="column">column</option>
	    	<option value="data">data</option>
	    </select>
	    <br><br>
	    <table class="table table-hover">
			<thead id="viewReadThead">
			</thead>
			<tbody id="viewReadTbody">
			</tbody>
		</table>
	  </fieldset>
  </div>
</div>



<script src="${cp}/resources/sqlEditor/js/rightClickView.js"></script>
<script src="${cp}/resources/sqlEditor/js/rightClickViewPackage.js"></script>
<script src="${cp}/resources/sqlEditor/js/viewManager.js"></script>