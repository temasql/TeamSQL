<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${cp }/resources/template/templateList.js"></script>

	
	<h3 class="sub-header">템플릿 추가</h3>
	<form class="form-inline" id="addTemplateFrm" action="${cp }/commonTemplate/addTemplate" method="post">
		<label class="mb-2 mr-sm-2" style="margin-top: 10px;">약어</label> 
		<input type="text" class="form-control mb-2 mr-sm-2" id="templateAbb" 	name="ctemplate_abb"> 
		<label	class="mb-2 mr-sm-2" style="margin-left: 20px; margin-top: 10px;">원문</label> 
		<input type="text" class="form-control mb-2 mr-sm-2" id="templateOriginal" 	name="ctemplate_original"> 
		
		<input type="submit"	class="btn" style="background: black; color: white; margin-left: 30px;" value="추가">
	</form>

	<br><br><br><br>


<div class="tableContainer">
	<form id="frm" method="post" action="${cp }/commonTemplate/modifyTemplate">
		<input type="hidden" id="updateAbb" name="ctemplate_abb" /> 
		<input type="hidden" id="updateOriginal" name="ctemplate_original" />
		<input type="hidden" id="updateId" name="ctemplate_id"/>
	</form>
	
	<form id="delFrm" method="post" action="${cp }/commonTemplate/deleteTemplate">
		<input type="hidden" id="deleteId" name="ctemplate_id" />
	</form>
	<h3 class="sub-header">공통 템플릿</h3>

	<table class="table table-hover">
		<thead>
			<tr class="templateModi">
				<th class="templateModi">약어</th>
				<th class="templateModi">원문</th>
				<th class="templateModi">편집</th>
			</tr>
		</thead>
		
		<tbody id="templateListTbody">
		</tbody>
	</table>
</div>

<div>
	<ul class="pagination">
	</ul>
</div>