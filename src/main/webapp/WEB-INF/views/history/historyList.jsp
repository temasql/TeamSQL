<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="${cp}/resources/history/js/historyListJs.js"></script>
<link href="${cp}/resources/history/css/historyStyle.css" rel="stylesheet">

<div class="tableContainer">
<table class="table table-hover" id="tableContainer">
  <thead>
    <tr>
      <th scope="col">계정명</th>
      <th scope="col">마지막 변경 일시</th>
    </tr>
  </thead>
  
  <tbody id="dbChangedListBody">
  </tbody>
</table> 
</div>

<div>
	<!--  페이지네이션 -->
	<ul class="pagination">
	</ul>
</div>
<form action="${cp }/history/historyDetail" id="frm" method="post">
	<input type="hidden" id="object_owner" name="object_owner">
</form>