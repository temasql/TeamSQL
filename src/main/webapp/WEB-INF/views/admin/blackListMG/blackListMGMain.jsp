<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${cp }/resources/blackList/js/blackListMG.js"></script>
<link href="${cp }/resources/blackList/css/insertBlackList"/>
				<input type="text" class="form-control" name="searchfor" id="searchfor" value="${searchVal }"
					placeholder="Search for...">  <span class="input-group-btn">
					<button type="button" class="btn btn-default" id="btnSearch" >Search!</button>
				</span>
	
<form action="/blackList/deleteBlackList" id="deleteForm">
	<button type="button" id="deleteBlackList" class="btn btn-default pull-right" >블랙리스트 해제</button>
</form>
<button id="btnInsertBlackList" class="btn btn-default pull-right" >블랙리스트 추가</button>
<div class="tableContainer">
	<table class="table table-hover">
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
	<!--  내가 현재 몇번째 페이지에 있는가? -->
	<ul class="pagination">
	</ul>
</div>


<!-- 아이디 찾기 Modal -->
<div id="insertBlackListModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
  	<span class="close">&times;</span>
  	<br><br>
  	<form action="${cp}/blackList/insertBlackList" method="post" id="insertBlackListForm">
	  <fieldset>
	    <legend>ID 찾기</legend>
	    <br><br>
	    <label for="exampleInputEmail1">아이디</label>
	    <input type="text" class="form-control" id="user_id_fk" name="user_id_fk" placeholder="블랙리스트에 추가할 계정 아이디">
	    <label for="exampleInputEmail1">사유</label>
	    <input type="text" class="form-control" id="reason" name="reason" placeholder="블랙리스트 사유"/>
	    <br><br>
	    <button type="button" id="insertBlackListModalOk" class="btn btn-secondary">확인</button>
  	 </fieldset>
	</form>
  </div>
</div>