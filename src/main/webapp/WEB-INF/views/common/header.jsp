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
  <div id="lnb">
 <nav id="lnb"class="navMenu">
	<ul>
	<c:choose>
	  <c:when test="${USER_INFO.user_right eq 'A' }">
	    <li><a href="${cp }/user/userManager">회원관리</a></li>
	    <li><a href="${cp }/blackList/blackListManager">블랙리스트관리</a></li>
	    <li><a href="${cp }/board/manager">게시판관리</a>
		  <ul>
			<c:forEach items="${boardList }" var="board">
				<c:if test="${board.board_use eq 'Y'}">
					<li><a href="${cp }/post/boardList?board_id=${board.board_id}">${board.board_name }</a></li>
				</c:if>
			</c:forEach>
		  </ul>
	    </li>
	    <li><a href="${cp}/quizRealMain">SQL퀴즈</a>
          <ul class="quiz-header">
          <li ><a href="/quizListView?quiz_right=02">OX퀴즈</a></li>
          <li ><a href="/quizListView?quiz_right=03">단답식퀴즈</a></li>
          <li ><a href="/quizListView?quiz_right=01">객관식퀴즈</a></li>
          <li ><a href="/quizListView?quiz_right=04">주관식퀴즈</a></li>
          </ul>
        </li>
        <li><a href="${cp }/commonDomain/manager">도메인 관리</a></li>
	    <li><a href="${cp }/user/adminManager">관리자관리</a>
	  </c:when>
	  
	  
	<c:otherwise>
	 <li><a href="${cp}/sqlEditor/sqlEditorMain">SQL에디터</a></li>
	 <li><a href="${cp}/history/historyList" id="current">DB변경이력관리</a></li>
	 <li><a href="${cp}/crew/crewManager">구성원관리</a></li>
	 <li ><a href="#">게시판</a>
	 	<ul >
			<c:forEach items="${boardList }" var="board">
				<c:if test="${board.board_use eq 'Y'}">
					<li><a href="${cp }/post/boardList?board_id=${board.board_id}">${board.board_name }</a></li>
				</c:if>
			</c:forEach>
		</ul>
	 </li>
	<li ><a  href="${cp}/quizRealMain">SQL퀴즈</a>
       <ul class="quiz-header">
          <li ><a href="/userQuiz?quiz_right=02">OX퀴즈</a></li>
          <li ><a href="/userQuiz?quiz_right=03">단답식퀴즈</a></li>
          <li ><a href="/userQuiz?quiz_right=01">객관식퀴즈</a></li>
          <li ><a href="/userQuiz?quiz_right=04">주관식퀴즈</a></li>
       </ul>
	</li>
	 </c:otherwise>
	</c:choose>
</ul>
 </nav>
<div></div>
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


</header>
<!-- Navigation -->
