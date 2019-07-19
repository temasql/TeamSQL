<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="${cp}/resources/sqlEditor/css/sqlEditorStyle.css" rel="stylesheet">
<script src="${cp}/resources/sqlEditor/js/sqlEditorJS.js"></script>

<div id="editorView">
	<div id="leftBar">
		<div id="buttonGroup">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">
					<img class="imgBtn" title="DB계정 추가" src="${cp}/resources/img/plus.png"></a></li>
				<li class="breadcrumb-item"><a href="#">
					<img class="imgBtn" title="워크시트 저장" src="${cp}/resources/img/save.png"></a></li>
				<li class="breadcrumb-item"><a href="#">
					<img class="imgBtn" title="워크시트 불러오기" src="${cp}/resources/img/open.png"></a></li>
				<li class="breadcrumb-item"><a href="#">
					<img class="imgBtn" title="도메인" src="${cp}/resources/img/domain.png"></a></li>
				<li class="breadcrumb-item"><a href="#">
					<img class="imgBtn" title="템플릿" src="${cp}/resources/img/template.png"></a></li>
				<li class="breadcrumb-item"><a href="#">
					<img class="imgBtn" title="쿼리매니저 실행" src="${cp}/resources/img/qmanager.png"></a></li>
			</ol>
		</div>
		<nav id="dbAccountView">
			계층구조
		</nav>
	</div>
	
	<section id="worksheet">
		<textarea class="form-control" id="worksheetView" rows="16" cols="229"></textarea> <br>
		<span class="badge badge-dark" id="resultViewSpan">Result View</span> <br>
		<textarea class="form-control" id="resultView" rows="6" cols="229" readonly>readonly test</textarea>
	</section>
</div>