<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Main</title>
<!-- css, js -->
<%@include file="/WEB-INF/views/common/basicLib.jsp" %>
<link href="${cp}/resources/login/signin.css" rel="stylesheet">
</head>

<body>

<!-- 	<!-- header --> 
	<tiles:insertAttribute name="signInheader"/>
	
	<!-- body -->
	<div class="signInDiv">
		<tiles:insertAttribute name="signInbody"/>
	</div>
	
</body>
</html>