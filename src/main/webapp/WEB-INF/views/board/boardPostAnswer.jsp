<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>답글 작성</title>

<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">답글 작성</h2>

		<form id="frm2" class="form-horizontal" role="form"
			action="${cp }/post/answerPost" method="post"
			enctype="multipart/form-data">

			<div class="form-group">
				<div class="col-sm-11 ">
					<%@include file="/SE2/answerIndex.jsp"%>
				</div>
			</div>
		</form>
	</div>
</div>