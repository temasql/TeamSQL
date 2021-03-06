<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="/WEB-INF/views/sqlEditor/modal/triggerPackage.jsp" %>
<%@include file="/WEB-INF/views/sqlEditor/modal/sequencePackage.jsp" %>
<%@include file="/WEB-INF/views/sqlEditor/modal/accountPackage.jsp" %>
<%@include file="/WEB-INF/views/sqlEditor/modal/tablePackage.jsp" %>
<%@include file="/WEB-INF/views/sqlEditor/modal/functionPackage.jsp" %>
<%@include file="/WEB-INF/views/sqlEditor/modal/ProcedurePackage.jsp" %>
<%@include file= "/WEB-INF/views/sqlEditor/modal/domainPackage.jsp" %>
<%@include file="/WEB-INF/views/sqlEditor/modal/templatePackage.jsp" %>
<%@include file="/WEB-INF/views/sqlEditor/modal/viewPackage.jsp" %>
<%@include file="/WEB-INF/views/sqlEditor/modal/indexPackage.jsp" %>	

<link href="${cp}/resources/sqlEditor/css/sqlEditorStyle.css" rel="stylesheet">
<link href="${cp}/resources/sqlEditor/css/treeMenu.css" rel="stylesheet">
<link href="${cp}/resources/sqlEditor/css/rightClick.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen" href="${cp}/resources/jquery-ui/jquery-ui.min.css">
<link rel="stylesheet" type="text/css" media="screen" href="${cp}/resources/jqGrid/css/ui.jqgrid.css">

<div id="editorView">
	<div id="leftBar">
		<div>
			<ol class="breadcrumb">
				<li class="breadcrumb-item">
					<img class="imgBtn" id="accountImg" title="DB계정 생성" src="${cp}/resources/img/plus.png"></li>
				<li class="breadcrumb-item">
					<img class="imgBtn" id="worksheetSave" title="워크시트 저장" src="${cp}/resources/img/save.png"></li>
				<li class="breadcrumb-item">
					<img class="imgBtn" id="worksheetLoad" title="워크시트 불러오기" src="${cp}/resources/img/open.png"></li>
				<li class="breadcrumb-item">
					<img class="imgBtn" id="domainId" title="도메인" src="${cp}/resources/img/domain.png"></li>
				<li class="breadcrumb-item">
					<img class="imgBtn" id="templateId" title="템플릿" src="${cp}/resources/img/template.png"></li>
				<li class="breadcrumb-item">
					<img class="imgBtn" id="queryManager" title="쿼리매니저" src="${cp}/resources/img/qmanager.png"></li>
				<li class="breadcrumb-item">
					<img class="imgBtn" id="runPlan" title="실행계획" src="${cp}/resources/img/explain.png"></li>
				<li class="breadcrumb-item">
					<img class="imgBtn" id="testData" title="테스트 데이터" src="${cp}/resources/img/flask.png"></li>
				<li class="breadcrumb-item">
					<img class="imgBtn" id="refresh" title="새로고침" src="${cp}/resources/img/refresh.png"></li>
			</ol>
		</div>
		<nav id="dbAccountView">
			<input type="hidden" id="userId" value="${USER_INFO.user_id}"/>
			<c:forEach items="${myAccountList}" var="accountId">
				<c:set var="account_id" value="${fn:substringBefore(accountId, '_')}" />
				<c:set var="ac_id" value="${fn:toUpperCase(accountId)}" />
				<div class="tree_box">
				    <div class="con">
				        <ul id="tree_menu" class="tree_menu">
				            <li class="depth_1">
				            	<input type="radio" class="radioClass" name="radioBtn" id="${ac_id}"/>
				            	<input type="hidden" id="radioId">
				            	<strong class="accounts">
				            		${account_id}
			            		<input type="hidden" id="acco_id2" value="${accountId}"/>
				            	</strong>
			            		<input type="hidden" id="acco_id" value="${accountId}"/>
				                <ul class="depth_2" >
				                    <li>
				                        <a class="tablePackage" href="#none"><em>폴더</em> 테이블</a>
				                        <ul class="depth_3">
				                        	<c:forEach items="${tableList}" var="tableVO">
				                        		<c:if test="${tableVO.owner == ac_id}">
						                            <li class="tables"><a href="#none">${tableVO.table_name}</a></li>
				                        		</c:if>
				                        	</c:forEach>
				                        </ul>
				                    </li>
				                    <li>
				                        <a class="viewPackage" href="#none"><em>폴더</em> 뷰
				                        	<input type="hidden" id="seok_id" value="${accountId}"/>
				                        </a>
				                        <ul class="depth_3">
				                        	<c:forEach items="${viewList}" var="viewVO">
				                        		<c:if test="${viewVO.owner == ac_id}">
						                            <li><a class="views" href="#none">${viewVO.view_name}</a></li>
				                        		</c:if>
				                        	</c:forEach>
				                        </ul>
				                    </li>
				                    <li>
				                        <a class="indexPackage" href="#none"><em>폴더</em> 인덱스
				                        	<input type="hidden" id="hogil_index1" value="${accountId}"/>
				                        </a>
				                        <ul class="depth_3">
				                            <c:forEach items="${indexList}" var="indexVO">
				                        		<c:if test="${indexVO.owner == ac_id}">
						                            <li>
						                            	<a class="indexs" href="#none">${indexVO.index_name}
						                            		<input type="hidden" id="hogil_index2" value="${accountId}"/>
						                            	</a>
					                            	</li>
				                        		</c:if>
				                        	</c:forEach>
				                        </ul>
				                    </li>
				                    <li>
				                        <a class="functionPackage" href="#none"><em>폴더</em> 함수
				                        	<input type="hidden" id="bum" value="${accountId}"/>
				                        </a>
				                        <ul class="depth_3">
				                            <c:forEach items="${functionList}" var="functionVO">
				                        		<c:if test="${functionVO.owner == ac_id}">
						                            <li>
						                            	<a class="functions" href="#none">${functionVO.object_name}
							                            	<input type="hidden" id="bumbum" value="${accountId}"/>
						                            	</a>
						                            </li>
				                        		</c:if>
				                        	</c:forEach>
				                        </ul>
				                    </li>
				                    <li>
				                        <a class="tiggerPackage" href="#none"><em>폴더</em> 트리거
				                        	<input type="hidden" id="bumper_id" value="${accountId}"/>
				                        </a>
				                        <ul class="depth_3">
				                        	<c:forEach items="${triggerList}" var="triggerVO">
				                        		<c:if test="${triggerVO.owner == ac_id}">
						                            <li>
						                            	<a class="triggers" href="#none">${triggerVO.trigger_name}
						                            		<input type="hidden" id="bum_id" value="${accountId}"/>
						                            	</a>
						                            </li>
				                        		</c:if>
				                        	</c:forEach>
				                        </ul>
				                    </li>
				                    <li>
				                        <a class = "sequencePackage" href="#none"><em>폴더</em> 시퀀스
				                        	<input type="hidden" id="hogil_id" value="${accountId}"/>
				                        </a>
				                        <ul class="depth_3">
				                        	<c:forEach items="${sequenceList}" var="sequenceVO">
				                        		<c:if test="${sequenceVO.sequence_owner == ac_id}">
						                            <li class="sequences">
						                            	<a href="#none">${sequenceVO.sequence_name}
						                            		<input type="hidden" id="hogil_id2" value="${accountId}"/>
						                            	</a>
						                            </li>
				                        		</c:if>
				                        	</c:forEach>
				                        </ul>
				                    </li>
				                    <li>
				                        <a class="procedurePackage" href="#none"><em>폴더</em> 프로시저
				                        	<input type="hidden" id="bum2" value="${accountId}"/>
				                        </a>
				                        <ul class="depth_3">
				                            <c:forEach items="${procedureList}" var="procedureVO">
				                        		<c:if test="${procedureVO.owner == ac_id}">
						                            <li class="procedures">
						                            	<a href="#none">${procedureVO.object_name}
						                            		<input type="hidden" id="bumbum2" value="${accountId}"/>
						                            	</a>
						                            </li>
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
		<div id="hiddenDiv"></div>
		<div id="sqlEditorView">
			<pre id="editor"></pre>
		</div>
		<div id="resultViewDiv">
			<span id="clearSpan">CLEAR</span>|
			<span class="resultSpans" id="resultViewSpan">Result View</span>|
			<span class="resultSpans" id="scriptViewSpan">Script View</span>|
			<br>
		</div>
		<div id="groupDives">
			<div id="resultTableDiv">
				<table id="resultTable"></table>
			</div>
			<div id="pager"></div>
			<div class="groupDiv" id="seconedDiv">
				<textarea class="form-control" id="scriptViewArea" rows="10" cols="229" readonly></textarea>
			</div>
		</div>
		
	</section>
	
</div>

<!-- File Load Modal -->
<div id="worksheetLoadModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
  	<span class="close">&times;</span>
  	<br><br>
	  <fieldset>
	    <legend>워크시트 불러오기</legend>
	    <br><br>
        <input type="file" class="form-control-file" id="worksheetLoadFile">
	    <br><br>
	    <button type="button" id="worksheetLoadBtn" class="btn btn-secondary">확인</button>
  	 </fieldset>
  </div>
</div>

<!-- File Load Modal -->
<div id="testDataModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content">
  	<span class="close">&times;</span>
  	<br><br>
  	<form id="tdFrm">
	  <fieldset>
	    <legend>테스트 데이터 생성</legend>
	    <br><br>
		<img class="imgBtn" id="testDataAddImg" src="${cp}/resources/img/add.png">&nbsp;&nbsp;
		<img class="deleteBtn" id="testDataDeleteImg" src="${cp}/resources/img/delete.png">
		<br><br>
    	<table class="table table-hover" id="tdTable">
			<colgroup>
				<col width="40%">
				<col width="40%">
				<col width="20%">
			</colgroup>
			<thead>
				<tr>
					<th scope="col">컬럼명</th>
					<th scope="col">데이터타입</th>
					<th scope="col">NULL여부</th>
				</tr>
			</thead>
			<tbody id="testDataTable"></tbody>
		</table>
		<br><br>
		<label for="exampleInputEmail1">테이블명</label>
		<input type="text" class="form-control" id="table_name" name="table_name" placeholder="테이블명">
		<br><br>
		<label for="exampleInputEmail1">수량</label>
		<input type="number" class="form-control" id="dataCnt" name="dataCnt" placeholder="1~100" min="1" max="100">
		<br><br>
		<input type="hidden" id="testDataHidden"/>
		<button type="button" id="createTestDataBtn" class="btn" style="background: black; color: white;">생성</button>
  	 </fieldset>
  	</form>
  </div>
</div>

<form id="calendarFrm">
	<input type="hidden" id="acc_id" name="acc_id"/>
</form>

<!-- DB계정 우클릭 -->
<ul class="contextmenu">
  	<li><span id="accountDeleteSpan">DB계정 삭제</span></li>
  	<li><span id="accountPwFindSpan">DB계정 PW찾기</span></li>
  	<li><span id="accountPwUpdateSpan">DB계정 PW변경</span></li>
  	<li id="calendarPopup"><span>팀 일정관리</span></li>
</ul>

<!-- 트리거 우클릭 -->
<ul class="triggerPackageMenu">
  	<li><span id="createTriggerSpan">트리거 생성</span></li>
</ul>

<!-- 테이블 패키지 우클릭 -->
<ul class="tablePackageMenu">
  <li><span id="createTableSpan">테이블 생성</span></li>
</ul>
<!-- 뷰 패키지 우클릭 -->
<ul class="viewPackageMenu">
  <li><span id="createViewSpan">뷰 생성</span></li>
</ul>
<!-- 뷰 우클릭 -->
<ul class="viewMenu">
  <li><span id="readViewSpan">뷰 조회</span></li>
  <li><span id="updateViewSpan">뷰 편집</span></li>
  <li><span id="deleteViewSpan">뷰 삭제</span></li>
</ul>
<!-- 테이블 우클릭 -->
<ul class="tableMenu">
  <li><span id="readTableSpan">테이블 조회</span></li>
  <li><span id="updateTableSpan">테이블 편집</span></li>
  <li><span id="deleteTableSpan">테이블 삭제</span></li>
  <li><span id="exportTable">테이블 EXPORT</span></li>
  <li><span id="createVO">자바 모델 생성</span></li>
</ul>

<!-- 시퀀스 패키지 우클릭 -->
<ul class="sequencePackageMenu">
  <li><span id="createsequenceSpan">시퀀스 생성</span></li>
</ul>
<!-- 시퀀스 우클릭 -->
<ul class="sequenceMenu">
  <li><span id="readSequenceSpan">시퀀스 조회</span></li>
  <li><span id="updateSequenceSpan">시퀀스 편집</span></li>
  <li><span id="deleteSequenceSpan">시퀀스 삭제</span></li>
</ul>

<!-- 인덱스 패키지 우클릭 -->
<ul class="indexPackageMenu">
  <li><span id="createIndexSpan">인덱스 생성</span></li>
</ul>
<!-- 인덱스 우클릭 -->
<ul class="indexMenu">
  <li><span id="readIndexSpan">인덱스 조회</span></li>
  <li><span id="updateIndexSpan">인덱스 편집</span></li>
  <li><span id="deleteIndexSpan">인덱스 삭제</span></li>
</ul>


<input type="hidden" id="accou_id"/>
<input type="hidden" id="accountListSize" value="${accountListSize}"/>

<script src="${cp}/resources/ace-builds-master/ace.js"></script>
<script src="${cp}/resources/sqlEditor/js/sqlEditorJS.js"></script>
<script src="${cp}/resources/sqlEditor/js/templateEditorJS.js"></script>
<script src="${cp}/resources/sqlEditor/js/treeMenu.js"></script>
<script src="${cp}/resources/sqlEditor/js/rightClick.js"></script>
<script src="${cp}/resources/jqGrid/js/i18n/grid.locale-kr.js"></script>
<script src="${cp}/resources/jqGrid/js/minified/jquery.jqGrid.min.js"></script>
