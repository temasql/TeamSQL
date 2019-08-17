<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${cp }/resources/user/js/adminMG.js"></script>
<button type="button" class="btn" style="background: black; color: white; float: right;" id="btnSearch" >검색</button>
<input type="text" class="form-control" name="searchfor" id="searchfor" value="${searchVal }"
placeholder="    아이디로 검색"  style="width: 180px;display: inline;float: right;padding-bottom: 0px;padding-top: 0px;margin-bottom: 10px;height: 42px;margin-right: 15px;">  <span class="input-group-btn">
</span>
	
	<input type="hidden" id="msg" value="${msg}">
<a href="/user/insertAdmin"><button type="button" class="btn" style="background: black; color: white;">관리자 추가</button></a>
<form action="/user/deleteAdminMG" style=" display: contents;" id="deleteForm">
	<button type="button" id="deleteAdmin" class="btn" style="background: black; color: white;">관리자 삭제</button>
</form>
<div class="tableContainer">
	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col"></th>
				<th scope="col">아이디</th>
				<th scope="col">이메일</th>
				<th scope="col">가입 일</th>
			</tr>
		</thead>
		<tbody id="adminListTbody">
		</tbody>
	</table>
</div>
<div>
	<!--  내가 현재 몇번째 페이지에 있는가? -->
	<ul class="pagination">
	</ul>
</div>
