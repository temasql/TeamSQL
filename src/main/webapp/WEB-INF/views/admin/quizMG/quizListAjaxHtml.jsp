<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach items="${quizList}" var="quizVO">
	<tr class="table-light">
      <th scope="row">${quizVO.quiz_id}</th>
      <td>${quizVO.quiz_question}</td>
    </tr>
</c:forEach>

SEPERATORSEPERATOR

<c:choose>
	<c:when test="${pageVO.page  == 1}">
		<li class="page-link disabled"><span>«</span></li>
		<li class="page-link disabled"><span>Previous</span></li>
	</c:when>
	<c:otherwise>
		<li class="page-link"><a href="javascript:quizList(1, ${pageVO.pageSize});">«</a></li>
		<li class="page-link"><a href="javascript:quizList(${pageVO.page - 1}, ${pageVO.pageSize});">Previous</a></li>
	</c:otherwise>
</c:choose>

<c:forEach begin="1" end="${pagination}" var="i">
	<li><c:choose>
		<c:when test="${pageVO.page == i}"><li class="page-link active"><span>${i}</span></li></c:when>
		<c:when test="${pageVO.page != i}"><a class="page-link" href="javascript:quizList(${i}, ${pageVO.pageSize});">${i}</a></c:when>
	</c:choose></li>
</c:forEach>

<c:choose>
	<c:when test="${pageVO.page  == pagination}">
		<li class="page-link disabled"><span>next</span></li>
		<li class="page-link disabled"><span>»</span></li>
	</c:when>
	<c:otherwise>
		<li class="page-link"><a href="javascript:quizList(${pageVO.page + 1}, ${pageVO.pageSize});">next</a></li>
		<li class="page-link"><a href="javascript:quizList(${pagination}, ${pageVO.pageSize});">»</a></li>
	</c:otherwise>
</c:choose>