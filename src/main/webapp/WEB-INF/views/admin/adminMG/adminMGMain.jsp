<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${cp }/resources/user/js/adminMG.js"></script>
<form id="search"
		action="${cp}/user/adminManager" >
		<div id="searchdiv" class="col-lg-8">
			<div class="input-group">
				<input type="text" class="form-control" name="searchfor" id="searchfor" value="${searchVal }"
					placeholder="Search for...">  <span class="input-group-btn">
					<button type="button" class="btn btn-default" id="btnSearch" >Search!</button>
				</span>
			</div>
		</div>
	</form>
	
<form action="/user/deleteAdminMG" id="deleteForm">
	<button type="button" id="deleteAdmin" class="btn btn-default pull-right" >회원 탈퇴</button>
</form>
<a href="/user/insertAdmin"><button type="button" class="btn btn-default pull-right" >관리자 추가</button></a>
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
