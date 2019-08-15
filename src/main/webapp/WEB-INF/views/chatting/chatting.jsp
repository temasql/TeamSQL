<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chatting</title>
<link href="${cp}/resources/chatting/css/chatting.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.3.0/sockjs.min.js"></script>
<script src="/resources/chatting/js/chattingJS.js"></script>
<script>
var socket;
function initSocket(url) {
	socket = new SockJS("/socketChat");
	
	socket.onmessage = function(evt) {
// 		$("#data").append(evt.data + "<br/>");
		var data = evt.data;
		var sessionid = null;
		var message = null;
		
		var todate = new Date();
		var year = todate.getFullYear();
		var month = todate.getMonth() + 1;
		var date = todate.getDate();
		var hours = todate.getHours();
		var seconds = todate.getSeconds();
		
		var today = year+"."+month+"."+date+" "+hours+":"+seconds;
		
		var strArray = data.split(":");
		
		for(var i=0; i<strArray.length; i++){
			console.log("str["+i+"]: " + strArray[i])
		}
		
		//current session id//
		var currentuser_session = $("#sessionuserid").val();
		console.log("현재 session Id : " + currentuser_session);
		
		sessionid = strArray[0];	//현재 메세지를 보낸 사람의 세션 등록
		message = strArray[1];		//현재 메세지 저장
		
		//나와 상대방이 보낸 메세지를 구분하여 영역을 나눈다.
		if(sessionid == currentuser_session){
			var printHTML = "<div class='well'>";
			printHTML += "<div class='userInfo'>";
			printHTML += "Me <div class='dt'>" +today + "</div>";
			printHTML += "</div><br><br>";
			printHTML += "<div class='alert alert-info'>";
			printHTML += "<strong>" + message + "</strong>";
			printHTML += "</div>";
			printHTML += "</div><br>";
			
			$("#data").append(printHTML);
		}else{
			var printHTML = "<div class='well'>";
			printHTML += "<div class='alert alert-warning'>";
			printHTML += "<strong>[" + sessionid + "] : " + message + "</strong>";
			printHTML += "</div>";
			printHTML += "</div><br>";
			
			$("#data").append(printHTML);
		}
	};
	
	socket.onclose = function(evt) {
		$("#data").append("${userId}님의 연결이 종료되었습니다.");
	}
}

$(document).ready(function() {
	var userId = "${userId}";	//사용자 아이디를 파라미터로 받는다
	$("#userId").text($("#account").val());
	var userChat = "${userChat}";
	var account_id = "${account_id}";
	account_id = account_id.substring(0, account_id.lastIndexOf("_"));
	
	$("select[name=account]").val(account_id);
	
	$("#data").append(userChat);
	
	initSocket("/socketChat?userId=" + userId);	//websocket 연결
});
</script>
</head>
<body>
	채팅방 명 : <h1 id="userId"></h1>
	계정명 : <select id="account" name="account" class="form-control">
		<c:forEach begin="0" end="${crewList.size()-1}" step="1" var="i" >
			<c:set var="crewVO" value="${crewList.get(i)}"/>
			<c:set var="crewVOCopy" value="${crewListCopy.get(i)}"/>
				<option class="accountNM" value="${crewVO.account_id_fk}">${crewVOCopy.account_id_fk}</option>
		</c:forEach>
	</select>
	<br><br>
	<div id="data" style=" width:500px; height:500px; border:1px solid black;"></div>
	<br>
	<input type="text" id="message" autofocus="autofocus"/>
	<button id="sendBtn" class="btn" style="background: black; color: white;">전송</button><br>
	<input type="hidden" id="sessionuserid" value="${userId}">
	
	<form id="frm" action="/teamChat/changeAccount" method="post">
		<input type="hidden" id="account_id" name="account_id">
		<input type="hidden" id="roomId" name="chat_room_id" value="${teamChatRoomVo.chat_room_id}">
	</form>
</body>
</html>