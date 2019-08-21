<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${cp }/resources/user/js/userMG.js"></script>
<div style="display: inline-block; width: 100%;">
<select class="form-control" style="width : 7%;margin : 10px;text-align-last: center;display: inline-block;padding-bottom: 0px;padding-top: 0px;" id="pageSizeSelect">
	<option value="10">10개씩 보기</option>
	<option value="20">20개씩 보기</option>
	<option value="30">30개씩 보기</option>
	<option value="50">50개씩 보기</option>
</select>
				<input type="text" class="form-control" name="searchfor" id="searchfor" value="${searchVal }"
					placeholder="    아이디로 검색"style="width: 180px;display: inline;padding-bottom: 0px;padding-top: 0px;margin-bottom: 10px;height: 53px;margin-right: 15px;margin-left: 71%;">
				<button type="button" class="btn" style="background: black;color: white;"id="btnSearch" >검색</button>
	
</div>
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
<form action="/user/deleteUserMG" id="deleteForm">
	<button type="button" id="deleteUser" class="btn" style="background: black; color: white;margin-left: 93%;">회원 탈퇴</button>
</form>
	<ul class="pagination">
	</ul>
</div>
