<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="${cp}/js/js.cookie.js"></script>

<form id="loginForm" action="${cp }/login" method="post">
	<input type="text" name="user_id"><br>
	<input type="password" name="user_pw">
	<div class="checkbox">
		<label> <input id="rememberme" name="rememberme" type="checkbox" value="remember-me">	remember</label>
	</div>
	<button id="loginBtn" class="btn btn-default pull-right" type="button">login </button>
	<a class="btn btn-default pull-right" href="${cp }/user/signIn">회원가입</a>
</form>

