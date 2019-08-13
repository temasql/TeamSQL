<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${cp }/resources/board/js/boardMG.js"></script>

	
<div class="tableContainer">
	<h3 class="sub-header">게시판 생성</h3>
	<form class="form-inline" id="addBoardFrm" action="${cp }/board/addBoard" method="post">
		<label class="mb-2 mr-sm-2">게시판명</label> 
		<input type="text" class="form-control mb-2 mr-sm-2" id="boardName" 	name="board_name" 	placeholder="게시판명"> 
		<label	class="mb-2 mr-sm-2">사용여부</label> 
		<select class="form-control createUse_yn" name="board_use" required>
			<option class="use_yes" value="Y">사용</option>
			<option class="use_no" value="N">미사용</option>
		</select> 
		<input type="submit"	class="btn" style="background: black; color: white;" value="생성">
	</form>

	<br><br><br><br>

	<form id="frm" method="post" action="${cp }/board/modifyBoard">
		<input type="hidden" id="boardId" name="board_id" /> 
		<input type="hidden" id="updateUse_yn" name="board_use" />
	</form>

	<h3 class="sub-header">게시판 사용 여부</h3>
	<table class="table table-hover">
	<tr>
		<th class="modi">No.</th>
		<th class="modi">게시판명</th>
		<th class="modi">사용여부</th>
		<th class="modi">수정</th>
	</tr>


		<c:forEach items="${boardList }" var="board">
			<tr class="boardTr">
				<td class="boardId">${board.board_id }</td>
				<td >${board.board_name}</td>

				<td class="modiSel">
					<select class="form-control updateUse_yn" 	name="use_yn">
						<c:choose>
							<c:when test="${board.board_use eq 'Y' }">
								<option class="use_yes" value="Y" selected="selected">사용</option>
								<option class="use_no" value="N">미사용</option>
							</c:when>
							<c:otherwise>
								<option class="use_yes" value="Y">사용</option>
								<option class="use_no" value="N" selected="selected">미사용</option>
							</c:otherwise>
						</c:choose>
					</select>
				</td>
				
				<td>	<button type="button" class="btn modiBtn" style="background: black; color: white;">완료</button></td>
			</tr>
		</c:forEach>
	</table>
</div>