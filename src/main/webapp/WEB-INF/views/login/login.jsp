<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form action="${cp }/login" method="post">
	<input type="text" name="userId"><form:errors path="userVo.userId"/>
	<input type="password" name="password"><form:errors path="userVo.pass"/>
	<input type="submit" value="전송">
	
</form>
