<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="${cp}/resources/chatBot/css/chatBotStyle.css" rel="stylesheet">
<link href="${cp}/resources/basicLib/css/basicLibStyle.css" rel="stylesheet">

<section class="chatBotRoom">
  <header class="chatBotHeader">
  	<div class="chatBotHeaderDiv">
  	챗봇
  	</div>
  </header>
  
  <article class="chatBotArticle">
  <ul class="chatBotUl">
   <li class="chatBotLi">
	 <span>챗봇</span>
	 <div class="boatAnswer">
	<p> 안녕하세요! 저는 TeamSQL 챗봇입니다.
	 무엇을 도와드릴까요?</p>
	</div>
   </li>
   <li class="userLi">
    <span>유저</span>
    <div class="userQuestion">
    	<p>테이블 생성하는 방법</p>
     </div>
   </li>
  </ul>
  </article>
  <form id="chatBotFrm"action="${cp}/chatBot/chatBot" method="post" >
  <footer class="chatBotFooter">
   <div class="chatBotFooterContainer">
     <div class="chatBotFooterBox">
      <input type="text" class="user" name="user">
      <button type="button"  class="">f</button>
     </div>
   </div>
  </footer>
   </form>
</section>