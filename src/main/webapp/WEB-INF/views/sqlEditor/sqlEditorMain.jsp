<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${cp}/resources/sqlEditor/css/sqlEditorStyle.css" rel="stylesheet">
<link href="${cp}/resources/sqlEditor/css/treeMenu.css" rel="stylesheet">
<link href="${cp}/resources/sqlEditor/css/rightClick.css" rel="stylesheet">
<script src="${cp}/resources/sqlEditor/js/sqlEditorJS.js"></script>
<script src="${cp}/resources/sqlEditor/js/treeMenu.js"></script>
<script src="${cp}/resources/sqlEditor/js/rightClick.js"></script>

<div id="editorView">
	<div id="leftBar">
		<div id="buttonGroup">
			<ol class="breadcrumb">
				<li class="breadcrumb-item">
					<img class="imgBtn" id="accountImg" title="DB계정 생성" src="${cp}/resources/img/plus.png"></li>
				<li class="breadcrumb-item">
					<img class="imgBtn" title="워크시트 저장" src="${cp}/resources/img/save.png"></li>
				<li class="breadcrumb-item">
					<img class="imgBtn" title="워크시트 불러오기" src="${cp}/resources/img/open.png"></li>
				<li class="breadcrumb-item">
					<img class="imgBtn" title="도메인" src="${cp}/resources/img/domain.png"></li>
				<li class="breadcrumb-item">
					<img class="imgBtn" title="템플릿" src="${cp}/resources/img/template.png"></li>
				<li class="breadcrumb-item">
					<img class="imgBtn" title="쿼리매니저" src="${cp}/resources/img/qmanager.png"></li>
				<li class="breadcrumb-item">
					<img class="imgBtn" id="runPlan" title="실행계획" src="${cp}/resources/img/explain.png"></li>
			</ol>
		</div>
		<nav id="dbAccountView">
			<c:forEach items="${accountList}" var="accountVO">
				<c:set var="ac_id" value="${accountVO.account_id}_${USER_INFO.user_id}"/>
				<div class="tree_box">
				    <div class="con">
				        <ul id="tree_menu" class="tree_menu">
				            <li class="depth_1"><strong class="accounts">${accountVO.account_id}</strong>
				                <ul class="depth_2" >
				                    <li>
				                        <a href="#none"><em>폴더</em> 테이블</a>
				                        <ul class="depth_3">
				                        	<c:forEach items="${tableList}" var="tableVO">
				                        		<c:if test="${tableVO.owner == ac_id}">
						                            <li><a href="">${tableVO.table_name}</a></li>
				                        		</c:if>
				                        	</c:forEach>
				                        </ul>
				                    </li>
				                    <li>
				                        <a href="#none"><em>폴더</em> 뷰</a>
				                        <ul class="depth_3">
				                        	<c:forEach items="${viewList}" var="viewVO">
				                        		<c:if test="${viewVO.owner == ac_id}">
						                            <li><a href="">${viewVO.view_name}</a></li>
				                        		</c:if>
				                        	</c:forEach>
				                        </ul>
				                    </li>
				                    <li>
				                        <a href="#none"><em>폴더</em> 인덱스</a>
				                        <ul class="depth_3">
				                            <c:forEach items="${indexList}" var="indexVO">
				                        		<c:if test="${indexVO.owner == ac_id}">
						                            <li><a href="">${indexVO.index_name}</a></li>
				                        		</c:if>
				                        	</c:forEach>
				                        </ul>
				                    </li>
				                    <li>
				                        <a href="#none"><em>폴더</em> 함수</a>
				                        <ul class="depth_3">
				                            <c:forEach items="${functionList}" var="functionVO">
				                        		<c:if test="${functionVO.owner == ac_id}">
						                            <li><a href="">${functionVO.object_name}</a></li>
				                        		</c:if>
				                        	</c:forEach>
				                        </ul>
				                    </li>
				                    <li>
				                        <a href="#none"><em>폴더</em> 트리거</a>
				                        <ul class="depth_3">
				                        	<c:forEach items="${triggerList}" var="triggerVO">
				                        		<c:if test="${triggerVO.owner == ac_id}">
						                            <li><a href="">${triggerVO.trigger_name}</a></li>
				                        		</c:if>
				                        	</c:forEach>
				                        </ul>
				                    </li>
				                    <li>
				                        <a href="#none"><em>폴더</em> 시퀀스</a>
				                        <ul class="depth_3">
				                        	<c:forEach items="${sequenceList}" var="sequenceVO">
				                        		<c:if test="${sequenceVO.sequence_owner == ac_id}">
						                            <li><a href="">${sequenceVO.sequence_name}</a></li>
				                        		</c:if>
				                        	</c:forEach>
				                        </ul>
				                    </li>
				                    <li>
				                        <a href="#none"><em>폴더</em> 프로시저</a>
				                        <ul class="depth_3">
				                            <c:forEach items="${procedureList}" var="procedureVO">
				                        		<c:if test="${procedureVO.owner == ac_id}">
						                            <li><a href="">${procedureVO.object_name}</a></li>
				                        		</c:if>
				                        	</c:forEach>
				                        </ul>
				                    </li>
				                </ul>
				            </li>
				        </ul>
				    </div>
				</div>
			</c:forEach>
		</nav>
	</div>
	
	<section id="worksheet">
		<textarea class="form-control" id="worksheetView" rows="16" cols="229"></textarea> <br>
		<span class="badge badge-dark" id="resultViewSpan">Result View</span> <br>
		<textarea class="form-control" id="resultView" rows="7" cols="229" readonly>asdjlas ajdlkasdjl</textarea>
	</section>
</div>

<!-- Account Modal -->
<div id="accountModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
    <span class="close">&times;</span>   
    <br><br>                  
	<form action="${cp}/sqlEditor/addAccount" method="post" id="accountAddfrm">
	  <fieldset>
	    <legend>DB계정 생성</legend>
	    <br><br>
	    <div class="form-group">
	      <label for="exampleInputEmail1">DB계정명</label>
	      <input type="text" class="form-control" id="accountName" name="account_id" placeholder="DB계정명">
	      <small class="form-text text-muted" id="accountNameHint">영문자, 숫자 포함 3~6자이며 첫 글자는 영문자로 시작</small>
	    </div>
	    <div class="form-group">
	      <label for="exampleInputPassword1">비밀번호</label>
	      <input type="password" class="form-control" id="accountPw" name="account_pw" placeholder="비밀번호">
	      <small class="form-text text-muted">4~12자로 구성되며 첫 글자는 영문자로 시작</small>
	    </div>
	    <div class="form-group">
	      <label for="exampleInputPassword1">채팅방이름</label>
	      <input type="text" class="form-control" id="chatRoomName" name="chatRoomName" placeholder="채팅방이름">
	      <small class="form-text text-muted">3~20자의 문자, 숫자, 특수문자로 입력</small>
	    </div>
	    <button type="button" id="addAccountBtn" class="btn btn-secondary">생성</button>
	    <input type="hidden" id="msg" value="${msg}"/>
	  </fieldset>
	</form>
  </div>
</div>

<!-- Account Delete Modal -->
<div id="accountDeleteModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
  	<span class="close">&times;</span>
  	<br><br>
  	<form action="${cp}/sqlEditor/deleteAccount" method="post" id="accountDeleteFrm">
	  <fieldset>
	    <legend>DB계정 삭제</legend>
	    <br><br>
	    <label for="exampleInputEmail1">DB계정 비밀번호</label>
	    <input type="password" class="form-control" id="deletePw" name="deletePw" placeholder="DB계정 비밀번호">
	    <input type="hidden" id="deleteId" name="deleteId"/>
	    <br><br>
	    <button type="button" id="accountDeleteBtn" class="btn btn-secondary">확인</button>
  	 </fieldset>
	</form>
  </div>
</div>

<!-- Account PW Find Modal -->
<div id="accountPwFindModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
  	<span class="close">&times;</span>
  	<br><br>
  	<form action="${cp}/sqlEditor/findPwAccount" method="post" id="accountPwFindFrm">
	  <fieldset>
	    <legend>DB계정 비밀번호 찾기</legend>
	    <br><br>
	    <label for="exampleInputEmail1">아이디</label>
	    <input type="text" class="form-control" id="user_id" name="user_id" placeholder="아이디">
	    <br><br>
	    <label for="exampleInputEmail1">이메일</label>
	    <input type="email" class="form-control" id="user_email" name="user_email" placeholder="이메일">
	    <br><br>
	    <label for="exampleInputEmail1">비밀번호는 회원님의 이메일로 보내집니다.</label>
	    <br><br>
	    <button type="button" id="accountPwFindBtn" class="btn btn-secondary">확인</button>
	    <input type="hidden" id="findId" name="findId"/>
  	 </fieldset>
	</form>
  </div>
</div>

<!-- Account PW Find Modal -->
<div id="accountPwUpdateModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
  	<span class="close">&times;</span>
  	<br><br>
  	<form action="${cp}/sqlEditor/updatePwAccount" method="post" id="accountPwUpdateFrm">
	  <fieldset>
	    <legend>DB계정 비밀번호 변경</legend>
	    <br><br>
	    <label for="exampleInputEmail1">기존 비밀번호</label>
	    <input type="password" class="form-control" id="originalPw" name="originalPw" placeholder="기존 비밀번호">
	    <br><br>
	    <label for="exampleInputEmail1">변경 비밀번호</label>
	    <input type="password" class="form-control" id="updatePw" name="updatePw" placeholder="변경 비밀번호">
	    <br><br>
	    <label for="exampleInputEmail1">변경 비밀번호 확인</label>
	    <input type="password" class="form-control" id="reUpdatePw" name="reUpdatePw" placeholder="변경 비밀번호 확인">
	    <br><br>
	    <button type="button" id="accountPwUpdateBtn" class="btn btn-secondary">확인</button>
	    <input type="hidden" id="updateId" name="updateId"/>
  	 </fieldset>
	</form>
  </div>
</div>

<form id="calendarFrm">
	<input type="hidden" id="acc_id" name="acc_id"/>
</form>

<!-- 우클릭 -->
<ul class="contextmenu">
  <li><span id="accountDeleteSpan">DB계정 삭제</span></li>
  <li><span id="accountPwFindSpan">DB계정 PW찾기</span></li>
  <li><span id="accountPwUpdateSpan">DB계정 PW변경</span></li>
  <li id="calendarPopup"><span>팀 일정관리</span></li>
</ul>
