<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="${cp}/resources/user/css/findUserIdAndPwModal.css" rel="stylesheet">
<script src="${cp}/resources/user/js/findUserIdAndPw.js"></script>

<!--     <h4 class="card-title">Dark card title</h4> -->
<!--     <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p> -->

<div>    
	<div style=" margin-left: 180px; margin-top: 100px;">
		<div class="card border-dark" style="max-width: 62rem; display: inline-block;margin-left: 65px;">
		<div class="card-header" style="text-align: -webkit-center; font-size: 2.0em; font-weight: bold; ">My Page</div>
			<div class="card-body" style="padding: 40px;">
				<img id="thumbnail" src="${cp}/user/profile?user_id=${userVo.user_id}" style=" width: 400px; height: 400px; margin-right: 100px;display: inline-block;vertical-align: top;">
				<div style="display: inline-block; text-align-last: center; margin-top: 64px">
					<p class="card-title" style="font-size: 2.5em; font-weight: bold;margin-bottom: 20px;">${userVo.user_id}</p>
					<p class="card-text" style="font-size: 2.5em;color: #666;">${userVo.user_name}</p>
					<p class="card-text" style="font-size: 2.5em;color: #666;">${userVo.user_email}</p>
					<form action="${cp}/user/modifyUser" style=" display: contents;">
						<input type="hidden" name="user_id" value="${userVo.user_id}">
						<input type="submit" class="btn" style="background: black; color: white;margin-right: 50px;" value="회원 정보 수정" />
					</form>
					<button id="btnDeleteUser" class="btn" style="background: black; color: white;">회원 탈퇴</button>
				</div>
			</div>
		</div>
	</div> 
</div>
<!-- 회원탈퇴 Modal -->
<div id="deleteUserModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
  	<span class="close">&times;</span>
  	<br><br>
  	<form action="${cp}/user/deleteUser" method="post" id="deleteUserPwForm">
	  <fieldset>
	    <legend>회원 탈퇴</legend>
	    <br><br>
		<input type="hidden" id="user_id" name="user_id" value="${userVo.user_id}">
	    <label for="exampleInputEmail1">비밀번호</label>
	    <input type="password" class="form-control" id="user_pw" name="user_pw" placeholder="해당 계정의 비밀번호를 입력 해주세요">
	    <br><br>
	    <button type="button" id="btnDeleteUserOk" class="btn" style="background: black; color: white;">확인</button>
  	 </fieldset>
	</form>
  </div>
</div>

