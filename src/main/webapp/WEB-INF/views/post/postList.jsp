<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${cp }/resources/post/js/postList.js"></script>


<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">${boardVo.board_name }</h2>

		<form id="frm" action="${cp }/post/readPost">
			<input type="hidden" id="postId" name="post_id"> <input
				type="hidden" id="userId" value="${USER_INFO.user_id }"> <input
				type="hidden" id="post_yn" value="${post.post_use}">
		</form>


		<div class="table-responsive">
			<table class="table table-striped">
				<tr>
					<th>No.</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일시</th>
				</tr>

				<c:forEach items="${postList}" var="post">
					<tr class="postTr">
						<form>
							<input type="hidden" class="post_yns" value="${post.post_use }">
						</form>
						<td class="postId">${post.post_id }</td>
						<c:choose>
							<c:when test="${post.lv > 1 }">
								<td><c:forEach begin="1" end="${post.lv}">
														&nbsp;&nbsp;&nbsp;&nbsp;
													</c:forEach> └▶ ${post.post_title}</td>
							</c:when>
							<c:otherwise>
								<td>${post.post_title}</td>
							</c:otherwise>
						</c:choose>
						<td>${post.user_id_fk }</td>
						<td><fmt:formatDate value="${post.post_dt }"
								pattern="yyyy-MM-dd a hh:mm:ss" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<c:if test="${USER_INFO != null }">
			<a
				href="${cp }/post/postForm?boardId=${boardVo.board_id}&userId=${USER_INFO.user_id}"
				class="btn btn-default pull-right">게시글 작성</a>
		</c:if>
		<div class="text-center">
			<ul class="pagination">

				<c:choose>
					<c:when test="${pageVo.page == 1 }">
						<li class="disabled"><span>≪</span></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="${cp }/post/postList?boardId=${boardVo.board_id }&page=1&pageSize=${pageVo.pageSize}">≪</a>
						</li>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${pageVo.page == 1 }">
						<li class="disabled"><span>＜</span></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="${cp }/post/postList?boardId=${boardVo.board_id }&page=${pageVo.page -1}&pageSize=${pageVo.pageSize}">＜</a>
						</li>
					</c:otherwise>
				</c:choose>

				<c:forEach begin="1" end="${paginationSize }" step="1" var="i">
					<li><c:choose>
							<c:when test="${pageVo.page  == i}">
								<li class="active"><span>${i }</span></li>
							</c:when>
							<c:otherwise>
								<li><a
									href="${cp }/post/postList?boardId=${boardVo.board_id }&page=${i}&pageSize=${pageVo.pageSize}">${i }</a></li>
							</c:otherwise>
						</c:choose></li>
				</c:forEach>

				<c:choose>
					<c:when test="${pageVo.page == paginationSize }">
						<li class="disabled"><span>＞</span></li>
					</c:when>

					<c:otherwise>
						<li><a
							href="${cp }/post/postList?boardId=${boardVo.board_id }&page=${pageVo.page + 1}&pageSize=${pageVo.pageSize}">＞</a></li>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${pageVo.page == paginationSize }">
						<li class="disabled"><span>≫</span></li>
					</c:when>

					<c:otherwise>
						<li><a
							href="${cp }/post/postList?boardId=${boardVo.board_id }&page=${paginationSize}&pageSize=${pageVo.pageSize}">≫</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</div>
