<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- DB 상세 변경 이력 테이블 -->
<c:forEach items="${changedDetailPagingList}" var="changedDetailPagingList">
   <tr class="table-active.none">
  	<td>${changedDetailPagingList.action_event }</td>
  	<td>${changedDetailPagingList.object_type }</td>
  	<td>${changedDetailPagingList.object_name }</td>
  	<td>${changedDetailPagingList.sql_text }</td>
  	<td><fmt:formatDate value="${changedDetailList.exec_dtm }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
   </tr>
   </c:forEach>


SEPERATORSEPERATOR

<!--  페이지네이션 -->
<c:choose>
	<c:when test="${pageMap.page  == 1}">
		<li class="page-link disabled"><span>«</span></li>
		<li class="page-link disabled"><span>Previous</span></li>
	</c:when>
	<c:otherwise>
		<li class="page-link"><a
			href="javascript:historyDetailPagingListAjaxHtml(1,${pageMap.pageSize});">«</a></li>
		<li class="page-link"><a
			href="javascript:historyDetailPagingListAjaxHtml(${pageMap.page - 1 },${pageMap.pageSize});">Previous</a></li>
	</c:otherwise>
</c:choose>

<c:forEach var="i" begin="1" end="${paginationSize}">
	<li><c:choose>
			<c:when test="${pageMap.page == i}">
				<li class="page-link active"><span>${i }</span></li>
			</c:when>
			<c:when test="${pageMap.page != i}">
				<a class="page-link"
					href="javascript:historyDetailPagingListAjaxHtml(${i},${pageMap.pageSize});">${i}</a>
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
			href="javascript:historyDetailPagingListAjaxHtml(${pageMap.page + 1 },${pageMap.pageSize});">next</a></li>
		<li class="page-link"><a
			href="javascript:historyDetailPagingListAjaxHtml(${paginationSize},${pageMap.pageSize});">»</a></li>
	</c:otherwise>
</c:choose>