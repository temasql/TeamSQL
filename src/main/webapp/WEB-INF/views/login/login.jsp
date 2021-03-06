<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/views/common/basicLib.jsp" %>
<script src="${cp}/js/js.cookie.js"></script>
<link href="${cp}/resources/user/css/findUserIdAndPwModal.css" rel="stylesheet">
<link href="${cp}/resources/login/login.css" rel="stylesheet">
<link href="${cp}/resources/loginBootstrap/css/modern-business.css" rel="stylesheet">
<%-- <link href="${cp}/resources/loginBootstrap/vendor/bootstrap/css/bootstrap.css" rel="stylesheet"> --%>
<%-- <link href="${cp}/resources/loginBootstrap/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"> --%>
<script src="${cp}/resources/user/js/findUserIdAndPw.js"></script>
<script src="${cp}/resources/loginBootstrap/js/contact_me.js"></script>
<script src="${cp}/resources/loginBootstrap/js/jqBootstrapValidation.js"></script>
<script src="${cp}/resources/loginBootstrap/vendor/bootstrap/js/bootstrap.js"></script>
<script src="${cp}/resources/loginBootstrap/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="${cp}/resources/loginBootstrap/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="${cp}/resources/loginBootstrap/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>

<script>
	$(document).ready(function () {
		// 문서로딩이 완료되고 나서 실행되는 부분
		// rememberme checkbox
		// 1. rememberme cookie가 있는지  있으면 값이 true인지
		// 1-1. rememberme가 true이면 input id="rememberme" 체크박스를 체크
		
		var rememberme = Cookies.get("rememberme");
		if(rememberme == "true"){
			$("#rememberme").prop("checked", true);
			$("#user_id").val(Cookies.get("user_id"));
		}
		
		
		var msg = '${msg}';
		if(msg != ''){
			alert(msg);
		}
		$('form input').keydown(function(e) {
	        if (e.keyCode == 13) {
	            $('#loginForm').submit();
	        }
	    });
		var user_id = $("#user_id").val();
		var user_pw = $("#user_pw").val();
		if(user_id > 0 || user_pw > 0){
			alert("아이디 또는 비밀번호를 맞게 입력해 주세요.")
		}
		// login button 클릭시 실행되는 핸들러
		$("#loginBtn").on("click", function() {
			//로그인 요청을 서버로 전송
			$("#loginForm").submit();
		});
	});
</script>
<title>Main</title>

</head>

<body>
	
	<div id="loginDiv">
		<h1 id="welcome">Welcome to TeamSQL</h1>
		<form id="loginForm" action="${cp }/login" method="post">
			<input class="loginInput" type="text" id="user_id"  value="${user_id}" name="user_id" placeholder="  ID">
			<br>
			<input class="loginInput" type="password" id="user_pw" name="user_pw" placeholder="  PW">
			<br>
			<label id="loginLabel"> <input id="rememberme" name="rememberme" type="checkbox" value="remember-me">	ID저장</label>
			<br>
			<div id="naverIdLogin"></div>
			<br>
			<button id="loginBtn" class="btn btn-default pull-right loginBtn" type="button">login </button>
		</form>
			<a id="singInBtn" class="btn btn-default pull-right loginBtn" href="${cp }/user/signIn">회원가입</a>
			<br>
			<button id="btnFindUserId" class="btn btn-default pull-right loginBtnSub" >ID찾기</button>
			<button id="btnFindUserPw" class="btn btn-default pull-right loginBtnSub" >PW찾기</button>
	</div>
	<div id="loginTopDiv">
		<img id="mainImage" 
			src="https://pbs.twimg.com/media/D8gztb-WwAAceuo.jpg">
<!-- 			https://pbs.twimg.com/media/D8gztb-WwAAceuo.jpg -->
<!-- https://t1.daumcdn.net/cfile/tistory/25773A345212D48502 -->
<!-- https://tinypetition.com/g/022-plan-template-disaster-recovery-and-business-continuity-960x971.jpg -->
	</div>
<!-- //네이버아이디로로그인 버튼 노출 영역 -->

<!-- 네이버아디디로로그인 초기화 Script -->
<script type="text/javascript">
	var naverLogin = new naver.LoginWithNaverId(
		{
			clientId: "IAFGbfmFN3O6uIiNDefU",
			callbackUrl: "http://localhost:80/user/naverCallback",
			isPopup: false, /* 팝업을 통한 연동처리 여부 */
			loginButton: {color: "white", type: 3, height: 55} /* 로그인 버튼의 타입을 지정 */
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
	    <button type="button" id="btnUserIdModalOk" class="btn" style="background-color: black;color: white;">확인</button>
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
	    <button type="button" id="btnUserPwModalOk" class="btn" style="background-color: black; color: white;">확인</button>
  	 </fieldset>
	</form>
  </div>
</div>
</body>
</html>
