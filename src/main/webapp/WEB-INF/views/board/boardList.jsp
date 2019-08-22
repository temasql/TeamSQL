<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${cp }/resources/post/js/boardList.js"></script>
<input type="hidden" id="board_id" value="${board_id }">
<div class="tableContainer">
	<form id="frm" action="${cp }/post/readPost">
		<input type="hidden" id="postId" name="post_id"> 
		<input type="hidden" id="userId" value="${USER_INFO.user_id }"> 
	</form>
	
	<h3 class="sub-header" style="color: black; font-weight: bold;">${boardVo.board_name }</h3>
	<br>
	<div>
		<div id="pageSelect" style="display: inline;">
			<select class="form-control" id="pageSizeSelect" style="width: 10%; display: inline;">
				<option value="10">10개씩 보기</option>
				<option value="20">20개씩 보기</option>
				<option value="30">30개씩 보기</option>
				<option value="50">50개씩 보기</option>
			</select>
		</div>
		
		
			<div id="searchdiv" style="display: inline;">
				<select class="form-control"  id="select" name="selectBox" style="width: 10%; display: inline;  margin-left: 59%;">
					<option value="post_title">제목으로 검색</option>
					<option value="po.user_id_fk">작성자로 검색</option>
				</select>
				<input type="text" class="form-control" name="search" id="searchfor" value="${searchVal }" style="width: 15%; display: inline;">
				<button type="button" class="btn" style="background: black; color: white;" id="btnSearch">검색</button>
			</div>
	</div>
	<br>
	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col">No.</th>
				<th scope="col">제목</th>
				<th scope="col">작성자</th>
				<th scope="col">작성일시</th>
				<th scope="col">조회수</th>
			</tr>
		</thead>
	
		<tbody id="boardListTbody">
		</tbody>
	</table>
</div>

<c:if test="${USER_INFO.user_id != null }">
	<a href="${cp }/post/postForm?board_id=${board_id}&user_id=${USER_INFO.user_id }"
	class="btn" style="background: black; color: white; margin-left: 60%; display: inline; margin-left: 92%;">게시글 작성</a>
</c:if>	
<br>
<br>
<br>
<div>
	<ul class="pagination">
	</ul>
</div>
