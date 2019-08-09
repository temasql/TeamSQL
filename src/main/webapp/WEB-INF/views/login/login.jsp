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
		
		
		var msg = '${deleteMsg}';
		if(msg != ''){
			alert(msg);
		}
		
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
	<header id="header">
	    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
	      <ol class="carousel-indicators">
	        <li data-target="#carouselExampleIndicators" data-slide-to="0" class=""></li>
	        <li data-target="#carouselExampleIndicators" data-slide-to="1" class=""></li>
	        <li data-target="#carouselExampleIndicators" data-slide-to="2" class="active"></li>
	      </ol>
	      <div class="carousel-inner" role="listbox">
	        <div class="carousel-item" style="background-image: url('https://www.firstumc.org/wp-content/uploads/2017/05/prayer-requests.jpg')">
	          <div class="carousel-caption d-none d-md-block">
	            <h2>Cooperation</h2>
	            <h3>팀원들과의 협업을 원할하게</h3>
	          </div>
	        </div>
	        <div class="carousel-item" style="background-image: url('http://unigatesystems.com/software/images/hero-big-data-new.jpg')">
	          <div class="carousel-caption d-none d-md-block">
	            <h2>Convenience</h2>
	            <h3>차별화된 기능을 통해 조금 더 쉽게</h3>
	          </div>
	        </div>
	        <div class="carousel-item active" style="background-image: url('https://community.arm.com/cfs-file/__key/communityserver-blogs-components-weblogfiles/00-00-00-20-57/0245.endless_5F00_mini_5F00_p2_5F00_xxlCarousel_5F00_03.jpg')">
	          <div class="carousel-caption d-none d-md-block">
	            <h2>Accessibility</h2>
	            <h3>Web에서 DB Developer를 간편하게</h3>
	          </div>
	        </div>
	      </div>
	      <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
	        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	        <span class="sr-only">Previous</span>
	      </a>
	      <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
	        <span class="carousel-control-next-icon" aria-hidden="true"></span>
	        <span class="sr-only">Next</span>
	      </a>
	    </div>
	</header>
	<div id="loginDiv">
		<h1 id="welcome">Welcome to TeamSQL</h1>
		<form id="loginForm" action="${cp }/login" method="post">
			<div id="inputDiv">
				<div id="inputAndRemember">
					<input class="loginInput" type="text" id="user_id"  value="${user_id}" name="user_id" placeholder="  ID">
					<input class="loginInput" type="password" id="user_pw" value="js1450@!" name="user_pw" placeholder="  PW">
					<label id="loginLabel"> <input id="rememberme" name="rememberme" type="checkbox" value="remember-me">	ID저장</label>
				</div>
				<div id="loginAndNaver">
					<button id="loginBtn" class="btn btn-default pull-right loginBtn" type="button">login </button>
					<div id="naverIdLogin"></div>
				</div>
			</div>
		</form>
		<div id="findAndSignIn">
			<a id="singInBtn" class="btn btn-default pull-right loginBtnSub" href="${cp }/user/signIn">회원가입</a>
			<button id="btnFindUserId" class="btn btn-default pull-right loginBtnSub" >ID찾기</button>
			<button id="btnFindUserPw" class="btn btn-default pull-right loginBtnSub" >PW찾기</button>
		</div>
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
</body>
</html>
