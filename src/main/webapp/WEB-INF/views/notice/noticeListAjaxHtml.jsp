<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${postList}" var="postVo">
	<form>
		<input type="hidden" class="post_yns" value="${postVo.post_use }"/>
	</form>
	<tr class="postTr" style="cursor: pointer">
		<td class="postId">${postVo.post_id }</td>
			<c:choose>
				<c:when test="${postVo.lv > 1 }">
					<td style="text-align: left;">
						<c:forEach begin="1" end="${postVo.lv}">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</c:forEach>
						 └▶ ${postVo.post_title}  &nbsp;&nbsp;
							<c:if test="${postVo.reply_cnt > 0 }">[${postVo.reply_cnt }] </c:if> 
					</td>
				</c:when>
				<c:otherwise>
					<td style="text-align: left;">${postVo.post_title}  &nbsp;&nbsp;
						<c:if test="${postVo.reply_cnt > 0 }">[${postVo.reply_cnt }] </c:if> 
					</td>
				</c:otherwise>
			</c:choose>
		<td>${postVo.user_id_fk }</td>
		<td><fmt:formatDate value="${postVo.post_dt }" pattern="yyyy-MM-dd a hh:mm:ss" /></td>
		<td>${postVo.view_cnt }</td>
	</tr>
</c:forEach>

SEPERATORSEPERATOR

<c:choose>
	<c:when test="${pageMap.page == 1 }">
		<li class="page-link disabled"><span>«</span></li>
		<li class="page-link disabled"><span>Previous</span></li>
	</c:when>
	<c:otherwise>
		<li class="page-link"><a href="javascript:postPagingListAjaxHtml(1, ${pageMap.pageSize});">«</a></li>
		<li class="page-link"><a href="javascript:postPagingListAjaxHtml(${pageMap.page - 1 },${pageMap.pageSize});">Previous</a></li>
	</c:otherwise>
</c:choose>

<c:forEach var="i" begin="${startPage }" end="${paginationSize}">
	<li>
		<c:choose>
			<c:when test="${pageMap.page == i}">
				<li class="page-link active"><span>${i }</span></li>
			</c:when>
			<c:when test="${pageMap.page != i}">
				<a class="page-link" href="javascript:postPagingListAjaxHtml(${i}, ${pageMap.pageSize});">${i}</a>
			</c:when>
		</c:choose>
	</li>
</c:forEach>

<c:choose>
	<c:when test="${pageMap.page  == lastpaginationSize}">
		<li class="page-link disabled"><span>next</span></li>
		<li class="page-link disabled"><span>»</span></li>
	</c:when>
	<c:otherwise>
		<li class="page-link"><a href="javascript:postPagingListAjaxHtml(${pageMap.page + 1 }, ${pageMap.pageSize});">next</a></li>
		<li class="page-link"><a href="javascript:postPagingListAjaxHtml(${lastpaginationSize}, ${pageMap.pageSize});">»</a></li>
	</c:otherwise>
</c:choose>

