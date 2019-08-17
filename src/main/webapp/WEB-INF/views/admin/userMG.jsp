<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${cp }/resources/user/js/userMG.js"></script>
				<button type="button" class="btn" style="background: black;color: white;float: right;"id="btnSearch" >검색</button>
				<input type="text" class="form-control" name="searchfor" id="searchfor" value="${searchVal }"
					placeholder="    아이디로 검색"style="width: 180px;display: inline;float: right;padding-bottom: 0px;padding-top: 0px;margin-bottom: 10px;height: 42px;margin-right: 15px;">  <span class="input-group-btn">
				</span>
	
<form action="/user/deleteUserMG" id="deleteForm">
	<button type="button" id="deleteUser" class="btn" style="background: black; color: white;">회원 탈퇴</button>
</form>
<div class="tableContainer">
	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col"></th>
				<th scope="col">아이디</th>
				<th scope="col">이름</th>
				<th scope="col">이메일</th>
				<th scope="col">프로필 사진</th>
				<th scope="col">가입 일</th>
			</tr>
		</thead>
		<tbody id="userListTbody">
		</tbody>
	</table>
</div>
<div>
	<!--  내가 현재 몇번째 페이지에 있는가? -->
	<ul class="pagination">
	</ul>
</div>
