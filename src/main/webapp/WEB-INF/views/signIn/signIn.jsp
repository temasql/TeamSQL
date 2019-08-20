<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="signInFrmDiv">
	<form id="signInForm" action="${cp }/user/signIn" method="post" enctype="multipart/form-data">
	  <fieldset>
	    <h1 id="signInText">회원가입</h1>
	    <div class="form-group">
	      <label for="exampleInputPassword1">ID</label>
	      <input type="text" class="form-control" value="${userVo.user_id}" name="user_id" placeholder="사용자 ID는 첫문을 영문자,숫자 포함 4~12글자입니다."><h3 style="color : red;"><form:errors path="userVO.user_id"/></h3>
	      <a href="${cp}/user/idCheck"><button id="idcheck" class="btn" style="background: black; color: white;">중복체크</button></a>
	      <input type="hidden" id="idCheckMsg" value="${msg}">
	      <input type="hidden" id="olduId" value="${userVo.user_id}">
	      <script>
	      	$(function(){
	      		$("#btnSignIn").on("click", function(){
	      			if ($("#olduId").val() != $("input[name=user_id]").val()) {
		      			alert("아이디 중복체크를 다시 해주세요")
		      			return;
					}else{
						$("#signInForm").submit();
					}
	      		})
	      		// id 체크 버튼을 클릭시
	      		$("#idcheck").on("click", function(){
	      			// form의 요청경로를 변경 후 전송
	      			$("#signInForm").attr("action", "${cp}/user/idCheck");
	      			$("#signInForm").submit();
	      		});
	      		
	      		// id체크 후 상황에 따라 변하는 메시지
	      		var msg = $("#idCheckMsg").val();
	   			if(msg != ""){
	   				$('#idCheckMsg').after('<h3 style="color : green;">' + msg +'</h3>');
	
	   			}
	   			
	   			// 비밀번호 재확인에서 포커스 아웃됐을 때
	   			$('#pwCheck').focusout(function() {
	   				// 비밀번호가 다르면 비밀번호가 지워짐
	   			 	 if($(this).val() != $("#pw").val()){
	   					  alert("입력하신 비밀번호와 비밀번호 재확인이 다릅니다.")
	   					  return;
	   				  }
	   			});
	   			
// 	   			$('input[name=user_name]').focusout(function() {
// 		   			var nameReg = /^[가-힣]{2,4}$/;
// 		   			if(!nameReg.test($("input[name=user_name]").val())){
// 		   				alert("이름은 2글자에서 4글자를 입력해주세요")
// 		   				return;
// 		   			}
// 	   			})
	   			
	   			
	      	})
	      </script>
	    <div class="form-group">
	      <label for="exampleInputPassword1">Password</label>
	      <input type="password" class="form-control" value="${userVo.user_pw}" id="pw" name="user_pw" placeholder="비밀번호는 문자,숫자, 1개이상의 특수문자를 포함하여 8~16글자로 작성가능합니다."><h3 style="color : red;"><form:errors path="userVO.user_pw"/></h3>
	    </div>
	    <div class="form-group">
	      <label for="exampleInputPassword1">Password 재확인</label>
	      <input type="password" class="form-control" id="pwCheck" placeholder="비밀번호는 문자,숫자, 1개이상의 특수문자를 포함하여 8~16글자로 작성가능합니다."><h3 style="color : red;"><form:errors path="userVO.user_pw"/></h3>
	    </div>
	    
	    
	    </div>
	    <div class="form-group">
	      <label for="exampleInputPassword1">name</label>
	      <input type="text" class="form-control" value="${userVo.user_name}" name="user_name" placeholder="이름은 한글만 가능하며 2~4글자로 입력해주세요."><h3 style="color : red;"><form:errors path="userVO.user_name"/></h3>
	    </div>
	    
	    <div class="form-group">
	      <label for="exampleInputEmail1">Email address</label>
	      <input type="email" class="form-control" value="${userVo.user_email}" name="user_email" placeholder="email@example.com"><h3 style="color : red;"><form:errors path="userVO.user_email"/></h3>
	      <input type="hidden" id="path" value="${userVo.user_path}" name="user_path"/>
	    </div>
	    <div class="form-group">
	      <label for="exampleInputFile">프로필 사진</label>
	      <input type="file" class="form-control-file" name="profile" >
	    </div>
	    <c:if test="${btnSignIn eq 'true'}">
	    	<input id="btnSignIn" type="button" class="btn" style="background: black; color: white;" value="가입"/>
	    </c:if>
	  </fieldset>
	</form>
</div>
