<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="${cp}/resources/main/css/mainCss.css" rel="stylesheet">
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
		
	})
</script>

<section id="mainDiv">
	<div id="leftLayout">
		
	   <!-- DB계정 -->	
	  <div id="leftTopLayout">	  
	  
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
				  <c:forEach items="${changedMainList}" var="changedMainList">
				  	<tr class="table-active clickEvent">
				  	  <td class="object_owner" scope="row">${changedMainList.object_owner}</td>
					  <td scope="row"><fmt:formatDate value="${changedMainList.exec_dtm}" pattern="yyyy-MM-dd a hh:mm:ss"/></td>
					  <td scope="row">${changedMainList.user_name}</td>
				  	</tr>
				  </c:forEach>
				</tbody>
			</table> 
	  	</div>
	  </div>
	</div>
	
	<!-- DB동향 -->
	<div id="rightLayout">
	
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