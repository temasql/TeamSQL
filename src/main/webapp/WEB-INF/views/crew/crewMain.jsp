<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${cp }/resources/crew/js/crewMain.js"></script>
<link href="${cp }/resources/crew/css/crewMain.css" rel="stylesheet"/>
<input type="hidden" id="ac_id" value="${selected}">
<input type="hidden" id="msg" value="${msg}">
<div>
	<span style="margin : 10px;">계정 명 : </span> 
<select class="form-control" id="select" style=" text-align-last: center; display: inline-block; width : 10%;margin : 10px;">
	<c:forEach items="${crewSelectList}" var="crewVo">
		<option value="${crewVo.account_id_fk }">${crewVo.account_id_fkSlice }</option>
	</c:forEach>
</select>
</div>
<input type="hidden" id="accountSelect" name="account_id_fk" value="${selected}">
<script>$("#select").val($("#accountSelect").val());</script>
<input type="hidden" id="accountVo_id"  name="accountVo_id" value="${accountVo.account_id}">
<div id="btnDelAndAdd" style=" display: inline-block;">

</div>
<button type="button" class="btn"style="background: black;color: white;float: right;" id="btnSearch" >검색</button>
<input type="text" class="form-control" name="searchfor" id="searchfor" value="${searchVal }"
placeholder="    아이디로 검색" style="width: 180px;display: inline;float: right;padding-bottom: 0px;padding-top: 0px;margin-bottom: 10px;height: 42px;margin-right: 15px;">  
<div class="tableContainer">
	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col"></th>
				<th scope="col">아이디</th>
				<th scope="col">이름</th>
				<th scope="col">이메일</th>
				<th scope="col">프로필 사진</th>
			</tr>
		</thead>
		<tbody id="crewListTbody">
		</tbody>
	</table>
</div>
<div>
	<!--  내가 현재 몇번째 페이지에 있는가? -->
	<ul class="pagination">
	</ul>
</div>


<!-- 아이디 찾기 Modal -->
<div id="inviteModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
  	<span class="close">&times;</span>
  	<br><br>
  	<form action="${cp}/crew/inviteCrew" method="post" id="inviteCrewForm">
	  <fieldset>
	    <legend>구성원 초대</legend>
	    <br><br>
	    <label for="exampleInputEmail1">아이디</label>
	    <input type="text" class="form-control" id="user_id" name="user_id" placeholder="초대할 계정 아이디">
	    <br><br>
	    <button type="button" id="btnInviteCrewOk" class="btn btn-secondary">초대</button>
  	 </fieldset>
	</form>
  </div>
</div>