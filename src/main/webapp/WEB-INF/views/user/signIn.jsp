<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form action="${cp }/user/signIn" method="post">
	<input type="text" name="user_id"><form:errors path="userVO.user_id"/><br>
	<input type="password" name="pw"><form:errors path="userVO.pw"/><br>
	<input type="text" name="name"><form:errors path="userVO.name"/><br>
	<input type="text" name="email"><form:errors path="userVO.email"/><br>
	<input type="file" name="profile"><br>
	<input type="submit" value="전송">
</form>
