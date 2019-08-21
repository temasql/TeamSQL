<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${cp }/resources/blackList/js/blackListMG.js"></script>
<link href="${cp }/resources/blackList/css/insertBlackList.css" rel="stylesheet"/>
<div style="display: inline-block;    width: 100%;">
	 
<select class="form-control" style=" width : 7%;margin : 10px; text-align-last: center; display: inline-block;"  id="pageSizeSelect">
	<option value="10">10개씩 보기</option>
	<option value="20">20개씩 보기</option>
	<option value="30">30개씩 보기</option>
	<option value="50">50개씩 보기</option>
</select>

				<input type="text" class="form-control" name="searchfor" id="searchfor" value="${searchVal }"
					placeholder="    아이디로 검색"style="width: 180px;display: inline;padding-bottom: 0px;padding-top: 0px;margin-bottom: 10px;height: 53px;margin-right: 15px;margin-left: 71%;">
					<button type="button" class="btn" id="btnSearch" style="background: black;color: white;">검색</button>
</div>
<div class="tableContainer">
	<table class="table table-hover" style="text-align: center;">
		<thead>
			<tr>
				<th scope="col"></th>
				<th scope="col">아이디</th>
				<th scope="col">이름</th>
				<th scope="col">이메일</th>
				<th scope="col">프로필 사진</th>
				<th scope="col">사유</th>
				<th scope="col">가입 일</th>
			</tr>
		</thead>
		<tbody id="blackListTbody">
		</tbody>
	</table>
</div>
<div>
<button id="btnInsertBlackList" class="btn" style="background: black; color: white; margin-left: 80%">블랙리스트 추가</button>
<form action="/blackList/deleteBlackList" id="deleteForm" style=" display: contents;" >
	<button type="button" id="deleteBlackList" class="btn" style="background: black; color: white;">블랙리스트 해제</button>
</form>
	<!--  내가 현재 몇번째 페이지에 있는가? -->
	<ul class="pagination">
	</ul>
</div>


<div id="insertBlackListModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
  	<span class="close">&times;</span>
  	<br><br>
  	<form action="${cp}/blackList/insertBlackList" method="post" id="insertBlackListForm">
	  <fieldset>
	    <legend>블랙리스트 추가</legend>
	    <br><br>
	    <label for="exampleInputEmail1">아이디</label>
	    <input type="text" class="form-control" id="user_id_fk" name="user_id_fk" placeholder="블랙리스트에 추가할 계정 아이디">
	    <label for="exampleInputEmail1">사유</label>
	    <input type="text" class="form-control" id="reason" name="reason" placeholder="블랙리스트 사유"/>
	    <br><br>
	    <button type="button" id="insertBlackListModalOk" class="btn" style="background: black; color: white;">확인</button>
  	 </fieldset>
	</form>
  </div>
</div>