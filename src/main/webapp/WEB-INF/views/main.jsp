<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="${cp}/resources/main/css/mainCss.css" rel="stylesheet">

<script type="text/javascript">

	$(function (){

		var list = new Array();
		<c:forEach items="${inviteList}" var="invite">
			var result = confirm("${invite.account_id_fk}에서 초대장이 왔습니다 수락 하시겠습니까?")
			if(result){
				$("#inviteCheck").val("true");
				$("#invite_id").val("${invite.invite_id}")
				$("#account_id_fk").val("${invite.account_id_fk}")
				$("#user_id_fk").val("${invite.user_id_fk}")
				$("#mainForm").submit();
			}else{
				$("#invite_id").val("${invite.invite_id}")
				$("#inviteCheck").val("false");
				$("#mainForm").submit();
			}
		</c:forEach>
		
// 		for(var i = 0; i < list.length; i++){
			
// 			var result = confirm("초대장이 왔습니다 수락 하시겠습니까?")
// 			if(result){
// 				$("#inviteCheck").val("true");
// 				$("#invite").val(list[i].account_id_fk)
// 				alert()
// 				$("#mainForm").submit();
// 			}else{
// 				$("#inviteCheck").val("false");
// // 				$("#mainForm").submit();
// 			}
// 		}
	})
</script>

<div id="mainDiv">
	<h2>main</h2>
</div>


<form id="mainForm" action="${cp}/crew/crewMain">
	<input type="hidden" id="inviteCheck" name="inviteCheck">
	<input type="hidden" id="invite_id" name="invite_id">
	<input type="hidden" id="account_id_fk" name="account_id_fk">
	<input type="hidden" id="user_id_fk" name="user_id_fk">
</form>