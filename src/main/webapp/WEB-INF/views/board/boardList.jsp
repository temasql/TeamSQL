<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${cp }/resources/post/js/boardList.js"></script>
<input type="hidden" id="board_id" value="${board_id }">
<div class="tableContainer">
	<c:if test="${USER_INFO != null }">
		<a href="${cp }/post/postForm?board_id=${board_id}&user_id=${USER_INFO.user_id }"
		class="btn btn-default">게시글 작성</a>
	</c:if>
	
	<form id="frm" action="${cp }/post/readPost">
		<input type="hidden" id="postId" name="post_id"> 
		<input type="hidden" id="userId" value="${USER_INFO.user_id }"> 
		<input type="hidden" id="post_yn" value="${post.post_use}">
	</form>
	
	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col">No.</th>
				<th scope="col">제목</th>
				<th scope="col">작성자</th>
				<th scope="col">작성일시</th>
			</tr>
		</thead>
		<tbody id="boardListTbody">
		</tbody>
	</table>
</div>

<div>
	<ul class="pagination">
	</ul>
</div>
