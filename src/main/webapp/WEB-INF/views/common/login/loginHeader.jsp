<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 헤더 -->
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
<header>
<script>
	$(document).ready(function () {
		// 문서로딩이 완료되고 나서 실행되는 부분
		// rememberme checkbox
		// 1. rememberme cookie가 있는지  있으면 값이 true인지
		// 1-1. rememberme가 true이면 input id="rememberme" 체크박스를 체크
		
		var rememberme = Cookies.get("rememberme");
		if(rememberme == "true"){
			$("#rememberme").prop("checked", true);
			$("#userId").val(Cookies.get("userId"));
			$("#password").focus();
		}
		
		// login button 클릭시 실행되는 핸들러
		$("#loginBtn").on("click", function() {
			//로그인 요청을 서버로 전송
			$("#loginForm").submit();
			
		});
	});
</script>

	<div class="header-right">
	</div>
<!-- 헤더 -->

  <!-- Navigation -->
 <nav class="navMenu">
	<ul class="ul-header-one">
		<c:choose>
			<c:when test="${user_right eq 'A' }">
				<li><a class="aNav" href="#">회원관리</a></li>
				<li><a class="aNav" href="#">블랙릭스트관리</a></li>
				<li><a class="aNav" href="#">공지사항</a></li>
				<li class="one"><a class="aNav" href="#">게시판관리</a>
					<ul class="ul-header-two">
						<li class="li-two"><a class="two-menu" href="#">공지사항</a></li>
						<li class="li-two"><a class="two-menu" href="#">자유게시판</a></li>
					</ul>
				</li>
				<li><a class="aNav" href="#">퀴즈관리</a></li>
				<li><a class="aNav" href="#">관리자관리</a></li>
			</c:when>
			<c:otherwise>
			<li><a class="aNav" href="${cp}/sqlEditor/sqlEditorMain">SQL에디터</a></li>
		  		<li><a class="aNav" href="#">DB변경이력관리</a></li>
				<li><a class="aNav" href="#">구성원관리</a></li>
				<li class="one"><a class="aNav" href="#">게시판</a>
					<ul class="ul-header-two">
						<li class="li-two"><a class="two-menu" href="#">공지사항</a></li>
						<li class="li-two"><a class="two-menu" href="#">자유게시판</a></li>
					</ul>
				</li>
				<li><a class="aNav" href="#">SQL퀴즈</a></li>
			</c:otherwise>
		</c:choose>
	</ul>

</nav>

</header>

	<!-- Navigation -->
