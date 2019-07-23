<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form id="signInForm" action="${cp }/user/signIn" method="post" enctype="multipart/form-data">
  <fieldset>
    <legend>회원가입</legend>
    <div class="form-group">
      <label for="exampleInputPassword1">ID</label>
      <input type="text" class="form-control" value="${userVo.user_id}" name="user_id" placeholder="사용자 ID는 첫문을 영문자,숫자 포함 4~12글자입니다."><form:errors path="userVO.user_id"/>
      <a href="${cp}/user/idCheck"><button id="idcheck" class="btn btn-primary">중복체크</button></a>
      <input type="hidden" id="idCheckMsg" value="${msg}">
      <script>
      	$(function(){
      		$("#idcheck").on("click", function(){
      			$("#signInForm").attr("action", "${cp}/user/idCheck");
      			$("#signInForm").submit();
      		});
      		var msg = $("#idCheckMsg").val();
   			alert(msg);
   			$('#pwCheck').focusout(function() {
	   			  if($(this).val() == $("#pw").val()){
	   				  
	   			  }else{
	   				  $(this).val("");
	   				  $("#pw").val("");
	   			  }
   			});
      	})
      </script>
    <div class="form-group">
      <label for="exampleInputPassword1">Password</label>
      <input type="password" class="form-control" value="${userVo.user_pw}" id="pw" name="user_pw" placeholder="비밀번호는 문자,숫자, 1개이상의 특수문자를 포함하여 8~16글자로 작성가능합니다."><form:errors path="userVO.user_pw"/>
    </div>
    <div class="form-group">
      <label for="exampleInputPassword1">Password 재확인</label>
      <input type="password" class="form-control" id="pwCheck" placeholder="비밀번호는 문자,숫자, 1개이상의 특수문자를 포함하여 8~16글자로 작성가능합니다."><form:errors path="userVO.user_pw"/>
    </div>
    
    
    </div>
    <div class="form-group">
      <label for="exampleInputPassword1">name</label>
      <input type="text" class="form-control" value="${userVo.user_name}" name="user_name" placeholder="이름은 한글만 가능하며 2~4글자로 입력해주세요."><form:errors path="userVO.user_name"/>
    </div>
    
    <div class="form-group">
      <label for="exampleInputEmail1">Email address</label>
      <input type="email" class="form-control" value="${userVo.user_email}" name="user_email" placeholder="email@example.com"><form:errors path="userVO.user_email"/>
      <input type="hidden" id="path" value="${userVo.user_path}" name="user_path"/>
    </div>
    <div class="form-group">
      <label for="exampleInputFile">프로필 사진</label>
      <input type="file" class="form-control-file" name="profile" >
    </div>
    <c:if test="${btnSignIn eq 'true'}">
    	<input id="btnSignIn" type="submit" class="btn btn-primary" value="가입"/>
    </c:if>
  </fieldset>
</form>
