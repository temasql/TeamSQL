<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${userList }" var="userVo">
	<tr>
		<td scope="row"><input type="checkbox" class="checkbox"
			value="${userVo.user_id }"></td>
		<td scope="row">${userVo.user_id}</td>
		<td scope="row">${userVo.user_name}</td>
		<td scope="row">${userVo.user_email}</td>
		<td scope="row"><img class="thumbnail"
			src="${cp}/user/profile?user_id=${userVo.user_id}"></td>
		<td scope="row"><fmt:formatDate value="${userVo.user_dt}"
				pattern="yyyy-MM-dd" /></td>
	</tr>
</c:forEach>

SEPERATORSEPERATOR

<c:choose>
	<c:when test="${pageMap.page  == 1}">
		<li class="page-link disabled"><span>«</span></li>
		<li class="page-link disabled"><span>Previous</span></li>
	</c:when>
	<c:otherwise>
		<li class="page-link"><a
			href="javascript:userPagingListAjaxHtml(1,${pageMap.pageSize});">«</a></li>
		<li class="page-link"><a
			href="javascript:userPagingListAjaxHtml(${pageMap.page - 1 },${pageMap.pageSize});">Previous</a></li>
	</c:otherwise>
</c:choose>

<c:forEach var="i" begin="${startPage }" end="${paginationSize}">
	<li><c:choose>
			<c:when test="${pageMap.page == i}">
				<li class="page-link active"><span>${i }</span></li>
			</c:when>
			<c:when test="${pageMap.page != i}">
				<a class="page-link"
					href="javascript:userPagingListAjaxHtml(${i},${pageMap.pageSize});">${i}</a>
			</c:when>
		</c:choose></li>
</c:forEach>

<c:choose>
	<c:when test="${pageMap.page  == lastpaginationSize}">
		<li class="page-link disabled"><span>next</span></li>
		<li class="page-link disabled"><span>»</span></li>
	</c:when>
	<c:otherwise>
		<li class="page-link"><a
			href="javascript:userPagingListAjaxHtml(${pageMap.page + 1 },${pageMap.pageSize});">next</a></li>
		<li class="page-link"><a
			href="javascript:userPagingListAjaxHtml(${lastpaginationSize},${pageMap.pageSize});">»</a></li>
	</c:otherwise>
</c:choose>
