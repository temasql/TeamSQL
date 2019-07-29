<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="${cp}/resources/user/css/findUserIdAndPwModal.css" rel="stylesheet">
<script src="${cp}/resources/user/js/findUserIdAndPw.js"></script>
    
<table class="table table-hover">
	<thead>
		<tr>
			<th scope="col">아이디</th>
			<th scope="col">이름</th>
			<th scope="col">이메일</th>
			<th scope="col">프로필 사진</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td scope="row">${userVo.user_id}</td>
			<td scope="row">${userVo.user_name}</td>
			<td scope="row">${userVo.user_email}</td>
			<td scope="row"><img id="thumbnail" src="${cp}/user/profile?user_id=${userVo.user_id}"></td>
		</tr>
	</tbody>
</table>
<form action="${cp}/user/modifyUser">
	<input type="hidden" name="user_id" value="${userVo.user_id}">
	<input type="submit" class="btn btn-default pull-right" value="회원 정보 수정" />
</form>

<button id="btnDeleteUser" class="btn btn-default pull-right">회원 탈퇴</button>

<!-- 회원탈퇴 Modal -->
<div id="deleteUserModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
  	<span class="close">&times;</span>
  	<br><br>
  	<form action="${cp}/user/deleteUser" method="post" id="deleteUserPwForm">
	  <fieldset>
	    <legend>PW 찾기</legend>
	    <br><br>
		<input type="hidden" id="user_id" name="user_id" value="${userVo.user_id}">
	    <label for="exampleInputEmail1">비밀번호</label>
	    <input type="password" class="form-control" id="user_pw" name="user_pw" placeholder="해당 계정의 비밀번호를 입력 해주세요">
	    <br><br>
	    <button type="button" id="btnDeleteUserOk" class="btn btn-secondary">확인</button>
  	 </fieldset>
	</form>
  </div>
</div>

