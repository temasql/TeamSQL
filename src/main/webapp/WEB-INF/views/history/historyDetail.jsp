<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="${cp}/resources/history/js/historyListJs.js"></script>

<div class="tableContainer">
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
  <tbody id="dbChangedListBody">
  <c:forEach items="${changedList}" var="changedList">
   <tr>
  	<td>${changedList.action_event }</td>
  	<td>${changedList.object_type }</td>
  	<td>${changedList.object_name }</td>
  	<td>${changedList.sql_text }</td>
  	<td>${changedList.exec_dtm }</td>
   </tr>
   </c:forEach>
  </tbody>
</table> 
</div>

<div>
	<!--  페이지네이션 -->
	<ul class="pagination">
	</ul>
</div>
