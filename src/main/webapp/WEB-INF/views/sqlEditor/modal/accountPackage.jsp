<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Account PW Find Modal -->
<div id="accountPwUpdateModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
  	<span class="close">&times;</span>
  	<br><br>
  	<form action="${cp}/sqlEditor/updatePwAccount" method="post" id="accountPwUpdateFrm">
	  <fieldset>
	    <legend>DB계정 비밀번호 변경</legend>
	    <br><br>
	    <label for="exampleInputEmail1">기존 비밀번호</label>
	    <input type="password" class="form-control" id="originalPw" name="originalPw" placeholder="기존 비밀번호">
	    <br><br>
	    <label for="exampleInputEmail1">변경 비밀번호</label>
	    <input type="password" class="form-control" id="updatePw" name="updatePw" placeholder="변경 비밀번호">
	    <br><br>
	    <label for="exampleInputEmail1">변경 비밀번호 확인</label>
	    <input type="password" class="form-control" id="reUpdatePw" name="reUpdatePw" placeholder="변경 비밀번호 확인">
	    <br><br>
	    <button type="button" id="accountPwUpdateBtn" class="btn btn-secondary">확인</button>
	    <input type="hidden" id="updateId" name="updateId"/>
  	 </fieldset>
	</form>
  </div>
</div>

<!-- Account PW Find Modal -->
<div id="accountPwFindModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
  	<span class="close">&times;</span>
  	<br><br>
  	<form action="${cp}/sqlEditor/findPwAccount" method="post" id="accountPwFindFrm">
	  <fieldset>
	    <legend>DB계정 비밀번호 찾기</legend>
	    <br><br>
	    <label for="exampleInputEmail1">아이디</label>
	    <input type="text" class="form-control" id="user_id" name="user_id" placeholder="아이디">
	    <br><br>
	    <label for="exampleInputEmail1">이메일</label>
	    <input type="email" class="form-control" id="user_email" name="user_email" placeholder="이메일">
	    <br><br>
	    <label for="exampleInputEmail1">비밀번호는 회원님의 이메일로 보내집니다.</label>
	    <br><br>
	    <button type="button" id="accountPwFindBtn" class="btn btn-secondary">확인</button>
	    <input type="hidden" id="findId" name="findId"/>
  	 </fieldset>
	</form>
  </div>
</div>

<!-- Account Delete Modal -->
<div id="accountDeleteModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
  	<span class="close">&times;</span>
  	<br><br>
  	<form action="${cp}/sqlEditor/deleteAccount" method="post" id="accountDeleteFrm">
	  <fieldset>
	    <legend>DB계정 삭제</legend>
	    <br><br>
	    <label for="exampleInputEmail1">DB계정 비밀번호</label>
	    <input type="password" class="form-control" id="deletePw" name="deletePw" placeholder="DB계정 비밀번호">
	    <input type="hidden" id="deleteId" name="deleteId"/>
	    <br><br>
	    <button type="button" id="accountDeleteBtn" class="btn btn-secondary">확인</button>
  	 </fieldset>
	</form>
  </div>
</div>

<!-- Account Modal -->
<div id="accountModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
    <span class="close">&times;</span>   
    <br><br>                  
	<form action="${cp}/sqlEditor/addAccount" method="post" id="accountAddfrm">
	  <fieldset>
	    <legend>DB계정 생성</legend>
	    <br><br>
	    <input type="hidden" id="run_id" value="${account_id}"/>
	    <div class="form-group">
	      <label for="exampleInputEmail1">DB계정명</label>
	      <input type="text" class="form-control" id="accountName" name="account_id" placeholder="DB계정명">
	      <small class="form-text text-muted" id="accountNameHint">영문자, 숫자 포함 3~6자이며 첫 글자는 영문자로 시작</small>
	    </div>
	    <div class="form-group">
	      <label for="exampleInputPassword1">비밀번호</label>
	      <input type="password" class="form-control" id="accountPw" name="account_pw" placeholder="비밀번호">
	      <small class="form-text text-muted">4~12자로 구성되며 첫 글자는 영문자로 시작</small>
	    </div>
	    <div class="form-group">
	      <label for="exampleInputPassword1">채팅방이름</label>
	      <input type="text" class="form-control" id="chatRoomName" name="chatRoomName" placeholder="채팅방이름">
	      <small class="form-text text-muted">3~20자의 문자, 숫자, 특수문자로 입력</small>
	    </div>
	    <button type="button" id="addAccountBtn" class="btn btn-secondary">생성</button>
	    <input type="hidden" id="msg" value="${msg}"/>
	  </fieldset>
	</form>
  </div>
</div>