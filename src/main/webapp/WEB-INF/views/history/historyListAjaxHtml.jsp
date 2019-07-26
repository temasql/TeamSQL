<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${changedList}" var="changedList">
  <tr class="table-active" id="changedTr">
    <td class="object_owner">${changedList.object_owner }</td>
    <td><fmt:formatDate value="${changedList.exec_dtm }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
  </tr>
</c:forEach>
<form action="${cp }/history/historyList" id="frm">
	<input type="hidden" id="object_owner" name="object_owner">
</form>
SEPERATORSEPERATOR

<c:choose>
	<c:when test="${pageMap.page  == 1}">
		<li class="page-link disabled"><span>«</span></li>
		<li class="page-link disabled"><span>Previous</span></li>
	</c:when>
	<c:otherwise>
		<li class="page-link"><a
			href="javascript:historyPagingListAjaxHtml(1,${pageMap.pageSize});">«</a></li>
		<li class="page-link"><a
			href="javascript:historyPagingListAjaxHtml(${pageMap.page - 1 },${pageMap.pageSize});">Previous</a></li>
	</c:otherwise>
</c:choose>

<c:forEach var="i" begin="1" end="${paginationSize}">
	<li><c:choose>
			<c:when test="${pageMap.page == i}">
				<li class="page-link active"><span>${i }</span></li>
			</c:when>
			<c:when test="${pageMap.page != i}">
				<a class="page-link"
					href="javascript:historyPagingListAjaxHtml(${i},${pageMap.pageSize});">${i}</a>
			</c:when>
		</c:choose></li>
</c:forEach>

<c:choose>
	<c:when test="${pageMap.page  == paginationSize}">
		<li class="page-link disabled"><span>next</span></li>
		<li class="page-link disabled"><span>»</span></li>
	</c:when>
	<c:otherwise>
		<li class="page-link"><a
			href="javascript:historyPagingListAjaxHtml(${pageMap.page + 1 },${pageMap.pageSize});">next</a></li>
		<li class="page-link"><a
			href="javascript:historyPagingListAjaxHtml(${paginationSize},${pageMap.pageSize});">»</a></li>
	</c:otherwise>
</c:choose>