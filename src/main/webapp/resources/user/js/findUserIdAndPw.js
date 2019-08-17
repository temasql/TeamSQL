$(document).ready(function() {
	
	
	// modal
	$("#btnFindUserId").on("click", function() {
		$("#findUserIdModal").css("display", "block");
	});
	$("#btnFindUserPw").on("click", function() {
		$("#findUserPwModal").css("display", "block");
	});
	$("#btnDeleteUser").on("click", function() {
		$("#deleteUserModal").css("display", "block");
	});
	
	// close
	$(".close").on("click", function() {
		$("#findUserIdModal").css("display", "none");
	});
	$(".close").on("click", function() {
		$("#findUserPwModal").css("display", "none");
	});
	$(".close").on("click", function() {
		$("#deleteUserModal").css("display", "none");
	});
	
	$("#btnUserIdModalOk").on("click", function(){
		$.ajax({
			 url	 : "/user/findUserId"
			,method  : "post"
			,data : "user_name=" + $("#user_name").val()+"&user_email=" +$("#find_id_user_email").val() 
			,success : function(data){
				console.log(data)
				console.log(data.user_id)
				if(data.user_id == null){
					$("#findUserIdModal legend").html("입력하신 내용과 일치하는 계정이 없습니다.")
				}else{
					var tp = "";
					$.each(data.user_id, function(idx ,dt){
						console.log(dt)
						tp += dt + " ";
					})
					$("#findUserIdModal legend").html("입력하신 내용과 일치하는 사용자 ID는 '" + tp + "'입니다.")
				}
			}
		});
	});
	$("#btnUserPwModalOk").on("click", function(){
		$.ajax({
			url	 : "/user/findUserPw"
				,method  : "post"
					,data : "user_id=" + $("#find_pw_user_id").val()+"&user_email=" +$("#find_pw_user_email").val() 
					,success : function(data){
						console.log(data)
						if(data.userVo.user_pw == null){
							$("#findUserPwModal legend").html("입력하신 정보와 일치하는 계정이 없습니다.")
						}else{
							$("#findUserPwModal legend").html(data.userVo.user_id + "님의 이메일" + data.userVo.user_email + "으로 임시 비밀번호를 발송 하였습니다")
						}
					}
		});
	});
	
	$("#btnDeleteUserOk").on("click", function(){
		$.ajax({
			url	 : "/user/deleteUser"
				,method  : "post"
					,data : "user_id=" + $("#user_id").val()+"&user_pw=" +$("#user_pw").val() 
					,success : function(data){
						console.log(data)
						if(data.userVo == null){
							$("legend").html("비밀번호를 잘못 입력 하셨습니다.")
						}else{
							alert(data.userVo.user_id + "님의 계정이 탈퇴되었습니다. 감사합니다.");
							location.replace("/login");
						}
					}
		});
	});
	
});

