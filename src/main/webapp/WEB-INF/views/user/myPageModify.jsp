<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form id="modifyUserForm" action="${cp }/user/modifyUser" method="post" enctype="multipart/form-data">
  <fieldset>
    <legend>회원 정보 수정</legend>
      <input type="hidden"value="${userVo.user_id}" name="user_id">
    <div class="form-group">
      <script>
      	$(function(){
   			
   			// 비밀번호 재확인에서 포커스 아웃됐을 때
   			$('#pwCheck').focusout(function() {
   				
   				// 비밀번호가 다르면 비밀번호가 지워짐
	   			  if($(this).val() != $("#pw").val()){
	   				  $(this).val("");
	   				  alert("비밀번호를 맞게 입력해주세요");
	   			  }
   			});
      	})
      </script>
      <label for="exampleInputPassword1">Password</label>
      <input type="password" class="form-control" id="pw" name="user_pw" placeholder="비밀번호는 문자,숫자, 1개이상의 특수문자를 포함하여 8~16글자로 작성가능합니다."><form:errors path="userVO.user_pw"/>
    </div>
    <div class="form-group">
      <label for="exampleInputPassword1">Password 재확인</label>
      <input type="password" class="form-control" id="pwCheck" placeholder="비밀번호는 문자,숫자, 1개이상의 특수문자를 포함하여 8~16글자로 작성가능합니다."><form:errors path="userVO.user_pw"/>
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
   	<input id="btnSignIn" type="submit" class="btn btn-primary" value="수정"/>
  </fieldset>
</form>
