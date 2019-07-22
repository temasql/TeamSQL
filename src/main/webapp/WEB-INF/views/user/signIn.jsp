<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form action="${cp }/user/signIn" method="post" enctype="multipart/form-data">
  <fieldset>
    <legend>회원가입</legend>
    <div class="form-group">
      <label for="exampleInputPassword1">ID</label>
      <input type="text" class="form-control" name="user_id" placeholder="사용자 ID는 첫문을 영문자,숫자 포함 4~12글자입니다."><form:errors path="userVO.user_id"/>
      
    <div class="form-group">
      <label for="exampleInputPassword1">Password</label>
      <input type="password" class="form-control" name="user_pw" placeholder="비밀번호는 문자,숫자, 1개이상의 특수문자를 포함하여 8~16글자로 작성가능합니다."><form:errors path="userVO.user_pw"/>
    </div>
    
    </div>
    <div class="form-group">
      <label for="exampleInputPassword1">name</label>
      <input type="text" class="form-control" name="user_name" placeholder="이름은 한글만 가능하며 2~4글자로 입력해주세요."><form:errors path="userVO.user_name"/>
    </div>
    
    <div class="form-group">
      <label for="exampleInputEmail1">Email address</label>
      <input type="email" class="form-control" name="user_email" placeholder="email@example.com"><form:errors path="userVO.user_email"/>
      
    </div>
    <div class="form-group">
      <label for="exampleInputFile">프로필 사진</label>
      <input type="file" class="form-control-file" name="profile" >
    </div>
    <input type="submit" class="btn btn-primary" value="가입"/>
  </fieldset>
</form>

<%-- <form action="${cp }/user/signIn" method="post" enctype="multipart/form-data"> --%>
<%-- 	<input type="text" name="user_id"><form:errors path="userVO.user_id"/><br> --%>
<%-- 	<input type="password" name="pw"><form:errors path="userVO.pw"/><br> --%>
<%-- 	<input type="text" name="name"><form:errors path="userVO.name"/><br> --%>
<!-- 	<input type="text" name="email"><br> -->
<!-- 	<input type="file" name="profile"><br> -->
<!-- 	<input type="submit" value="전송"> -->
<%-- </form> --%>
