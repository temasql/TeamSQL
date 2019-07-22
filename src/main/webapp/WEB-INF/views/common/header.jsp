<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 헤더 -->
<header>
	<div class="header-right">
		<a class="aHeader"  href="#"><img id="thumbnail" src="${cp }/resources/header/img/user.png" alt="Mypage" title="Mypage">MyPage</a>
		<a class="aHeader" href="#">로그아웃</a>
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


<!-- 챗봇 -->	
<div class="chatbot">
<a class="aRight" id="chatBotPopup" href="javascript:openWin('${cp }/chatBot/chatBot','0')"><img class="imgRight" alt="챗봇" src="${cp }/resources/img/chatbot.png" title="챗봇"> </a>
</div> 
<!-- 챗봇 -->	
	
<!-- 그룹채팅 -->
<div class="groupchat">
<a class="aRight" href="#"><img class="imgRight" alt="챗봇" src="${cp }/resources/img/groupchat.png" title="챗봇"></a>
</div>
<!-- 그룹채팅 -->

</nav>

<!-- 허접한 우클릭 -->
<script type="text/javascript">
$(document).bind("contextmenu", function(event) { 
    event.preventDefault();
    $("<div class='custom-menu'><ul><li><a href='#'>save<a></li><li>exit</li></ul></div>")
        .appendTo("body")
        .css({top: event.pageY + "px", left: event.pageX + "px"});
}).bind("click", function(event) {
    $("div.custom-menu").hide();
});
</script>
</header>

	<!-- Navigation -->
