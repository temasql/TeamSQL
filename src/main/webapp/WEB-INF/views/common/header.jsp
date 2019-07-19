<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 헤더 -->
<header>
	<div class="header-left">
		<a class="aHeader" href="#"><img id="user" alt="" src="${pageContext.request.contextPath}/resources/header/img/user.png">MyPage</a>
		<a class="aHeader" href="#">로그아웃</a>
	</div>
</header>
<!-- 헤더 -->

  <!-- Navigation -->
 <nav class="navMenu">
	<ul class="ul-header-one">
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
	</ul>


<!-- 챗봇 -->	
<div class="chatbot">
<button type="button" class="img-chatbot">챗봇버튼</button>
</div> 
<!-- 챗봇 -->	

<!-- 그룹채팅 -->
<div class="groupchat">
<button type="button" class="img-groupchat">그룹챗</button>
</div>
<!-- 그룹채팅 -->

</nav>
	<!-- Navigation -->
