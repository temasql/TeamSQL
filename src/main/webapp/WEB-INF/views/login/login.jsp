<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="${cp}/js/js.cookie.js"></script>
<link href="${cp}/resources/user/css/findUserIdAndPwModal.css" rel="stylesheet">
<script src="${cp}/resources/user/js/findUserIdAndPw.js"></script>

<form id="loginForm" action="${cp }/login" method="post">
	<input type="text" id="user_id"  value="${user_id}" name="user_id"><br>
	<input type="password" id="user_pw" value="kbh711482!" name="user_pw">
	<div class="checkbox">
		<label> <input id="rememberme" name="rememberme" type="checkbox" value="remember-me">	remember</label>
	</div>
	<button id="loginBtn" class="btn btn-default pull-right" type="button">login </button>
	<a class="btn btn-default pull-right" href="${cp }/user/signIn">회원가입</a>
</form>
<button id="btnFindUserId" class="btn btn-default pull-right" >ID찾기</button>
<button id="btnFindUserPw" class="btn btn-default pull-right" >PW찾기</button>
<div id="naverIdLogin"></div>
<!-- //네이버아이디로로그인 버튼 노출 영역 -->

<!-- 네이버아디디로로그인 초기화 Script -->
<script type="text/javascript">
	var naverLogin = new naver.LoginWithNaverId(
		{
			clientId: "IAFGbfmFN3O6uIiNDefU",
			callbackUrl: "http://localhost:80/user/naverCallback",
			isPopup: false, /* 팝업을 통한 연동처리 여부 */
			loginButton: {color: "white", type: 1, height: 30} /* 로그인 버튼의 타입을 지정 */
		}
	);
	
	/* 설정정보를 초기화하고 연동을 준비 */
	naverLogin.init();
	
</script>

<!-- 아이디 찾기 Modal -->
<div id="findUserIdModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
  	<span class="close">&times;</span>
  	<br><br>
  	<form action="${cp}/user/findUserId" method="post" id="findUserIdForm">
	  <fieldset>
	    <legend>ID 찾기</legend>
	    <br><br>
	    <label for="exampleInputEmail1">이름</label>
	    <input type="text" class="form-control" id="user_name" name="user_name" placeholder="계정의 생성자 이름">
	    <label for="exampleInputEmail1">이메일</label>
	    <input type="text" class="form-control" id="find_id_user_email" name="user_email" placeholder="계정의 생성자 이메일"/>
	    <br><br>
	    <button type="button" id="btnUserIdModalOk" class="btn btn-secondary">확인</button>
  	 </fieldset>
	</form>
  </div>
</div>

<!-- 비밀번호 찾기 Modal -->
<div id="findUserPwModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
  	<span class="close">&times;</span>
  	<br><br>
  	<form action="${cp}/user/findUserPw" method="post" id="findUserPwForm">
	  <fieldset>
	    <legend>PW 찾기</legend>
	    <br><br>
	    <label for="exampleInputEmail1">아이디</label>
	    <input type="text" class="form-control" id="find_pw_user_id" name="user_id" placeholder="비밀번호를 찾는 계정의 아이디">
	    <label for="exampleInputEmail1">이메일</label>
	    <input type="text" class="form-control" id="find_pw_user_email" name="user_email" placeholder="계정의 생성자 이메일"/>
	    <br><br>
	    <button type="button" id="btnUserPwModalOk" class="btn btn-secondary">확인</button>
  	 </fieldset>
	</form>
  </div>
</div>