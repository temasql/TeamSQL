<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${templateList }" var="template">
	<tr class="templateTr">
		<td class="templateId" style="display: none;">
			<input type="hidden" value="${template.ctemplate_id }">
		</td>
			
		<td class="templateAbb" style="vertical-align: middle;">
			<input type="text" style="width: 100px; text-align: center;" class="form-control mb-2 mr-sm-2" value="${template.ctemplate_abb }" >
		</td>
				
		<td class="templateOriginal" style="vertical-align: middle;">
			<input type="text" style="width: 1000px;" class="form-control mb-2 mr-sm-2" value="${template.ctemplate_original}" >
		</td>
						
		<td>
			<button type="button" class="btn modiBtn" style="background: black; color: white;">수정</button>
			<button type="button" class="btn delBtn" style="background: black; color: white; margin-left: 10px;">삭제</button>
		</td>
	</tr>
</c:forEach>

SEPERATORSEPERATOR

<c:choose>
	<c:when test="${pageMap.page == 1 }">
		<li class="page-link disabled"><span>«</span></li>
		<li class="page-link disabled"><span>Previous</span></li>
	</c:when>
	<c:otherwise>
		<li class="page-link"><a href="javascript:templatePagingListAjaxHtml(1, ${pageMap.pageSize});">«</a></li>
		<li class="page-link"><a href="javascript:templatePagingListAjaxHtml(${pageMap.page - 1 },${pageMap.pageSize});">Previous</a></li>
	</c:otherwise>
</c:choose>

<c:forEach var="i" begin="${startPage}" end="${paginationSize}">
	<li>
		<c:choose>
			<c:when test="${pageMap.page == i}">
				<li class="page-link active"><span>${i }</span></li>
			</c:when>
			<c:when test="${pageMap.page != i}">
				<a class="page-link" href="javascript:templatePagingListAjaxHtml(${i}, ${pageMap.pageSize});">${i}</a>
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
		<li class="page-link"><a href="javascript:templatePagingListAjaxHtml(${pageMap.page + 1 }, ${pageMap.pageSize});">next</a></li>
		<li class="page-link"><a href="javascript:templatePagingListAjaxHtml(${lastpaginationSize}, ${pageMap.pageSize});">»</a></li>
	</c:otherwise>
</c:choose>






