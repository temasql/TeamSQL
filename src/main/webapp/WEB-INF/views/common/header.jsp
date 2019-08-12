<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${cp}/resources/header/js/headerJs.js"></script>
<!-- 헤더 -->
<div class="header-right">
	<a class="aHeader"  href="${cp}/user/mypage"><img id="thumbnail" src="${cp}/user/profile?user_id=${USER_INFO.user_id}" alt="Mypage" title="Mypage">MyPage</a>
	<a class="aHeader" href="${cp}/user/logout">로그아웃</a>
</div>
<!-- 헤더 -->
<header>
  <!-- Navigation -->
 <nav class="navMenu">
  <div class="divNav">
 	<div class="navLogo">
 	<a class="aLogo"  id ="mainLogo" href="${cp}/main"><img id="logo" src="${cp}/resources/img/logo.png" alt="Main" title="Main"></a>
 	</div>
	<ul class="ul-header-one">
		<c:choose>
			<c:when test="${USER_INFO.user_right eq 'A' }">
				<li><a class="aNav" href="${cp }/user/userManager">회원관리</a></li>
				<li><a class="aNav" href="${cp }/blackList/blackListManager">블랙리스트관리</a></li>
				<li class="one"><a class="aNav" href="${cp }/board/manager">게시판관리</a>
					<ul class="ul-header-two">
						<c:forEach items="${boardList }" var="board">
							<c:if test="${board.board_use eq 'Y'}">
								<li class="li-two"><a class="two-menu" href="${cp }/post/boardList?board_id=${board.board_id}">${board.board_name }</a></li>
							</c:if>
						</c:forEach>	
					</ul>
				</li>
				<li><a class="aNav" href="/quizMain">퀴즈관리</a></li>
				<li><a class="aNav" href="${cp }/user/adminManager">관리자관리</a></li>
			</c:when>
			<c:otherwise>
			<li><a class="aNav" href="${cp}/sqlEditor/sqlEditorMain">SQL에디터</a></li>
		  		<li><a class="aNav" href="${cp}/history/historyList">DB변경이력관리</a></li>
				<li><a class="aNav" href="${cp}/crew/crewManager">구성원관리</a></li>
				<li class="one"><a class="aNav" href="#">게시판</a>
					<ul class="ul-header-two">
						<c:forEach items="${boardList }" var="board">
							<c:if test="${board.board_use eq 'Y'}">
								<li class="li-two"><a class="two-menu" href="${cp }/post/boardList?board_id=${board.board_id}">${board.board_name }</a></li>
							</c:if>
						</c:forEach>
					</ul>
				</li>
				<li><a class="aNav" href="${cp}/quizMain">SQL퀴즈</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
	</div>

<!-- 챗봇 -->	
<div class="chatbot">
	<p class="rightImg">
		<a href='javascript:;' class="aRight" id="chatBotPopup">
			<img class="imgRight" alt="챗봇" src="${cp }/resources/img/chatbot.png" title="챗봇">
		</a>
	</p>
</div> 
<!-- 챗봇 -->	
	
<!-- 그룹채팅 -->
<div class="groupchat">
	<p class="rightImg">
		<a href="javascript:;" class="aRight" id="groupChat" >
			<img class="imgRight" alt="채팅" src="${cp }/resources/img/groupchat.png" title="채팅">
		</a>
	</p>
</div>
<!-- 그룹채팅 -->

</nav>
</header>
<!-- Navigation -->
