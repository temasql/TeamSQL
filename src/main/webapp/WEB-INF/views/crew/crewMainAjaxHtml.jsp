<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${accountVo ne null }">
<form action="/crew/deleteCrew" id="deleteForm">
		<input type="hidden" id="acc_id" name="acc_id" value="${selected}"> 
	<button type="button" id="deleteCrew" class="btn" style="background: black; color: white;" >구성원 탈퇴</button>
<button type="button" id="btnInviteCrew" class="btn" style="background: black; color: white;">구성원 초대</button>
</form>
</c:if>
SEPERATORSEPERATOR
<c:forEach items="${crewList }" var="crewVo">
	<tr>
	<c:choose>
		<c:when test="${accountVo ne null }">
			<td scope="row"><input type="checkbox" class="checkbox"
				value="${crewVo.user_id_fk}"></td>
		</c:when>
		<c:otherwise>
			<td></td>
		</c:otherwise>
	</c:choose>
		<td scope="row">${crewVo.user_id_fk}</td>
		<td scope="row">${crewVo.user_name}</td>
		<td scope="row">${crewVo.user_email}</td>
		<td scope="row"><img class="thumbnail"
			src="${cp}/user/profile?user_id=${crewVo.user_id_fk}"></td>
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
			href="javascript:crewPagingListAjaxHtml(1,${pageMap.pageSize});">«</a></li>
		<li class="page-link"><a
			href="javascript:crewPagingListAjaxHtml(${pageMap.page - 1 },${pageMap.pageSize});">Previous</a></li>
	</c:otherwise>
</c:choose>

<c:forEach var="i" begin="${startPage }" end="${paginationSize}">
	<li><c:choose>
			<c:when test="${pageMap.page == i}">
				<li class="page-link active"><span>${i }</span></li>
			</c:when>
			<c:when test="${pageMap.page != i}">
				<a class="page-link"
					href="javascript:crewPagingListAjaxHtml(${i},${pageMap.pageSize});">${i}</a>
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
			href="javascript:crewPagingListAjaxHtml(${pageMap.page + 1 },${pageMap.pageSize});">next</a></li>
		<li class="page-link"><a
			href="javascript:crewPagingListAjaxHtml(${lastpaginationSize},${pageMap.pageSize});">»</a></li>
	</c:otherwise>
</c:choose>
