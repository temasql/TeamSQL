<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="${cp}/resources/history/js/historyListJs.js" charset="utf-8"></script>
<script type="text/javascript" src="${cp}/resources/history/js/historyDetailListJs.js"></script>
<link href="${cp}/resources/history/css/historyDetail.css" rel="stylesheet">

<!-- DB 상세 변경 이력 테이블 -->
<c:set var="owner" value="${fn:substringBefore(object_owner, '_')}"/>
<h3 class="sub-header" style="color: black; font-weight: bold;"><c:out value="${owner}"></c:out></h3>
	<br>
<div>
	<div id="pageSelect" style="display: inline;">
		<select class="form-control" id="pageSizeSelect" style="width: 10%; display: inline;">
			<option value="10">10개씩 보기</option>
			<option value="20">20개씩 보기</option>
			<option value="30">30개씩 보기</option>
			<option value="50">50개씩 보기</option>
		</select>
		
		<div id="searchdiv" style="display: inline;">
					<select class="form-control"  id="select" name="selectBox" style="width: 10%; display: inline;  margin-left: 59%;">
						<option value="ACTION_EVENT">발생이벤트</option>
						<option value="OBJECT_TYPE">객체타입</option>
						<option value="OBJECT_NAME">객체명</option>
						<option value="SQL_TEXT">원문</option>
					</select>
					<input type="text" class="form-control" name="search" id="searchfor" value="${searchVal }" style="width: 15%; display: inline;">
					<button type="button" class="btn" style="background: black; color: white;" id="btnSearch">검색</button>
				</div>
	</div>
</div>
<div class="tableCon">
<table class="table table-hover" id="tableContainer">
  <thead>
    <tr>
      <th scope="col">발생이벤트</th>
      <th scope="col">객체타입</th>
      <th scope="col">객체명</th>
      <th scope="col">원문</th>
      <th scope="col">일시</th>
    </tr>
  </thead>
  
  <tbody id="dbChangedDetailListBody">
  </tbody>
</table> 
<div>
	<!--  페이지네이션 -->
	<ul class="pagination">
	</ul>
</div>
</div>

<input type="hidden" id= "object_owner" value = "${object_owner}">

<!-- 모달창 -->
<div id="ddlQueryModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content" id="modal_query">
    <span id="close" class="close">&times;</span>   
	  <fieldset id="queryView">
	    <legend id="queryLegend">변경 이력 원문</legend>
	    <div class="form-group" id="textQuery">
	    </div>
	  </fieldset>
  </div>
</div>


