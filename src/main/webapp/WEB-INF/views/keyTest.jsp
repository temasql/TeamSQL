<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>
<script>
$(function(){
	webSocket.init({ url: "<c:url value=/" + socket + " />" });

	
	$('#btnSend').on('click',function (evt){
		evt.preventDefault();
		if(socket.readyState !== 1) return;
		let msg = $('input#msg').val();
		socket.send(msg);
	});
	connect();
});
var isCtrl = false;
	document.onkeydown=function(e){
		// Ctrl 키
		if(e.which == 17) 
			isCtrl = true;
		// 엔터 키
		if(e.which == 13 && isCtrl == true){
			alert("hi")
			isCtrl = false;
		}
		
	}

	webSocket = { init: function (oParam) { this._url = oParam.url || ""; this._initSocket(); this._initEvent(); }, _initSocket: function () { this._socket = new SockJS(this._url); this._socket.onmessage = function (evt) { $("#data").append(evt.data + "<br/>"); }; this._socket.onclose = function (evt) { $("#data").append("연결 끊김"); } }, _initEvent: function () { $("#sendBtn").on("click", (function() { var msg = $("#message").val(); this._socket.send(msg); }).bind(this)); } }

	
</script>

<script>
var socket =null;
function connect(){
	var ws = new WebSocket("ws://localhost:80/echo");
	socket = ws;
	ws.onopen = function(){
		console.log("Info: connection opened.");
		setTimeout( function() {connect();}, 1000);
	};
	
	ws.onmessage = function (event){
		console.log(event.data+'\n');
	};
	ws.onclose = function (event) {
		console.log('Info : connection closed');
	};
	ws.onerror = function (err) {console.log('Error :', err);};
	
}
	

</script>

</head>
<body>
	1

	<input type="text" id="msg">
	<input type="submit" id="btnSend" value="hi">
</body>
</html>