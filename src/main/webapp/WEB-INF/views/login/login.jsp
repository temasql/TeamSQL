<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="${cp}/js/js.cookie.js"></script>

<form id="loginForm" action="${cp }/login" method="post">
	<input type="text" name="user_id"><br>
	<input type="password" name="user_pw">
	<div class="checkbox">
		<label> <input id="rememberme" name="rememberme" type="checkbox" value="remember-me">	remember</label>
	</div>
	<button id="loginBtn" class="btn btn-default pull-right" type="button">login </button>
	<a class="btn btn-default pull-right" href="${cp }/user/signIn">회원가입</a>
</form>

<div id="naverIdLogin"></div>
<!-- //네이버아이디로로그인 버튼 노출 영역 -->

<!-- 네이버아디디로로그인 초기화 Script -->
<script type="text/javascript">
	var naverLogin = new naver.LoginWithNaverId(
		{
			clientId: "IAFGbfmFN3O6uIiNDefU",
			callbackUrl: "http://localhost:80/user/naverCallback",
			isPopup: false, /* 팝업을 통한 연동처리 여부 */
			loginButton: {color: "white", type: 2, height: 30} /* 로그인 버튼의 타입을 지정 */
		}
	);
	
	/* 설정정보를 초기화하고 연동을 준비 */
	naverLogin.init();
	
</script>

