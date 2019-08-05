<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${cp }/resources/crew/js/crewMain.js"></script>

<select id="select">
	<c:forEach items="${crewSelectList}" var="crewVo">
		<option value="${crewVo.account_id_fk }">${crewVo.account_id_fkSlice }</option>
	</c:forEach>
</select>
<input type="hidden" id="accountSelect" name="account_id_fk" value="${selected}">
<script>$("#select").val($("#accountSelect").val());</script>
<input type="hidden" id="accountVo_id"  name="accountVo_id" value="${accountVo.account_id}">
<form id="search"
		action="${cp}/crew/crewManager" >
		<div id="searchdiv" class="col-lg-8">
			<div class="input-group">
				<input type="text" class="form-control" name="searchfor" id="searchfor" value="${searchVal }"
					placeholder="Search for...">  <span class="input-group-btn">
					<button type="button" class="btn btn-default" id="btnSearch" >Search!</button>
				</span>
			</div>
		</div>
	</form>
<div id="btnDelAndAdd">

</div>
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
	    <legend>ID 찾기</legend>
	    <br><br>
	    <label for="exampleInputEmail1">아이디</label>
	    <input type="text" class="form-control" id="user_id" name="user_id" placeholder="초대할 계정 아이디">
	    <br><br>
	    <button type="button" id="btnInviteCrewOk" class="btn btn-secondary">초대</button>
  	 </fieldset>
	</form>
  </div>
</div>