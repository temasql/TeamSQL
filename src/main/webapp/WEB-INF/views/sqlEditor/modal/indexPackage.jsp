<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${cp}/resources/sqlEditor/css/rightClickIndex.css" rel="stylesheet">
<link href="${cp}/resources/sqlEditor/css/rightClickIndexPackage.css" rel="stylesheet">

<!-- 인덱스 생성 모달 -->
<div id="craeteIndexModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content index" id="modal_index">
    <span class="close">&times;</span>   
    <br><br>                  
	<form action="${cp}/sqlEditor/createIndex" method="post" id="createIndexFrm">
	  <fieldset>
	    <legend>인덱스 생성</legend>
	    <label id="owner"></label>
	    <div class="form-group">
	      <input type="text" class="form-control" id="indexName" name="param_name" placeholder="인덱스명">
	    </div>
	    <div id="select_table">
			  <input type="hidden" id="table_owner" name="param_owner">
	    	<table id="index_option">
	    		<tr>
		    		<td id ="td_tableName" >테이블 :</td>
		    		<td>
			    		<select class="form-control" id="td_select" name="param_table">
			    		</select>
		    		</td>
	    		</tr>
	    		<tr id="index_type">
	    			<td id="td_indexType">인덱스 유형 :</td>
	    			<td>
	    			<select class="form-control td_select" id="td_indexType_select" name="param_indexType">
	    				<option value="">&lt;지정되지 않음&gt;</option>
	    				<option value="">고유하지않음</option>
	    				<option value="UNIQUE">고유</option>
	    				<option value="BITMAP">비트맵</option>
	    			</select>
	    			</td>
	    		</tr>
	    	</table>
	    	
	    </div>
	    
	    <div id="selectDiv_1">
	    	<select id="select_1">
	    		<option id="define">정의</option>
	    		<option>DDL</option>
	    	</select>
	    	<div id="pAndm">
			
			
			
		    <img class="plustBtn" id="appendIndex" src="${cp}/resources/img/add.png">&nbsp;&nbsp;
		    <img class="minusBtn" id="removeIndex" src="${cp}/resources/img/delete.png">
		    </div>
	    </div>
	    <div id="table_scroll">
	    <div id="hidden_table"></div>
		    <input type="hidden" id="deleteHidden"/>
	    <table class="table table-hover index" id="index_table">
			<thead>
				<tr>
					<th id="expression" scope="col">표현식</th>
					<th id="order" scope="col">정렬</th>
				</tr>
			</thead>
			<tbody id="index_tableBody">
			</tbody>
		</table>
		</div>
	    <button type="button" id="createIndexBtn" class="btn btn-secondary">생성</button>
	  </fieldset>
	</form>
  </div>
</div>

<!-- 인덱스 조회 모달 -->
<div id= "selectIndexModal" class="modal">
	<!-- Modal content -->
	<div class="modal-content">
	  	<span class="close">&times;</span>
	  	<br><br>
	  	<input type="hidden" id="tableOwner">
	  	<input type="hidden" id="indexName">
	  	<select  id="readIndexSelect" class="form-control">
	  		<option id="selectIdxCode" selected="selected">코드</option>
			<option>열</option>
			<option>세부정보</option>
	  	</select>
	  	<div id="readIndexDiv"></div>
	</div>
</div>
<!-- 인덱스 편집 모달 -->
<%-- <div id="updateIndexModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content index" id="updateModal_index">
    <span class="close">&times;</span>   
    <br><br>                  
	<form action="${cp}/sqlEditor/updateIndex" method="post" id="updateIndexFrm">
	  <fieldset>
	    <legend>인덱스 편집</legend>
	      <div id="updateIndex">
	      <input type="hidden"  id="hidden_tableName">
	      </div>
		<button type="button" class="btn btn-secondary" id="updateIdxBtn">확인</button>	   
      </fieldset>
     </form>
  </div>
</div> --%>

<!-- 인덱스 편집 모달 -->
<div id="updateIndexModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content index" id="updateModal_index">
    <span class="close">&times;</span>   
    <br><br>                  
	<form action="${cp}/sqlEditor/updateIndex" method="post" id="updateIndexFrm">
	  <fieldset>
	    <legend>인덱스 편집</legend>
	    <div><label id="tblOwner"></label></div>
	    <div class="form-group">
	      <label id= "upIdxName"> 인덱스 명 :</label>
	      <input type="text" class="form-control indexText" id="updateIndexName" name="update_name" placeholder="인덱스명">
	    </div>
	    <div id="select_table">
			  <input type="hidden" id="table_owner" name="param_owner">
	    	<table id="index_option">
	    		<tr>
		    		<td id ="updateTd_tableName" >테이블 :</td>
		    		<td>
			    		<input type="text" class="form-control indexText" id="selectedTable_name" name="update_table" placeholder="테이블명">
		    		</td>
	    		</tr>
	    		<tr id="index_type">
	    			<td id="td_indexType">인덱스 유형 :</td>
	    			<td>
	    			<select class="form-control tdSelect" id="updateTd_indexType_select" name="param_indexType">
	    				<option id="nonUinque" value="">고유하지않음</option>
	    				<option id="unique" value="UNIQUE">고유</option>
	    				<option id="bitMap" value="BITMAP">비트맵</option>
	    			</select>
	    			</td>
	    		</tr>
	    	</table>
	    	
	    </div>
	    
	    <div id="selectDiv_1">
	    	<select id="select_1">
	    		<option id="define">정의</option>
	    		<option>DDL</option>
	    	</select>
	    	<div id="pAndm">
			
			
			
		    <img class="plustBtn" id="updateaApendIndex" src="${cp}/resources/img/add.png">&nbsp;&nbsp;
		    <img class="minusBtn" id="updateRemoveIndex" src="${cp}/resources/img/delete.png">
		    </div>
	    </div>
	    <div class="table_scroll">
	    <div id="hidden_table"></div>
		    <input type="hidden" id="deleteHidden"/>
	    <table class="table table-hover index" id="index_table">
			<thead>
				<tr>
					<th id="expression" scope="col">표현식</th>
					<th id="order" scope="col">정렬</th>
				</tr>
			</thead>
			<tbody id="updateIndex_tableBody">
			</tbody>
		</table>
		</div>
	    <button type="button" id="updateIndexBtn" class="btn btn-secondary">생성</button>
	  </fieldset>
	</form>
  </div>
</div>



<script src="${cp}/resources/sqlEditor/js/rightClickIndex.js"></script>
<script src="${cp}/resources/sqlEditor/js/rightClickIndexPackage.js"></script>
<script src="${cp}/resources/sqlEditor/js/indexManager.js"></script>