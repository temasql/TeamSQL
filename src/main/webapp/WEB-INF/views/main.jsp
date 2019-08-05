<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="${cp}/resources/main/css/mainCss.css" rel="stylesheet">
<link href="${cp}/resources/main/css/mainBodyStyle.css" rel="stylesheet">
<script type="text/javascript" src="${cp}/resources/main/js/mainJs.js"></script>

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
		
		<c:forEach items="${crewMap.crewList}" var="crew" end="${crewMap.acSize}" >
			$("#crewTbody").append("<tr>")
			$("#crewTbody").append("<td class='sqlTd' scope='row'>${crew.account_id_fkSlice}<input type='hidden' class='account_id' value='${crew.account_id_fk}'/></td>")
			$("#crewTbody").append("<td class='crewTd' scope='row'>${crew.user_id_fk}</td>")
			$("#crewTbody").append("</tr>")
		</c:forEach>
		$(document).on("click", ".sqlTd", function(){location.href="/sqlEditor/sqlEditorMain"})
		$(document).on("click", ".crewTd", function(){location.href="/crew/crewManager"})
	})
</script>

<section id="mainDiv">
	<div id="leftLayout">
	   <!-- DB계정 -->	
	  <div id="leftTopLayout">	  
	  <table class="table table-hover">
	 			<thead>
	    			<tr>
				      <th scope="col">DB계정</th>
				      <th scope="col">구성원 이름</th>
	   				</tr>
				</thead>
				<tbody id="crewTbody">
				</tbody>
			</table> 
	  </div>
	  
	  <!-- DB변경이력 -->
	  <div id="leftBottomLayout">
	  	<div id="bottomDiv">
		   <table class="table table-hover" id="tableContainer">
	 			<thead>
	    			<tr>
				      <th scope="col">계정명</th>
				      <th scope="col">마지막 변경 일시</th>
				      <th scope="col">생성자</th>
	   				</tr>
				</thead>
				<tbody id="changedMainBody">
				  <c:forEach items="${changedMainList}" var="cVO">
				  	<tr class="table-active clickEvent">
				  	  <td class="object_owner" scope="row">${cVO.object_ownerSlice}<input type="hidden" class="original_owners" value="${cVO.object_owner }"/></td>
					  <td scope="row"><fmt:formatDate value="${cVO.exec_dtm}" pattern="yyyy-MM-dd a hh:mm:ss"/></td>
					  <td scope="row">${cVO.user_name}</td>
				  	</tr>
				  </c:forEach>
				</tbody>
			</table> 
	  	</div>
	  </div>
	</div>
	
	<!-- DB동향 -->
	<div id="rightLayout">
		<div id="itNewsTitle">
			<span id="itNewsSpan">IT NEWS</span>
		</div>
		<c:forEach items="${itNewsList}" var="cList">
			<c:forEach items="${cList}" var="cVO">
				<div class="itNewsDiv">
					<a href="${cVO.link}" target="_blank">
						<img class="itNewsImg" src="${cVO.img}">
					</a>
					<a href="${cVO.link}" target="_blank">
						<h3 class="itNewsTitle">${cVO.title}</h3>
					</a>
					<h5 class="itNewsContent">${cVO.content}</h5>
				</div>
			</c:forEach>
		</c:forEach>
	</div>
	
</section>

<form action="${cp }/history/historyDetailView" id="frm" method="POST">
	<input type="hidden" id="object_owner" name="object_owner">
</form>

<form id="mainForm" action="${cp}/crew/crewMain">
	<input type="hidden" id="inviteCheck" name="inviteCheck">
	<input type="hidden" id="invite_id" name="invite_id">
	<input type="hidden" id="account_id_fk" name="account_id_fk">
	<input type="hidden" id="user_id_fk" name="user_id_fk">
</form>