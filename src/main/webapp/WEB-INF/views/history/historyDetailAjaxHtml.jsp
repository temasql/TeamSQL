<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- DB 상세 변경 이력 테이블 -->
<c:forEach items="${changedDetailPagingList}" var="cVO">
   <tr >
  	<td scope="row">${cVO.action_event }</td>	<!-- 발생이벤트 -->
  	<td scope="row">${cVO.object_type }</td>	<!-- 객체타입 -->
  	<td scope="row">${cVO.object_name }</td>	<!-- 객체명 -->
  	<!-- 원문 -->
  	<td scope="row">
<%--   	${cVO.sql_text } --%>
  	<c:set var="str" value="${cVO.sql_text }" />
	<c:if test="${str =">
	    홍길동이 맞습니다.
	</c:if>
  	</td>		
  	<td scope="row"><fmt:formatDate value="${cVO.exec_dtm }" pattern="yyyy-MM-dd HH:mm:ss"/></td>	<!-- 변경일시 -->
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
		<li class="page-link"><a href="javascript:historyDetailPagingListAjaxHtml(1,${pageMap.pageSize});">«</a></li>
		<li class="page-link"><a href="javascript:historyDetailPagingListAjaxHtml(${pageMap.page - 1 },${pageMap.pageSize});">Previous</a></li>
	</c:otherwise>
</c:choose>

<c:forEach var="i" begin="${startPage}" end="${paginationSize}">
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
	<c:when test="${pageMap.page  == lastpaginationSize}">
		<li class="page-link disabled"><span>next</span></li>
		<li class="page-link disabled"><span>»</span></li>
	</c:when>
	<c:otherwise>
		<li class="page-link"><a
			href="javascript:historyDetailPagingListAjaxHtml(${pageMap.page + 1 },${pageMap.pageSize});">next</a></li>
		<li class="page-link"><a
			href="javascript:historyDetailPagingListAjaxHtml(${lastpaginationSize},${pageMap.pageSize});">»</a></li>
	</c:otherwise>
</c:choose>