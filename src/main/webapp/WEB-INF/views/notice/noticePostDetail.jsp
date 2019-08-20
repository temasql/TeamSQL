<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	$(document).ready(function(){
		$(".filelabel").on("click", function() {
			$("#fileId").val($(this).next().val())
			$("#frm").submit();
		})
		
		$("#addReply").on("click", function() {
			$("#replyFrm").submit();
		})

		$(".delReply").on("click", function() {
			var replyId = $(this).parents("td").prevAll(".reId").html();
			$("#replyId").val(replyId);
			$("#delFrm").submit();
		})
	})
</script>

<div class="tableContainer">
	<h3 class="sub-header">${boardVo.board_name }</h3>
	<form id="frm" class="form-horizontal" role="form" action ="${cp }/post/fileDownLoad" method="post">
		<input type="hidden" id="fileId" name="file_id">
	</form>
		
		<table class="table" style="border: 1px solid #dadada;">
			<tr>
				<td style="border: 1px solid #dadada;" align="center">제목</td>
				<td colspan="3">${postVo.post_title }</td>
			</tr>

			<tr>
				<td style="border: 1px solid #dadada;" align="center">작성자</td>
				<td align="center">${postVo.user_id_fk }</td>
				<td style="border: 1px solid #dadada;" align="center">작성일시</td>
				<td align="center"><fmt:formatDate value="${postVo.post_dt }" pattern="yyyy-MM-dd a hh:mm:ss" /></td>
			</tr>
			
			<tr>
				<td colspan="4" align="center">내용</td>
			</tr>
			
			<tr>
				<td colspan="4">${postVo.post_content }</td>
			</tr>
			
		
			<tr>
				<td style="border: 1px solid #dadada;" align="center">첨부파일</td>
				<td colspan="3">
				<c:forEach items="${fileList }" var="file">
					<label class="filelabel" style="cursor: pointer;">${file.tsfile_filename }</label>
					<input type="hidden" class="hiddenFile" name="fileIds" value="${file.tsfile_id }">
					<br>
				</c:forEach>
				</td>
			</tr>
	</table>	
		<div class="tableContainer">

			<c:if test="${USER_INFO.user_id eq postVo.user_id_fk }">
				<a href="${cp }/post/modifyPost?post_id=${postVo.post_id}" id="updateBtn"
				class="btn" style="background: black; color: white;">수정</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="${cp }/post/deletePost?post_id=${postVo.post_id}" id="deleteBtn"
				class="btn" style="background: black; color: white;">삭제</a>
			</c:if>
		</div>
	<br><br>
	
	<form id="delFrm" action="${cp }/reply/deleteReply?post_id=${postVo.post_id}">
		<input type="hidden" id="replyId" name="reply_id">
	</form>
	
</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	