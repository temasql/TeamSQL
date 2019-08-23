<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chatting</title>
<link href="${cp}/resources/chatting/css/chatting.css" rel="stylesheet">
<!-- 구글 폰트 -->
<link rel="stylesheet" type="text/css" href="https://cdn.rawgit.com/innks/NanumSquareRound/master/nanumsquareround.min.css">
<link href="https://fonts.googleapis.com/css?family=Alegreya+Sans&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.3.0/sockjs.min.js"></script>
<script src="/resources/chatting/js/chattingJS.js"></script>
<script src="${cp}/resources/loginBootstrap/vendor/bootstrap/js/bootstrap.js"></script>
<script src="${cp}/resources/loginBootstrap/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="${cp}/resources/loginBootstrap/vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="${cp}/resources/loginBootstrap/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<script>
var socket;

function initSocket(url) {
	socket = new SockJS("/socketChat");
	
	socket.onmessage = function(evt) {
// 		$("#data").append(evt.data + "<br/>");
		console.log(evt);
		data = evt.data;
		var sessionid = null;
		var message = null;
		
		var todate = new Date();
		var year = todate.getFullYear().toString();
		var month = todate.getMonth() + 1;
		var date = todate.getDate();
		var hours = todate.getHours();
		var min = todate.getMinutes();
		
		//한자리수로 표현되는 걸 두자릿수로 표현방식을 바꾸기
		year = year.substring(2,4);
		
		month = ("0" + month).slice(-2);
		date = ("0" + date).slice(-2);
		hours = ("0" + hours).slice(-2);
		hours = ("0" + hours).slice(-2);
		min = ("0" + min).slice(-2);
		
		var today = year+"."+month+"."+date+" "+hours+":"+min;
		
		var strArray = data.split(":");
		
		for(var i=0; i<strArray.length; i++){
			console.log("str["+i+"]: " + strArray[i])
		}
		
		//current session id//
		var currentuser_session = $("#sessionuserid").val();
		console.log("현재 session Id : " + currentuser_session);
		
		sessionid = strArray[0];	//현재 메세지를 보낸 사람의 세션 등록
		message = strArray[1];		//현재 메세지 저장
		
		//접속 여부 이미지 바꾸기
		if(data.startsWith("accountConnect&")){
			var str = data.substring(data.indexOf("&")+1, data.length);
			console.log("문자열 테스트 : " + str);
			str = str.split(",");
			var text = document.getElementsByClassName("userListNM");
			console.log("text 길이" + text.length);
			console.log("str 길이" + text.length);
			var imgArr = document.getElementsByClassName("onOff");
			
			
			for(var i=0; i<text.length; i++){
				console.log("가나다?" + text[i].innerText);
				for(var j=0; j<str.length; j++){
					if(text[i].innerText == str[j]){
						console.log("str 배열 값 : "+str[j]);
						imgArr[i].setAttribute('src', '/img/chatting/green.png');
	// 					var connectOF = document.getElementsByClassName("connectOF");
	// 					$(".connectOF").get(i).html('<img class="onOff" src="/img/chatting/green.png">');
					}
					
				}
			}
		}else if(data.startsWith("accountDisConnect&")){
			var str = data.substring(data.indexOf("&")+1, data.length);
			console.log("문자열 테스트 : " + str);
			str = str.split(",");
			var text = document.getElementsByClassName("userListNM");
			console.log("text 길이" + text.length);
			console.log("str 길이" + text.length);
			var imgArr = document.getElementsByClassName("onOff");
			
			
			for(var i=0; i<text.length; i++){
				console.log("가나다?" + text[i].innerText);
				for(var j=0; j<str.length; j++){
					if(text[i].innerText == str[j]){
						console.log("str 배열 값 : "+str[j]);
						imgArr[i].setAttribute('src', '/img/chatting/red.png');
	// 					var connectOF = document.getElementsByClassName("connectOF");
	// 					$(".connectOF").get(i).html('<img class="onOff" src="/img/chatting/green.png">');
					}
					
				}
			}
		}else{// 채팅 입력한 내용 append() 하기
			var printHTML = "";
			//나와 상대방이 보낸 메세지를 구분하여 영역을 나눈다.
			if(sessionid == currentuser_session){
				printHTML += "<div class='myDiv'>";
				printHTML += "Me <div class='myDt'>" +today + "</div>";
				printHTML += "<p class='Mmessage'>" + message + "</p>";
				printHTML += "</div>";
				
				$("#data").append(printHTML);
				
				$("#data").scrollTop($("#data")[0].scrollHeight);
				$(".chattingArticle").scrollTop($(".chattingArticle")[0].scrollHeight);
			}else{
				printHTML += "<div class='youDiv'>";
				printHTML += "<p class='youId'>"+ sessionid+"</p>";
				printHTML += "<strong class='Ymessage'>"+ message + "</strong>";
				printHTML += "<div class='youDt'>" + today + "</div>";
				printHTML += "</div>";
				
				$("#data").append(printHTML);
				
				$("#data").scrollTop($("#data")[0].scrollHeight);
				$(".chattingArticle").scrollTop($(".chattingArticle")[0].scrollHeight);
			}
		}
	};
	
	socket.onclose = function(evt) {
		$("#data").append("${userId}님의 연결이 종료되었습니다.");
	}
}

$(document).ready(function() {
	var userId = "${userId}";	//사용자 아이디를 파라미터로 받는다
	$("#userId").text("${chat_room_name}");
	var userChat = "${userChat}";
	var userList = "${userList}";
	var account_id = "${account_id}";
	
// 	account_id = account_id.substring(0, account_id.lastIndexOf("_"));
	
	$("select[name=account]").val(account_id);
	
	$("#data").append(userChat);
	$("#memberSelect").html(userList);
	var connectOF = document.getElementsByClassName("connectOF");
	console.log(connectOF[0].innerHTML);
	
	$("#data").scrollTop($("#data")[0].scrollHeight);
	$(".chattingArticle").scrollTop($(".chattingArticle")[0].scrollHeight);
	
	initSocket("/socketChat?userId=" + userId);	//websocket 연결
});
</script>
</head>
<body>
	<br><br>
	<div id="memberWrap" style="float: right;">
        <div id="memberList">
            <div id="memberHeader">현재 접속 인원</div>
            <div id="memberSelect">
<!--             	<div class="memberEl"><label class="userListNM">test1</label><span class="connectOF"><img class="onOff" src="/img/chatting/red.png"></span></div> -->
<!--             	<div class="memberEl"><label class="userListNM">test2</label><span class="connectOF"><img class="onOff" src="/img/chatting/red.png"></span></div> -->
<!--             	<div class="memberEl"><label class="userListNM">test3</label><span class="connectOF"><img class="onOff" src="/img/chatting/red.png"></span></div> -->
<!--             	<div class="memberEl"><label class="userListNM">test4</label><span class="connectOF"><img class="onOff" src="/img/chatting/red.png"></span></div> -->
            </div>
        </div>
    </div>
	<section>
		<article class="chattingArticle" style="float: left; margin-left: 1%;">
			<div id="userId" style="width:463px;"></div>
			<div id="accountDiv">계정명 : 
				<select id="account" class="form-control" name="account" class="form-control">
					<c:forEach begin="0" end="${crewList.size()-1}" step="1" var="i" >
						<c:set var="crewVO" value="${crewList.get(i)}"/>
						<c:set var="crewCopy" value="${crewListCopy.get(i)}"/>
							<option class="accountNM" value="${crewVO.account_id_fk}">${crewCopy}</option>
					</c:forEach>
				</select>
			</div>
			<div id="data" style="height:500px; width: 463px; border:1px solid #f1f0f0;"></div>
			<div id="chatFooter">
				<input type="text" id="message" autofocus="autofocus" placeholder="메세지 입력"/>
				<button id="sendBtn" class="btn">보내기</button><br>
			</div>
		</article>
	</section>
	<input type="hidden" id="sessionuserid" value="${userId}">
	
	<form id="frm" action="/teamChat/changeAccount" method="post">
		<input type="hidden" id="account_id" name="account_id">
		<input type="hidden" id="roomId" name="chat_room_id" value="${teamChatRoomVo.chat_room_id}">
	</form>
</body>
</html>