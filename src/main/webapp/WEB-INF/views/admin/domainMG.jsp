<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${cp }/resources/domain/js/domainList.js"></script>

	
<div class="tableContainer">
	<h3 class="sub-header">도메인 추가</h3>
	<form class="form-inline" id="addDomainFrm" action="${cp }/commonDomain/addDomain" method="post">
		<label class="mb-2 mr-sm-2" style="margin-top: 10px;">도메인명</label> 
		<input type="text" class="form-control mb-2 mr-sm-2" id="domainName" 	name="cdomain_name"> 
		<label	class="mb-2 mr-sm-2" style="margin-left: 20px; margin-top: 10px;">데이터 타입</label> 
		<input type="text" class="form-control mb-2 mr-sm-2" id="domainType" 	name="cdomain_type"> 
		
		<input type="submit"	class="btn" style="background: black; color: white; margin-left: 30px;" value="추가">
	</form>

	<br><br><br><br>

	<form id="frm" method="post" action="${cp }/board/modifyBoard">
		<input type="hidden" id="boardId" name="board_id" /> 
		<input type="hidden" id="updateUse_yn" name="board_use" />
	</form>

	<h3 class="sub-header">공통 도메인</h3>
	<table class="table table-hover">
	<tr class="domainModi">
		<th class="domainModi">도메인명</th>
		<th class="domainModi">데이터 타입</th>
		<th class="domainModi">편집</th>
	</tr>


		<c:forEach items="${domainList }" var="domain">
			<tr class="domainTr">
				<td class="domainName" style="vertical-align: middle;">
					<input type="text" value="${domain.cdomain_name }">
				</td>
				
				<td class="domainType" style="vertical-align: middle;">
					<input type="text" value="${domain.cdomain_type }">
				</td>
						
				<td>
					<button type="button" class="btn modiBtn" style="background: black; color: white;">수정</button>
					<button type="button" class="btn delBtn" style="background: black; color: white; margin-left: 10px;">삭제</button>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>