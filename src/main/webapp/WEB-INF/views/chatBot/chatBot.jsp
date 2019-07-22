<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${cp}/resources/chatBot/css/chatBotStyle.css" rel="stylesheet">
<link href="${cp}/resources/basicLib/css/basicLibStyle.css" rel="stylesheet">
<script src="${cp}/resources/chatBot/js/chatBotJS.js"></script>

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
    	<p class="userMSG" id="idUserMSG">테이블 생성하는 방법</p>
     </div>
   </li>
  </ul>
  </article>
  
	  <footer class="chatBotFooter">
	   <div class="chatBotFooterContainer">
	     <div class="chatBotFooterBox">
	     <textarea class="userInput" id="userInput"></textarea>
	      <button id="questionBtn"type="button" class="btn btn-secondary">입력</button>
	     </div>
	   </div>
	  </footer>
  
</section>