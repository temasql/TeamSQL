<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${cp }/resources/user/js/adminMG.js"></script>
<div style="display: inline-block;    width: 100%;">
<select class="form-control" style=" width : 7%;margin : 10px; text-align-last: center;display: inline-block;"  id="pageSizeSelect">
	<option value="10">10개씩 보기</option>
	<option value="20">20개씩 보기</option> 
	<option value="30">30개씩 보기</option>
	<option value="50">50개씩 보기</option>
</select>
<input type="text" class="form-control" name="searchfor" id="searchfor" value="${searchVal }"
placeholder="    아이디로 검색"  style="width: 180px;display: inline;padding-bottom: 0px;padding-top: 0px;margin-bottom: 10px;height: 53px;margin-right: 15px;margin-left: 71%;">
<button type="button" class="btn" style="background: black; color: white;" id="btnSearch" >검색</button>
	
	<input type="hidden" id="msg" value="${msg}">
</div>
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
<a href="/user/insertAdmin"><button type="button" class="btn" style="background: black; color: white;margin-left: 84%">관리자 추가</button></a>
<form action="/user/deleteAdminMG" style=" display: contents;" id="deleteForm">
	<button type="button" id="deleteAdmin" class="btn" style="background: black; color: white;">관리자 삭제</button>
</form>
	<!--  내가 현재 몇번째 페이지에 있는가? -->
	<ul class="pagination">
	</ul>
</div>
