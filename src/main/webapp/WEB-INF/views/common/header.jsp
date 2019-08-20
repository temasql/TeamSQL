<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${cp}/resources/header/js/headerJs.js"></script>
<link href="${cp }/resources/header/css/headerStyle.css" rel="stylesheet">
<!-- 헤더 -->  
<div class="header-right">
<input type="hidden" id="hname" value="${USER_INFO.user_name }">
<input type="hidden" id="hemail" value="${USER_INFO.user_email }">
<div id="te" style="opacity: 0;position: absolute;z-index: 11;transform: translate(1600px, 60px);border: 1px solid #ccc;width: 294.67px;height: 205.818px;background: white;color: #000;line-height: normal;box-shadow: 0 2px 5px;display: none;">
	<div style="margin: 20px;">
		<img class="mcl" src="${cp}/user/profile?user_id=${USER_INFO.user_id}"style="width: 100px; height: 100px; margin-right: 20px; position: relative; border-radius: 100%;    display: inline-block; vertical-align: top;float: left;" >
		<div style="display: inline-block; vertical-align: top; width: 100px; height: 100px; text-align-last: center;margin-top: 25px;margin-right: 13px;">
			<p id="pname" class="mclna" style=" font-weight: bold; margin: -4px 0 1px 0;"></p>
			<p id="pemail" class="mclem" style="color: #666;margin-top: 15px;"></p>
		</div>
	</div>
	<div style=" background: #f5f5f5; border-top: 1px solid #ccc; border-color: rgba(0,0,0,.2); padding: 10px 0; width: 100%; display: table;padding: 10px; margin-top: -27px;">
		<div style="text-align: left;display: table-cell;margin: 20px 10px;">
			<a class="btn" href="${cp}/user/mypage">MyPage</a>
		</div>
		<div style="display: table-cell;text-align: right;">
			<a class="btn" href="${cp}/user/logout">로그아웃</a>
		</div>
	</div>
</div>
<img class="mouseEnt" id="thumbnail" src="${cp}/user/profile?user_id=${USER_INFO.user_id}" >
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
        
        <li><a href="#">에디터 기능 관리</a>
			<ul class="editor-header">
				<li><a href="${cp }/commonDomain/manager">도메인 관리</a></li>
				<li><a href="${cp }/commonTemplate/manager">템플릿 관리</a></li>
          </ul>
        </li>
        
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
