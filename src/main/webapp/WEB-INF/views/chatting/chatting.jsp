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
		$("#data").append(evt.data + "<br/>");
	};
	
	socket.onclose = function(evt) {
		$("#data").append("연결 종료");
	}
}

$(document).ready(function() {
	var userId = "${userId}";	//사용자 아이디를 파라미터로 받는다
	$("#userId").text(userId);
		
	initSocket("/socketChat?userId=" + userId);	//websocket 연결
});
</script>
</head>
<body>
	<h1 id="userId">테스트이름</h1>
	
	<div id="data" style=" width:500px; height:500px; border:1px solid black;"></div>
	<br>
	<input type="text" id="message"/>
	<button id="sendBtn">전송</button><br>
</body>
</html>