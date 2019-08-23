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
	<h3 class="sub-header" style="color: black; font-weight: bold;"><a href="${cp }/post/boardList?board_id=${boardVo.board_id}">${boardVo.board_name }</a></h3>
	<br>
	<form id="frm" class="form-horizontal" role="form" action ="${cp }/post/fileDownLoad" method="post">
		<input type="hidden" id="fileId" name="file_id">
	</form>
		
		<table class="table" style="border: 1px solid #dadada;">
			<tr>
				<td style="border: 1px solid #dadada;" align="center">제목</td>
				<td colspan="5">${postVo.post_title }</td>
			</tr>

			<tr>
				<td style="border: 1px solid #dadada;" align="center">작성자</td>
				<td align="center">${postVo.user_id_fk }</td>
				<td style="border: 1px solid #dadada;" align="center">작성일시</td>
				<td align="center"><fmt:formatDate value="${postVo.post_dt }" pattern="yyyy-MM-dd a hh:mm:ss" /></td>
				<td style="border: 1px solid #dadada;" align="center">조회수</td>
				<td align="center">${postVo.view_cnt }</td>
			</tr>
			
			<tr>
				<td colspan="6" align="center">내용</td>
			</tr>
			
			<tr>
				<td colspan="6">${postVo.post_content }</td>
			</tr>
			

			<tr>
				<td style="border: 1px solid #dadada;" align="center">첨부파일</td>
				<td colspan="5">
				<c:forEach items="${fileList }" var="file">
					<label class="filelabel" style="cursor: pointer;">${file.tsfile_filename }</label>
					<input type="hidden" class="hiddenFile" name="fileIds" value="${file.tsfile_id }">
					<br>
				</c:forEach>
				</td>
			</tr>
	</table>	
		<div class="tableContainer" style="margin-left: 77%; display: inline;">

			<c:if test="${USER_INFO.user_id eq postVo.user_id_fk }">
				<a href="${cp }/post/modifyPost?post_id=${postVo.post_id}" id="updateBtn"
				class="btn" style="background: black; color: white;">수정</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="${cp }/post/deletePost?post_id=${postVo.post_id}" id="deleteBtn"
				class="btn" style="background: black; color: white;">삭제</a>
			</c:if>
		</div>
		<div style="width: 70px; float: right; margin-left: 6px;">
			<a href="${cp }/post/boardList?board_id=${boardVo.board_id}" class="btn" style="background: black; color: white;">목록</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<div style="width: 70px; float: right; margin-left: 5px;">
			<a href="${cp }/post/answerPost?post_id=${postVo.post_id}" id="answerBtn"  class="btn" style="background: black; color: white;">답글</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
	<br><br><br><br>

		<table class="table table-hover">
			<tr>
				<th colspan="4" style="text-align:-webkit-left; background-color: #dadada;"> comments <c:if test="${postVo.reply_cnt > 0 }">'${postVo.reply_cnt }'</c:if></th>
			</tr>
			<c:forEach items="${replyList }" var="reply">
				<tr>
					<td class="reId" style="display: none;">${reply.reply_id }</td>
					<td><label class=replyLabel style="font-weight: bold;">${reply.user_id_fk }</label></td>
					<td style="text-align: left;">${reply.reply_content }</td>
					<td>
						<label class=replyLabel><fmt:formatDate value="${reply.reply_dt }" pattern="yy-MM-dd hh:mm:ss"/></label>
					</td>
					<td>
						<c:if test="${USER_INFO.user_id eq reply.user_id_fk }">
							<c:if test="${reply.reply_use != 'N' }">
								<a href="${cp }/reply/deleteReply?reply_id=${reply.reply_id }"><img src="${cp }/img/deleteBtn.png"></a>
							</c:if>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	
		<form id="replyFrm" action="${cp }/reply/addReply?post_id=${postVo.post_id}" method="post">
		<div>
			<input type="text" class="form-control" name="reply_content" style="width: 93%; display: inline;">
			<button type="button" id="addReply" class="btn" style="background: black; color: white;">댓글등록</button>
		</div>	
	</form>
	<br><br>
	<form id="delFrm" action="${cp }/reply/deleteReply?post_id=${postVo.post_id}">
		<input type="hidden" id="replyId" name="reply_id">
	</form>
	
</div>
