<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${cp }/resources/domain/domainList.js"></script>
	
	<h3 class="sub-header">도메인 추가</h3>
	<form class="form-inline" id="addDomainFrm" action="${cp }/commonDomain/addDomain" method="post">
		<label class="mb-2 mr-sm-2" style="margin-top: 10px;">도메인명</label> 
		<input type="text" class="form-control mb-2 mr-sm-2" id="domainName" 	name="cdomain_name"> 
		<label	class="mb-2 mr-sm-2" style="margin-left: 20px; margin-top: 10px;">데이터 타입</label> 
		<input type="text" class="form-control mb-2 mr-sm-2" id="domainType" 	name="cdomain_type"> 
		
		<button type="button" id="domAddBtn" class="btn" style="background: black; color: white; margin-left: 30px;">추가</button>
	</form>

	<br><br><br><br>


<div class="tableContainer">
	<form id="frm" method="post" action="${cp }/commonDomain/modifyDomain">
		<input type="hidden" id="updateName" name="cdomain_name" /> 
		<input type="hidden" id="updateType" name="cdomain_type" />
		<input type="hidden" id="updateId" name="cdomain_id"/>
	</form>
	
	<form id="delFrm" method="post" action="${cp }/commonDomain/deleteDomain">
		<input type="hidden" id="deleteId" name="cdomain_id" />
	</form>
	<h3 class="sub-header">공통 도메인</h3>

	<table class="table table-hover">
		<thead>
			<tr class="domainModi">
				<th class="domainModi">도메인명</th>
				<th class="domainModi">데이터 타입</th>
				<th class="domainModi">편집</th>
			</tr>
		</thead>
		
		<tbody id="domainListTbody">
		</tbody>
	</table>
</div>

<div>
	<ul class="pagination">
	</ul>
</div>