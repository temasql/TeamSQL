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
		
	})
</script>

<div class="tableContainer">
	<h3 class="sub-header">${boardVo.board_name }</h3>
	<form id="frm" class="form-horizontal" role="form" action ="${cp }/fileDownload/download" method="post">
		<input type="hidden" id="fileId" name="file_id">
		
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
					<label class="filelabel">${file.tsfile_filename }</label>
					<input type="hidden" class="hiddenFile" name="fileIds" value="${file.tsfile_id }">
					<br>
				</c:forEach>
				</td>
			</tr>
	</table>	
	
	
	<table class="table table-hover">
		<c:forEach items="${replyList }" var="reply">
			<tr>
				<td class="reId" style="display: none;">${reply.reply_id }</td>
				<td><label style="font-weight: bold;">${reply.user_id_fk }</label></td>
				<td align="center">${reply.reply_content }</td>
				<td><label style="margin-left: 50px"><fmt:formatDate value="${reply.reply_dt }" pattern="yy-MM-dd hh:mm:ss"/></label>
					<c:if test="${USER_INFO.user_id eq reply.user_id_fk }">
							<c:if test="${reply.reply_use != 'N' }">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${cp }/reply/deleteReply"><img src="${cp }/img/deleteBtn.png"></a>
							</c:if>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
</form>
</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	